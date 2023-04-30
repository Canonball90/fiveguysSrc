package me.dev.fiveguys.settings;

import me.dev.fiveguys.module.Module;

public class SettingDouble extends Setting{

    private double value, min, max;

    public SettingDouble(String name, Module parent, double value, double min, double max) {
        super(name, parent);
        this.value = value;
        this.min = min;
        this.max = max;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
