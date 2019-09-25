package utils;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import org.json.JSONException;
import org.json.JSONObject;

public class SharedConfig {

    public static String VERSION = "1.0.1";

    public static JSONObject DATA = new JSONObject();

    public static boolean read(){
        if( Common.checkFile( "app_config.json" ) ){
            try {
                DATA = new JSONObject( Common.readJSONFile("app_config.json") );
            } catch (JSONException e ){
                e.printStackTrace();
            }
            return true;
        } else {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Gitaş FTS");
                alert.setHeaderText("Hata oluştu. Kod: CONFIG_EKSIK");
                alert.setContentText("Sistem yöneticisine hatayı bildirin.");
                ButtonType cancelBtn = new ButtonType("İptal", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(cancelBtn );
                alert.showAndWait();
            });
        }
        return false;
    }

}
