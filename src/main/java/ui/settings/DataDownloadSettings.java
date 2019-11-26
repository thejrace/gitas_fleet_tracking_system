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
public class DataDownloadSettings extends UIComponent implements SettingsTab{

    /**
     * {@inheritDoc}
     */
    @Override
    public void initUI(){
        loadFXML("data_download_settings");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillForms() {
        ((DataDownloadSettingsController)controller).fillForms();
    }

}
