/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package controllers;

import bots.RouteDataDownloader;
import bots.RunSuggestionsDataDownloader;
import com.google.common.eventbus.Subscribe;
import events.RunSuggestionsTriggerEvent;
import interfaces.Subscriber;
import repositories.RunSuggestionsRepository;
import utils.GitasEventBus;
import utils.ThreadHelper;

public class RunSuggestionsController implements Subscriber {

    /**
     * RunSuggestionsDataDownloader instance
     */
    private RunSuggestionsDataDownloader fleetRunSuggestionsDataDownloader;

    /**
     * RunSuggestionsRepository instance
     */
    private RunSuggestionsRepository runSuggestionsRepository;

    /**
     * RouteDataDownloader instance
     */
    private RouteDataDownloader routeDataDownloader;

    /**
     * Constructor
     */
    public RunSuggestionsController(){
        GitasEventBus.register(this);
        fleetRunSuggestionsDataDownloader = new RunSuggestionsDataDownloader();
        runSuggestionsRepository = new RunSuggestionsRepository();
        routeDataDownloader = new RouteDataDownloader();
    }

    /**
     * Download and process suggestions
     */
    public void downloadSuggestions(){
        ThreadHelper.func(() -> {
            // first download route list
            routeDataDownloader.action();
            fleetRunSuggestionsDataDownloader.setRoutes(routeDataDownloader.getData());
            // download suggestions
            fleetRunSuggestionsDataDownloader.action();

            // wait all downloaders to finish
            while( !fleetRunSuggestionsDataDownloader.ready() ){
                ThreadHelper.delay(100);
            }

            // process data
            runSuggestionsRepository.processData(fleetRunSuggestionsDataDownloader.getData());

            // post event to the bus


        });
    }

    @Subscribe
    private void subscribeDownloadSuggestionTriggerEvent(RunSuggestionsTriggerEvent event){
        downloadSuggestions();
    }

}
