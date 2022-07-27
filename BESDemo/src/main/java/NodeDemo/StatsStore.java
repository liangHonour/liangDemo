package NodeDemo;

import java.util.concurrent.atomic.AtomicLong;

public class StatsStore {
    /**
     * 原子类型的long集合
     */
    private final AtomicLong values[];
    /**
     * 集合大小
     */
    private final int size;
    /**
     * 从0开始的索引
     */
    private int index;

    /**
     * 初始化构造函数，给集合中的元素赋初始值-1
     * @param size 集合的初始大小
     */
    public StatsStore(int size) {
        this.size = size;
        values = new AtomicLong[size];
        for (int i = 0; i < size; i++) {
            values[i] = new AtomicLong(-1);
        }
    }

    /**
     * 从0开始赋值,如果赋值超过集合上线，重置索引，从零开始赋值
     * @param value 传入的元素大小
     */
    public synchronized void add(long value) {
        values[index].set(value);
        index++;
        if (index == size) {
            index = 0;
        }
    }

    /**
     *
     * @return
     */
    public long getMean() {
        double result = 0;
        int counter = 0;
        for (int i = 0; i < size; i++) {
            long value = values[i].get();
            if (value != -1) {
                counter++;

                result = result *
                        ((counter - 1) / (double) counter)
                        + value / (double) counter;
            }
        }
        return (long) result;
    }
}
