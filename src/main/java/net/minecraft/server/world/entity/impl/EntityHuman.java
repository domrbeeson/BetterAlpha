package net.minecraft.server.world.entity.impl;

import net.minecraft.server.inventory.IInventory;
import net.minecraft.server.inventory.InventoryPlayer;
import net.minecraft.server.item.Item;
import net.minecraft.server.item.ItemStack;
import net.minecraft.server.nbt.NBTBase;
import net.minecraft.server.nbt.NBTTagCompound;
import net.minecraft.server.nbt.NBTTagList;
import net.minecraft.server.utils.MathHelper;
import net.minecraft.server.world.World;
import net.minecraft.server.world.block.Block;
import net.minecraft.server.world.block.material.Material;
import net.minecraft.server.world.block.tile.TileEntityFurnace;
import net.minecraft.server.world.block.tile.TileEntitySign;
import net.minecraft.server.world.entity.Entity;

import java.util.List;

public class EntityHuman extends EntityLiving {

    public InventoryPlayer inventory = new InventoryPlayer(this);
    public byte al = 0;
    public int am = 0;
    public float an;
    public float ao;
    public boolean ap = false;
    public int aq = 0;
    public String username;
    public int dimension;
    private int a = 0;
    public EntityFishingHook at = null;

    public EntityHuman(World world) {
        super(world);
        this.G = 1.62F;
        this.c((double) world.m + 0.5D, (double) (world.n + 1), (double) world.o + 0.5D, 0.0F, 0.0F);
        this.health = 20;
        this.aI = "humanoid";
        this.aH = 180.0F;
        this.X = 20;
        this.aF = "/char.png";
    }

    public void y() {
        super.y();
        this.an = this.ao;
        this.ao = 0.0F;
    }

    protected void c() {
        if (this.ap) {
            ++this.aq;
            if (this.aq == 8) {
                this.aq = 0;
                this.ap = false;
            }
        } else {
            this.aq = 0;
        }

        this.aO = (float) this.aq / 8.0F;
    }

    public void D() {
        if (this.world.k == 0 && this.health < 20 && this.W % 20 * 4 == 0) {
            this.a(1);
        }

        this.inventory.c();
        this.an = this.ao;
        super.D();
        float f = MathHelper.a(this.s * this.s + this.u * this.u);
        float f1 = (float) Math.atan(-this.t * 0.20000000298023224D) * 15.0F;

        if (f > 0.1F) {
            f = 0.1F;
        }

        if (!this.A || this.health <= 0) {
            f = 0.0F;
        }

        if (this.A || this.health <= 0) {
            f1 = 0.0F;
        }

        this.ao += (f - this.ao) * 0.4F;
        this.aX += (f1 - this.aX) * 0.8F;
        if (this.health > 0) {
            List list = this.world.b((Entity) this, this.boundingBox.b(1.0D, 0.0D, 1.0D));

            if (list != null) {
                for (int i = 0; i < list.size(); ++i) {
                    this.h((Entity) list.get(i));
                }
            }
        }
    }

    private void h(Entity entity) {
        entity.a(this);
    }


    @Override
    public void die(Entity entity) {
        this.a(0.2F, 0.2F);
        this.a(this.p, this.q, this.r);
        this.t = 0.10000000149011612D;
        if (this.username.equals("Notch")) {
            this.dropItem(new ItemStack(Item.APPLE, 1), true);
        }

        this.inventory.dropAll();
        if (entity != null) {
            this.s = (double) (-MathHelper.b((this.aT + this.v) * 3.1415927F / 180.0F) * 0.1F);
            this.u = (double) (-MathHelper.a((this.aT + this.v) * 3.1415927F / 180.0F) * 0.1F);
        } else {
            this.s = this.u = 0.0D;
        }

        this.G = 0.1F;
    }

    public void b(Entity entity, int i) {
        this.am += i;
    }

    public void dropItem(ItemStack itemstack) {
        this.dropItem(itemstack, false);
    }

    public void dropItem(ItemStack itemstack, boolean flag) {
        if (itemstack != null) {
            EntityItem entityitem = new EntityItem(this.world, this.p, this.q - 0.30000001192092896D + (double) this.s(), this.r, itemstack);

            entityitem.c = 40;
            float f = 0.1F;
            float f1;

            if (flag) {
                f1 = this.V.nextFloat() * 0.5F;
                float f2 = this.V.nextFloat() * 3.1415927F * 2.0F;

                entityitem.s = (double) (-MathHelper.a(f2) * f1);
                entityitem.u = (double) (MathHelper.b(f2) * f1);
                entityitem.t = 0.20000000298023224D;
            } else {
                f = 0.3F;
                entityitem.s = (double) (-MathHelper.a(this.v / 180.0F * 3.1415927F) * MathHelper.b(this.w / 180.0F * 3.1415927F) * f);
                entityitem.u = (double) (MathHelper.b(this.v / 180.0F * 3.1415927F) * MathHelper.b(this.w / 180.0F * 3.1415927F) * f);
                entityitem.t = (double) (-MathHelper.a(this.w / 180.0F * 3.1415927F) * f + 0.1F);
                f = 0.02F;
                f1 = this.V.nextFloat() * 3.1415927F * 2.0F;
                f *= this.V.nextFloat();
                entityitem.s += Math.cos((double) f1) * (double) f;
                entityitem.t += (double) ((this.V.nextFloat() - this.V.nextFloat()) * 0.1F);
                entityitem.u += Math.sin((double) f1) * (double) f;
            }

            this.a(entityitem);
        }
    }

    protected void a(EntityItem entityitem) {
        this.world.a((Entity) entityitem);
    }

    public float a(Block block) {
        float f = this.inventory.a(block);

        if (this.a(Material.f)) {
            f /= 5.0F;
        }

        if (!this.A) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean b(Block block) {
        return this.inventory.b(block);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.k("Inventory");

        this.inventory.b(nbttaglist);
        this.dimension = nbttagcompound.d("Dimension");
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.a("Inventory", (NBTBase) this.inventory.a(new NBTTagList()));
        nbttagcompound.a("Dimension", this.dimension);
    }

    public void a(IInventory iinventory) {
    }

    public void F() {
    }

    public void c(Entity entity, int i) {
    }

    public float s() {
        return 0.12F;
    }

    public boolean a(Entity entity, int i) {
        this.bf = 0;
        if (this.health <= 0) {
            return false;
        } else {
            if (entity instanceof EntityMonster || entity instanceof EntityArrow) {
                if (this.world.k == 0) {
                    i = 0;
                }

                if (this.world.k == 1) {
                    i = i / 3 + 1;
                }

                if (this.world.k == 3) {
                    i = i * 3 / 2;
                }
            }

            if ((float) this.ab <= (float) this.au / 2.0F) {
                int j = 25 - this.inventory.e();
                int k = i * j + this.a;

                this.inventory.b(i);
                i = k / 25;
                this.a = k % 25;
            }

            return i == 0 ? false : super.a(entity, i);
        }
    }

    public void a(TileEntityFurnace tileentityfurnace) {
    }

    public void a(TileEntitySign tileentitysign) {
    }

    public ItemStack G() {
        return this.inventory.b();
    }

    public void H() {
        this.inventory.setItem(this.inventory.d, (ItemStack) null);
    }

    public double A() {
        return (double) (this.G - 0.5F);
    }

    public void E() {
        this.aq = -1;
        this.ap = true;
    }
}
