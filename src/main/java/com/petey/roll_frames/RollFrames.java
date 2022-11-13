package com.petey.roll_frames;

import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import net.combatroll.api.event.ServerSideRollEvents;
import net.combatroll.config.ServerConfig;
import net.combatroll.config.ServerConfigWrapper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(RollFrames.MOD_ID)
public class RollFrames {
    public static final String MOD_ID = "roll_frames";

    public static final Logger LOGGER = LogUtils.getLogger();


    public RollFrames() {
        ServerSideRollEvents.PLAYER_START_ROLLING.register(RollFrames::onRoll);
    }

    public static void onRoll(ServerPlayer player, Vec3 velocity) {
        ServerConfig combatRollConfig = AutoConfig.getConfigHolder(ServerConfigWrapper.class).getConfig().server;
//        RollFrames.LOGGER.info("Rolled!");
        IRollable rollablePlayer = (IRollable)player;
        rollablePlayer.setRollingFrames(combatRollConfig.roll_duration);
    }
}
