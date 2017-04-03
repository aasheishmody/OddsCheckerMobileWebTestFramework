package util;

public class Sleeper extends WebConnector{

    public static void sleep(Long millis)  {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            getLogger().severe("Sleep has been interrupted:"+e.getMessage() );
        }
    }
}
