package me.dev.fiveguys.settings;

import me.dev.fiveguys.module.Module;

public class SettingBoolean extends Setting{

    private boolean value;

    public SettingBoolean(String name, Module parent, boolean value) {
        super(name, parent);
        this.value =value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
