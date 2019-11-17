/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package utils;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SharedConfig { // @todo Hash the static config files

    /**
     * Version string
     */
    public static String VERSION = "1.0.0";

    /**
     * Active userId
     */
    public static int USER_ID;

    /**
     * Application config data
     */
    public static JSONObject DATA = new JSONObject();

    /**
     * Application user settings
     */
    public static JSONObject SETTINGS = new JSONObject();

    /**
     * Setup location, will be empty for release
     */
    private static String setupFolderTemp = "C://gfts/";

    /**
     * Method to read configuration resources
     *
     * @return boolean
     */
    public static boolean read(){
        if( Common.checkFile( setupFolderTemp + "app_config.json" ) ){
            try {
                DATA = new JSONObject( Common.readJSONFile(setupFolderTemp+"app_config.json") );
                SETTINGS = new JSONObject( Common.readJSONFile(setupFolderTemp+"settings.json") );

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

    /**
     * Updates static config file to contain user credentials data after login
     */
    public static void updateStaticConfigToRememberUser(){
        JSONObject oldData = new JSONObject(Common.readJSONFile(setupFolderTemp + "app_config.json"));
        if( oldData.has("init") ){
            oldData.remove("init");
            oldData.put("user_id", SharedConfig.USER_ID);
            oldData.put("api_key", APIRequest.API_TOKEN);
            Common.writeStaticData(setupFolderTemp + "app_config.json", oldData.toString());
        }
    }

    /**
     * Cache the user credentials after remember me action
     */
    public static void copyUserCredentials(){
        SharedConfig.USER_ID = DATA.getInt("user_id");
        APIRequest.API_TOKEN = DATA.getString("api_key");
    }

    /**
     * Test method to mimic config file
     *
     * @return JSONObject
     */
    private static JSONObject testConfig(){
        JSONObject config = new JSONObject();
        JSONArray apiURLS = new JSONArray();
        apiURLS.put("http://gitas_api.test/api/");
        apiURLS.put("http://gitfilo.com/api/");
        config.put("base_api", apiURLS);
        config.put("init", true); // not loggedin
        config.put("download_url", "http://gitas_api.test/storage/fts_download/GFTS.json");
        config.put("installDir", "C://gfts/");
        return config;
    }

    /**
     * Test method to mimic settings file
     * @return
     */
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
