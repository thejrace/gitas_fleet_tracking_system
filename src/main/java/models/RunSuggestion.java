/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
public class RunSuggestion {

    /**
     * Code of the suggested bus
     */
    @Getter
    private String busCode;

    /**
     * ORER of the cancelled run
     */
    @Getter
    private String ORER;

    /**
     * Route details of the run
     */
    @Getter
    private String routeDetails;

    public ArrayList<String> getAsArray() {
        ArrayList<String> output = new ArrayList<>();
        output.add(busCode);
        output.add(ORER);
        output.add(routeDetails);
        return output;
    }
}
