/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package repositories;

import controllers.ControllerHub;
import events.RunSuggestionsProcessFinishedEvent;
import events.RunSuggestionsStatusUpdateEvent;
import lombok.Getter;
import models.Bus;
import models.BusRun;
import models.RunSuggestion;
import utils.GitasEventBus;
import utils.RunTimeDiff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RunSuggestionsRepository {

    @Getter
    private Map<BusRun, ArrayList<RunSuggestion>> suggestions;

    /**
     * Loops through cancelled runs and mark the ones which our buses can complete
     *
     * @param cancelledRuns
     */
    public void processData(ArrayList<BusRun> cancelledRuns){
        suggestions = new HashMap<>();

        ArrayList<Bus> fleet = ControllerHub.FleetController.getBuses();

        GitasEventBus.post(new RunSuggestionsStatusUpdateEvent("Veriler işleniyor.."));
        for( BusRun cancelledRun : cancelledRuns ){
            // for each run we check if our fleet can make it
            for( Bus bus : fleet ){
                // we also check if cancelled runs ORER is 1 hour later than our buses last ORER
                if( !RunTimeDiff.isPast( bus.getLastORER(), cancelledRun.getORER() ) && RunTimeDiff.calculate( bus.getLastORER(), cancelledRun.getORER() ) > 60 ){
                    if( !suggestions.containsKey(cancelledRun)){
                        suggestions.put(cancelledRun,new ArrayList<>());
                    }
                    suggestions.get(cancelledRun).add(new RunSuggestion(
                        bus.getCode(),
                        bus.getLastRouteDetails(),
                        bus.getLastORER()
                    ));
                }
            }
        }
        GitasEventBus.post(new RunSuggestionsProcessFinishedEvent(suggestions));
    }
}
