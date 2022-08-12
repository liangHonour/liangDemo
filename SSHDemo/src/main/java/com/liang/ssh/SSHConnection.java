package com.liang.ssh;

import com.liang.ssh.util.Machine;

public class SSHConnection {
    private final Machine machine;
    private final int port = 22;
    private String address;
    private String user;
    private String password;

    public SSHConnection(Machine machine) {
        this.machine = machine;
    }

    public void connect() {

    }
}
