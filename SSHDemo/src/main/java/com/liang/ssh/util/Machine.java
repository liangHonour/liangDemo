package com.liang.ssh.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Machine {
    private String os;

    private String sshPort;

    private String keyStorePath;

    private String keyPassword;

    private String remoteLoginType;

    private String name;

    private String hostName;

    private String userName;

    private String machinePassword;
}
