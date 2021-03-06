package net.minecraft.server.world.entity.impl;

import net.minecraft.server.item.Item;
import net.minecraft.server.nbt.NBTTagCompound;
import net.minecraft.server.world.World;
import net.minecraft.server.world.entity.EntityAnimal;

public class EntityCow extends EntityAnimal {

    public boolean a = false;

    public EntityCow(World world) {
        super(world);
        this.aF = "/mob/cow.png";
        this.a(0.9F, 1.3F);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    protected String d() {
        return "mob.cow";
    }

    protected String e() {
        return "mob.cowhurt";
    }

    protected String f() {
        return "mob.cowhurt";
    }

    protected float h() {
        return 0.4F;
    }

    protected int g() {
        return Item.LEATHER.aW;
    }
}
