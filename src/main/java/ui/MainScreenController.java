package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import ui.block.ContentContainer;
import ui.block.TopBar;
import ui.popup.Popup;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {


    @FXML private StackPane uiWrapper;
    @FXML private AnchorPane uiContentWrapper;
    @FXML private VBox uiPopupOverlay;
    @FXML private VBox uiPopup;

    private AnchorPane uiTopBar;
    public static ScrollPane CONTENT_CONTAINER;
    public static StackPane WRAPPER;

    @Override
    public void initialize(URL url, ResourceBundle rb ){
        try {

            //SideBar sideBar = new SideBar();
            TopBar topBar = new TopBar();

            ContentContainer contentContainer = new ContentContainer();
            topBar.initUI();
            contentContainer.initUI();

            uiTopBar = (AnchorPane)topBar.getUI();
            CONTENT_CONTAINER = (ScrollPane)contentContainer.getUI();
            WRAPPER = uiWrapper;

            uiContentWrapper.getChildren().add( uiTopBar );
            AnchorPane.setLeftAnchor(uiTopBar, 0.0);
            AnchorPane.setTopAnchor(uiTopBar, 0.0);
            AnchorPane.setRightAnchor(uiTopBar, 0.0);

            uiContentWrapper.getChildren().add( CONTENT_CONTAINER );
            AnchorPane.setLeftAnchor(CONTENT_CONTAINER, 0.0);
            AnchorPane.setRightAnchor(CONTENT_CONTAINER, 0.0);
            AnchorPane.setTopAnchor(CONTENT_CONTAINER, 150.0);
            AnchorPane.setBottomAnchor(CONTENT_CONTAINER, 0.0);

            // initalize popup
            Popup.initialize( uiPopupOverlay, uiPopup );

        } catch( Exception e ){
            e.printStackTrace();
        }
    }


    // @todo - debounce the event ??
    public void screenResizeAction(){

    }

}