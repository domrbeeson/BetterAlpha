package net.minecraft.server.packet;

import net.minecraft.server.network.handler.NetHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet14BlockDig extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public Packet14BlockDig() {
    }

    public void a(DataInputStream datainputstream) throws IOException {
        this.e = datainputstream.read();
        this.a = datainputstream.readInt();
        this.b = datainputstream.read();
        this.c = datainputstream.readInt();
        this.d = datainputstream.read();
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.write(this.e);
        dataoutputstream.writeInt(this.a);
        dataoutputstream.write(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.write(this.d);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 11;
    }
}
