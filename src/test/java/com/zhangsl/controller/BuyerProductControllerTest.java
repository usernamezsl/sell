package com.zhangsl.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Administrator on 2018/1/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BuyerProductControllerTest {

    @Autowired
    private MockMvc mMockMvc;

    @Test
    public void list() throws Exception {
        mMockMvc.perform(MockMvcRequestBuilders.get("/sell/buyer/product/list").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}