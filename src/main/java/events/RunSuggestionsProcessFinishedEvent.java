/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package events;

import interfaces.Postable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import models.BusRun;
import models.RunSuggestion;

import java.util.ArrayList;
import java.util.Map;

@AllArgsConstructor
public class RunSuggestionsProcessFinishedEvent implements Postable {

    @Getter
    private Map<BusRun, ArrayList<RunSuggestion>> suggestions;

}
