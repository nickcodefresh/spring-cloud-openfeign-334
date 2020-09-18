package com.example.matrixdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class ServerController {

    @GetMapping("/api/server{matrixVars}")
    public void matrixParamsMap(@MatrixVariable Map<String, List<String>> matrixVars) {

    }


    @GetMapping("/api/server{account}{name}")
    public void matrixParams(@MatrixVariable("account") String account, @MatrixVariable("name") String name) {

    }

}
