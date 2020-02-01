/*
 *  Gitas Fleet Tracking System 2020
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package bots;

import enums.DataSourceSettings;
import utils.SharedConfig;

public class FleetDataDownloaderFactory {
    public FleetDataDownloader getSource(String busCode){
        int source = SharedConfig.SETTINGS.getInt("data_source");

        if( source == DataSourceSettings.FLEET.ordinal() ){
            return new FleetDataDownloaderIETT(busCode);
        } else {
            return new FleetDataDownloaderServer(busCode);
        }
    }
}
