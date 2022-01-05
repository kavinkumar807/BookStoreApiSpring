package com.codesimple.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//    @RequestMapping(value = "/hello",method = RequestMethod.GET)
//    public String getHello(){
//        return "Hello World";
//    }
//both are same it will be default get
    @RequestMapping("/hello")
    public String getHello(){
        return "Hello World";
    }
}
