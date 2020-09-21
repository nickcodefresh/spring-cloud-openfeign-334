package com.example.matrixdemo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;

import java.util.List;
import java.util.Map;


@FeignClient(url = "http://localhost:8080", name = "internal")
public interface FeignInternalServer {

    @GetMapping("/server/matrixParamsMap{matrixVars}")
    void matrixParamsMap(@MatrixVariable Map<String, List<String>> matrixVars);

    @GetMapping("/server/matrixParams{account}{name}")
    void matrixParams(@MatrixVariable("account") String account, @MatrixVariable("name") String name);

}
