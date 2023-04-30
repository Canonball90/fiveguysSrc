package me.dev.fiveguys.event.events;

import me.dev.fiveguys.event.EventSystem;
import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

public class EventPacket extends EventSystem {

    public final Packet<?> packet;

    public EventPacket(int stage, Packet<?> packet) {
        super(stage);
        this.packet = packet;
    }

    public <T extends Packet<?>> T getPacket() {
        return (T) this.packet;
    }

    @Cancelable
    public static class Send
            extends EventPacket {
        public Send(int stage, Packet<?> packet) {
            super(stage, packet);
        }
    }

    @Cancelable
    public static class Receive
            extends EventPacket {
        public Receive(int stage, Packet<?> packet) {
            super(stage, packet);
        }
    }

}
