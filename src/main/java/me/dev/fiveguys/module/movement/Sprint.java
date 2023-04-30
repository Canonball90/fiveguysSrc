package me.dev.fiveguys.module.movement;

import me.dev.fiveguys.module.Category;
import me.dev.fiveguys.module.Module;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", Category.MOVEMENT, "makes u sprint");
    }

    @Override public void onUpdate() {
        mc.player.setSprinting(true);
    }

    @Override public void onDisable() {
        mc.player.setSprinting(false);
    }
}
