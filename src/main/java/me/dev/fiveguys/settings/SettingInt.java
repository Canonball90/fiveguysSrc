package me.dev.fiveguys.settings;

import me.dev.fiveguys.module.Module;

public class SettingInt extends Setting{

    private int value, min, max;

    public SettingInt(String name, Module parent, int value, int min, int max) {
        super(name, parent);
        this.value =value;
        this.min = min;
        this.max = max;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
