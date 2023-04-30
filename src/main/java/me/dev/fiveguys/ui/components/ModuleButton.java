package me.dev.fiveguys.ui.components;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.dev.fiveguys.FiveGuysMain;
import me.dev.fiveguys.module.Module;
import me.dev.fiveguys.module.client.ClickGui;
import me.dev.fiveguys.settings.*;
import me.dev.fiveguys.ui.Component;
import me.dev.fiveguys.ui.Frame;
import me.dev.fiveguys.ui.components.settings.*;
import me.dev.fiveguys.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;


import java.awt.*;
import java.util.ArrayList;

public class ModuleButton extends Component {

    public Module module;
    public Frame frame;
    public int offset;
    private boolean isHovered;
    private final ArrayList<Component> components;
    public boolean open;
    private final int height;
    private Minecraft mc;
    private ResourceLocation resourceLocation = new ResourceLocation("assets/fonts/minecraft/textures/borgor.png");

    public ModuleButton(Module module, Frame frame, int offset) {
        this.module = module;
        this.frame = frame;
        this.offset = offset;
        this.components = new ArrayList<>();
        this.open = false;
        this.height = 16;
        int settingY = this.offset + 16;
        if (!FiveGuysMain.settingManager.getSettings(module).isEmpty()) {
            for (Setting s : FiveGuysMain.settingManager.getSettings(module)) {
                    if (s instanceof SettingBoolean) {
                        components.add(new BooleanButton((SettingBoolean) s, this, settingY));
                        settingY += height;
                        continue;
                    }
                if (s instanceof SettingDouble) {
                        components.add(new SliderButton((SettingDouble) s, this, settingY));
                    settingY += height;
                        continue;
                }
                if (s instanceof SettingString) {
                        components.add(new ModeButton((SettingString) s, this, settingY));
                    settingY += height;
                        continue;
                }
                if (s instanceof SettingInt) {
                    components.add(new IntSlider((SettingInt) s,this,settingY ));
                    settingY += height;
                    continue;
                }
            }
        }
        components.add(new KeyButton(this, settingY));
    }

    @Override
    public void setOffset(final int offset) {
        this.offset = offset;
        int settingY = this.offset + 16;
        for (Component c : components) {
            c.setOffset(settingY);
            settingY += 16;
        }
    }

    @Override
    public int getHeight() {
        if (open) return 16 * (components.size() + 1);
        return 16;
    }

    @Override
    public void updateComponent(final double mouseX, final double mouseY) {
        isHovered = isHovered(mouseX, mouseY);
        if (!components.isEmpty()) {
            components.forEach(c -> {
                c.updateComponent(mouseX, mouseY);
            });
        }
    }

    @Override
    public void mouseClicked(final double mouseX, final double mouseY, final int button) {
        if (isHovered(mouseX, mouseY) && button == 0) {
            module.enable();
        }
        if (isHovered(mouseX, mouseY) && button == 1) {
            open = !open;
            frame.update();
        }
        components.forEach(c -> {
            c.mouseClicked(mouseX, mouseY, button);
        });
    }

    @Override
    public void mouseReleased(final double mouseX, final double mouseY, final int mouseButton) {
        components.forEach(c -> {
            c.mouseReleased(mouseX, mouseY, mouseButton);
        });
    }



    @Override
    public void keyTyped(final int key) {
        components.forEach(c -> {
            c.keyTyped(key);
        });
    }

    public static void drawCompleteImage(float posX, float posY, int width, int height) {
        GL11.glPushMatrix();
        GL11.glTranslatef(posX, posY, 0.0f);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(0.0f, (float) height, 0.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f((float) width, (float) height, 0.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f((float) width, 0.0f, 0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    @Override
    public void render() {

        /*if (ClickGui.getInstance().borgor.getValue()) {
            mc.getTextureManager().bindTexture(this.resourceLocation);
            drawCompleteImage(frame.getX() - 1.5f + (float) frame.getWidth() - 7.4f, frame.getY() - 2.2f - (float) offset, 8, 8);
        }*/

        RenderUtil.drawBorderedRect(frame.getX(), frame.getY() + offset, frame.getX() + frame.getWidth(), frame.getY() + offset + 16, frame.getY(),new Color(0, 0, 0, 255).getRGB(), new Color(0, 0, 0, 60).getRGB());
        FiveGuysMain.textManager.drawStringWithShadow(module.getName(), frame.getX() + 3, frame.getY() + offset + 1, module.isEnabled() ? -1 :new Color(170, 170, 170).getRGB());

        if (open) components.forEach(Component::render);

    }

    public boolean isHovered(final double x, final double y) {
        return x > this.frame.getX() && x < this.frame.getX() + this.frame.getWidth() && y > this.frame.getY() + this.offset && y < this.frame.getY() + 16 + this.offset;
    }
}
