/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package enums;

public class BusRunStatusStyleClass {

    /**
     * Return the style class name according to the status
     *
     * @param status
     * @return
     */
    public static String get(String status){
        try {
            switch(status){
                case BusRunStatus.A:
                    return "green";
                case BusRunStatus.B:
                    return "white";
                case BusRunStatus.T:
                    return "gray";
                case BusRunStatus.I:
                    return "red";
                case BusRunStatus.Y:
                    return "pink";
                default:
                    return "default";
            }
        } catch( NullPointerException e ){
            return "default";
        }
    }
}
