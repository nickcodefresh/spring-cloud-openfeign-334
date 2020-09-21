package com.example.matrixdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class ClientController {

    @Autowired
    private FeignDemo feignDemo;


    @GetMapping("/client/matrixParamsMap")
    public void matrixParamsMap() {

        Map<String, List<String>> matrixVars = new HashMap<>();
        matrixVars.put("account", Collections.singletonList("a"));
        matrixVars.put("name", Collections.singletonList("n"));
        feignDemo.matrixParamsMap(matrixVars);

    }


    @GetMapping("/client/matrixParams")
    public void matrixParams() {

        feignDemo.matrixParams("a", "n");

    }

}
