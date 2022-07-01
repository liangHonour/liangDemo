package com.bes.boot.sample.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;


@Controller
public class SimpleController {
    @ResponseBody
    @RequestMapping(value = "/cas/login.action", method = {
            RequestMethod.GET,
            RequestMethod.POST,
            RequestMethod.HEAD,
            RequestMethod.TRACE,
            RequestMethod.DELETE,
            RequestMethod.PUT,
            RequestMethod.PATCH,
            RequestMethod.OPTIONS})
    public String welcome(HttpServletRequest request, Map<String, Object> model) {
        System.out.println("X-Forwarded-For:" + request.getHeader("X-Forwarded-For"));
        System.out.println("X-Real-Ip:" + request.getHeader("X-Real-Ip"));
        System.out.println("X-Forwarded-Proto:" + request.getHeader("X-Forwarded-Proto"));
        System.out.println("request.getRemoteAddr():" + request.getRemoteAddr());
        System.out.println("SW_AGENT_NAME" + System.getProperty("SW_AGENT_NAME"));
        return "hello, test5";
    }
}
