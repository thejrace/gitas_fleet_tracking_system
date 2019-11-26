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
public class Route {

    /**
     * Code of the route
     */
    private String code;

    /**
     * Name of the route
     */
    private String name;

    /**
     * URL safe code
     */
    private String urlSafeCode;

}
