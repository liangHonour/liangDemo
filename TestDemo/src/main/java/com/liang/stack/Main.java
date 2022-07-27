package com.liang.stack;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public void MainTest(){

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();


        System.out.println(stackTrace[2].getMethodName());
        Iterator<StackTraceElement> iterator = Arrays.stream(stackTrace).iterator();
        while (iterator.hasNext()){
            StackTraceElement next = iterator.next();
            String methodName = next.getMethodName();
            String className = next.getClassName();
            System.out.println(methodName);
//            System.out.println(className);
        }
    }

    @Test
    public void main(){
            try{
                InetAddress ip = InetAddress.getLocalHost();
                String localName = ip.getHostName();
                boolean contains = System.getProperty("osme", "").contains("Windows");
                System.out.println(contains);
                String osName = System.getProperty("os.name");
                String userName = System.getProperty("user.name");
                String osVersion = System.getProperty("os.version");
                String osArch = System.getProperty("os.arch");
                System.out.println("当前用户：" + userName);
                System.out.println("主机名称：" + localName);
                System.out.println("主机系统：" + osName);
                System.out.println("系统版本：" + osVersion);
                System.out.println("系统架构：" + osArch);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


}
