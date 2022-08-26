package com.liang.ssh;

import com.trilead.ssh2.Session;
import org.junit.Test;

public class SSHConnectionTest {

    @Test
    public void Test() throws Exception {
        String user = "root";
        String password = "yg1505272629";
        String hostName = "101.43.65.207";
        String privateKeyPath = "D:\\work\\TX_Key\\docker";
        SSHConnection sshConnection = new SSHConnection(user, password, privateKeyPath, hostName);
        Session session = sshConnection.connection();
        session.execCommand("mkdir testliang");
        session.close();
    }
}
