/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui;

import controllers.ControllerHub;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainScreen extends Application {

    public static Stage Stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/main_screen.fxml"));
            Parent content = loader.load();
            primaryStage.setTitle("Gitas FTS Obarey");

            try {
                Font.loadFont(getClass().getResource("/font/montserratbold.otf").toExternalForm().replace("%20", " "), 10);
                Font.loadFont(getClass().getResource("/font/montserratsemibold.otf").toExternalForm().replace("%20", " "), 10);
                Font.loadFont(getClass().getResource("/font/montserratlight.otf").toExternalForm().replace("%20", " "), 10);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Map<String, Double> resData = Common.calculateAppWindowSize();
            primaryStage.setScene(new Scene(content, 1280, 1024 )); // @todo - calculate client's width-height, give offset to that

            primaryStage.getIcons().add(new Image(getClass().getResource("/img/app_ico.png").toExternalForm()));

            Stage = primaryStage;
            primaryStage.show();

            MainScreenController controller = loader.getController();
            controller.screenResizeAction();

            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {

                    ControllerHub.PopupPageController.closeAll();
                    Platform.exit();
                    System.exit(0);
                }
            });

        } catch( Exception e ){
            e.printStackTrace();
        }
    }

}