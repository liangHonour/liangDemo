import org.junit.Test;

public class SystemDemo {
    @Test
    public void test(){
        System.setProperty("AuthorizedUser", String.valueOf(false));
        boolean b = Boolean.parseBoolean(System.getProperty("AuthorizedUser", "true"));
        System.out.println(b+"");
    }
}
