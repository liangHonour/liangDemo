import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class CollectionStudy {
    public static void main(String[] args) {
        //阻塞队列
        ArrayBlockingQueue<Object> arr = new ArrayBlockingQueue<>(10);
        try {
            arr.put("sda");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
