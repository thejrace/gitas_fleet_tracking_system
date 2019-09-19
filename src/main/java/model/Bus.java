/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package model;

public class Bus {

    private String code;
    private String officialPlate;
    private String activePlate;

    public Bus(String code, String officialPlate, String activePlate ){
        this.code = code;
        this.officialPlate = officialPlate;
        this.activePlate = activePlate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOfficialPlate() {
        return officialPlate;
    }

    public void setOfficialPlate(String officialPlate) {
        this.officialPlate = officialPlate;
    }

    public String getActivePlate() {
        return activePlate;
    }

    public void setActivePlate(String activePlate) {
        this.activePlate = activePlate;
    }

}
