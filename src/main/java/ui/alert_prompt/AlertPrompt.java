/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.alert_prompt;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class AlertPrompt {

    /**
     * Show error alert
     *
     * @param header
     * @param context
     */
    public static void showAndWait(Alert.AlertType alertType, String header, String context ){
        Alert alert = new Alert(alertType);
        alert.setTitle("Gitaş FTS");
        alert.setHeaderText(header);
        alert.setContentText(context);
        ButtonType cancelBtn = new ButtonType("İptal", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(cancelBtn );
        alert.showAndWait();
    }

}
