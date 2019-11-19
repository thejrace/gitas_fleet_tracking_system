/*
 *  Kahya - Gitas 2019
 *
 *  Contributors:
 *      Ahmet Ziya Kanbur 2019-
 *
 * */
package utils;

import controllers.ControllerHub;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import java.io.IOException;

public class APIRequest {

    /**
     * Main API URL
     */
    public static String API_URL;

    /**
     * Sends PUT request
     *
     * @param url request url
     * @param data data to be sent
     *
     */
    public static String PUT( String url, String data ){
        try {
            Connection.Response response = Jsoup.connect(url)
                    .method(Connection.Method.PUT)
                    .data("data", data)
                    .header("Authorization", "Bearer " + ControllerHub.UserController.getModel().getApiToken())
                    .header("Accept", "application/json")
                    .ignoreContentType(true)
                    .execute();

            return response.parse().text();
        } catch (HttpStatusException e) {
            e.printStackTrace();
            System.out.println("sendDataToAPI !!!!check API Token!!!!");
        } catch( IOException e ) {
            System.out.println("sendDataToAPI error!");
            e.printStackTrace();
        }
        return "{}";
    }

    /**
     * Sends GET request
     *
     * @param url request url
     */
    public static String GET( String url ){
        try {
            Connection connection = Jsoup.connect(url)
                    .method(Connection.Method.GET);
            try {
                connection.header("Authorization", "Bearer " + ControllerHub.UserController.getModel().getApiToken());
            } catch( NullPointerException e ){

            }
            connection.header("Accept", "application/json")
                    .ignoreContentType(true)
                    .execute();

            return connection.execute().parse().text();
        } catch (HttpStatusException e) {
            e.printStackTrace();
            System.out.println("sendDataToAPI !!!!check API Token!!!!");
        } catch( IOException e ) {
            System.out.println("sendDataToAPI error!");
            e.printStackTrace();
        }
        return "{}";
    }

    /**
     * Sends POST request
     *
     * @param url request url
     * @param data data to be sent
     */
    public static String POST( String url, String data ){
        try {
            Connection connection = Jsoup.connect(url)
                    .method(Connection.Method.POST);

            try {
                connection.header("Authorization", "Bearer " + ControllerHub.UserController.getModel().getApiToken());
            } catch( NullPointerException e ){

            }
            connection.header("Accept", "application/json")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .data("data", data)
                    .ignoreContentType(true);

            return connection.execute().parse().text();
        } catch (HttpStatusException e) {
            e.printStackTrace();
            System.out.println("sendDataToAPI !!!!check API Token!!!!");
        } catch( IOException e ) {
            System.out.println("sendDataToAPI error!");
            e.printStackTrace();
        }
        return "{}";
    }
}
