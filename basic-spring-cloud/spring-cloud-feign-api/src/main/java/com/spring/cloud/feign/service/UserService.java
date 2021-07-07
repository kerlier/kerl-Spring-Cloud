package com.spring.cloud.feign.service;

import com.spring.cloud.feign.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "feign-provider")
public interface UserService {

    @GetMapping("/find/{id}")
    UserDTO findById(@PathVariable Long id);

    @PostMapping("/insert")
    void insert(@RequestBody UserDTO userDTO);
}
