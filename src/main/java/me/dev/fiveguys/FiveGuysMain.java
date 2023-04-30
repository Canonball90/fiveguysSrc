package me.dev.fiveguys;

import me.dev.fiveguys.customfont.CustomFontManager;
import me.dev.fiveguys.managers.ModuleManager;
import me.dev.fiveguys.managers.SettingManager;
import me.dev.fiveguys.managers.TextManager;
import me.dev.fiveguys.ui.ClickGuiMainScreen;
import me.security.HWIDManger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

//TODO : redo build.gradle (bigj is bad at it)
@Mod(modid = FiveGuysMain.MODID, name = FiveGuysMain.NAME, version = FiveGuysMain.VERSION)
public class FiveGuysMain
{
    public static final String MODID = "fiveguys";
    public static final String NAME = "FiveGuys";
    public static final String VERSION = "0.2";

    public static String HWIDUrl = "https://pastebin.com/raw/RcVKHY0Z";

    public static ModuleManager moduleManager;
    public static SettingManager settingManager = new SettingManager();
    public static CustomFontManager customFontManager;
    public static TextManager textManager;
    public static HWIDManger hwidManager;

    public static Logger logger;
    public static ClickGuiMainScreen gui;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        moduleManager = new ModuleManager();
        settingManager = new SettingManager();
        customFontManager = new CustomFontManager("roboto", 18);
        textManager = new TextManager();
        hwidManager = new HWIDManger(HWIDUrl);
        logger.info("rat initialised L");
        Display.setTitle("FiveGuys v0.1");
        gui = new ClickGuiMainScreen();

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        hwidManager.processVerification();

    }

}
