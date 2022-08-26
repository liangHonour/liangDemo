package com.liang.file;

import com.liang.ssh.SSHConnection;
import com.trilead.ssh2.Session;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FileHandlingTest {
    @Test
    public void test() throws Exception {
        Session session = test2();
        OutputStream outputStream = session.getStdin();
        File file = new File("\\test.txt");
        try {
            //如果加了true,会在原有的基础上新增，如果没有加，会直接覆盖

//            outputStream = new FileOutputStream(file, true);
            //只能通过字节实现，所以要将字符串转成字节
            outputStream.write("abcd".getBytes(StandardCharsets.UTF_8));
            outputStream.write("中国".getBytes(StandardCharsets.UTF_8));
            outputStream.write(97);//文本中显示的是a对应的char字符
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public Session test2() {
        String user = "root";
        String password = "yg1505272629";
        String hostName = "101.43.65.207";
        String privateKeyPath = "D:\\work\\TX_Key\\docker";
        SSHConnection sshConnection = new SSHConnection(user, password, privateKeyPath, hostName);
        sshConnection.setTime(5 * 10000, 5 * 10000, 5 * 10000);
        Session session = sshConnection.connection();
        return session;
    }


}
