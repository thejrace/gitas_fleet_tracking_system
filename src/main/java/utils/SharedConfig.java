package utils;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SharedConfig {

    public static String VERSION = "1.0.13";

    public static JSONObject DATA = new JSONObject();

    public static boolean read(){
        if( /*Common.checkFile( "app_config.json" )*/ true ){
            try {
                //DATA = new JSONObject( Common.readJSONFile("app_config.json") );
                DATA = testConfig();
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

    private static JSONObject testConfig(){
        JSONObject config = new JSONObject();
        JSONArray apiURLS = new JSONArray();
        apiURLS.put("http://gitas_api.test/api/");
        apiURLS.put("http://gitfilo.com/api/");
        config.put("base_api", apiURLS);

        config.put("download_url", "http://gitas_api.test/storage/fts_download/GFTS.json");
        config.put("installDir", "C://gfts/");

        return config;
    }

}
