/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.settings;

import lombok.NoArgsConstructor;
import ui.UIComponent;

@NoArgsConstructor
public class AlarmSettings extends UIComponent implements SettingsTab {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initUI(){
        loadFXML("alarm_settings");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillForms() {
        ((AlarmSettingsController)controller).fillForms();
    }
}
