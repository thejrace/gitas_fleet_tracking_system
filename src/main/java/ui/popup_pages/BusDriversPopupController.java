/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.popup_pages;

import com.google.common.eventbus.Subscribe;
import events.bus_box.BusDriversDataDownloadFailedEvent;
import events.bus_box.BusDriversDataDownloadFinishedEvent;
import interfaces.Subscriber;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import ui.component.DriverBox;
import utils.GitasEventBus;
import utils.ThreadHelper;

import java.net.URL;
import java.util.ResourceBundle;

public class BusDriversPopupController extends BusBoxPopupPageController implements Initializable, Subscriber {

    @FXML
    private VBox uiContainer;

    @FXML
    private ImageView uiLoader;

    @FXML
    private Label uiNotfLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GitasEventBus.register(this);
    }

    @Subscribe
    private void subscribeBusDriversDataDownloadFinishedEvent(BusDriversDataDownloadFinishedEvent event){
        if( !event.getBusData().getCode().equals(bus.getCode()) ) return;
        ThreadHelper.runOnUIThread(() -> {
            uiContainer.getChildren().remove(uiLoader);

        });

        int dataLength = event.getData().size();
        if( dataLength > 0 ){
            ThreadHelper.runOnUIThread( () -> {
                uiContainer.getChildren().remove(uiNotfLabel);
            });

            for( int k = 0; k < dataLength; k++ ){
                DriverBox driverBox = new DriverBox();
                driverBox.initUI();
                driverBox.setData(event.getData().get(k));

                ThreadHelper.runOnUIThread(() -> {
                    uiContainer.getChildren().add(driverBox.getUI());
                });
            }
        } else {
            ThreadHelper.runOnUIThread(() -> {
                uiNotfLabel.setText("Otobüsün PDKS verisi yok!");
            });
        }
    }

    @Subscribe
    private void subscribeBusDriverDataDownloadFailedEvent(BusDriversDataDownloadFailedEvent event){
        if( !event.getBusData().getCode().equals(bus.getCode()) ) return;
        ThreadHelper.runOnUIThread(() -> {
            uiContainer.getChildren().remove(uiLoader);
            uiNotfLabel.setText("Veri alınamadı veya otobüsün PDKS bilgisi yok. Bu pencereyi kapatıp, tekrar deneyin.");
        });
    }

    public void updateUI() {
        initCommonEvents();
    }
}
