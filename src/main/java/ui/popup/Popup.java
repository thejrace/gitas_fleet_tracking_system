package ui.popup;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import ui.MainScreenController;

public class Popup {

    private static VBox POPUP_OVERLAY, POPUP_CONTENT, LOADER_CONTENT;

    public static void show(){
        MainScreenController.WRAPPER.getChildren().setAll(reverseElems());
    }

    public static void hide(){
        MainScreenController.WRAPPER.getChildren().setAll(reverseElems());
        POPUP_CONTENT.getChildren().clear();
    }

    public static void showLoader(){
        setContent( LOADER_CONTENT );
        show();
    }

    private static ObservableList<Node> reverseElems(){
        ObservableList<Node> prevList = MainScreenController.WRAPPER.getChildren();
        ObservableList<Node> newList = FXCollections.observableArrayList();
        newList.add(0, prevList.get(1) );
        newList.add(1, prevList.get(0) );
        return newList;
    }


    public static void initialize( VBox overlay, VBox content  ){
        POPUP_OVERLAY = overlay;
        POPUP_CONTENT = content;
        PopupLoaderContent popupLoaderContent = new PopupLoaderContent();
        LOADER_CONTENT = (VBox)popupLoaderContent.getUI();
    }

    public static void setContent( Node content ){
        POPUP_CONTENT.getChildren().add(content);
    }


}
