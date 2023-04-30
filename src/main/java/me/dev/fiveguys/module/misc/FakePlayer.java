package me.dev.fiveguys.module.misc;

import com.mojang.authlib.GameProfile;
import me.dev.fiveguys.module.Category;
import me.dev.fiveguys.module.Module;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.world.GameType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import java.util.UUID;

public class FakePlayer extends Module {
    public FakePlayer() {
        super("FakePlayer", Category.MISC, "renders a fakeplayer to test stuff on");
    }

    private EntityOtherPlayerMP clonedPlayer;

    public void onEnable() {
        clonedPlayer = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString("48efc40f-56bf-42c3-aa24-28e0c053f325"), "FiveGuysBot"));
        clonedPlayer.copyLocationAndAnglesFrom(mc.player);
        clonedPlayer.rotationYawHead = mc.player.rotationYawHead;
        clonedPlayer.rotationYaw = mc.player.rotationYaw;
        clonedPlayer.rotationPitch = mc.player.rotationPitch;
        clonedPlayer.setGameType(GameType.SURVIVAL);
        clonedPlayer.setHealth(20);
        mc.world.addEntityToWorld(-12345, clonedPlayer);
        clonedPlayer.onLivingUpdate();
    }

    public void onDisable() {
        if (mc.world != null) {
            mc.world.removeEntityFromWorld(-12345);
        }
    }

    @SubscribeEvent
    public void onClientDisconnect(final FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        if (isEnabled()){
            disable();
        }
    }
}
