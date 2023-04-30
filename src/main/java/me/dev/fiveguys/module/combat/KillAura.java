package me.dev.fiveguys.module.combat;

import me.dev.fiveguys.module.Category;
import me.dev.fiveguys.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;

import java.util.Comparator;

public class KillAura extends Module {

    static final Minecraft mc;

    public KillAura() {
        super("KillAura", Category.COMBAT, "kill shit");
    }

    public float[] rotations(final Entity entity) {
        final double d0 = entity.posX - KillAura.mc.player.posX;
        final double d2 = entity.posY - (KillAura.mc.player.posY + KillAura.mc.player.getEyeHeight());
        final double d3 = entity.posZ - KillAura.mc.player.posZ;
        final double d4 = MathHelper.sqrt(d0 * d0 + d3 * d3);
        final float f = (float)(MathHelper.atan2(d3, d0) * 57.29577951308232) - 90.0f;
        final float f2 = (float)(-(MathHelper.atan2(d2, d4) * 57.29577951308232));
        return new float[] { f, f2 };
    }

    @Override
    public void onUpdate() {
        final float range = 4.5f;
        final EntityPlayer target = (EntityPlayer)KillAura.mc.world.playerEntities.stream().filter(entityPlayer -> entityPlayer != KillAura.mc.player).min(Comparator.comparing(entityPlayer -> entityPlayer.getDistanceSq((Entity)KillAura.mc.player))).filter(entityPlayer -> entityPlayer.getDistanceSq((Entity)KillAura.mc.player) <= range).orElse(null);
        if (target != null) {
            KillAura.mc.player.rotationYaw = this.rotations((Entity)target)[0];
            KillAura.mc.player.rotationPitch = this.rotations((Entity)target)[1];
            if (KillAura.mc.player.getCooledAttackStrength(0.0f) == 1.0f) {
                KillAura.mc.playerController.attackEntity((EntityPlayer)KillAura.mc.player, (Entity)target);
                KillAura.mc.player.swingArm(EnumHand.MAIN_HAND);
                KillAura.mc.player.resetCooldown();
            }
        }
        super.onEnable();
    }

    static {
        mc = Minecraft.getMinecraft();
    }
}
