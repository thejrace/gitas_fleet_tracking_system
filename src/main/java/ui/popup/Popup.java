/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.popup;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ui.MainScreenController;

public class Popup {

    /**
     * Class identifiers for show message method.
     */
    public static int DANGER = 0;
    public static int WARNING = 1;
    public static int INFO = 3;
    public static int SUCCESS = 4;
    public static int DEFAULT = 5;

    /**
     * Popup elements.
     */
    private static VBox POPUP_OVERLAY, POPUP_CONTENT, LOADER_CONTENT;
    /**
     * Flag for Popup status.
     */
    private static boolean shown = false;

    /**
     * Show the popup.
     */
    public static void show(){
        MainScreenController.WRAPPER.getChildren().setAll(reverseElems());
        shown = true;
    }

    /**
     * Hide popup.
     */
    public static void hide(){
        if( !shown ) return;
        MainScreenController.WRAPPER.getChildren().setAll(reverseElems());
        POPUP_CONTENT.getChildren().clear();
        shown = false;
    }

    /**
     * Show Popup with loader.
     */
    public static void showLoader(){
        setContent( LOADER_CONTENT );
        show();
    }

    /**
     * Reverse the MainScreenController.WRAPPER elements to put either Popup or Content to the top.
     * ( Switching z-index )
     *
     * @return Reversed nodes.
     */
    private static ObservableList<Node> reverseElems(){
        ObservableList<Node> prevList = MainScreenController.WRAPPER.getChildren();
        ObservableList<Node> newList = FXCollections.observableArrayList();
        newList.add(0, prevList.get(1) );
        newList.add(1, prevList.get(0) );
        return newList;
    }

    /**
     * Initialize Popup components.
     *
     * @param overlay Background.
     * @param content Contents
     */
    public static void initialize( VBox overlay, VBox content  ){
        POPUP_OVERLAY = overlay;
        POPUP_CONTENT = content;
        PopupLoaderContent popupLoaderContent = new PopupLoaderContent();
        LOADER_CONTENT = (VBox)popupLoaderContent.getUI();
    }

    /**
     * Show message in the popup.
     *
     * @param type
     * @param text
     */
    public static void showMessage(int type, String text ){
        // todo -> make it UI component
        Label label = new Label(text);
        VBox container = new VBox();
        container.getChildren().add(label);
        container.setAlignment(Pos.CENTER);
        setContent(container);
    }

    /**
     * Set content to popup.
     *
     * @param content UI layouts.
     */
    public static void setContent( Node content ){
        POPUP_CONTENT.getChildren().clear();
        POPUP_CONTENT.getChildren().add(content);
    }

}
