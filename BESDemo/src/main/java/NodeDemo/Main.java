package NodeDemo;

public class Main {
    public static void main(String[] args) {
        StatsStore statsStore = new StatsStore(5);
        statsStore.add(5);
        statsStore.add(6);
        statsStore.add(15);
        statsStore.add(16);
        statsStore.add(11);


        long mean = statsStore.getMean();
        System.out.println(mean);
    }
}
