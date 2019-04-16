
import org.junit.Test;

import java.io.IOException;

public class TaggerTest {
   /* public static void main(String[] args) {
        try {
            tag("Print Say Hello 10 times");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    } */
    @Test
    public static String[] tag(String str) throws IOException, ClassNotFoundException {
        TaggerExample tagging = new TaggerExample();
        String[] a=tagging.tag(str);
        return a;
    }
}
