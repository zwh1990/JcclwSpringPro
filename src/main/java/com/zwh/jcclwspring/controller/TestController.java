package com.zwh.jcclwspring.controller;

import com.zwh.jcclwspring.domain.LimitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
   private LimitConfig limitConfig;

    @GetMapping("/info")
    public String getDescription(@RequestParam(value = "id",defaultValue = "0",required = false) Integer id){
//        return "说明:" + limitConfig.getDescription();
        return "id:" + id;
    }
}
