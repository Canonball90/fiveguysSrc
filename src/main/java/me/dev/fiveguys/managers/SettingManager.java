package me.dev.fiveguys.managers;

import me.dev.fiveguys.IMinecraft;
import me.dev.fiveguys.module.Module;
import me.dev.fiveguys.settings.Setting;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SettingManager implements IMinecraft {

    private ArrayList<Setting> settings = new ArrayList<>();

    public ArrayList<Setting> getSettings() {
        return settings;
    }

    public void setSettings(ArrayList<Setting> settings) {
        this.settings = settings;
    }

    public ArrayList<Setting> getSettings(Module module) {
        return (ArrayList<Setting>) settings.stream().filter(s ->
                s.getParent() == module).collect(Collectors.toList());
    }

    public void addSetting(Setting setting) {
        settings.add(setting);
    }
}
