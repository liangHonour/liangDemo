import org.junit.Test;

import java.lang.reflect.Modifier;

public class ModifierTests {
    @Test
    public void isAbstractTests(){
        final int a = 1451446;
        int b = 16351;
        boolean aA = Modifier.isAbstract(a);
        boolean bA = Modifier.isAbstract(b);
        int i = a & 0x00000400;
        System.out.println(i);
        System.out.println(String.valueOf(aA)+"  "+String.valueOf(bA));
    }
}
