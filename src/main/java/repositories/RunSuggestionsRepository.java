/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package repositories;

import controllers.ControllerHub;
import lombok.Getter;
import models.Bus;
import models.BusRun;
import models.RunSuggestion;
import utils.RunTimeDiff;

import java.util.ArrayList;

public class RunSuggestionsRepository {

    @Getter
    private ArrayList<RunSuggestion> suggestions;

    /**
     * Loops through cancelled runs and mark the ones which our buses can complete
     *
     * @param cancelledRuns
     */
    public void processData(ArrayList<BusRun> cancelledRuns){
        suggestions = new ArrayList<>();

        ArrayList<Bus> fleet = ControllerHub.FleetController.getBuses();

        System.out.println("Suggestion data processing started");
        for( BusRun cancelledRun : cancelledRuns ){
            // for each run we check if our fleet can make it
            for( Bus bus : fleet ){
                // we also check if cancelled runs ORER is 1 hour later than our buses last ORER
                if( !RunTimeDiff.isPast( bus.getLastORER(), cancelledRun.getORER() ) && RunTimeDiff.calculate( bus.getLastORER(), cancelledRun.getORER() ) > 60 ){
                    suggestions.add(new RunSuggestion(
                        cancelledRun.getBusCode(),
                        bus.getCode(),
                        cancelledRun.getORER(),
                        bus.getLastORER(),
                        cancelledRun.getRouteDetails(),
                        cancelledRun.getStatus(),
                        cancelledRun.getStatusCode()
                    ));
                    System.out.println(
                            cancelledRun.getBusCode()  + " @ " + cancelledRun.getORER() + " [ "+cancelledRun.getStatus() + " - " + cancelledRun.getStatusCode() +" ] "+
                            "replace it with " + bus.getCode() + " @ " + bus.getLastORER()
                    );
                }
            }
        }
    }
}
