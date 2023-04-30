package me.dev.fiveguys.module.movement;

import me.dev.fiveguys.module.Category;
import me.dev.fiveguys.module.Module;
import me.dev.fiveguys.settings.SettingDouble;
import me.dev.fiveguys.settings.SettingInt;

public class Step extends Module {
    public Step() {
        super("Step", Category.MOVEMENT, "steps up blocks that u can't normally step up");
    }

    SettingDouble height = this.addSetting("Height",this,2.0,0.1,2.5);

    @Override public void onUpdate() {
        mc.player.stepHeight = (float) height.getValue();
    }

    @Override public void onDisable() {
        mc.player.stepHeight = 0.5f;
    }
}
