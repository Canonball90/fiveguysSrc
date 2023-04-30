package me.dev.fiveguys.module;

public enum Category {
    CLIENT("CLIENT"),
    COMBAT("COMBAT"),
    MOVEMENT("MOVEMENT"),
    RENDER("RENDER"),
    MISC("MISC"),
    CHAT("CHAT");

    String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
