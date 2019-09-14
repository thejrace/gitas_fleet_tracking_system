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
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import ui.MainScreenController;
import ui.page.FleetPage;
import ui.page.SettingsPage;
import ui.page.UIPage;
import ui.popup.Popup;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TopBarController implements Initializable {

    @FXML
    private MenuBar uiMenuBar;

    @FXML
    private MenuItem uiSettingsBtn;

    @FXML
    private Button uiDashboardBtn;

    private int PIDFleet = 1,
                PIDSettings = 2;
    private int activePage = 0;
    private Map<Integer, UIPage> pagesCached = new HashMap<>();


    @Override
    public void initialize(URL url, ResourceBundle rb ){

    }

    public void initEvents(){
        Popup.showLoader();
        Thread load1 = new Thread(() -> {
            FleetPage fleetPage = new FleetPage();
            fleetPage.getController().setTitle("Filo Takip"); // @todo language
            pagesCached.put(PIDFleet, fleetPage);
            loadPage(PIDFleet, fleetPage);
        });
        load1.setDaemon(true);
        load1.start();

        uiSettingsBtn.setOnAction( ev -> {
            if( !checkPageInitied( PIDSettings ) ){
                Popup.showLoader();
                Thread load = new Thread( () -> {
                    SettingsPage projectFormPage = new SettingsPage();
                    projectFormPage.getController().setTitle("Ayarlar"); // @todo language
                    pagesCached.put(PIDSettings, projectFormPage);
                    loadPage(PIDSettings, projectFormPage );
                });
                load.setDaemon(true);
                load.start();
            } else {
                loadPageFromCache( PIDSettings );
            }

        });

        uiDashboardBtn.setOnAction( ev -> {
            loadPageFromCache( PIDFleet );
        });
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
