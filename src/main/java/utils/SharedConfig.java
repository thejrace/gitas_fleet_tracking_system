/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class SharedConfig { // @todo Hash the static config files

    /**
     * Version string
     */
    public static String VERSION = "1.0.0";

    /**
     * Application config data
     */
    public static JSONObject DATA = new JSONObject();

    /**
     * Application user settings
     */
    public static JSONObject SETTINGS = new JSONObject();

    /**
     * Setup location, will be empty for release ( configs and executable will be in the same folder )
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
        }
        return false;
    }

    /**
     * Updates static config file to contain user credentials data after login
     */
    public static void updateStaticConfigToRememberUser(String apiToken){
        JSONObject oldData = new JSONObject(Common.readJSONFile(setupFolderTemp + "app_config.json"));
        if( oldData.has("init") ){
            oldData.remove("init");
            oldData.put("api_key", apiToken);
            Common.writeStaticData(setupFolderTemp + "app_config.json", oldData.toString());
        }
    }

    /**
     * Get static config structure from API and update local config files and than read it.
     */
    public static boolean readAndUpdateStaticConfigStructure(){
        if( Common.checkFile( setupFolderTemp + "app_config.json" ) && Common.checkFile(setupFolderTemp + "settings.json") ){
            try {
                // @todo data contains base api urls, so how to validate it's structure?
                DATA = new JSONObject( Common.readJSONFile(setupFolderTemp+"app_config.json") );
                APIRequest.API_URL = DATA.getJSONArray("base_api").getString(0);

                JSONObject settingsData = new JSONObject( Common.readJSONFile(setupFolderTemp+"settings.json") );
                JSONObject updatedSettingsData = new JSONObject(APIRequest.GET(APIRequest.API_URL+"fts/setup")).getJSONObject("settings");

                // compare settings.json
                Iterator<String> keys = updatedSettingsData.keys();
                while(keys.hasNext()) {
                    String key = keys.next();
                    if( !settingsData.has(key) ){
                        settingsData.put(key, updatedSettingsData.get(key));
                    }
                }
                // update static file
                Common.writeStaticData(setupFolderTemp + "settings.json", settingsData.toString());
                // cache new file
                SETTINGS = settingsData;

                System.out.println(SharedConfig.SETTINGS);
            } catch( JSONException e ) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * Updates the static config file after logout
     */
    public static void resetStaticConfigToForgetUser(){
        JSONObject oldData = new JSONObject(Common.readJSONFile(setupFolderTemp + "app_config.json"));
        oldData.remove("api_key");
        oldData.put("init", true);
        Common.writeStaticData(setupFolderTemp + "app_config.json", oldData.toString());
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
