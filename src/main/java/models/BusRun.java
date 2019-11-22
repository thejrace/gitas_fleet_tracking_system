/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package models;

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

    /**
     * Getter for ORER
     *
     * @return
     */
    public String getORER() {
        return ORER;
    }

    /**
     * Setter for ORER
     *
     * @param ORER
     */
    public void setORER(String ORER) {
        this.ORER = ORER;
    }

    /**
     * Getter for arrivalTime
     *
     * @return
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Setter for arrivalTime
     * @param arrivalTime
     */
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Getter for estimatedEndTime
     *
     * @return
     */
    public String getEstimatedEndTime() {
        return estimatedEndTime;
    }

    /**
     * Setter estimatedEndTime
     *
     * @param estimatedEndTime
     */
    public void setEstimatedEndTime(String estimatedEndTime) {
        this.estimatedEndTime = estimatedEndTime;
    }

    /**
     * Getter for endTime
     *
     * @return
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Setter for endTime
     *
     * @param endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Getter for direction
     *
     * @return
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Setter for direction
     *
     * @param direction new direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Getter for departureNo
     *
     * @return
     */
    public int getDepartureNo() {
        return departureNo;
    }

    /**
     * Setter for departureNo
     *
     * @param departureNo new data
     */
    public void setDepartureNo(int departureNo) {
        this.departureNo = departureNo;
    }

    /**
     * Getter for status
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter for status
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter for busCode
     *
     * @return
     */
    public String getBusCode() {
        return busCode;
    }

    /**
     * Setter for busCode
     *
     * @param busCode new data
     */
    public void setBusCode(String busCode) {
        this.busCode = busCode;
    }

    /**
     * Getter for currentStop
     *
     * @return
     */
    public String getCurrentStop() {
        return currentStop;
    }

    /**
     * Setter for currentStop
     *
     * @param currentStop new data
     */
    public void setCurrentStop(String currentStop) {
        this.currentStop = currentStop;
    }

    /**
     * Getter for departureTime
     *
     * @return
     */
    public String getDepartureTime() {
        return departureTime;
    }

    /**
     * Setter for departureTime
     *
     * @param departureTime new data
     */
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * Getter for routeDetails
     *
     * @return
     */
    public String getRouteDetails() {
        return routeDetails;
    }

    /**
     * Setter for routeDetails
     *
     * @param routeDetails new data
     */
    public void setRouteDetails(String routeDetails) {
        this.routeDetails = routeDetails;
    }

    /**
     * Getter for statusCode
     *
     * @return
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Setter for statusCode
     *
     * @param statusCode new data
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Getter for route
     *
     * @return
     */
    public String getRoute() {
        return route;
    }

    /**
     * Setter for route
     *
     * @param route new data
     */
    public void setRoute(String route) {
        this.route = route;
    }

    /**
     * Getter for service code
     *
     * @return
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * Setter for service code
     *
     * @param serviceCode
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * Getter for alternativeOrer
     *
     * @return
     */
    public String getAlternativeORER() {
        return alternativeORER;
    }

    /**
     * Setter for alternativeOrer
     *
     * @return
     */
    public void setAlternativeORER(String alternativeORER) {
        this.alternativeORER = alternativeORER;
    }

    /**
     * Getter for driverCode
     *
     * @return
     */
    public String getDriverCode() {
        return driverCode;
    }

    /**
     * Setter for driverCode
     *
     * @param driverCode
     */
    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }
}
