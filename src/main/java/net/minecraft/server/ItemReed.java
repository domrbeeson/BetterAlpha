package net.minecraft.server;

import net.minecraft.server.world.block.Block;
import net.minecraft.server.world.entity.EntityHuman;

public class ItemReed extends Item {

    private int a;

    public ItemReed(int i, Block block) {
        super(i);
        this.a = block.bi;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        if (world.a(i, j, k) == Block.SNOW.bi) {
            l = 0;
        } else {
            if (l == 0) {
                --j;
            }

            if (l == 1) {
                ++j;
            }

            if (l == 2) {
                --k;
            }

            if (l == 3) {
                ++k;
            }

            if (l == 4) {
                --i;
            }

            if (l == 5) {
                ++i;
            }
        }

        if (itemstack.a == 0) {
            return false;
        } else {
            if (world.a(this.a, i, j, k, false)) {
                Block block = Block.n[this.a];

                if (world.d(i, j, k, this.a)) {
                    Block.n[this.a].c(world, i, j, k, l);
                    world.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), block.br.c(), (block.br.a() + 1.0F) / 2.0F, block.br.b() * 0.8F);
                    --itemstack.a;
                }
            }

            return true;
        }
    }
}
