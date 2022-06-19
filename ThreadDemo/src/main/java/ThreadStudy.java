import org.junit.Test;



public class ThreadStudy{

    //数据同步
    @Test
    public void dataSynchronization() throws Exception{
        Thread thread2 = new Thread(this::getLocalDatabaseData);
        thread2.start();
        getCounterpartyDatabaseData();
        thread2.join();
        dataToCompare();
        UpdateLocalData();
    }

    //获取对方数据库数据
    private void getCounterpartyDatabaseData(){
        try {
            System.out.println("开始获取对方数据库数据");
            Thread.sleep(5000);
            System.out.println("------对方数据库数据获取完毕------");
        }catch (Exception e){
            System.out.println("对方接口异常");
        }
    }

    //获取本地数据数据
    private void getLocalDatabaseData(){
        try {
            System.out.println("开始获取本地数据库数据");
            Thread.sleep(5000);
            System.out.println("------本地数据库数据获取完毕------");
        }catch (Exception e){
            System.out.println("对方接口异常");
        }
    }

    //数据进行比对，排除
    private void dataToCompare(){
        try {
            System.out.println("开始进行数据比对");
            Thread.sleep(1000);
            System.out.println("------数据比对完毕------");
        }catch (Exception e){
            System.out.println("比对出现异常");
        }
    }

    //跟新本地数据
    private void UpdateLocalData(){
        try {
            System.out.println("开始跟新本地数据库");
            Thread.sleep(10000);
            System.out.println("------本地数据库更新完毕-----");
        }catch (Exception e){
            System.out.println("本地数据库跟新异常");
        }
    }

}
