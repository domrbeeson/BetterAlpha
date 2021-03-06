package net.minecraft.server.packet;

import net.minecraft.server.network.handler.NetHandler;
import net.minecraft.server.utils.MathHelper;
import net.minecraft.server.world.entity.Entity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet34EntityTeleport extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public byte e;
    public byte f;

    public Packet34EntityTeleport() {
    }

    public Packet34EntityTeleport(Entity entity) {
        this.a = entity.g;
        this.b = MathHelper.b(entity.p * 32.0D);
        this.c = MathHelper.b(entity.q * 32.0D);
        this.d = MathHelper.b(entity.r * 32.0D);
        this.e = (byte) ((int) (entity.v * 256.0F / 360.0F));
        this.f = (byte) ((int) (entity.w * 256.0F / 360.0F));
    }

    public Packet34EntityTeleport(int i, int j, int k, int l, byte b0, byte b1) {
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = l;
        this.e = b0;
        this.f = b1;
    }

    public void a(DataInputStream datainputstream) throws IOException {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readInt();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readInt();
        this.e = (byte) datainputstream.read();
        this.f = (byte) datainputstream.read();
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeInt(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeInt(this.d);
        dataoutputstream.write(this.e);
        dataoutputstream.write(this.f);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 34;
    }
}
