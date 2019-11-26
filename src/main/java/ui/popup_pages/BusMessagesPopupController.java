/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.popup_pages;

import com.google.common.eventbus.Subscribe;
import events.bus_box.BusMessagesDataDownloadFailedEvent;
import events.bus_box.BusMessagesDataDownloadFinishedEvent;
import interfaces.Subscriber;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import models.BusDriver;
import models.BusMessage;
import ui.component.MessageBalloon;
import utils.GitasEventBus;
import utils.RunTimeDiff;
import utils.ThreadHelper;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class BusMessagesPopupController extends BusBoxPopupPageController implements Initializable, Subscriber {

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

    public void updateUI() {
        initCommonEvents();
    }

    @Subscribe
    private void subscribeMessageDataDownloadFinishedEvent(BusMessagesDataDownloadFinishedEvent event){
        if( !event.getBusData().getCode().equals(bus.getCode()) ) return;
        ThreadHelper.runOnUIThread(() -> {
            uiContainer.getChildren().remove(uiLoader);

        });
        ArrayList<BusMessage> data = event.getData();
        int dataLength = data.size();
        if( dataLength > 0 ){
            ThreadHelper.runOnUIThread( () -> {
                uiContainer.getChildren().remove(uiNotfLabel);
            });

            // order messages according to timestamps

            // Sorting
            Collections.sort(data, new Comparator<BusMessage>() {
                @Override
                public int compare(BusMessage message1, BusMessage message2) {
                    boolean isPast = RunTimeDiff.isPast(message1.getTimestamp(), message2.getTimestamp());
                    if( isPast ){
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });

            for( int k = 0; k < dataLength; k++ ){
                MessageBalloon messageBalloon = new MessageBalloon();
                messageBalloon.initUI();
                messageBalloon.setData(data.get(k));

                ThreadHelper.runOnUIThread(() -> {
                    uiContainer.getChildren().add(messageBalloon.getUI());
                });
            }
        } else {
            ThreadHelper.runOnUIThread(() -> {
                uiNotfLabel.setText("Otobüsün Mesaj verisi yok!");
            });
        }

    }

    @Subscribe
    private void subscribeMessageDataDownloadFailedEvent( BusMessagesDataDownloadFailedEvent event ){
        if( !event.getBusData().getCode().equals(bus.getCode()) ) return;
        ThreadHelper.runOnUIThread(() -> {
            uiContainer.getChildren().remove(uiLoader);
            uiNotfLabel.setText("Veri alınamadı veya otobüsün Mesaj bilgisi yok. Bu pencereyi kapatıp, tekrar deneyin.");
        });
    }

}
