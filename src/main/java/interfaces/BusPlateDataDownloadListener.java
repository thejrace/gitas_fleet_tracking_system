/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package interfaces;

import org.json.JSONObject;

public interface BusPlateDataDownloadListener {
    /**
     * Notfies the BusBox to update BusModel after plate data is downloaded
     *
     * @param data
     */
    void onFinish(JSONObject data);
}
