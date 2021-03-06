package com.zwh.jcclwspring.service;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;

/**
 * @author by zwh
 * @description:
 * @date Created in 2021/8/25 10:59
 */
@Service
public class LuckymoneyService {

    private static final Logger logger = LoggerFactory.getLogger(LuckymoneyService.class);


    /**
     * 将图片Base64编码转化成图片保存在本地返回相应的路径
     */
    public String saveImageToSD(String content, String name) {
        logger.warn("name--->" + name);
        try {
            InetAddress localHost = InetAddress.getLocalHost();
//            String address = localHost.getHostAddress();
            String address = "hjzwh.top";
            String path = "C:" + File.separator + "temp" + File.separator;
            File file = new File(path, name);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                return "https://" + address + "/learnspringboot/images?name=" + name;
            } else {
                Base64 base64 = new Base64();
                byte[] b = base64.decode(content);
//                BASE64Decoder decoder = new BASE64Decoder();
//                byte[] b = decoder.decodeBuffer(content);
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {
                        b[i] += 256;
                    }
                }
                OutputStream out = new FileOutputStream(file);
                out.write(b);
                out.flush();
                out.close();
                return "http://" + address + ":8081/learnspringboot/images?name=" + name;
            }
        } catch (IOException exception) {
            logger.info("error--->" + exception.getMessage());
        }
        return "";
    }

}
