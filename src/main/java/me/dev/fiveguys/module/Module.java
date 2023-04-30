package me.dev.fiveguys.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.dev.fiveguys.FiveGuysMain;
import me.dev.fiveguys.IMinecraft;
import me.dev.fiveguys.managers.SettingManager;
import me.dev.fiveguys.settings.*;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.Arrays;

public class Module implements IMinecraft {

    private String name, descrption;

    private Category category;

    private boolean enabled;

    private boolean disabled;

    private int bind = -1;

    public ArrayList<Setting> settings = new ArrayList<>();

    public SettingManager settingManager = new SettingManager();


    public Module(String name, Category category, String descrption) {
        this.name =name;
        this.category = category;
        this.descrption = descrption;
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onUpdate() {
    }

    public void onTick() {
    }

    public void onRender3D(RenderGameOverlayEvent event) {
    }

    public void onRender2D(/*Render2DEvent event*/) {
    }

    public void enable() {
        MinecraftForge.EVENT_BUS.register(this);
        setEnabled(true);
        onEnable();
    }

    public void disable() {
        MinecraftForge.EVENT_BUS.unregister(this);
        setEnabled(false);
        onDisable();
    }

    public void toggle() {
        this.setEnabled(!this.isEnabled());
        if(isEnabled())
        {
            onEnable();
        }else
        {
            onDisable();
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getBind() {
        return bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }

    //register settings here

    public SettingBoolean addSetting(String name, Module parent, boolean value) {
        SettingBoolean setting = new SettingBoolean(name, this,  value);
        FiveGuysMain.settingManager.addSetting(setting);
        return setting;
    }

    public SettingDouble addSetting(String name, Module parent, double value,double min, double max) {
        SettingDouble setting = new SettingDouble(name, this, value, min, max);
        FiveGuysMain.settingManager.addSetting(setting);
        return setting;
    }

    public SettingString register(String name, Module parent, String value, String... modes) {
        SettingString setting = new SettingString(name, this, value, Arrays.asList(modes));
        FiveGuysMain.settingManager.addSetting(setting);
        return setting;
    }

    public SettingInt register(String name, Module parent, int value, int min, int max) {
        SettingInt setting = new SettingInt(name, parent,value,min,max);
        settingManager.addSetting(setting);
        return setting;
    }

}
