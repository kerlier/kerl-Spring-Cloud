package com.spring.cloud.nacos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosController {

    @GetMapping("/echo/{string}")
    public String echo(@PathVariable String string){
        return "hello nacos Discovery " + string;
    }
}
