package com.github.galcyurio.freetodo.commons;

import com.google.common.eventbus.EventBus;

/**
 * @author galcyurio
 */
public class BusProvider {
    private static EventBus sBus;

    public static EventBus get() {
        if (sBus == null) {
            sBus = new EventBus();
        }
        return sBus;
    }
}
