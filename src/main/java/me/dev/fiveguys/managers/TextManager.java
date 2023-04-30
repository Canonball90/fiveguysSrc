package me.dev.fiveguys.managers;

import me.dev.fiveguys.FiveGuysMain;
import me.dev.fiveguys.IMinecraft;
import me.dev.fiveguys.customfont.CustomFontManager;

public class TextManager implements IMinecraft {

    public void drawStringWithShadow(String text, float x, float y, int color) {
        if(FiveGuysMain.moduleManager.getModuleByName("CustomFont").isEnabled())
        {
            FiveGuysMain.customFontManager.drawStringWithShadow(text, x, y, color);
        }else {
            TextManager.mc.fontRenderer.drawString(text, x, y, color, true);
        }
    }

    public float drawString(String text, float x, float y, int color) {
        if(FiveGuysMain.moduleManager.getModuleByName("CustomFont").isEnabled())
        {
            FiveGuysMain.customFontManager.drawString(text, x, y, color);
        }else {
            TextManager.mc.fontRenderer.drawString(text, x, y, color, false);
        }
        return x;
    }

    public int getStringWidth(String text) {
        return TextManager.mc.fontRenderer.getStringWidth(text);
    }

    public int getFontHeight() {
        return TextManager.mc.fontRenderer.FONT_HEIGHT;
    }

}
