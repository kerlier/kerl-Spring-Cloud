package com.fashion.feign.consumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.spring.cloud.feign.dto.UserDTO;
import com.spring.cloud.feign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/insert")
    public void insert(@RequestBody UserDTO userDTO) {
        userService.insert(userDTO);
        System.out.println("userDto: " + JSONObject.toJSONString(userDTO));
    }
}
