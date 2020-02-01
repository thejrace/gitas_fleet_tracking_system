/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package interfaces;

import bots.FleetDataDownloaderIETT;
import models.Bus;
import repositories.BusStatusRepository;

public interface BusFleetDataDownloadListener {

    /**
     * Called from BusController to trigger BusBox when fleet data download and process action is finished
     *
     * @param bus updated Bus model instance
     * @param busFleetDataDownloader object itself
     * @param busStatusRepository object itself
     */
    void onFinish(Bus bus, FleetDataDownloaderIETT busFleetDataDownloader, BusStatusRepository busStatusRepository);

    /**
     * Called from BusController to trigger BusBox when data download resulted in error.
     *
     * @param error error details
     */
    void onError( String error );
}
