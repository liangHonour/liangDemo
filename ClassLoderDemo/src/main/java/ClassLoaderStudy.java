import org.junit.Test;

import java.util.ArrayList;

public class ClassLoaderStudy {
    public static Integer a;

    public static void main(String[] args) throws ClassNotFoundException {
        add();
        System.out.println(a);
    }

    public static void add() {
        ArrayList<Object> objects = new ArrayList<>();
        String aaa =  "asdad";
    }

    @Test
    public static void ClassLoaderEquals() {

    }


    class OneClassLoader extends ClassLoader {

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            return super.findClass(name);
        }
    }
}
