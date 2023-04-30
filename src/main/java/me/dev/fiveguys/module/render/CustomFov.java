package me.dev.fiveguys.module.render;

import me.dev.fiveguys.module.Category;
import me.dev.fiveguys.module.Module;
import me.dev.fiveguys.settings.SettingDouble;

public class CustomFov extends Module {

    public CustomFov() {
        super("CustomFov", Category.RENDER, "changes your fov");
    }

    public SettingDouble amount = this.addSetting("amount",this,110.0,0.0,150.0);

    @Override public void onUpdate() {
        mc.gameSettings.fovSetting = (float) amount.getValue();
    }
}
