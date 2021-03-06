package net.minecraft.server.packet;

import net.minecraft.server.network.handler.NetHandler;
import net.minecraft.server.world.World;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet53BlockChange extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public Packet53BlockChange() {
        this.j = true;
    }

    public Packet53BlockChange(int i, int j, int k, World world) {
        this.j = true;
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = world.a(i, j, k);
        this.e = world.b(i, j, k);
    }

    public void a(DataInputStream datainputstream) throws IOException {
        this.a = datainputstream.readInt();
        this.b = datainputstream.read();
        this.c = datainputstream.readInt();
        this.d = datainputstream.read();
        this.e = datainputstream.read();
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.write(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.write(this.d);
        dataoutputstream.write(this.e);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 11;
    }
}
