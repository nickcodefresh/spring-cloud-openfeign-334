package com.example.matrixdemo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;

import java.util.List;
import java.util.Map;


/**
 * Ths calls the WireMock server
 */
@FeignClient(url = "http://localhost:8080", name = "name")
public interface FeignDemo {

    @GetMapping("/api/server{matrixVars}")
    public void matrixParamsMap(@MatrixVariable Map<String, List<String>> matrixVars);

    @GetMapping("/api/server{account}{name}")
    public void matrixParams(@MatrixVariable("account") String account, @MatrixVariable("name") String name);

}
