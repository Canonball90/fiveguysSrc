package me.dev.fiveguys.ui.components.settings;

import me.dev.fiveguys.FiveGuysMain;
import me.dev.fiveguys.module.client.ClickGui;
import me.dev.fiveguys.settings.SettingDouble;
import me.dev.fiveguys.settings.SettingInt;
import me.dev.fiveguys.ui.Component;
import me.dev.fiveguys.ui.components.ModuleButton;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class IntSlider extends Component {

    private final SettingInt setting;
    private final ModuleButton button;
    private boolean isHovered;
    private int offset;
    private int x;
    private int y;
    private boolean dragging;
    private double renderWidth;
    private Minecraft mc;

    public IntSlider(final SettingInt setting, final ModuleButton button, final int offset) {
        this.setting = setting;
        this.button = button;
        this.x = button.frame.getX() + button.frame.getWidth();
        this.y = button.frame.getY() + button.offset;
        this.offset = offset;
    }

    @Override
    public void setOffset(final int offset) {
        this.offset = offset;
    }

    @Override
    public void mouseClicked(final double mouseX, final double mouseY, final int button) {
        if (isHovered(mouseX, mouseY) && button == 0 && this.button.open) {
            dragging = true;
        }
    }

    @Override
    public void mouseReleased(final double mouseX, final double mouseY, final int mouseButton) {
        dragging = false;
    }

    @Override
    public void updateComponent(final double mouseX, final double mouseY) {
        isHovered = isHovered(mouseX, mouseY);
        y = button.frame.getY() + offset;
        x = button.frame.getX();
        final int diff = (int) Math.min(88, Math.max(0, mouseX - x));
        final int min = setting.getMin();
        final int max = setting.getMax();
        renderWidth = 88 * (setting.getValue() - min) / (max - min);
        if (dragging) {
            if (diff == 0) setting.setValue(setting.getMin());
            else {
                final int newValue = round(diff / 88 * (max - min) + min, 1);
                setting.setValue(newValue);
            }
        }
    }

    @Override
    public void render() {
        FiveGuysMain.gui.drawGradient(button.frame.getX(), button.frame.getY() + offset, button.frame.getX() + button.frame.getWidth(), button.frame.getY() + offset + 16, isHovered ? new Color(0, 0, 0, 75).getRGB() : new Color(0, 0, 0, 60).getRGB(), isHovered ? new Color(0, 0, 0, 75).getRGB() : new Color(0, 0, 0, 60).getRGB());
        FiveGuysMain.gui.drawGradient(button.frame.getX(), button.frame.getY() + offset, (int) (button.frame.getX() + renderWidth), button.frame.getY() + offset + 16, isHovered ? ClickGui.color.darker().getRGB() : ClickGui.color.getRGB(), isHovered ? ClickGui.color.darker().getRGB() : ClickGui.color.getRGB());
        FiveGuysMain.textManager.drawStringWithShadow(setting.getName(),  button.frame.getX() + 5, button.frame.getY() + offset + 1, isHovered ? new Color(170, 170, 170).getRGB() : -1);
        FiveGuysMain.textManager.drawStringWithShadow(String.valueOf(setting.getValue()), button.frame.getX() + button.frame.getWidth() - 2 - FiveGuysMain.textManager.getStringWidth(String.valueOf(setting.getValue())), button.frame.getY() + offset + 1, isHovered ? new Color(170, 170, 170).getRGB() : -1);
    }

    private static int round(int i, final int value) {

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(0,RoundingMode.HALF_UP);
        return (int) bd.doubleValue();
    }

    public boolean isHovered(final double x, final double y) {
        return x > this.x && x < this.x + 88 && y > this.y && y < this.y + 16;
    }

}
