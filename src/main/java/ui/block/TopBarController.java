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
import ui.page.PageFactory;
import ui.page.UIPage;
import ui.popup.Popup;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TopBarController implements Initializable {

    @FXML
    private Button uiDashboardBtn;

    /**
     * Key of the activate page.
     */
    private static String activePage;

    /**
     * Initialized pages.
     */
    public static Map<String, UIPage> pagesCached = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle rb ){
        loadPage("FleetPage");

        uiDashboardBtn.setOnMouseClicked( ev -> {
            switchPage("FleetPage");
        });
    }

    /**
     * Load the page with given key.
     * This method is triggered from TopNavButton's controller
     *
     * @param page page class name
     */
    public static void switchPage(String page){
        if( page.equals(activePage) ) return;
        if( pagesCached.containsKey(page) ){
            showPage(page);
        } else {
            loadPage(page);
        }
    }

    /**
     * Load page from factory.
     *
     * @param page Name of the page to be loaded.
     */
    private static void loadPage(String page){
        Platform.runLater(()->{ Popup.showLoader(); });
        Thread load = new Thread( () -> {
            UIPage pageClass = PageFactory.getPage(page);
            pagesCached.put(page, pageClass);
            showPage(page);
        });
        load.setDaemon(true);
        load.start();
    }

    /**
     * Show loaded pages.
     *
     * @param page Name of the page to be shown.
     */
    private static void showPage(String page){
        Platform.runLater(() -> {
            MainScreenController.CONTENT_CONTAINER.setContent( pagesCached.get(page).getUI() );
            activePage = page;
            Popup.hide();
        });
    }

}
