package com.vtyurin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @RequestMapping("hellorest")
    public String helloRest() {
        return "hello rest!";
    }
}
