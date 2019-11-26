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
    
    /**
     * RunSuggestionsController instance
     */
    public static RunSuggestionsController RunSuggestionController;

    /**
     * Constructor
     */
    public RunSuggestionsPage(){
        loadFXML("run_suggestions_page");
        getController().setTitle("Ek Sefer Önerileri");

        RunSuggestionController = new RunSuggestionsController();
    }

    @Override
    public RunSuggestionsPageController getController(){
        return (RunSuggestionsPageController)controller;
    }
}
