package net.minecraft.server.block;

import net.minecraft.server.*;
import net.minecraft.server.entity.Entity;
import net.minecraft.server.entity.EntityHuman;

import java.util.Random;

public class BlockRedstoneOre extends Block {

    private boolean a;

    public BlockRedstoneOre(int i, int j, boolean flag) {
        super(i, j, Material.d);
        if (flag) {
            this.a(true);
        }

        this.a = flag;
    }

    public int b() {
        return 30;
    }

    public void b(World world, int i, int j, int k, EntityHuman entityhuman) {
        this.g(world, i, j, k);
        super.b(world, i, j, k, entityhuman);
    }

    public void b(World world, int i, int j, int k, Entity entity) {
        this.g(world, i, j, k);
        super.b(world, i, j, k, entity);
    }

    public boolean a(World world, int i, int j, int k, EntityHuman entityhuman) {
        this.g(world, i, j, k);
        return super.a(world, i, j, k, entityhuman);
    }

    private void g(World world, int i, int j, int k) {
        this.h(world, i, j, k);
        if (this.bi == Block.REDSTONE_ORE.bi) {
            world.d(i, j, k, Block.GLOWING_REDSTONE_ORE.bi);
        }
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (this.bi == Block.GLOWING_REDSTONE_ORE.bi) {
            world.d(i, j, k, Block.REDSTONE_ORE.bi);
        }
    }

    public int a(int i, Random random) {
        return Item.REDSTONE.aW;
    }

    public int a(Random random) {
        return 4 + random.nextInt(2);
    }

    private void h(World world, int i, int j, int k) {
        Random random = world.l;
        double d0 = 0.0625D;

        for (int l = 0; l < 6; ++l) {
            double d1 = (double) ((float) i + random.nextFloat());
            double d2 = (double) ((float) j + random.nextFloat());
            double d3 = (double) ((float) k + random.nextFloat());

            if (l == 0 && !world.d(i, j + 1, k)) {
                d2 = (double) (j + 1) + d0;
            }

            if (l == 1 && !world.d(i, j - 1, k)) {
                d2 = (double) (j + 0) - d0;
            }

            if (l == 2 && !world.d(i, j, k + 1)) {
                d3 = (double) (k + 1) + d0;
            }

            if (l == 3 && !world.d(i, j, k - 1)) {
                d3 = (double) (k + 0) - d0;
            }

            if (l == 4 && !world.d(i + 1, j, k)) {
                d1 = (double) (i + 1) + d0;
            }

            if (l == 5 && !world.d(i - 1, j, k)) {
                d1 = (double) (i + 0) - d0;
            }

            if (d1 < (double) i || d1 > (double) (i + 1) || d2 < 0.0D || d2 > (double) (j + 1) || d3 < (double) k || d3 > (double) (k + 1)) {
                world.a("reddust", d1, d2, d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
