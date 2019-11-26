/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package controllers;

public class DownloaderController {

    /**
     * Active counter
     */
    private int concurrentDownloadCounter = 0;

    /**
     * Couner limit @todo Get from settings
     */
    private int getConcurrentDownloadLimit = 10;

    /**
     * Constructor
     */
    public DownloaderController(){

    }

    /**
     * Ask to get clearance
     *
     * @return
     */
    public synchronized boolean request(){
        if( getConcurrentDownloadLimit > concurrentDownloadCounter ){
            incCounter();
            return true;
        }
        return false;
    }

    /**
     * Give back the clearance
     */
    public synchronized void release(){
        decCounter();
    }

    /**
     * Increment active counter
     */
    private synchronized void incCounter(){
        concurrentDownloadCounter++;
        System.out.println(concurrentDownloadCounter + " active IETT downloaders");
    }

    /**
     * Decrement active counter
     */
    private synchronized void decCounter(){
        concurrentDownloadCounter--;
    }

}
