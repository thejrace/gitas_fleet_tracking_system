/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package ui.component;

import lombok.NoArgsConstructor;
import models.BusMessage;
import ui.UIComponent;

@NoArgsConstructor
public class MessageBalloon extends UIComponent {

    /**
     * Initialize the UI
     */
    public void initUI(){
        loadFXML("message_balloon");
    }

    /**
     * Setter for data
     *
     * @param busMessage
     */
    public void setData(BusMessage busMessage){
        getController().setData(busMessage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MessageBalloonController getController(){
        return (MessageBalloonController)controller;
    }

}
