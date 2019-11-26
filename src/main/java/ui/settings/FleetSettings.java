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
public class FleetSettings extends UIComponent implements SettingsTab{

    /**
     * {@inheritDoc}
     */
    @Override
    public void initUI(){
        loadFXML("fleet_settings");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillForms() {
        ((FleetSettingsController)controller).fillForms();
    }

}
