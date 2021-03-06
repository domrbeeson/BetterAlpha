package net.minecraft.server.world.chunk;

import net.minecraft.server.utils.AxisAlignedBB;
import net.minecraft.server.utils.MathHelper;
import net.minecraft.server.world.World;
import net.minecraft.server.world.block.Block;
import net.minecraft.server.world.block.BlockContainer;
import net.minecraft.server.world.block.EnumSkyBlock;
import net.minecraft.server.world.block.tile.TileEntity;
import net.minecraft.server.world.entity.Entity;

import java.util.*;

public class Chunk {

    public static boolean a;
    public byte[] blocks;
    public boolean c;
    public World world;
    public NibbleArray blockData;
    public NibbleArray skyLight;
    public NibbleArray blockLight;
    public byte[] heightMap;
    public int i;
    public final int x;
    public final int z;
    public Map<ChunkPosition, TileEntity> tileEntities;
    public List<Entity>[] entities;
    public boolean terrainPopulated;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public long s;

    public Chunk(World world, int i, int x) {
        this.tileEntities = new HashMap();
        this.entities = new List[8];
        this.terrainPopulated = false;
        this.o = false;
        this.q = false;
        this.r = false;
        this.s = 0L;
        this.world = world;
        this.x = i;
        this.z = x;
        this.heightMap = new byte[256];

        for (int k = 0; k < this.entities.length; ++k) {
            this.entities[k] = new ArrayList();
        }
    }

    public Chunk(World world, byte[] abyte, int i, int x) {
        this(world, i, x);
        this.blocks = abyte;
        this.blockData = new NibbleArray(abyte.length);
        this.skyLight = new NibbleArray(abyte.length);
        this.blockLight = new NibbleArray(abyte.length);
    }

    public boolean a(int i, int j) {
        return i == this.x && j == this.z;
    }

    public int b(int i, int j) {
        return this.heightMap[j << 4 | i] & 255;
    }

    public void a() {
    }

    public void b() {
        int i = 127;

        int j;
        int k;

        for (j = 0; j < 16; ++j) {
            for (k = 0; k < 16; ++k) {
                this.heightMap[k << 4 | j] = -128;
                this.g(j, 127, k);
                if ((this.heightMap[k << 4 | j] & 255) < i) {
                    i = this.heightMap[k << 4 | j] & 255;
                }
            }
        }

        this.i = i;

        for (j = 0; j < 16; ++j) {
            for (k = 0; k < 16; ++k) {
                this.c(j, k);
            }
        }

        this.o = true;
    }

    public void c() {
        byte b0 = 32;

        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                int k = i << 11 | j << 7;

                int l;
                int i1;

                for (l = 0; l < 128; ++l) {
                    i1 = Block.t[this.blocks[k + l]];
                    if (i1 > 0) {
                        this.blockLight.a(i, l, j, i1);
                    }
                }

                l = 15;

                for (i1 = b0 - 2; i1 < 128 && l > 0; this.blockLight.a(i, i1, j, l)) {
                    ++i1;
                    byte b1 = this.blocks[k + i1];
                    int j1 = Block.r[b1];
                    int k1 = Block.t[b1];

                    if (j1 == 0) {
                        j1 = 1;
                    }

                    l -= j1;
                    if (k1 > l) {
                        l = k1;
                    }

                    if (l < 0) {
                        l = 0;
                    }
                }
            }
        }

        this.world.a(EnumSkyBlock.BLOCK, this.x * 16, b0 - 1, this.z * 16, this.x * 16 + 16, b0 + 1, this.z * 16 + 16);
        this.o = true;
    }

    private void c(int i, int j) {
        int k = this.b(i, j);
        int l = this.x * 16 + i;
        int i1 = this.z * 16 + j;

        this.f(l - 1, i1, k);
        this.f(l + 1, i1, k);
        this.f(l, i1 - 1, k);
        this.f(l, i1 + 1, k);
    }

    private void f(int i, int j, int k) {
        int l = this.world.d(i, j);

        if (l > k) {
            this.world.a(EnumSkyBlock.SKY, i, k, j, i, l, j);
        } else if (l < k) {
            this.world.a(EnumSkyBlock.SKY, i, l, j, i, k, j);
        }

        this.o = true;
    }

    private void g(int i, int j, int k) {
        int l = this.heightMap[k << 4 | i] & 255;
        int i1 = l;

        if (j > l) {
            i1 = j;
        }

        for (int j1 = i << 11 | k << 7; i1 > 0 && Block.r[this.blocks[j1 + i1 - 1]] == 0; --i1) {
            ;
        }

        if (i1 != l) {
            this.world.f(i, k, i1, l);
            this.heightMap[k << 4 | i] = (byte) i1;
            int k1;
            int l1;
            int i2;

            if (i1 < this.i) {
                this.i = i1;
            } else {
                k1 = 127;

                for (l1 = 0; l1 < 16; ++l1) {
                    for (i2 = 0; i2 < 16; ++i2) {
                        if ((this.heightMap[i2 << 4 | l1] & 255) < k1) {
                            k1 = this.heightMap[i2 << 4 | l1] & 255;
                        }
                    }
                }

                this.i = k1;
            }

            k1 = this.x * 16 + i;
            l1 = this.z * 16 + k;
            if (i1 < l) {
                for (i2 = i1; i2 < l; ++i2) {
                    this.skyLight.a(i, i2, k, 15);
                }
            } else {
                this.world.a(EnumSkyBlock.SKY, k1, l, l1, k1, i1, l1);

                for (i2 = l; i2 < i1; ++i2) {
                    this.skyLight.a(i, i2, k, 0);
                }
            }

            i2 = 15;

            int j2;

            for (j2 = i1; i1 > 0 && i2 > 0; this.skyLight.a(i, i1, k, i2)) {
                --i1;
                int k2 = Block.r[this.a(i, i1, k)];

                if (k2 == 0) {
                    k2 = 1;
                }

                i2 -= k2;
                if (i2 < 0) {
                    i2 = 0;
                }
            }

            while (i1 > 0 && Block.r[this.a(i, i1 - 1, k)] == 0) {
                --i1;
            }

            if (i1 != j2) {
                this.world.a(EnumSkyBlock.SKY, k1 - 1, i1, l1 - 1, k1 + 1, j2, l1 + 1);
            }

            this.o = true;
        }
    }

    public int a(int i, int j, int k) {
        return this.blocks[i << 11 | k << 7 | j];
    }

    public boolean a(int i, int j, int k, int l, int i1) {
        byte b0 = (byte) l;
        int j1 = this.heightMap[k << 4 | i] & 255;
        int k1 = this.blocks[i << 11 | k << 7 | j] & 255;

        if (k1 == l && this.blockData.a(i, j, k) == i1) {
            return false;
        } else {
            int l1 = this.x * 16 + i;
            int i2 = this.z * 16 + k;

            this.blocks[i << 11 | k << 7 | j] = b0;
            if (k1 != 0 && !this.world.z) {
                Block.n[k1].b(this.world, l1, j, i2);
            }

            this.blockData.a(i, j, k, i1);
            if (!this.world.q.c) {
                if (Block.r[b0] != 0) {
                    if (j >= j1) {
                        this.g(i, j + 1, k);
                    }
                } else if (j == j1 - 1) {
                    this.g(i, j, k);
                }

                this.world.a(EnumSkyBlock.SKY, l1, j, i2, l1, j, i2);
            }

            this.world.a(EnumSkyBlock.BLOCK, l1, j, i2, l1, j, i2);
            this.c(i, k);
            if (l != 0) {
                Block.n[l].e(this.world, l1, j, i2);
            }

            this.o = true;
            return true;
        }
    }

    public boolean a(int i, int j, int k, int l) {
        byte b0 = (byte) l;
        int i1 = this.heightMap[k << 4 | i] & 255;
        int j1 = this.blocks[i << 11 | k << 7 | j] & 255;

        if (j1 == l) {
            return false;
        } else {
            int k1 = this.x * 16 + i;
            int l1 = this.z * 16 + k;

            this.blocks[i << 11 | k << 7 | j] = b0;
            if (j1 != 0) {
                Block.n[j1].b(this.world, k1, j, l1);
            }

            this.blockData.a(i, j, k, 0);
            if (Block.r[b0] != 0) {
                if (j >= i1) {
                    this.g(i, j + 1, k);
                }
            } else if (j == i1 - 1) {
                this.g(i, j, k);
            }

            this.world.a(EnumSkyBlock.SKY, k1, j, l1, k1, j, l1);
            this.world.a(EnumSkyBlock.BLOCK, k1, j, l1, k1, j, l1);
            this.c(i, k);
            if (l != 0 && !this.world.z) {
                Block.n[l].e(this.world, k1, j, l1);
            }

            this.o = true;
            return true;
        }
    }

    public int b(int i, int j, int k) {
        return this.blockData.a(i, j, k);
    }

    public void b(int i, int j, int k, int l) {
        this.o = true;
        this.blockData.a(i, j, k, l);
    }

    public int a(EnumSkyBlock enumskyblock, int i, int j, int k) {
        return enumskyblock == EnumSkyBlock.SKY ? this.skyLight.a(i, j, k) : (enumskyblock == EnumSkyBlock.BLOCK ? this.blockLight.a(i, j, k) : 0);
    }

    public void a(EnumSkyBlock enumskyblock, int i, int j, int k, int l) {
        this.o = true;
        if (enumskyblock == EnumSkyBlock.SKY) {
            this.skyLight.a(i, j, k, l);
        } else {
            if (enumskyblock != EnumSkyBlock.BLOCK) {
                return;
            }

            this.blockLight.a(i, j, k, l);
        }
    }

    public int c(int i, int j, int k, int l) {
        int i1 = this.skyLight.a(i, j, k);

        if (i1 > 0) {
            a = true;
        }

        i1 -= l;
        int j1 = this.blockLight.a(i, j, k);

        if (j1 > i1) {
            i1 = j1;
        }

        return i1;
    }

    public void a(Entity entity) {
        if (!this.q) {
            this.r = true;
            int i = MathHelper.b(entity.p / 16.0D);
            int j = MathHelper.b(entity.r / 16.0D);

            if (i != this.x || j != this.z) {
                System.out.println("Wrong location! " + entity);
            }

            int k = MathHelper.b(entity.q / 16.0D);

            if (k < 0) {
                k = 0;
            }

            if (k >= this.entities.length) {
                k = this.entities.length - 1;
            }

            entity.ae = true;
            entity.af = this.x;
            entity.ag = k;
            entity.ah = this.z;
            this.entities[k].add(entity);
        }
    }

    public void b(Entity entity) {
        this.a(entity, entity.ag);
    }

    public void a(Entity entity, int i) {
        if (i < 0) {
            i = 0;
        }

        if (i >= this.entities.length) {
            i = this.entities.length - 1;
        }

        this.entities[i].remove(entity);
    }

    public boolean c(int i, int j, int k) {
        return j >= (this.heightMap[k << 4 | i] & 255);
    }

    public TileEntity d(int i, int j, int k) {
        ChunkPosition chunkposition = new ChunkPosition(i, j, k);
        TileEntity tileentity = (TileEntity) this.tileEntities.get(chunkposition);

        if (tileentity == null) {
            int l = this.a(i, j, k);

            if (!Block.q[l]) {
                return null;
            }

            BlockContainer blockcontainer = (BlockContainer) Block.n[l];

            blockcontainer.e(this.world, this.x * 16 + i, j, this.z * 16 + k);
            tileentity = (TileEntity) this.tileEntities.get(chunkposition);
        }

        return tileentity;
    }

    public void a(TileEntity tileentity) {
        int x = tileentity.x - this.x * 16;
        int y = tileentity.y;
        int z = tileentity.z - this.z * 16;

        this.a(x, y, z, tileentity);
    }

    public void a(int i, int j, int k, TileEntity tileEntity) {
        ChunkPosition chunkposition = new ChunkPosition(i, j, k);

        tileEntity.world = this.world;
        tileEntity.x = this.x * 16 + i;
        tileEntity.y = j;
        tileEntity.z = this.z * 16 + k;
        if (this.a(i, j, k) != 0 && Block.n[this.a(i, j, k)] instanceof BlockContainer) {
            if (this.c) {
                if (this.tileEntities.get(chunkposition) != null) {
                    this.world.tileEntities.remove(this.tileEntities.get(chunkposition));
                }

                this.world.tileEntities.add(tileEntity);
            }

            this.tileEntities.put(chunkposition, tileEntity);
        } else {
            System.out.println("Attempted to place a tile entity where there was no entity tile!");
        }
    }

    public void e(int i, int j, int k) {
        ChunkPosition chunkposition = new ChunkPosition(i, j, k);

        if (this.c) {
            this.world.tileEntities.remove(this.tileEntities.remove(chunkposition));
        }
    }

    public void d() {
        this.c = true;
        this.world.tileEntities.addAll(this.tileEntities.values());

        for (int i = 0; i < this.entities.length; ++i) {
            this.world.a(this.entities[i]);
        }
    }

    public void e() {
        this.c = false;
        this.world.tileEntities.removeAll(this.tileEntities.values());

        for (int i = 0; i < this.entities.length; ++i) {
            this.world.b(this.entities[i]);
        }
    }

    public void f() {
        this.o = true;
    }

    public void a(Entity entity, AxisAlignedBB axisalignedbb, List list) {
        int i = MathHelper.b((axisalignedbb.b - 2.0D) / 16.0D);
        int j = MathHelper.b((axisalignedbb.e + 2.0D) / 16.0D);

        if (i < 0) {
            i = 0;
        }

        if (j >= this.entities.length) {
            j = this.entities.length - 1;
        }

        for (int k = i; k <= j; ++k) {
            List list1 = this.entities[k];

            for (int l = 0; l < list1.size(); ++l) {
                Entity entity1 = (Entity) list1.get(l);

                if (entity1 != entity && entity1.boundingBox.a(axisalignedbb)) {
                    list.add(entity1);
                }
            }
        }
    }

    public void a(Class oclass, AxisAlignedBB axisalignedbb, List list) {
        int i = MathHelper.b((axisalignedbb.b - 2.0D) / 16.0D);
        int j = MathHelper.b((axisalignedbb.e + 2.0D) / 16.0D);

        if (i < 0) {
            i = 0;
        }

        if (j >= this.entities.length) {
            j = this.entities.length - 1;
        }

        for (int k = i; k <= j; ++k) {
            List list1 = this.entities[k];

            for (int l = 0; l < list1.size(); ++l) {
                Entity entity = (Entity) list1.get(l);

                if (oclass.isAssignableFrom(entity.getClass()) && entity.boundingBox.a(axisalignedbb)) {
                    list.add(entity);
                }
            }
        }
    }

    public boolean a(boolean flag) {
        return this.p ? false : (this.r && this.world.lastUpdate != this.s ? true : this.o);
    }

    public int a(byte[] abyte, int i, int j, int k, int l, int i1, int j1, int k1) {
        int l1;
        int i2;
        int j2;
        int k2;

        for (l1 = i; l1 < l; ++l1) {
            for (i2 = k; i2 < j1; ++i2) {
                j2 = l1 << 11 | i2 << 7 | j;
                k2 = i1 - j;
                System.arraycopy(this.blocks, j2, abyte, k1, k2);
                k1 += k2;
            }
        }

        for (l1 = i; l1 < l; ++l1) {
            for (i2 = k; i2 < j1; ++i2) {
                j2 = (l1 << 11 | i2 << 7 | j) >> 1;
                k2 = (i1 - j) / 2;
                System.arraycopy(this.blockData.data, j2, abyte, k1, k2);
                k1 += k2;
            }
        }

        for (l1 = i; l1 < l; ++l1) {
            for (i2 = k; i2 < j1; ++i2) {
                j2 = (l1 << 11 | i2 << 7 | j) >> 1;
                k2 = (i1 - j) / 2;
                System.arraycopy(this.blockLight.data, j2, abyte, k1, k2);
                k1 += k2;
            }
        }

        for (l1 = i; l1 < l; ++l1) {
            for (i2 = k; i2 < j1; ++i2) {
                j2 = (l1 << 11 | i2 << 7 | j) >> 1;
                k2 = (i1 - j) / 2;
                System.arraycopy(this.skyLight.data, j2, abyte, k1, k2);
                k1 += k2;
            }
        }

        return k1;
    }

    public Random a(long i) {
        return new Random(this.world.u + (long) (this.x * this.x * 4987142) + (long) (this.x * 5947611) + (long) (this.z * this.z) * 4392871L + (long) (this.z * 389711) ^ i);
    }
}
