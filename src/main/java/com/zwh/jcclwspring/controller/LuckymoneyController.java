package com.zwh.jcclwspring.controller;

import com.google.gson.*;
import com.zwh.jcclwspring.domain.LimitConfig;
import com.zwh.jcclwspring.domain.Luckymoney;
import com.zwh.jcclwspring.domain.Picture;
import com.zwh.jcclwspring.domain.Result;
import com.zwh.jcclwspring.repository.LuckymoneyRepository;
import com.zwh.jcclwspring.service.LuckymoneyService;
import com.zwh.jcclwspring.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @author by zwh
 * @description:红包Controller
 * @date Created in 2021/8/24 11:29
 */
@RestController
public class LuckymoneyController {

    private static final Logger logger = LoggerFactory.getLogger(LuckymoneyController.class);

    @Autowired
    private LuckymoneyService service;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${limit.minMoney}")
    private BigDecimal minMoney;

    @Autowired
    private LimitConfig limitConfig;

    @Autowired
    LuckymoneyRepository repository;

    /**
     * 获取红包列表
     */
    @GetMapping("/getLuckMoneys")
    public List<Luckymoney> getLuckMoneys() {
        return (List<Luckymoney>) repository.findAll();
    }

    /**
     * 创建红包
     */
    @PostMapping("/createLuckMoney")
    public Luckymoney createLuckMoney(@RequestParam("producer") String producer,
                                      @RequestParam("money") BigDecimal money) {
        Luckymoney luckymoney = new Luckymoney();
        luckymoney.setProducer(producer);
        luckymoney.setMoney(money);
        return repository.save(luckymoney);
    }

    /**
     * 通过id 查询红包
     */
    @GetMapping("/getLuckMoney/{id}")
    public Luckymoney getLuckMoney(@PathVariable("id") Integer id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * 获取图片集合
     */
    @GetMapping("/hello")
    public String say() {
        return "最小金额:" + minMoney + limitConfig.getDescription();
    }

    /**
     * 保存图片
     */
    @PostMapping("/savePictures")
    public Result savePictutes(@RequestBody String json) {
        logger.info("json--->" + json);
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonElement jsonElement = jsonObject.get("list");
        if(jsonElement != null){
            Gson mGson = new Gson();
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for(JsonElement elem : jsonArray){
                Picture picture = mGson.fromJson(elem, Picture.class);
                mongoTemplate.insert(picture);
            }
            return ResultUtil.success();
        }
        return ResultUtil.fail(1,"数据为空");
    }

    /**
     * 获取图片集合
     */
    @PostMapping("/getPictures")
    public Result<List<Picture>> getPictures(@RequestBody HashMap<String, String> map) {
        String key = map.get("key");
        logger.info("key--->" + key);
        Query query;
        if (key == null || key.isEmpty()) {
            query = new Query();
        } else {
            query = new Query(Criteria.where("key").is(key));
        }
        List<Picture> data = mongoTemplate.find(query, Picture.class);
        logger.info("data --->" + data);
        for (int i = 0; i < data.size(); i++) {
            String uri = service.saveImageToSD(data.get(i).getContent(), data.get(i).getName());
            data.get(i).setContent("");
            data.get(i).setUrl(uri);
        }
        return ResultUtil.success(data);
    }

    /**
     * 根据路径加载图片
     */
    @GetMapping("/images")
    public void getImage(@RequestParam("name") String name, HttpServletResponse response) throws IOException {
        File file = new File("C:\\temp\\" + name);
        FileInputStream inputStream = new FileInputStream(file);
        response.setContentType("application/octet-stream");
        response.setHeader("content-length", String.valueOf(file.length()));
        response.addHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
        byte[] buff = new byte[1024 * 1024];
        int len = 0;
        while ((len = inputStream.read(buff)) != -1) {
            //把文件流写入到response的输出流中，供请求端请求
            response.getOutputStream().write(buff, 0, len);
            response.getOutputStream().flush();
        }
    }

}
