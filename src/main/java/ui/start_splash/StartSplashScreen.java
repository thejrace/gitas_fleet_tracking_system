package ui.start_splash;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartSplashScreen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/start_splash.fxml"));
            Parent content = loader.load();
            primaryStage.setTitle("Gitas FTS Obarey");
            primaryStage.initStyle(StageStyle.UNDECORATED);

            primaryStage.setScene( new Scene(content, 500, 258 ));

            //stage.getIcons().add(new Image(getClass().getResource("/gpts/res/img/gpts_ico.png").toExternalForm()));
            primaryStage.show();

            StartSplashScreenController controller = loader.getController();

            // read config


            // check updates


            // download user data

        } catch( Exception e ){
            e.printStackTrace();
        }
    }
}
