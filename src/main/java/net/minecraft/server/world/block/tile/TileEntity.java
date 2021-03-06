package net.minecraft.server.world.block.tile;

import net.minecraft.server.nbt.NBTTagCompound;
import net.minecraft.server.world.World;

import java.util.HashMap;
import java.util.Map;

public class TileEntity {

    private static Map e = new HashMap();
    private static Map f = new HashMap();
    public World world;
    public int x;
    public int y;
    public int z;

    public TileEntity() {
    }

    private static void a(Class oclass, String s) {
        if (f.containsKey(s)) {
            throw new IllegalArgumentException("Duplicate id: " + s);
        } else {
            e.put(s, oclass);
            f.put(oclass, s);
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.x = nbttagcompound.d("x");
        this.y = nbttagcompound.d("y");
        this.z = nbttagcompound.d("z");
    }

    public void b(NBTTagCompound nbttagcompound) {
        String s = (String) f.get(this.getClass());

        if (s == null) {
            throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
        } else {
            nbttagcompound.a("id", s);
            nbttagcompound.a("x", this.x);
            nbttagcompound.a("y", this.y);
            nbttagcompound.a("z", this.z);
        }
    }

    public void b() {
    }

    public static TileEntity c(NBTTagCompound nbttagcompound) {
        TileEntity tileentity = null;

        try {
            Class oclass = (Class) e.get(nbttagcompound.h("id"));

            if (oclass != null) {
                tileentity = (TileEntity) oclass.newInstance();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (tileentity != null) {
            tileentity.a(nbttagcompound);
        } else {
            System.out.println("Skipping TileEntity with id " + nbttagcompound.h("id"));
        }

        return tileentity;
    }

    public void c() {
        this.world.b(this.x, this.y, this.z, this);
    }

    static {
        a(TileEntityFurnace.class, "Furnace");
        a(TileEntityChest.class, "Chest");
        a(TileEntitySign.class, "Sign");
        a(TileEntityMobSpawner.class, "MobSpawner");
    }
}
