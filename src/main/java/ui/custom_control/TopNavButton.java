/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.custom_control;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import ui.block.TopBarController;
import ui.page.PageFactory;
import ui.page.UIPage;
import ui.popup.Popup;

import java.io.IOException;

/**
 * Custom component for TopBar's navigation between pages buttons.
 */
public class TopNavButton extends MenuItem {

    @FXML
    private FontAwesomeIconView icon;

    /**
     * Constructor.
     *
     * @param page Page object reference
     * @param iconName Glyph Icon class name.
     * @param fill Glyph Icon color (hex).
     */
    public TopNavButton( @NamedArg("page") String page, @NamedArg("iconName") String iconName, @NamedArg("fill") String fill ){
        // load layout
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/custom_controls/TopNavButton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();

            icon.setGlyphName(iconName);
            icon.setFill(Color.valueOf(fill));

            // page load action
            setOnAction( ev -> {
                if( TopBarController.pagesCached.containsKey(page) ){
                    TopBarController.loadPage(page);
                    System.out.println("FROM CACHE");
                } else {
                    System.out.println("INIT");
                    Popup.showLoader();
                    Thread load = new Thread( () -> {
                        UIPage pageClass = PageFactory.getPage(page);
                        TopBarController.pagesCached.put(page, pageClass);
                        TopBarController.loadPage(page);
                    });
                    load.setDaemon(true);
                    load.start();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
