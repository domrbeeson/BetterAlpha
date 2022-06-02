package net.minecraft.server.world.block;

import net.minecraft.server.world.block.material.Material;

public interface IBlockAccess {

    int a(int i, int j, int k);

    int b(int i, int j, int k);

    Material c(int i, int j, int k);

    boolean d(int i, int j, int k);
}
