/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.page;

public class SettingsPage extends UIPage {

    public SettingsPage(){
        loadFXML("settings_page");
        getController().setTitle("Ayarlar");
    }

    @Override
    public SettingsPageController getController(){
        return (SettingsPageController)controller;
    }

}
