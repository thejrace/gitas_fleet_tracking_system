package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainScreen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/main_screen.fxml"));
            Parent content = loader.load();
            primaryStage.setTitle("Gitas FTS");

            //Map<String, Double> resData = Common.calculateAppWindowSize();
            primaryStage.setScene(new Scene(content, 1280, 1024 )); // @todo - calculate client's width-height, give offset to that

            //stage.getIcons().add(new Image(getClass().getResource("/gpts/res/img/gpts_ico.png").toExternalForm()));
            primaryStage.show();

            MainScreenController controller = loader.getController();
            controller.screenResizeAction();

        } catch( Exception e ){
            e.printStackTrace();
        }
    }

}