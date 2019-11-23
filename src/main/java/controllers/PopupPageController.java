/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package controllers;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class PopupPageController {

    /**
     * List of open stages
     */
    private Map<Integer, Stage> pageList;

    /**
     * Active index counter
     */
    private int indexCounter = 0;

    /**
     * Initialize the controller
     */
    public void initialize(){
        pageList = new HashMap<>();
    }

    /**
     * Add new popup page
     *
     * @param stage
     */
    public void addPage( Stage stage ){
        pageList.put(indexCounter, stage);
        indexCounter++;
    }

    /**
     * Close the page with given index
     *
     * @param index
     */
    public void closePage( int index ){
        pageList.get(index).close();
        pageList.remove(index);
    }

    /**
     * Close all open pages, triggered from MainScreen
     */
    public void closeAll(){
        for( Map.Entry<Integer, Stage> entry : pageList.entrySet() ){
            entry.getValue().close();
        }
    }

    /**
     * Get the index of last added page
     *
     * @return
     */
    public int getLastInsertedIndex(){
        return indexCounter-1;
    }

    /**
     * Getter for indexCounter
     *
     * @return
     */
    public int getIndexCounter(){
        return indexCounter;
    }
}
