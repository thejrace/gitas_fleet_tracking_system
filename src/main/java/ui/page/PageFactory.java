/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.page;

public class PageFactory {
    /**
     * Return the page object with given class name.
     *
     * @param className class name of the page.
     *
     * @return Page class.
     */
    public static UIPage getPage( String className ){
        switch( className ){
            case "FleetPage":
                return new FleetPage();
            case "SettingsPage":
                return new SettingsPage();
            case "ReportsPage":
                return new ReportsPage();
            case "RunSuggestionsPage":
                return new RunSuggestionsPage();
            default:
                return null;
        }
    }
}
