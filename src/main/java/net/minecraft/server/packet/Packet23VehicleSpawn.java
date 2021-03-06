package net.minecraft.server.packet;

import net.minecraft.server.network.handler.NetHandler;
import net.minecraft.server.utils.MathHelper;
import net.minecraft.server.world.entity.Entity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet23VehicleSpawn extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public Packet23VehicleSpawn() {
    }

    public Packet23VehicleSpawn(Entity entity, int i) {
        this.a = entity.g;
        this.b = MathHelper.b(entity.p * 32.0D);
        this.c = MathHelper.b(entity.q * 32.0D);
        this.d = MathHelper.b(entity.r * 32.0D);
        this.e = i;
    }

    public void a(DataInputStream datainputstream) throws IOException {
        this.a = datainputstream.readInt();
        this.e = datainputstream.readByte();
        this.b = datainputstream.readInt();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeByte(this.e);
        dataoutputstream.writeInt(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeInt(this.d);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 17;
    }
}
