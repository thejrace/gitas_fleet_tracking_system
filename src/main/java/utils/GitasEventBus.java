/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package utils;

import com.google.common.eventbus.EventBus;
import interfaces.Postable;
import interfaces.Subscriber;
import lombok.Getter;

public class GitasEventBus {

    @Getter
    private static GitasEventBus instance;
    private static EventBus bus;

    static {
        instance = new GitasEventBus();
        bus = new com.google.common.eventbus.EventBus();
    }

    /**
     * Empty constructor
     */
    protected GitasEventBus() {

    }

    /**
     * Register a subscriber to the bus
     *
     * @param subscriber
     */
    public static void register(Subscriber subscriber) {
        bus.register(subscriber);
    }

    /**
     * Emit an event to the bus
     *
     * @param event
     */
    public static void post(Postable event) {
        bus.post(event);
    }

}
