/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.block;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import ui.MainScreenController;
import ui.page.UIPage;
import ui.popup.Popup;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TopBarController implements Initializable {

    @FXML
    private MenuBar uiMenuBar;

    private int PIDProjects = 1,
                PIDProjectForm = 2,
                PIDSettings = 3;
    private int activePage = 0;
    private Map<Integer, UIPage> pagesCached = new HashMap<>();


    @Override
    public void initialize(URL url, ResourceBundle rb ){



    }


    private boolean checkPageInitied( int pageID ){
        return pagesCached.containsKey(pageID);
    }

    private void loadPageFromCache( int pageID ){
        if( activePage == pageID ) return;
        MainScreenController.CONTENT_CONTAINER.setContent( pagesCached.get(pageID).getUI() );
        activePage = pageID;
    }

    private void loadPage( int pageID, UIPage pageObject ){
        Platform.runLater( () -> {
            MainScreenController.CONTENT_CONTAINER.setContent(pageObject.getUI());
            activePage = pageID;
            Popup.hide();
        });
    }

}
