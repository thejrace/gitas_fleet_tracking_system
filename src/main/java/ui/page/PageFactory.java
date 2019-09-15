package ui.page;

public class PageFactory {

    public static UIPage getPage( String className ){
        String page;
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
