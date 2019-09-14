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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        // entry point - check user etc..

        // for testing, directy go to MainScreen
        MainScreen main = new MainScreen();
        main.start(new Stage());


    }

    public static void main(String[] args) {
        launch(args);
    }
}
