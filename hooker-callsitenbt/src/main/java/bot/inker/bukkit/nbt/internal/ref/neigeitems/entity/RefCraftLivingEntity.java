package bot.inker.bukkit.nbt.internal.ref.neigeitems.entity;

import bot.inker.bukkit.nbt.internal.annotation.CbVersion;
import bot.inker.bukkit.nbt.internal.annotation.HandleBy;

@HandleBy(version = CbVersion.v1_12_R1, reference = "org/bukkit/craftbukkit/v1_12_R1/entity/CraftLivingEntity")
public class RefCraftLivingEntity extends RefCraftEntity {
    @HandleBy(version = CbVersion.v1_12_R1, reference = "Lorg/bukkit/craftbukkit/v1_12_R1/entity/CraftLivingEntity;getHandle()Lnet/minecraft/server/v1_12_R1/EntityLiving;")
    @HandleBy(version = CbVersion.v1_17_R1, reference = "Lorg/bukkit/craftbukkit/v1_17_R1/entity/CraftLivingEntity;getHandle()Lnet/minecraft/world/entity/LivingEntity;")
    public native RefEntityLiving getHandle();
}