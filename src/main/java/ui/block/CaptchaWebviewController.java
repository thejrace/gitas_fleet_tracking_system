package ui.block;

import cookie_agent.CookieAgent;
import interfaces.ActionCallback;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.jsoup.Jsoup;
import utils.ThreadHelper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class CaptchaWebviewController implements Initializable {

    /**
     * Webview container
     */
    @FXML HBox uiWebviewContainer;

    /**
     * Notf label
     */
    @FXML Label uiNotfLabel;

    /**
     * Captcha webview
     */
    private WebView webView;

    /**
     * Request url.
     */
    private URL url;

    /**
     * Webview init flag to detect captcha errors.
     */
    private boolean wwInited = false;

    /**
     * Observer for Cookie Agent
     */
    private ActionCallback listener;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getSessionId();
    }

    /**
     * Setter for listener
     *
     * @param callback
     */
    public void setListener(ActionCallback callback ){
        this.listener = callback;
    }

    /**
     * Gets  a session cookie from fleet.
     */
    private void getSessionId(){
        loginThread();
    }

    /**
     * Show the given message.
     *
     * @param label
     */
    public void setNotf( String label ){
        Platform.runLater(() -> {
            uiNotfLabel.setText(label);
        });
    }

    /**
     * Initialize web view
     */
    private void initWebView(){
        try {
            url = new URL("https://filotakip.iett.gov.tr/login.php");
        } catch( MalformedURLException e ){
            e.printStackTrace();
        }
        webView = new WebView();
        webView.setPrefWidth(600);
        URI uri = null;
        try {
            uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
        } catch( URISyntaxException | NullPointerException e ){
            e.printStackTrace();
        }
        Map<String, List<String>> headers = new LinkedHashMap<>();
        headers.put("Set-Cookie", Arrays.asList("PHPSESSID="+ CookieAgent.FILO5_COOKIE));
        try {
            java.net.CookieHandler.getDefault().put(uri, headers);
        } catch( IOException e ){
            e.printStackTrace();
        }

        WebEngine we = webView.getEngine();
        try {
            URL url = new URL("https://filotakip.iett.gov.tr/login.php");
            we.setJavaScriptEnabled(true);
            we.getLoadWorker().stateProperty().addListener(
                    (ObservableValue<? extends Worker.State> observable,
                     Worker.State oldValue,
                     Worker.State newValue) -> {
                        if (newValue != Worker.State.SUCCEEDED) {
                            return;
                        }

                        try {
                            we.executeScript(" "+
                                    " function hide( elem ){ if( elem != undefined ) elem.style.display = \"none\"; } "+
                                    " var link = document.getElementsByTagName(\"a\"); link[1].style.display = \"block\"; link[1].style.position = \"relative\"; link[1].style.color= \"#fff\"; link[1].style.left = \"260px\"; link[1].style.top = \"-70px\"; link[1].style.fontFamily = \"Tahoma\"; link[1].innerHTML = \"Kodu Değiştir\";" +
                                    " link[1].style.textDecoration = \"none\"; link[1].style.fontSize = \"11\";  link[1].style.fontWeight = \"bold\";  "+
                                    " document.body.style.backgroundColor = \"#302e2e\"; document.body.style.overflowY = \"hidden\";" +
                                    " var loginBody = document.querySelectorAll(\".login-box-body\"); loginBody[0].style.paddingTop = \"0px\"; loginBody[0].style.backgroundColor = \"#302e2e\"; " +
                                    " var logo = document.querySelectorAll(\".login-logo\"); hide(logo[0]); var infoText = document.querySelectorAll(\".login-box-msg\"); hide(infoText[0]); " +
                                    " var form_login = document.querySelectorAll('[name=\"login\"]');" +
                                    " var form_pass = document.querySelectorAll('[name=\"password\"]');" +
                                    " if( form_login[0] != undefined ) hide(form_login[0]); form_login[0].value=\""+CookieAgent.LOGIN+"\"; if( form_pass[0] != undefined ) hide(form_pass[0]); form_pass[0].value=\""+CookieAgent.PASS+"\"; "+
                                    " var cin = document.querySelectorAll('[name=\"captcha\"]'); cin[0].style.width = \"60px\"; cin[0].style.height = \"20px\"; cin[0].style.position = \"relative\"; cin[0].style.top = \"0px\"; cin[0].style.left = \"260px\";" +
                                    " var cimg = document.getElementById(\"captcha\"); cimg.style.position = \"relative\"; cimg.style.left = \"50px\"; cimg.style.top = \"-20px\";  " +
                                    " var submitbtn = document.querySelectorAll('[type=\"submit\"]'); submitbtn[0].style.width = \"40px\"; submitbtn[0].style.marginTop = \"-60px\"; submitbtn[0].style.marginLeft = \"260px\";" +
                                    " submitbtn[0].style.backgroundColor = \"#7b3275\"; submitbtn[0].style.color = \"#d1d1d1\"; submitbtn[0].style.fontWeight = \"bold\";  submitbtn[0].style.border = \"none\"; " +
                                    " submitbtn[0].style.padding = \"6px 10px 6px 10px\"; submitbtn[0].style.borderRadius = \"3px\"; submitbtn[0].style.fontSize = \"11px\"; submitbtn[0].style.cursor = \"pointer\"; "

                            );
                        } catch ( netscape.javascript.JSException e  ){
                            //listener.on_refresh();
                        }

                        if( wwInited ){
                            try {
                                // throw js exception if there is not captcha image = success
                                we.executeScript( " var fs = document.getElementById(\"captcha\");" +
                                        " fs[0].style.opacity = 0.3; ");
                                listener.onSuccess();
                            } catch( netscape.javascript.JSException e ) {
                                listener.onError(0);
                            }
                        }
                        if( !wwInited ){
                            Platform.runLater(() -> {
                                uiWebviewContainer.getChildren().add(0, webView);
                                wwInited = true;
                            });
                        }
                    });
            we.load(url.toString());
        } catch( MalformedURLException e ){
            e.printStackTrace();
        }
    }

    /**
     * Make a request to the fleet.
     */
    private void loginThread(){
        ThreadHelper.func(() -> {
            org.jsoup.Connection.Response res;
            try{
                System.out.println( "Filo phpsessid alınıyor..");
                res = Jsoup.connect("https://filotakip.iett.gov.tr/login.php")
                        .method(org.jsoup.Connection.Method.POST)
                        .timeout(0)
                        .execute();

                CookieAgent.FILO5_COOKIE = res.cookies().get("PHPSESSID");
                System.out.println( " phpsessid alındı. -c["+res.cookies().get("PHPSESSID")+"]");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        initWebView();
                    }
                });
            } catch( IOException e ){
                e.printStackTrace();
                System.out.println( " HATA");
            }
        });
    }
}
