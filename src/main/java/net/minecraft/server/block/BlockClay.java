package net.minecraft.server.block;

import net.minecraft.server.Item;
import net.minecraft.server.Material;

import java.util.Random;

public class BlockClay extends Block {

    public BlockClay(int i, int j) {
        super(i, j, Material.v);
    }

    public int a(int i, Random random) {
        return Item.CLAY_BALL.aW;
    }

    public int a(Random random) {
        return 4;
    }
}