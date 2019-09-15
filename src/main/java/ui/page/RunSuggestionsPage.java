/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.page;

public class RunSuggestionsPage extends UIPage{

    public RunSuggestionsPage(){
        loadFXML("run_suggestions_page");
        getController().setTitle("Ek Sefer Önerileri");
    }

    @Override
    public RunSuggestionsPageController getController(){
        return (RunSuggestionsPageController)controller;
    }
}
