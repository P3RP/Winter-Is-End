package com.example.week2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTestController {

    @RequestMapping(value = "/rest", method = RequestMethod.GET)
    public String test(){
        return "Hello World";
    }
}
