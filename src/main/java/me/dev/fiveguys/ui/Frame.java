package me.dev.fiveguys.ui;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.dev.fiveguys.FiveGuysMain;
import me.dev.fiveguys.module.Category;
import me.dev.fiveguys.module.Module;
import me.dev.fiveguys.ui.components.ModuleButton;
import me.dev.fiveguys.utils.RenderUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.Render;
import net.minecraftforge.common.ForgeVersion;

import java.awt.*;
import java.util.ArrayList;

public class Frame extends GuiScreen {

    public Category category;
    public ArrayList<Component> components;
    private boolean open;
    private final int width;
    private int y;
    private int x;
    private final int barHeight;
    private boolean isDragging;
    public int dragX;
    public int dragY;
    private int height;
    private Module module;

    public Frame(Category category) {
        this.category = category;
        this.components = new ArrayList<>();
        this.width = 88;
        this.x = 5;
        this.y = 5;
        this.barHeight = 16;
        this.dragX = 0;
        this.open = true;
        this.isDragging = false;
        int componentY = this.barHeight;
        for (Module m : FiveGuysMain.moduleManager.getModulesInCategory(category)) {
            ModuleButton moduleButton = new ModuleButton(m, this, componentY);
            components.add(moduleButton);
            componentY += 16;
        }
        update();
    }

    public void renderFrame() {
        //RenderUtil.drawVerticalLine(x,y,getWidth(), ClickGuiMainScreen.color.getRGB());
        RenderUtil.drawBorderedRect(x, y, x + width, y + barHeight, y, new Color(0,0,0,255).getRGB(), new Color(255,255,255,255).getRGB());
        FiveGuysMain.textManager.drawStringWithShadow( category.name(), x + width / 2 - FiveGuysMain.textManager.getStringWidth(category.name()) / 2, y + 1, -1);
        if (open && !components.isEmpty()) {
            components.forEach(Component::render);
        }
    }

    public ArrayList<Component> getComponents() {
        return this.components;
    }

    public void setX(final int newX) {
        this.x = newX;
    }

    public void setY(final int newY) {
        this.y = newY;
    }

    public void setDrag(final boolean drag) {
        this.isDragging = drag;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(final boolean open) {
        this.open = open;
    }

    public void update() {
        int off = this.barHeight;
        for (final Component comp : this.components) {
            comp.setOffset(off);
            off += comp.getHeight();
        }
        this.height = off;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public void updatePosition(final int mouseX, final int mouseY) {
        if (this.isDragging) {
            this.setX(mouseX - this.dragX);
            this.setY(mouseY - this.dragY);
        }
    }

    public boolean isHover(final double x, final double y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight;
    }

}
