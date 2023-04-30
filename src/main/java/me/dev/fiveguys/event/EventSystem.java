package me.dev.fiveguys.event;

import net.minecraftforge.fml.common.eventhandler.Event;

public class EventSystem extends Event {

    private int stage;

    public EventSystem(int stage) {
        this.stage = stage;
    }

    public EventSystem() {}

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }
}
