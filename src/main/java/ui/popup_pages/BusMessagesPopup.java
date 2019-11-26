/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.popup_pages;

import controllers.ControllerHub;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Bus;
import utils.Common;

public class BusMessagesPopup extends Application {

    private BusMessagesPopupController controller;

    // @todo SAVE UI Parent?

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/bus_messages_popup.fxml"));
            Parent content = loader.load();
            primaryStage.setTitle("Otobüs Mesajlar");

            try {
                Font.loadFont(getClass().getResource("/font/montserratbold.otf").toExternalForm().replace("%20", " "), 10);
                Font.loadFont(getClass().getResource("/font/montserratsemibold.otf").toExternalForm().replace("%20", " "), 10);
                Font.loadFont(getClass().getResource("/font/montserratlight.otf").toExternalForm().replace("%20", " "), 10);
            } catch (Exception e) {
                e.printStackTrace();
            }

            primaryStage.setScene(new Scene(content, 600, 750 ));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            //stage.getIcons().add(new Image(getClass().getResource("/gpts/res/img/gpts_ico.png").toExternalForm()));

            // alarm popup minimizes additional windows for some reason
            // this is a work around to overcome that problem
            primaryStage.setAlwaysOnTop(true);

            primaryStage.show();

            Common.makeStageDraggable(primaryStage, content);

            ControllerHub.PopupPageController.addPage(primaryStage);

            controller = loader.getController();
            controller.setPageIndex(ControllerHub.PopupPageController.getLastInsertedIndex());
        } catch( Exception e ){
            e.printStackTrace();
        }
    }

    public void setData(Bus bus){
        controller.setBus(bus);
        controller.updateUI();
    }
}
