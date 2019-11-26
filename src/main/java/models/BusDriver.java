/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BusDriver {

    /**
     * Code of the driver
     */
    private String code;


    /**
     * Name of the driver
     */
    private String name;

    /**
     * Phone number
     */
    private String phone;

    /**
     * TC No of the driver
     */
    private String tcNo;

    /**
     * IMG URL of the driver
     */
    private String imgURL;

}
