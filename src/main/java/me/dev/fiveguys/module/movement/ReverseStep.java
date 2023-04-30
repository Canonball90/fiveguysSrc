package me.dev.fiveguys.module.movement;

import me.dev.fiveguys.module.*;
import me.dev.fiveguys.settings.SettingDouble;

public class ReverseStep extends Module {
    public ReverseStep() {
        super("ReverseStep", Category.MOVEMENT, "how?");
    }

    SettingDouble height = this.addSetting("Height",this,2.0,0.1,4);

    @Override public void onUpdate() {
        if (mc.player != null && mc.player.onGround && !mc.player.isInWater() && !mc.player.isOnLadder()) {
            for (double y = 0.0; y < height.getValue() + 0.5; y += 0.01) {
                if (!mc.world.getCollisionBoxes(mc.player, mc.player.getEntityBoundingBox().offset(0.0, -y, 0.0)).isEmpty()) {
                    mc.player.motionY = -10.0;
                    break;
                }
            }
        }
    }
}
