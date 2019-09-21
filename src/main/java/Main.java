/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
import javafx.application.Application;
import javafx.stage.Stage;
import ui.MainScreen;
import ui.start_splash.StartSplashScreen;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        StartSplashScreen splashPage = new StartSplashScreen();
        splashPage.start(new Stage());

        /*MainScreen main = new MainScreen();
        main.start(new Stage());*/


    }

    public static void main(String[] args) {
        launch(args);
    }
}
