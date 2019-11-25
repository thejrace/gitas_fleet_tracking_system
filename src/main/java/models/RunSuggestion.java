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
     * Code of the bus we'll replace
     */
    private String actualBusCode;

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
     * Last ORER data of our bus
     */
    @Getter
    private String lastORER;

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
