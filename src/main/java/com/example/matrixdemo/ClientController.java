package com.example.matrixdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class ClientController {

    @Autowired
    private FeignDemo feignDemo;


    @GetMapping("/api/matrixParamsMap")
    public void matrixParamsMap() {

        Map<String, List<String>> matrixVars = new HashMap<>();
        matrixVars.put("account", Arrays.asList("a"));
        matrixVars.put("name", Arrays.asList("n"));
        feignDemo.matrixParamsMap(matrixVars);

    }


    @GetMapping("/api/matrixParams")
    public void matrixParams() {

        feignDemo.matrixParams("a", "n");

    }

}
