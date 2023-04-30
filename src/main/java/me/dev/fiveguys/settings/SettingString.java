package me.dev.fiveguys.settings;

import me.dev.fiveguys.module.Module;

import java.util.List;

public class SettingString extends Setting{

    private String value;

    private List<String> modes;

    public SettingString(String name, Module parent, String value, List<String> modes) {
        super(name, parent);
        this.value = value;
        this.modes = modes;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getModes() {
        return modes;
    }

    public void setModes(List<String> modes) {
        this.modes = modes;
    }
}
