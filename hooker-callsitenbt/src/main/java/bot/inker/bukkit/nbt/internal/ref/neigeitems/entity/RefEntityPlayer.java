package bot.inker.bukkit.nbt.internal.ref.neigeitems.entity;

import bot.inker.bukkit.nbt.internal.annotation.CbVersion;
import bot.inker.bukkit.nbt.internal.annotation.HandleBy;
import bot.inker.bukkit.nbt.internal.ref.neigeitems.argument.RefAnchor;
import bot.inker.bukkit.nbt.internal.ref.neigeitems.network.RefPlayerConnection;
import bot.inker.bukkit.nbt.internal.ref.neigeitems.world.RefVec3;

@HandleBy(version = CbVersion.v1_12_R1, reference = "net/minecraft/server/v1_12_R1/EntityPlayer")
@HandleBy(version = CbVersion.v1_17_R1, reference = "net/minecraft/server/level/ServerPlayer")
public final class RefEntityPlayer extends RefEntityHuman {
    @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/EntityPlayer;playerInteractManager:Lnet/minecraft/server/v1_12_R1/PlayerInteractManager;")
    @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/server/level/ServerPlayer;gameMode:Lnet/minecraft/server/level/ServerPlayerGameMode;")
    public final RefPlayerInteractManager playerInteractManager = null;

    @HandleBy(version = CbVersion.v1_12_R1, reference = "Lnet/minecraft/server/v1_12_R1/EntityPlayer;playerConnection:Lnet/minecraft/server/v1_12_R1/PlayerConnection;")
    @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/server/level/ServerPlayer;connection:Lnet/minecraft/server/network/ServerGamePacketListenerImpl;")
    public final RefPlayerConnection playerConnection = null;

    @HandleBy(version = CbVersion.v1_17_R1, reference = "Lnet/minecraft/server/level/ServerPlayer;lookAt(Lnet/minecraft/commands/arguments/EntityAnchorArgument$Anchor;Lnet/minecraft/world/phys/Vec3;)V")
    public native void lookAt(RefAnchor anchorPoint, RefVec3 target);
}
