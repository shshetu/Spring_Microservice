package com.shetu.microservicesdemo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {
    // default value
    @GetMapping(value = "/{firstName}/{lastName}")
    public String hello(@PathVariable("firstName") String firstName,
                        @PathVariable("lastName") String lastName){
        return String.format("{\"message\":\"Hello %s %s\"}",firstName,lastName);
    }
}
