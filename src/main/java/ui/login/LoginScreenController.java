package ui.login;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import interfaces.ActionCallback;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui.MainScreen;
import utils.LoginAttempt;
import utils.ThreadHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {

    @FXML
    private TextField uiEmailInput;

    @FXML
    private TextField uiPassInput;

    @FXML
    private Button uiActionBtn;

    @FXML
    private FontAwesomeIconView uiCloseBtn;

    @FXML
    private Label uiErrorNotf;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        uiCloseBtn.setOnMouseClicked( ev -> {
            uiCloseBtn.getScene().getWindow().hide();
        });

        uiActionBtn.setOnMouseClicked( ev -> {
            LoginAttempt loginAttempt = new LoginAttempt(uiEmailInput.getText(), uiPassInput.getText());
            loginAttempt.commit(new ActionCallback() {
                @Override
                public void onSuccess(String... params) {
                    // open main screen
                    ThreadHelper.runOnUIThread(() -> {
                        try {
                            System.out.println("SUCCESS!!!");

                            MainScreen mainScreen = new MainScreen();
                            mainScreen.start( new Stage() );
                        } catch( Exception e ){
                            e.printStackTrace();
                        }
                    });
                }
                @Override
                public void onError(int type) {
                    ThreadHelper.runOnUIThread(() -> {
                        uiErrorNotf.setText("Hata oluştu. ["+type+"]");
                    });
                }
            });
        });
    }
}
