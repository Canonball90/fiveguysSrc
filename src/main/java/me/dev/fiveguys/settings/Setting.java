package me.dev.fiveguys.settings;

import me.dev.fiveguys.IMinecraft;
import me.dev.fiveguys.module.Module;

public class Setting implements IMinecraft {

    private String  name;

    private Module parent;

    public Setting(String name, Module parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Module getParent() {
        return parent;
    }

    public void setParent(Module parent) {
        this.parent = parent;
    }
}
