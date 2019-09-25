package ui.start_splash;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONObject;
import ui.MainScreen;
import utils.APIRequest;
import utils.SharedConfig;
import utils.UpdateChecker;

import java.io.IOException;

public class StartSplashScreen extends Application {

    public static Stage stage = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/start_splash.fxml"));
            Parent content = loader.load();
            primaryStage.setTitle("Gitas FTS Obarey");
            primaryStage.initStyle(StageStyle.UNDECORATED);

            primaryStage.setScene( new Scene(content, 500, 258 ));

            stage = primaryStage;

            //stage.getIcons().add(new Image(getClass().getResource("/gpts/res/img/gpts_ico.png").toExternalForm()));
            primaryStage.show();

            StartSplashScreenController controller = loader.getController();

            Thread thread = new Thread(() -> {
                // read config
                if( SharedConfig.read() ){
                    System.out.println(SharedConfig.DATA);

                    controller.updateStatus("Güncellemeler kontrol ediliyor..", "Lütfen bekleyin...");
                    if( UpdateChecker.action() ){
                        controller.updateStatus("Yeni versiyon bulundu, indiriliyor..", "Lütfen bekleyin...");
                        UpdateChecker.download();

                        controller.updateStatus("Yeni versiyon indirildi!", "Bu pencere kapandıktan sonra programı tekrar başlatabilirsiniz.");

                        try {
                            Thread.sleep(2500);
                        } catch( InterruptedException e ){
                            e.printStackTrace();
                        }

                        Platform.setImplicitExit(false);
                        closeUpdateScreen();

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Platform.exit();
                            }
                        });

                    } else {
                        controller.updateStatus("Güncelleme yok.", "");
                    }
                }

                // download user data
                controller.updateStatus("Senkronizasyon yapılıyor..", "Lütfen bekleyin..");

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // kill platform when main screen is closed
                            Platform.setImplicitExit(true);
                            closeUpdateScreen();

                            MainScreen mainScreen = new MainScreen();
                            mainScreen.start( new Stage() );
                        } catch( Exception e ){
                            e.printStackTrace();
                        }
                    }
                });

            });
            thread.setDaemon(true);
            thread.start();


        } catch( Exception e ){
            e.printStackTrace();
        }
    }

    private void closeUpdateScreen(){
        Platform.runLater(() -> {
            StartSplashScreen.stage.close();
        });
    }

}
