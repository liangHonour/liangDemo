package java.sql;

import org.junit.Test;
import util.MySQLUtil;

public class SQLStudy {
    @Test
    public void test() {
        try {
            Connection connection = DriverManager.getConnection(MySQLUtil.TX_MYSQL_URL,MySQLUtil.TX_MYSQL_USERNAME,MySQLUtil.TX_MYSQL_PASSWORD);
        } catch (SQLException e) {
            System.out.println("获取sql链接错误");
            throw new RuntimeException(e);
        }
    }
}
