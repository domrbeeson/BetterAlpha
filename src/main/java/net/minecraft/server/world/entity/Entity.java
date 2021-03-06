package net.minecraft.server.world.entity;

import net.minecraft.server.item.ItemStack;
import net.minecraft.server.nbt.*;
import net.minecraft.server.utils.AxisAlignedBB;
import net.minecraft.server.utils.MathHelper;
import net.minecraft.server.utils.Vec3D;
import net.minecraft.server.world.World;
import net.minecraft.server.world.block.Block;
import net.minecraft.server.world.block.BlockFluids;
import net.minecraft.server.world.block.material.Material;
import net.minecraft.server.world.block.sound.StepSound;
import net.minecraft.server.world.entity.impl.EntityHuman;
import net.minecraft.server.world.entity.impl.EntityItem;

import java.util.List;
import java.util.Random;

public abstract class Entity {

    private static int a = 0;
    public int g;
    public double h;
    public boolean i;
    public Entity j;
    public Entity k;
    public World world;
    public double m;
    public double n;
    public double o;
    public double p;
    public double q;
    public double r;
    public double s;
    public double t;
    public double u;
    public float v;
    public float w;
    public float x;
    public float y;
    public final AxisAlignedBB boundingBox;
    public boolean A;
    public boolean B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public float G;
    public float H;
    public float I;
    public float J;
    public float K;
    public boolean L;
    public float M;
    private int b;
    public double N;
    public double O;
    public double P;
    public float Q;
    public float R;
    public boolean S;
    public float T;
    public boolean U;
    public Random V;
    public int W;
    public int X;
    public int Y;
    public int Z;
    public boolean aa;
    public int ab;
    public int ac;
    private boolean c;
    public boolean ad;
    private double d;
    private double e;
    public boolean ae;
    public int af;
    public int ag;
    public int ah;

    public Entity(World world) {
        this.g = a++;
        this.h = 1.0D;
        this.i = false;
        this.boundingBox = AxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.A = false;
        this.D = false;
        this.E = true;
        this.F = false;
        this.G = 0.0F;
        this.H = 0.6F;
        this.I = 1.8F;
        this.J = 0.0F;
        this.K = 0.0F;
        this.L = true;
        this.M = 0.0F;
        this.b = 1;
        this.Q = 0.0F;
        this.R = 0.0F;
        this.S = false;
        this.T = 0.0F;
        this.U = false;
        this.V = new Random();
        this.W = 0;
        this.X = 1;
        this.Y = 0;
        this.Z = 300;
        this.aa = false;
        this.ab = 0;
        this.ac = 300;
        this.c = true;
        this.ad = false;
        this.ae = false;
        this.world = world;
        this.a(0.0D, 0.0D, 0.0D);
    }

    public boolean equals(Object object) {
        return object instanceof Entity ? ((Entity) object).g == this.g : false;
    }

    public int hashCode() {
        return this.g;
    }

    public void l() {
        this.F = true;
    }

    public void a(float f, float f1) {
        this.H = f;
        this.I = f1;
    }

    public void b(float f, float f1) {
        this.v = f;
        this.w = f1;
    }

    public void a(double d0, double d1, double d2) {
        this.p = d0;
        this.q = d1;
        this.r = d2;
        float f = this.H / 2.0F;
        float f1 = this.I;

        this.boundingBox.c(d0 - (double) f, d1 - (double) this.G + (double) this.Q, d2 - (double) f, d0 + (double) f, d1 - (double) this.G + (double) this.Q + (double) f1, d2 + (double) f);
    }

    public void b_() {
        this.m();
    }

    public void m() {
        if (this.k != null && this.k.F) {
            this.k = null;
        }

        ++this.W;
        this.J = this.K;
        this.m = this.p;
        this.n = this.q;
        this.o = this.r;
        this.y = this.w;
        this.x = this.v;
        if (this.r()) {
            if (!this.aa && !this.c) {
                float f = MathHelper.a(this.s * this.s * 0.20000000298023224D + this.t * this.t + this.u * this.u * 0.20000000298023224D) * 0.2F;

                if (f > 1.0F) {
                    f = 1.0F;
                }

                this.world.a(this, "random.splash", f, 1.0F + (this.V.nextFloat() - this.V.nextFloat()) * 0.4F);
                float f1 = (float) MathHelper.b(this.boundingBox.b);

                int i;
                float f2;
                float f3;

                for (i = 0; (float) i < 1.0F + this.H * 20.0F; ++i) {
                    f2 = (this.V.nextFloat() * 2.0F - 1.0F) * this.H;
                    f3 = (this.V.nextFloat() * 2.0F - 1.0F) * this.H;
                    this.world.a("bubble", this.p + (double) f2, (double) (f1 + 1.0F), this.r + (double) f3, this.s, this.t - (double) (this.V.nextFloat() * 0.2F), this.u);
                }

                for (i = 0; (float) i < 1.0F + this.H * 20.0F; ++i) {
                    f2 = (this.V.nextFloat() * 2.0F - 1.0F) * this.H;
                    f3 = (this.V.nextFloat() * 2.0F - 1.0F) * this.H;
                    this.world.a("splash", this.p + (double) f2, (double) (f1 + 1.0F), this.r + (double) f3, this.s, this.t, this.u);
                }
            }

            this.M = 0.0F;
            this.aa = true;
            this.Y = 0;
        } else {
            this.aa = false;
        }

        if (this.Y > 0) {
            if (this.ad) {
                this.Y -= 4;
                if (this.Y < 0) {
                    this.Y = 0;
                }
            } else {
                if (this.Y % 20 == 0) {
                    this.a((Entity) null, 1);
                }

                --this.Y;
            }
        }

        if (this.t()) {
            this.n();
        }

        if (this.q < -64.0D) {
            this.o();
        }

        this.c = false;
    }

    public void n() {
        if (this.ad) {
            this.Y = 600;
        } else {
            this.a((Entity) null, 4);
            this.Y = 600;
        }
    }

    public void o() {
        this.l();
    }

    public boolean b(double d0, double d1, double d2) {
        AxisAlignedBB axisalignedbb = this.boundingBox.c(d0, d1, d2);
        List list = this.world.a(this, axisalignedbb);

        return list.size() > 0 ? false : !this.world.b(axisalignedbb);
    }

    public void c(double d0, double d1, double d2) {
        if (this.S) {
            this.boundingBox.d(d0, d1, d2);
            this.p = (this.boundingBox.a + this.boundingBox.d) / 2.0D;
            this.q = this.boundingBox.b + (double) this.G - (double) this.Q;
            this.r = (this.boundingBox.c + this.boundingBox.f) / 2.0D;
        } else {
            double d3 = this.p;
            double d4 = this.r;
            double d5 = d0;
            double d6 = d1;
            double d7 = d2;
            AxisAlignedBB axisalignedbb = this.boundingBox.b();
            boolean flag = this.A && this.p();

            if (flag) {
                double d8;

                for (d8 = 0.05D; d0 != 0.0D && this.world.a(this, this.boundingBox.c(d0, -1.0D, 0.0D)).size() == 0; d5 = d0) {
                    if (d0 < d8 && d0 >= -d8) {
                        d0 = 0.0D;
                    } else if (d0 > 0.0D) {
                        d0 -= d8;
                    } else {
                        d0 += d8;
                    }
                }

                for (; d2 != 0.0D && this.world.a(this, this.boundingBox.c(0.0D, -1.0D, d2)).size() == 0; d7 = d2) {
                    if (d2 < d8 && d2 >= -d8) {
                        d2 = 0.0D;
                    } else if (d2 > 0.0D) {
                        d2 -= d8;
                    } else {
                        d2 += d8;
                    }
                }
            }

            List list = this.world.a(this, this.boundingBox.a(d0, d1, d2));

            for (int i = 0; i < list.size(); ++i) {
                d1 = ((AxisAlignedBB) list.get(i)).b(this.boundingBox, d1);
            }

            this.boundingBox.d(0.0D, d1, 0.0D);
            if (!this.E && d6 != d1) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            boolean flag1 = this.A || d6 != d1 && d6 < 0.0D;

            int j;

            for (j = 0; j < list.size(); ++j) {
                d0 = ((AxisAlignedBB) list.get(j)).a(this.boundingBox, d0);
            }

            this.boundingBox.d(d0, 0.0D, 0.0D);
            if (!this.E && d5 != d0) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            for (j = 0; j < list.size(); ++j) {
                d2 = ((AxisAlignedBB) list.get(j)).c(this.boundingBox, d2);
            }

            this.boundingBox.d(0.0D, 0.0D, d2);
            if (!this.E && d7 != d2) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            double d9;
            double d10;
            int k;

            if (this.R > 0.0F && flag1 && this.Q < 0.05F && (d5 != d0 || d7 != d2)) {
                d9 = d0;
                d10 = d1;
                double d11 = d2;

                d0 = d5;
                d1 = (double) this.R;
                d2 = d7;
                AxisAlignedBB axisalignedbb1 = this.boundingBox.b();

                this.boundingBox.b(axisalignedbb);
                list = this.world.a(this, this.boundingBox.a(d5, d1, d7));

                for (k = 0; k < list.size(); ++k) {
                    d1 = ((AxisAlignedBB) list.get(k)).b(this.boundingBox, d1);
                }

                this.boundingBox.d(0.0D, d1, 0.0D);
                if (!this.E && d6 != d1) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                for (k = 0; k < list.size(); ++k) {
                    d0 = ((AxisAlignedBB) list.get(k)).a(this.boundingBox, d0);
                }

                this.boundingBox.d(d0, 0.0D, 0.0D);
                if (!this.E && d5 != d0) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                for (k = 0; k < list.size(); ++k) {
                    d2 = ((AxisAlignedBB) list.get(k)).c(this.boundingBox, d2);
                }

                this.boundingBox.d(0.0D, 0.0D, d2);
                if (!this.E && d7 != d2) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                if (d9 * d9 + d11 * d11 >= d0 * d0 + d2 * d2) {
                    d0 = d9;
                    d1 = d10;
                    d2 = d11;
                    this.boundingBox.b(axisalignedbb1);
                } else {
                    this.Q = (float) ((double) this.Q + 0.5D);
                }
            }

            this.p = (this.boundingBox.a + this.boundingBox.d) / 2.0D;
            this.q = this.boundingBox.b + (double) this.G - (double) this.Q;
            this.r = (this.boundingBox.c + this.boundingBox.f) / 2.0D;
            this.B = d5 != d0 || d7 != d2;
            this.C = d6 != d1;
            this.A = d6 != d1 && d6 < 0.0D;
            this.D = this.B || this.C;
            if (this.A) {
                if (this.M > 0.0F) {
                    this.a(this.M);
                    this.M = 0.0F;
                }
            } else if (d1 < 0.0D) {
                this.M = (float) ((double) this.M - d1);
            }

            if (d5 != d0) {
                this.s = 0.0D;
            }

            if (d6 != d1) {
                this.t = 0.0D;
            }

            if (d7 != d2) {
                this.u = 0.0D;
            }

            d9 = this.p - d3;
            d10 = this.r - d4;
            this.K = (float) ((double) this.K + (double) MathHelper.a(d9 * d9 + d10 * d10) * 0.6D);
            int l;
            int i1;
            int j1;

            if (this.L && !flag) {
                l = MathHelper.b(this.p);
                i1 = MathHelper.b(this.q - 0.20000000298023224D - (double) this.G);
                j1 = MathHelper.b(this.r);
                k = this.world.a(l, i1, j1);
                if (this.K > (float) this.b && k > 0) {
                    ++this.b;
                    StepSound stepsound = Block.n[k].br;

                    if (this.world.a(l, i1 + 1, j1) == Block.SNOW.bi) {
                        stepsound = Block.SNOW.br;
                        this.world.a(this, stepsound.c(), stepsound.a() * 0.15F, stepsound.b());
                    } else if (!Block.n[k].bt.d()) {
                        this.world.a(this, stepsound.c(), stepsound.a() * 0.15F, stepsound.b());
                    }

                    Block.n[k].b(this.world, l, i1, j1, this);
                }
            }

            l = MathHelper.b(this.boundingBox.a);
            i1 = MathHelper.b(this.boundingBox.b);
            j1 = MathHelper.b(this.boundingBox.c);
            k = MathHelper.b(this.boundingBox.d);
            int k1 = MathHelper.b(this.boundingBox.e);
            int l1 = MathHelper.b(this.boundingBox.f);

            for (int i2 = l; i2 <= k; ++i2) {
                for (int j2 = i1; j2 <= k1; ++j2) {
                    for (int k2 = j1; k2 <= l1; ++k2) {
                        int l2 = this.world.a(i2, j2, k2);

                        if (l2 > 0) {
                            Block.n[l2].a(this.world, i2, j2, k2, this);
                        }
                    }
                }
            }

            this.Q *= 0.4F;
            boolean flag2 = this.r();

            if (this.world.c(this.boundingBox)) {
                this.b(1);
                if (!flag2) {
                    ++this.Y;
                    if (this.Y == 0) {
                        this.Y = 300;
                    }
                }
            } else if (this.Y <= 0) {
                this.Y = -this.X;
            }

            if (flag2 && this.Y > 0) {
                this.world.a(this, "random.fizz", 0.7F, 1.6F + (this.V.nextFloat() - this.V.nextFloat()) * 0.4F);
                this.Y = -this.X;
            }
        }
    }

    public boolean p() {
        return false;
    }

    public AxisAlignedBB q() {
        return null;
    }

    public void b(int i) {
        this.a((Entity) null, i);
    }

    public void a(float f) {
    }

    public boolean r() {
        return this.world.a(this.boundingBox.b(0.0D, -0.4000000059604645D, 0.0D), Material.f, this);
    }

    public boolean a(Material material) {
        double d0 = this.q + (double) this.s();
        int i = MathHelper.b(this.p);
        int j = MathHelper.d((float) MathHelper.b(d0));
        int k = MathHelper.b(this.r);
        int l = this.world.a(i, j, k);

        if (l != 0 && Block.n[l].bt == material) {
            float f = BlockFluids.b(this.world.b(i, j, k)) - 0.11111111F;
            float f1 = (float) (j + 1) - f;

            return d0 < (double) f1;
        } else {
            return false;
        }
    }

    public float s() {
        return 0.0F;
    }

    public boolean t() {
        return this.world.a(this.boundingBox.b(0.0D, -0.4000000059604645D, 0.0D), Material.g);
    }

    public void a(float f, float f1, float f2) {
        float f3 = MathHelper.c(f * f + f1 * f1);

        if (f3 >= 0.01F) {
            if (f3 < 1.0F) {
                f3 = 1.0F;
            }

            f3 = f2 / f3;
            f *= f3;
            f1 *= f3;
            float f4 = MathHelper.a(this.v * 3.1415927F / 180.0F);
            float f5 = MathHelper.b(this.v * 3.1415927F / 180.0F);

            this.s += (double) (f * f5 - f1 * f4);
            this.u += (double) (f1 * f5 + f * f4);
        }
    }

    public float b(float f) {
        int i = MathHelper.b(this.p);
        double d0 = (this.boundingBox.e - this.boundingBox.b) * 0.66D;
        int j = MathHelper.b(this.q - (double) this.G + d0);
        int k = MathHelper.b(this.r);

        return this.world.j(i, j, k);
    }

    public void spawnIn(World world) {
        this.world = world;
    }

    public void b(double d0, double d1, double d2, float f, float f1) {
        this.m = this.p = d0;
        this.n = this.q = d1;
        this.o = this.r = d2;
        this.v = f;
        this.w = f1;
        this.Q = 0.0F;
        double d3 = (double) (this.x - f);

        if (d3 < -180.0D) {
            this.x += 360.0F;
        }

        if (d3 >= 180.0D) {
            this.x -= 360.0F;
        }

        this.a(this.p, this.q, this.r);
    }

    public void c(double d0, double d1, double d2, float f, float f1) {
        this.m = this.p = d0;
        this.n = this.q = d1 + (double) this.G;
        this.o = this.r = d2;
        this.v = f;
        this.w = f1;
        this.a(this.p, this.q, this.r);
    }

    public float a(Entity entity) {
        float f = (float) (this.p - entity.p);
        float f1 = (float) (this.q - entity.q);
        float f2 = (float) (this.r - entity.r);

        return MathHelper.c(f * f + f1 * f1 + f2 * f2);
    }

    public double d(double d0, double d1, double d2) {
        double d3 = this.p - d0;
        double d4 = this.q - d1;
        double d5 = this.r - d2;

        return d3 * d3 + d4 * d4 + d5 * d5;
    }

    public double e(double d0, double d1, double d2) {
        double d3 = this.p - d0;
        double d4 = this.q - d1;
        double d5 = this.r - d2;

        return (double) MathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);
    }

    public double b(Entity entity) {
        double d0 = this.p - entity.p;
        double d1 = this.q - entity.q;
        double d2 = this.r - entity.r;

        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public void a(EntityHuman entityhuman) {
    }

    public void c(Entity entity) {
        if (entity.j != this && entity.k != this) {
            double d0 = entity.p - this.p;
            double d1 = entity.r - this.r;
            double d2 = MathHelper.a(d0, d1);

            if (d2 >= 0.009999999776482582D) {
                d2 = (double) MathHelper.a(d2);
                d0 /= d2;
                d1 /= d2;
                double d3 = 1.0D / d2;

                if (d3 > 1.0D) {
                    d3 = 1.0D;
                }

                d0 *= d3;
                d1 *= d3;
                d0 *= 0.05000000074505806D;
                d1 *= 0.05000000074505806D;
                d0 *= (double) (1.0F - this.T);
                d1 *= (double) (1.0F - this.T);
                this.f(-d0, 0.0D, -d1);
                entity.f(d0, 0.0D, d1);
            }
        }
    }

    public void f(double d0, double d1, double d2) {
        this.s += d0;
        this.t += d1;
        this.u += d2;
    }

    public boolean a(Entity entity, int i) {
        return false;
    }

    public boolean c_() {
        return false;
    }

    public boolean u() {
        return false;
    }

    public void b(Entity entity, int i) {
    }

    public boolean c(NBTTagCompound nbttagcompound) {
        String s = this.v();

        if (!this.F && s != null) {
            nbttagcompound.a("id", s);
            this.d(nbttagcompound);
            return true;
        } else {
            return false;
        }
    }

    public void d(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Pos", (NBTBase) this.a(new double[]{this.p, this.q, this.r}));
        nbttagcompound.a("Motion", (NBTBase) this.a(new double[]{this.s, this.t, this.u}));
        nbttagcompound.a("Rotation", (NBTBase) this.a(new float[]{this.v, this.w}));
        nbttagcompound.a("FallDistance", this.M);
        nbttagcompound.a("Fire", (short) this.Y);
        nbttagcompound.a("Air", (short) this.ac);
        nbttagcompound.a("OnGround", this.A);
        this.a(nbttagcompound);
    }

    public void e(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = nbttagcompound.k("Pos");
        NBTTagList nbttaglist1 = nbttagcompound.k("Motion");
        NBTTagList nbttaglist2 = nbttagcompound.k("Rotation");

        this.a(0.0D, 0.0D, 0.0D);
        this.s = ((NBTTagDouble) nbttaglist1.a(0)).a;
        this.t = ((NBTTagDouble) nbttaglist1.a(1)).a;
        this.u = ((NBTTagDouble) nbttaglist1.a(2)).a;
        this.m = this.N = this.p = ((NBTTagDouble) nbttaglist.a(0)).a;
        this.n = this.O = this.q = ((NBTTagDouble) nbttaglist.a(1)).a;
        this.o = this.P = this.r = ((NBTTagDouble) nbttaglist.a(2)).a;
        this.x = this.v = ((NBTTagFloat) nbttaglist2.a(0)).a;
        this.y = this.w = ((NBTTagFloat) nbttaglist2.a(1)).a;
        this.M = nbttagcompound.f("FallDistance");
        this.Y = nbttagcompound.c("Fire");
        this.ac = nbttagcompound.c("Air");
        this.A = nbttagcompound.l("OnGround");
        this.a(this.p, this.q, this.r);
        this.b(nbttagcompound);
    }

    public final String v() {
        return EntityTypes.b(this);
    }

    public abstract void b(NBTTagCompound nbttagcompound);

    public abstract void a(NBTTagCompound nbttagcompound);

    public NBTTagList a(double... adouble) {
        NBTTagList nbttaglist = new NBTTagList();
        double[] adouble1 = adouble;
        int i = adouble.length;

        for (int j = 0; j < i; ++j) {
            double d0 = adouble1[j];

            nbttaglist.a((NBTBase) (new NBTTagDouble(d0)));
        }

        return nbttaglist;
    }

    public NBTTagList a(float... afloat) {
        NBTTagList nbttaglist = new NBTTagList();
        float[] afloat1 = afloat;
        int i = afloat.length;

        for (int j = 0; j < i; ++j) {
            float f = afloat1[j];

            nbttaglist.a((NBTBase) (new NBTTagFloat(f)));
        }

        return nbttaglist;
    }

    public EntityItem a(int i, int j) {
        return this.a(i, j, 0.0F);
    }

    public EntityItem a(int i, int j, float f) {
        EntityItem entityitem = new EntityItem(this.world, this.p, this.q + (double) f, this.r, new ItemStack(i, j));

        entityitem.c = 10;
        this.world.a((Entity) entityitem);
        return entityitem;
    }

    public boolean w() {
        return !this.F;
    }

    public boolean x() {
        int i = MathHelper.b(this.p);
        int j = MathHelper.b(this.q + (double) this.s());
        int k = MathHelper.b(this.r);

        return this.world.d(i, j, k);
    }

    public AxisAlignedBB d(Entity entity) {
        return null;
    }

    public void y() {
        if (this.k.F) {
            this.k = null;
        } else {
            this.s = 0.0D;
            this.t = 0.0D;
            this.u = 0.0D;
            this.b_();
            this.k.z();
            this.e += (double) (this.k.v - this.k.x);

            for (this.d += (double) (this.k.w - this.k.y); this.e >= 180.0D; this.e -= 360.0D) {
                ;
            }

            while (this.e < -180.0D) {
                this.e += 360.0D;
            }

            while (this.d >= 180.0D) {
                this.d -= 360.0D;
            }

            while (this.d < -180.0D) {
                this.d += 360.0D;
            }

            double d0 = this.e * 0.5D;
            double d1 = this.d * 0.5D;
            float f = 10.0F;

            if (d0 > (double) f) {
                d0 = (double) f;
            }

            if (d0 < (double) (-f)) {
                d0 = (double) (-f);
            }

            if (d1 > (double) f) {
                d1 = (double) f;
            }

            if (d1 < (double) (-f)) {
                d1 = (double) (-f);
            }

            this.e -= d0;
            this.d -= d1;
            this.v = (float) ((double) this.v + d0);
            this.w = (float) ((double) this.w + d1);
        }
    }

    public void z() {
        this.j.a(this.p, this.q + this.j() + this.j.A(), this.r);
    }

    public double A() {
        return (double) this.G;
    }

    public double j() {
        return (double) this.I * 0.75D;
    }

    public void e(Entity entity) {
        this.d = 0.0D;
        this.e = 0.0D;
        if (this.k == entity) {
            this.k.j = null;
            this.k = null;
            this.c(entity.p, entity.boundingBox.b + (double) entity.I, entity.r, this.v, this.w);
        } else {
            if (this.k != null) {
                this.k.j = null;
            }

            if (entity.j != null) {
                entity.j.k = null;
            }

            this.k = entity;
            entity.j = this;
        }
    }

    public Vec3D B() {
        return null;
    }

    public void C() {
    }
}
