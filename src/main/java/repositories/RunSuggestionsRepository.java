/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package repositories;

import lombok.Getter;
import models.BusRun;
import models.RunSuggestion;

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



    }

}
