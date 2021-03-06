package net.minecraft.server.packet;

import net.minecraft.server.item.ItemStack;
import net.minecraft.server.network.handler.NetHandler;
import net.minecraft.server.utils.MathHelper;
import net.minecraft.server.world.entity.impl.EntityHuman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet20NamedEntitySpawn extends Packet {

    public int a;
    public String b;
    public int c;
    public int d;
    public int e;
    public byte f;
    public byte g;
    public int h;

    public Packet20NamedEntitySpawn() {
    }

    public Packet20NamedEntitySpawn(EntityHuman entityhuman) {
        this.a = entityhuman.g;
        this.b = entityhuman.username;
        this.c = MathHelper.b(entityhuman.p * 32.0D);
        this.d = MathHelper.b(entityhuman.q * 32.0D);
        this.e = MathHelper.b(entityhuman.r * 32.0D);
        this.f = (byte) ((int) (entityhuman.v * 256.0F / 360.0F));
        this.g = (byte) ((int) (entityhuman.w * 256.0F / 360.0F));
        ItemStack itemstack = entityhuman.inventory.b();

        this.h = itemstack == null ? 0 : itemstack.c;
    }

    public void a(DataInputStream datainputstream) throws IOException {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readUTF();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readInt();
        this.e = datainputstream.readInt();
        this.f = datainputstream.readByte();
        this.g = datainputstream.readByte();
        this.h = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeUTF(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeInt(this.d);
        dataoutputstream.writeInt(this.e);
        dataoutputstream.writeByte(this.f);
        dataoutputstream.writeByte(this.g);
        dataoutputstream.writeShort(this.h);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 28;
    }
}
