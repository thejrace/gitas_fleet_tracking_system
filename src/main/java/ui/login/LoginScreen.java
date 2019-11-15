package ui.login;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class LoginScreen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/login.fxml"));
            Parent content = loader.load();
            primaryStage.setTitle("Gitas FTS Obarey");

            primaryStage.setScene(new Scene(content, 630, 350 ));

            primaryStage.getIcons().add(new Image(getClass().getResource("/img/login_logo.png").toExternalForm()));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();

            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    Platform.exit();
                    System.exit(0);
                }
            });

            LoginScreenController controller = loader.getController();
            controller.setStage(primaryStage);

        } catch( Exception e ){
            e.printStackTrace();
        }
    }

}
