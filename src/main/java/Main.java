/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
import controllers.ControllerHub;
import javafx.application.Application;
import javafx.stage.Stage;
import ui.start_splash.StartSplashScreen;
import utils.SharedConfig;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("VERSION = " + SharedConfig.VERSION );

        // initialize static controllers
        ControllerHub controllerHub = new ControllerHub();
        controllerHub.initialize();

        StartSplashScreen splashPage = new StartSplashScreen();
        splashPage.start(new Stage());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
