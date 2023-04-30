package me.dev.fiveguys.module.render;

import me.dev.fiveguys.module.Category;
import me.dev.fiveguys.module.Module;

public class Fullbright extends Module {
    public Fullbright() {
        super("Fullbright", Category.RENDER, "makes u see better");
    }

    @Override public void onEnable() {
        mc.gameSettings.gammaSetting = 1000;
    }

    @Override public void onDisable() {
        mc.gameSettings.gammaSetting = 1;
    }
}
