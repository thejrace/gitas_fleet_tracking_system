package ui.popup;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class PopupLoaderContentController implements Initializable {

    @FXML private Label uiMessageLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb ){

        uiMessageLabel.setText("Lütfen bekleyin.."); // @todo language

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), uiMessageLabel);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.3);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();

    }

}
