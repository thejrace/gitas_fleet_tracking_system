/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class UICommon {

    protected Object controller;
    protected Node UI;

    public void loadFXML( String fxmlName ){
        try {
            // load fxml layouts
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/"+fxmlName+".fxml"));
            UI = loader.load();
            controller = loader.getController();
        } catch( IOException e ){
            e.printStackTrace();
        }
    }

    public Object getController(){
        return controller;
    }

    public Node getUI(){
        return UI;
    }

}
