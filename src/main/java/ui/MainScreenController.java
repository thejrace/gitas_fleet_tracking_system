/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import ui.block.ContentContainer;
import ui.block.TopBar;
import ui.block.TopBarController;
import ui.popup.Popup;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    /**
     * Base container for content and popup
     */
    @FXML private StackPane uiWrapper;

    /**
     * Main UI container where Pages are shown.
     */
    @FXML private AnchorPane uiContentWrapper;

    /**
     * Overlay for popup
     */

    @FXML private VBox uiPopupOverlay;
    /**
     * Content container for popup
     */
    @FXML private VBox uiPopup;

    /**
     * Top menu bar container
     */
    private AnchorPane uiTopBar;

    /**
     * Static UI instance of the uiContentWrapper.
     * It's accessed from navigation for changing the page.
     */
    public static ScrollPane CONTENT_CONTAINER;

    /**
     * Static instance of the uiWrapper
     */
    public static StackPane WRAPPER;

    @Override
    public void initialize(URL url, ResourceBundle rb ){
        try {
            // initialize main UI blocks
            TopBar topBar = new TopBar();

            ContentContainer contentContainer = new ContentContainer();
            topBar.initUI();
            contentContainer.initUI();

            uiTopBar = (AnchorPane)topBar.getUI();
            CONTENT_CONTAINER = (ScrollPane)contentContainer.getUI();
            WRAPPER = uiWrapper;

            // make them sticky during resize
            uiContentWrapper.getChildren().add( uiTopBar );
            AnchorPane.setLeftAnchor(uiTopBar, 0.0);
            AnchorPane.setTopAnchor(uiTopBar, 0.0);
            AnchorPane.setRightAnchor(uiTopBar, 0.0);

            uiContentWrapper.getChildren().add( CONTENT_CONTAINER );
            AnchorPane.setLeftAnchor(CONTENT_CONTAINER, 0.0);
            AnchorPane.setRightAnchor(CONTENT_CONTAINER, 0.0);
            AnchorPane.setTopAnchor(CONTENT_CONTAINER, 35.0);
            AnchorPane.setBottomAnchor(CONTENT_CONTAINER, 0.0);

            // initialize popup
            Popup.initialize( uiPopupOverlay, uiPopup );

            ((TopBarController)topBar.getController()).initEvents();

        } catch( Exception e ){
            e.printStackTrace();
        }
    }


    // @todo - debounce the event ??
    public void screenResizeAction(){

    }

}