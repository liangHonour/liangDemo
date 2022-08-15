package com.liang;

import com.liang.ssh.SSHConnection;
import com.trilead.ssh2.Session;

public class Main {

    public static void main(String[] args) {
        String user = "root";
        String password = "yg1505272629";
        String hostName = "101.43.65.207";
        String privateKeyPath = "D:\\work\\TX_Key\\docker";
        SSHConnection sshConnection = new SSHConnection(user, password, privateKeyPath, hostName);
        Session session = sshConnection.connection();

        session.close();
    }
}
