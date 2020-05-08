/*
 *  Gitas Fleet Tracking System Setup 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package interfaces;

public interface ActionCallback {

    // https://stackoverflow.com/questions/3158730/java-3-dots-in-parameters
    void onSuccess( String... params  );

    void onError( int type );

}
