package com.liang.ssh;

import com.trilead.ssh2.Connection;
import com.trilead.ssh2.Session;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * 获取服务器Session链接
 */
public class SSHConnection {
    /**
     * ssh链接端口默认22
     */
    private int port = 22;
    /**
     * 服务器密钥文件
     */
    private String privateKeyPath;
    /**
     * 服务器地址
     */
    private String hostName;
    /**
     * 用户名
     */
    private String user;
    /**
     * 密码
     */
    private String password;

    private int connectTimeout = 500;
    private int readTimeout = 500;
    private int kexTimeout = 500;

    public SSHConnection() {

    }

    public SSHConnection(String user, String password, String privateKeyPath, String hostName) {
        this.user = user;
        this.password = password;
        this.privateKeyPath = privateKeyPath;
        this.hostName = hostName;
    }

    public SSHConnection(String user, String password, int port, String privateKeyPath, String hostName) {
        this.user = user;
        this.password = password;
        this.port = port;
        this.privateKeyPath = privateKeyPath;
        this.hostName = hostName;
    }

    /**
     * 设置链接服务器的超时时间
     *
     * @param connectTimeout 连接超时
     * @param readTimeout    读取超时
     * @param kexTimeout     密钥文件校验超时
     */
    public void setTime(int connectTimeout, int readTimeout, int kexTimeout) {
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.kexTimeout = kexTimeout;
    }

    /**
     * 获取一个Session会话窗口
     * 结束后请不要忘记close()关闭连接
     *
     * @return Session 可能为null
     */
    public Session connection() {
        //获取ssh主机链接
        Connection sshConnection = new Connection(hostName, port);
        //设置超时时间
        try {
            sshConnection.connect(null, connectTimeout, readTimeout, kexTimeout);
        } catch (IOException e) {
            System.out.println("超时时间设置失败");
            e.printStackTrace();
        }
        char[] privateKey = getPrivateKey(privateKeyPath);
        try {
            sshConnection.authenticateWithPublicKey(user, privateKey, password);
        } catch (IOException e) {
            System.out.println("密钥文件或账号密码错误");
            e.printStackTrace();
        }
        Session session = null;
        try {
            //获取一个会话
            session = sshConnection.openSession();
        } catch (IOException e) {
            System.out.println("服务器会话连接建立失败");
            e.printStackTrace();
        }
        return session;
    }

    /**
     * 从传入路径获取密钥文件信息
     *
     * @param patch 密钥文件路径
     * @return 密钥文件内容
     */
    private char[] getPrivateKey(String patch) {
        Stream<String> lines = null;
        try {
            lines = Files.lines(Paths.get(patch));
        } catch (IOException e) {
            System.out.println("获取密钥文件失败，请检查路径。");
            e.printStackTrace();
        }
        if (lines == null) {
            System.out.println("未获取到密钥文件，请检查路径。");
            return null;
        }
        StringBuffer privateKey = new StringBuffer();
        lines.forEach(a -> privateKey.append(a).append("\n"));
        return privateKey.toString().toCharArray();
    }
}
