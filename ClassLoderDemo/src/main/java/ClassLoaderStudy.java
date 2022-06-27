import object.ExpandClassLoader;

public class ClassLoaderStudy {
    public static void main(String[] args) throws ClassNotFoundException {
        ExpandClassLoader expandClassLoader = new ExpandClassLoader();
        Class<?> aClass = expandClassLoader.loadClass("object.People");

    }
}
