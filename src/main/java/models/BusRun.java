/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package models;

import lombok.Data;
import org.omg.SendingContext.RunTime;
import utils.RunTimeDiff;

import java.util.ArrayList;

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
     * BusDriver ID
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

    /**
     * Shorter constructor, used in RunSuggestions
     *
     * @param busCode
     * @param routeDetails
     * @param ORER
     * @param status
     * @param statusCode
     */
    public BusRun( String busCode, String routeDetails, String ORER, String status, String statusCode ){
        this.busCode = busCode;
        this.ORER = ORER;
        this.routeDetails = routeDetails;
        this.status = status;
        this.statusCode = statusCode;
    }

    /**
     * Get as arraylist to fill fleet table without explicitly change the labels.
     *
     * @return
     */
    public ArrayList<String> getAsArrayList(){
        ArrayList<String> output = new ArrayList<>();
        output.add(String.valueOf(departureNo));
        output.add(routeDetails);
        output.add(driverCode); // @todo process it
        output.add(arrivalTime);
        output.add(ORER);
        output.add(String.valueOf(RunTimeDiff.calculate(arrivalTime, ORER)));
        output.add(alternativeORER);
        output.add(departureTime);
        output.add(estimatedEndTime);
        output.add(endTime);
        output.add(statusCode);
        output.add(String.valueOf(RunTimeDiff.calculate(departureTime, endTime)));
        output.add("100");
        return output;
    }

    /**
     * Get as arraylist for RunSuggestionItem.
     *
     * @return
     */
    public ArrayList<String> getAsArrayListRunSuggestions(){
        ArrayList<String> output = new ArrayList<>();
        output.add(busCode);
        output.add(routeDetails);
        output.add(ORER);
        output.add(status);
        output.add(statusCode);
        return output;
    }

}
