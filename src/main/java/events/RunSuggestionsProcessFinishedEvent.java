/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import models.RunSuggestion;

import java.util.ArrayList;

@AllArgsConstructor
public class RunSuggestionsProcessFinishedEvent {

    @Getter
    private ArrayList<RunSuggestion> suggestions;

}
