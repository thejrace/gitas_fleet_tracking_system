/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.page;

import controllers.RunSuggestionsController;

public class RunSuggestionsPage extends UIPage{

    public static RunSuggestionsController RunSuggestionController;

    public RunSuggestionsPage(){
        loadFXML("run_suggestions_page");
        getController().setTitle("Ek Sefer Önerileri");

        initialize();
    }

    private void initialize() {
        RunSuggestionController = new RunSuggestionsController();

    }

    @Override
    public RunSuggestionsPageController getController(){
        return (RunSuggestionsPageController)controller;
    }
}
