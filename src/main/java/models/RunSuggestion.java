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

@AllArgsConstructor
public class RunSuggestion {

    /**
     * Code of the suggested bus
     */
    @Getter
    private String busCode;

    /**
     * ORER data
     */
    @Getter
    private String ORER;

    /**
     * Route details of the run
     */
    @Getter
    private String routeDetails;

    /**
     * Status of the run
     */
    @Getter
    private String status;

    /**
     * Status code of the run
     */
    @Getter
    private String statusCode;

}
