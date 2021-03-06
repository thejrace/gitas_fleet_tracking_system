/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package utils;

import interfaces.ThreadCallback;
import javafx.application.Platform;

public class ThreadHelper {

    public static void func(ThreadCallback cb){
        Thread thread = new Thread(()->{
            cb.action();
        });
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Delay the thread for given time
     *
     * @param milliseconds delay interval
     */
    public static void delay(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch( InterruptedException e ){
            e.printStackTrace();
        }
    }

    /**
     * Execute callback on UI Thread
     *
     * @param cb
     */
    public static void runOnUIThread(ThreadCallback cb){
        Platform.runLater(() -> {
            cb.action();
        });
    }

}
