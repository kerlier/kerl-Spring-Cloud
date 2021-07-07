package com.fashion.feign.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.spring.cloud.feign.dto.UserDTO;
import com.spring.cloud.feign.service.UserService;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController implements UserService {

    private static List<UserDTO> users = new ArrayList<>();

    static{
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("1");
        userDTO.setPassword("2");

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setId(1L);
        userDTO1.setUsername("1");
        userDTO1.setPassword("2");

        users.add(userDTO);
        users.add(userDTO1);
    }

    @Override
    public UserDTO findById(Long id) {
        System.out.println("执行provider findById");
        for (UserDTO user : users) {
            if(Objects.equals(id,user.getId())){
                return user;
            }
        }
        return null;
    }

    @Override
    public void insert(UserDTO userDTO) {
        System.out.println("执行provider insert");
        System.out.println("userDto: " + JSONObject.toJSONString(userDTO));
    }
}
