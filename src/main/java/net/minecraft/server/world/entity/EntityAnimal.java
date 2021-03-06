package net.minecraft.server.world.entity;

import net.minecraft.server.nbt.NBTTagCompound;
import net.minecraft.server.utils.MathHelper;
import net.minecraft.server.world.World;
import net.minecraft.server.world.block.Block;
import net.minecraft.server.world.entity.impl.EntityCreature;

public abstract class EntityAnimal extends EntityCreature implements IAnimal {

    public EntityAnimal(World world) {
        super(world);
    }

    protected float a(int i, int j, int k) {
        return this.world.a(i, j - 1, k) == Block.GRASS.bi ? 10.0F : this.world.j(i, j, k) - 0.5F;
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public boolean a() {
        int i = MathHelper.b(this.p);
        int j = MathHelper.b(this.boundingBox.b);
        int k = MathHelper.b(this.r);

        return this.world.a(i, j - 1, k) == Block.GRASS.bi && this.world.h(i, j, k) > 8 && super.a();
    }

    public int b() {
        return 120;
    }
}
