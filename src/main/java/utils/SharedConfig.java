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

    public static int USER_ID = 1;

    public static JSONObject DATA = new JSONObject();
    public static JSONObject SETTINGS = new JSONObject();

    public static boolean read(){
        if( /*Common.checkFile( "app_config.json" )*/ true ){
            try {
                //DATA = new JSONObject( Common.readJSONFile("app_config.json") );
                //SETTINGS = new JSONObject( Common.readJSONFile("settings.json") );
                DATA = testConfig();
                SETTINGS = testSettings();

                // @todo ffix
                APIRequest.API_URL = DATA.getJSONArray("base_api").getString(0);
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

    private static JSONObject testSettings(){
        JSONObject config = new JSONObject();
        config.put("data_source", 1);
        config.put("data_download_frequency", "");
        config.put("alert_frequency", "");
        config.put("alert_visible_delay", "");
        config.put("bus_box_template", 0);
        config.put("alert_filters", "");
        config.put("filters", "");

        return config;
    }

}
