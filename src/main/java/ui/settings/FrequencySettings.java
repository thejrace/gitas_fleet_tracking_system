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
public class FrequencySettings extends UIComponent implements SettingsTab {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initUI(){
        loadFXML("frequency_settings");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillForms() {
        ((FrequencySettingsController)controller).fillForms();
    }

}
