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
import ui.MainScreenController;
import ui.page.UIPage;
import ui.popup.Popup;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TopBarController implements Initializable {

    @FXML
    private Button uiDashboardBtn;

    private static String activePage = "ui.page.FleetPage";

    public static Map<String, UIPage> pagesCached = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle rb ){

    }

    public static void loadPage(String page){
        if( page.equals(activePage) ) return;
        Platform.runLater(() -> {
            MainScreenController.CONTENT_CONTAINER.setContent( pagesCached.get(page).getUI() );
            activePage = page;
            Popup.hide();
        });
    }

}
