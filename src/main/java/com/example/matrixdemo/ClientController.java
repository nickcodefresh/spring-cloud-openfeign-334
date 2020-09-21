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
    private FeignInternalServer feignInternalServer;

    @Autowired
    private FeignWireMockServer feignWireMockServer;


    @GetMapping("/client/internalMatrixParamsMap")
    public void internalMatrixParamsMap() {

        Map<String, List<String>> matrixVars = new HashMap<>();
        matrixVars.put("account", Collections.singletonList("a"));
        matrixVars.put("name", Collections.singletonList("n"));
        feignInternalServer.matrixParamsMap(matrixVars);

    }


    @GetMapping("/client/internalMatrixParams")
    public void internalMatrixParams() {

        feignInternalServer.matrixParams("a", "n");

    }


    @GetMapping("/client/wiremockMatrixParamsMap")
    public void wiremockMatrixParamsMap() {

        Map<String, List<String>> matrixVars = new HashMap<>();
        matrixVars.put("account", Collections.singletonList("a"));
        matrixVars.put("name", Collections.singletonList("n"));
        feignWireMockServer.matrixParamsMap(matrixVars);

    }


    @GetMapping("/client/wiremockMatrixParams")
    public void wiremocklMatrixParams() {

        feignWireMockServer.matrixParams("a", "n");

    }

}
