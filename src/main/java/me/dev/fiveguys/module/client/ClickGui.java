package me.dev.fiveguys.module.client;

import me.dev.fiveguys.FiveGuysMain;
import me.dev.fiveguys.module.Category;
import me.dev.fiveguys.module.Module;
import me.dev.fiveguys.settings.SettingBoolean;
import me.dev.fiveguys.settings.SettingDouble;
import me.dev.fiveguys.settings.SettingInt;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class ClickGui extends Module {
    public ClickGui() {
        super("GUI", Category.CLIENT, "module menu");
        setBind(Keyboard.KEY_RSHIFT);
    }

    public static ClickGui instance;

    public static Color color;

    public SettingBoolean borgor = this.addSetting("Borgor",this,true);
    public SettingDouble red = this.addSetting("red",this,0.0,0.0,255.0);
    public SettingDouble green = this.addSetting("green",this,10.0,0.0,255.0);
    public SettingDouble blue = this.addSetting("blue",this,100.0,0.0,255.0);


    public static ClickGui getInstance() {
        return instance;
    }

    public static void setInstance(ClickGui instance) {
        ClickGui.instance = instance;
    }

    public void onEnable() {
        mc.displayGuiScreen(FiveGuysMain.gui);
        this.disable();
    }
}
