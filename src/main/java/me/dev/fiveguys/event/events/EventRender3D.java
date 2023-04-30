package me.dev.fiveguys.event.events;

import me.dev.fiveguys.event.EventSystem;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EventRender3D extends EventSystem {

    public float partialTicks;

    public EventRender3D(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public EventRender3D(int stage, float partialTicks) {
        super(stage);
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public void setPartialTicks(float partialTicks) {
        this.partialTicks = partialTicks;
    }
}
