package net.minecraft.server.world.entity.impl;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.item.Item;
import net.minecraft.server.item.ItemInWorldManager;
import net.minecraft.server.item.ItemStack;
import net.minecraft.server.network.handler.NetServerHandler;
import net.minecraft.server.packet.*;
import net.minecraft.server.world.World;
import net.minecraft.server.world.WorldServer;
import net.minecraft.server.world.block.tile.TileEntity;
import net.minecraft.server.world.chunk.ChunkCoordIntPair;
import net.minecraft.server.world.entity.Entity;
import net.minecraft.server.world.entity.EntityTracker;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class EntityPlayer extends EntityHuman {

    public NetServerHandler netServerHandler;
    public MinecraftServer minecraftServer;
    public ItemInWorldManager itemInWorldManager;
    public double d;
    public double e;
    public List f = new LinkedList();
    public Set ai = new HashSet();
    public double aj;

    public EntityPlayer(MinecraftServer minecraftserver, World world, String name, ItemInWorldManager iteminworldmanager) {
        super(world);
        int i = world.m;
        int j = world.o;
        int k = world.n;

        if (!world.q.c) {
            i += this.V.nextInt(20) - 10;
            k = world.e(i, j);
            j += this.V.nextInt(20) - 10;
        }

        this.c((double) i + 0.5D, (double) k, (double) j + 0.5D, 0.0F, 0.0F);
        this.minecraftServer = minecraftserver;
        this.R = 0.0F;
        iteminworldmanager.a = this;
        this.name = name;
        this.itemInWorldManager = iteminworldmanager;
        this.G = 0.0F;
    }

    public void a(World world) {
        this.world = world;
        this.itemInWorldManager = new ItemInWorldManager(world);
        this.itemInWorldManager.a = this;
    }

    public void b_() {
    }

    public void die(Entity entity) {
    }

    public boolean hurt(Entity source, int amount) {
        super.hurt(source, amount);
        this.netServerHandler.sendPacket(new Packet8UpdateHealth(health));
        return true;
    }

    public void a(int i) {
    }

    public void k() {
        super.b_();
        ChunkCoordIntPair chunkcoordintpair = null;
        double d0 = 0.0D;

        for (int i = 0; i < this.f.size(); ++i) {
            ChunkCoordIntPair chunkcoordintpair1 = (ChunkCoordIntPair) this.f.get(i);
            double d1 = chunkcoordintpair1.a(this);

            if (i == 0 || d1 < d0) {
                chunkcoordintpair = chunkcoordintpair1;
                d0 = chunkcoordintpair1.a(this);
            }
        }

        if (chunkcoordintpair != null) {
            boolean flag = false;

            if (d0 < 1024.0D) {
                flag = true;
            }

            if (this.netServerHandler.b() < 2) {
                flag = true;
            }

            if (flag) {
                WorldServer worldserver = this.minecraftServer.getWorldByDimension(this.dimension);
                this.f.remove(chunkcoordintpair);
                this.netServerHandler.sendPacket((Packet) (new Packet51MapChunk(chunkcoordintpair.a * 16, 0, chunkcoordintpair.b * 16, 16, 128, 16, worldserver)));
                List list = worldserver.d(chunkcoordintpair.a * 16, 0, chunkcoordintpair.b * 16, chunkcoordintpair.a * 16 + 16, 128, chunkcoordintpair.b * 16 + 16);

                for (int j = 0; j < list.size(); ++j) {
                    TileEntity tileentity = (TileEntity) list.get(j);

                    this.netServerHandler.sendPacket((Packet) (new Packet59ComplexEntity(tileentity.x, tileentity.y, tileentity.z, tileentity)));
                }
            }
        }
    }

    public void D() {
        this.motX = this.motY = this.motZ = 0.0D;
        this.bj = false;
        super.D();
    }

    public void c(Entity entity, int i) {
        if (!entity.F) {
            EntityTracker entitytracker = this.minecraftServer.b(this.dimension);

            if (entity instanceof EntityItem) {
                this.netServerHandler.sendPacket((Packet) (new Packet17AddToInventory(((EntityItem) entity).a, i)));
                entitytracker.a(entity, new Packet22Collect(entity.g, this.g));
            }

            if (entity instanceof EntityArrow) {
                this.netServerHandler.sendPacket((Packet) (new Packet17AddToInventory(new ItemStack(Item.ARROW, 1), i)));
                entitytracker.a(entity, new Packet22Collect(entity.g, this.g));
            }
        }

        super.c(entity, i);
    }

    public void mount(Entity entity) {
        super.e(entity);
        this.netServerHandler.a(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
    }

    public void E() {
        if (!this.ap) {
            EntityTracker entitytracker = this.minecraftServer.b(this.dimension);
            this.aq = -1;
            this.ap = true;
            entitytracker.a(this, new Packet18ArmAnimation(this, 1));
        }
    }

    public float s() {
        return 1.62F;
    }
}
