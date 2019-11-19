/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.start_splash;

import controllers.ControllerHub;
import interfaces.ActionCallback;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.MainScreen;
import ui.alert_prompt.AlertPrompt;
import ui.login.LoginScreen;
import utils.SharedConfig;
import utils.ThreadHelper;
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

            stage.getIcons().add(new Image(getClass().getResource("/img/login_logo.png").toExternalForm()));
            primaryStage.show();

            StartSplashScreenController controller = loader.getController();

            ThreadHelper.func(() -> {
                // read config
                if( SharedConfig.read() ){
                    System.out.println(SharedConfig.DATA);

                    controller.updateStatus("Güncellemeler kontrol ediliyor..", "Lütfen bekleyin...");
                    if( UpdateChecker.action() ){
                        controller.updateStatus("Yeni versiyon bulundu, indiriliyor..", "Lütfen bekleyin...");
                        UpdateChecker.download();

                        controller.updateStatus("Yeni versiyon indirildi!", "Bu pencere kapandıktan sonra programı tekrar başlatabilirsiniz.");

                        ThreadHelper.delay(1000);

                        Platform.setImplicitExit(false);
                        closeUpdateScreen();

                        ThreadHelper.runOnUIThread(() -> {
                            try {
                                Process proc = Runtime.getRuntime().exec("java -jar fts_update_helper.jar");
                            } catch( IOException e ){
                                e.printStackTrace();
                            }
                            Platform.exit();
                        });

                    } else {
                        // download user data
                        controller.updateStatus("Senkronizasyon yapılıyor..", "Lütfen bekleyin..");

                        ThreadHelper.runOnUIThread(() -> {
                            try {
                                // check user logged in or not
                                if( SharedConfig.DATA.has("init") ){
                                    // kill platform when main screen is closed
                                    Platform.setImplicitExit(true);
                                    closeUpdateScreen();

                                    LoginScreen loginScreen = new LoginScreen();
                                    loginScreen.start(new Stage());
                                } else {
                                    ControllerHub.UserController.remember(new ActionCallback() {
                                        @Override
                                        public void onSuccess(String... params) {
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
                                        @Override
                                        public void onError(int type) {
                                            AlertPrompt.showAndWait(Alert.AlertType.ERROR,"Hata oluştu. Kod: YETKI_HATASI", "Sistem yöneticisine hatayı bildirin." );
                                            Platform.exit();
                                        }
                                    });
                                }
                            } catch( Exception e ){
                                e.printStackTrace();
                            }
                        });
                    }
                } else {
                    ThreadHelper.runOnUIThread(() -> {
                        AlertPrompt.showAndWait(Alert.AlertType.ERROR,"Hata oluştu. Kod: CONFIG_EKSIK", "Sistem yöneticisine hatayı bildirin." );
                        Platform.exit();
                    });
                }
            });

        } catch( Exception e ){
            e.printStackTrace();
        }
    }

    private void closeUpdateScreen(){
        ThreadHelper.runOnUIThread(() -> {
            StartSplashScreen.stage.close();
        });
    }

}
