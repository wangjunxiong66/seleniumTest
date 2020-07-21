package com.wjxselenium.web.controller;

import com.wjxselenium.protocol.ResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/7/21 上午9:47
 * function: 配合jenkins的健康检查，在Jenkins中配置健康检查时配置 /healthCheck或者/health
 */
@RestController
public class HealthController {

    @RequestMapping(path = {"/healthCheck","/health"})
    public ResponseDTO healthCheck(){
        return ResponseDTO.OK();
    }

    @GetMapping("/")
    public ResponseDTO index(){
        return ResponseDTO.OK();
    }

    @RequestMapping(value = "systemCheck")
    public ResponseDTO systemCheck(){
        return ResponseDTO.systemError();
    }
}
