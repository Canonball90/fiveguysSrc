package me.dev.fiveguys.managers;

import me.dev.fiveguys.*;
import me.dev.fiveguys.module.*;
import me.dev.fiveguys.module.client.*;
import me.dev.fiveguys.module.misc.*;
import me.dev.fiveguys.module.movement.*;
import me.dev.fiveguys.module.render.*;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ModuleManager implements IMinecraft {

    private ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
        MinecraftForge.EVENT_BUS.register(this);

        this.modules.add(new ClickGui());
        this.modules.add(new Sprint());
        this.modules.add(new Fullbright());
        this.modules.add(new CustomFont());
        this.modules.add(new ReverseStep());
        this.modules.add(new Step());
        this.modules.add(new FakePlayer());
        this.modules.add(new CustomFov());
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    public Module getModuleByName(String name) {
        for (Module module : modules) {
            if (module.getName().equals(name)) {
                return module;
            }
        }
        return null;
    }

    public ArrayList<Module> getModulesInCategory(Category c){
        return (ArrayList<Module>) modules.stream().filter(h -> h.getCategory().equals(c)).collect(Collectors.toList());
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if(Keyboard.getEventKeyState() && Keyboard.getEventKey() > 0) for(final Module module : getModules()) if(module.getBind() == Keyboard.getEventKey()) module.toggle();
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        modules.stream().filter(Module::isEnabled).forEach(Module::onUpdate);
    }

    @SubscribeEvent
    public void onRender3D(RenderGameOverlayEvent event) {
        this.modules.stream().filter(Module::isEnabled).forEach(module -> module.onRender3D(event));
    }
/*
    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event) {
        final ArrayList<Command> commands = new ArrayList<>();
        if (mc.currentScreen != null) return;
        for(Command cmd : commands)if(cmd.getKey() == Keyboard.getEventKey()) FiveGuysMain.commandManager.runCommands("." + cmd.getExecute());
    }

 */
}
