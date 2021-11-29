package com.zwh.jcclwspring.controller;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author by zwh
 * @description:controller 测试类
 * @date Created in 2021/8/26 11:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LuckymoneyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    public void testList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/luckmoneys"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}