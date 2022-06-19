package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.code.kaptcha.Producer;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private Producer kaptchaProducer;

    @GetMapping({"/login"})
    @ResponseBody
    public String login(HttpServletResponse response) throws IOException {
        String text = kaptchaProducer.createText();
        BufferedImage  image = kaptchaProducer.createImage(text);
        ServletOutputStream  outputStream = response.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        outputStream.flush();
        outputStream.close();
        return null;
    }


    @GetMapping({"/getRuntime/stop"})
    @ResponseBody
    public void stop1() {
        Thread thread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(thread);
        System.exit(0);
    }

    @GetMapping({"/System/stop"})
    @ResponseBody
    public void stop2() {
        //获取上下文的的tomcat容器
        ServletWebServerApplicationContext serverApplicationContext = new ServletWebServerApplicationContext();
        WebServer webServer = serverApplicationContext.getWebServer();
        TomcatWebServer tomcat = (TomcatWebServer) webServer;
        System.out.printf(tomcat.getPort()+"");
    }

    @GetMapping({"/start"})
    @ResponseBody
    public void start() {
        try {
            System.out.println("开始执行任务");
            Thread.sleep(40*1000);
            System.out.println("任务执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
