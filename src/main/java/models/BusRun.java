/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package models;

import lombok.Data;

@Data
public class BusRun {

    /**
     * Bus code
     */
    private String busCode;

    /**
     * Current stop name
     */
    private String currentStop;

    /**
     * Service code of the run
     */
    private String serviceCode;

    /**
     * ORER
     */
    private String ORER;

    /**
     * Deparure time
     */
    private String departureTime;

    /**
     * Time of arrival
     */
    private String arrivalTime;

    /**
     *  Alternative orer ( amir )
     */
    private String alternativeORER;

    /**
     * Estimated end time of the run
     */
    private String estimatedEndTime;

    /**
     * End time of the run
     */
    private String endTime;

    /**
     * Route details string
     */
    private String routeDetails;

    /**
     * Status string
     */
    private String status;

    /**
     * Code of the route
     */
    private String route;

    /**
     * Status code string
     */
    private String statusCode;

    /**
     * Driver ID
     */
    private String driverCode;

    /**
     * Run no
     */
    private int departureNo;

    /**
     * Run direction
     */
    private int direction;

    /**
     * Full constructor
     *
     * @param busCode
     * @param route
     * @param departureNo
     * @param serviceCode
     * @param currentStop
     * @param arrivalTime
     * @param ORER
     * @param alternativeORER
     * @param departureTime
     * @param estimatedEndTime
     * @param endTime
     * @param routeDetails
     * @param status
     * @param statusCode
     */
    public BusRun( String busCode, String route, String driverCode, int departureNo, String serviceCode, String currentStop, String arrivalTime, String ORER, String alternativeORER, String departureTime, String estimatedEndTime, String endTime, String routeDetails, String status, String statusCode ){
        this.busCode = busCode;
        this.route = route;
        this.driverCode = driverCode;
        this.departureNo = departureNo;
        this.serviceCode = serviceCode;
        this.currentStop = currentStop;
        this.arrivalTime = arrivalTime;
        this.ORER = ORER;
        this.alternativeORER = alternativeORER;
        this.departureTime = departureTime;
        this.estimatedEndTime = estimatedEndTime;
        this.endTime = endTime;
        this.routeDetails = routeDetails;
        this.status = status;
        this.statusCode = statusCode;
    }
}
