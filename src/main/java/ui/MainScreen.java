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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainScreen extends Application {

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

            //stage.getIcons().add(new Image(getClass().getResource("/gpts/res/img/gpts_ico.png").toExternalForm()));
            primaryStage.show();

            MainScreenController controller = loader.getController();
            controller.screenResizeAction();

            // initialize static controllers
            ControllerHub controllerHub = new ControllerHub();
            controllerHub.initialize();

        } catch( Exception e ){
            e.printStackTrace();
        }
    }

}