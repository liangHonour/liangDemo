package com.liang.sender;

import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        //发送者账号密码
        final String user = "liangglory@163.com";
        final String password = "yg1505272629";
        //接收者邮箱
        final String recipient = "755762551@qq.com";

        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String s = "";

        System.out.println("请输入要发送的信息");
        while (!(s = scanner.nextLine()).equals("end")) {
            sb.append(s);
            sb.append("<br>");
        }

        try {
            sendEmail(user, password, recipient, "test", sb.toString());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static void sendEmail(String user, String password, String recipient, String title, String sendMessage)
            throws MessagingException {
        Properties p = new Properties();
        p.setProperty("mail.smtp.host", "163.com");//发送者邮箱服务器
        p.setProperty("mail.smtp.port", "25");//发送者端口
        p.setProperty("mail.smtp.auth", "true");//是否开启权限控制
        p.setProperty("mail.transport", "smtp");//协议
        Session session = Session.getInstance(p, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        //设置收件人地址
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(title);
        message.setContent(sendMessage, "text/html;charset=UTF-8");
        try {
            Transport.send(message, user, password);
        } catch (AuthenticationFailedException e) {
            System.out.println("身份验证失败");
        }
        System.out.println("sent");
    }

}
