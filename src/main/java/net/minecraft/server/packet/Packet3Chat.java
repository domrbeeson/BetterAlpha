package net.minecraft.server.packet;

import net.minecraft.server.network.handler.NetHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet3Chat extends Packet {

    public String a;

    public Packet3Chat() {
    }

    public Packet3Chat(String s) {
        this.a = s;
    }

    public void a(DataInputStream datainputstream) throws IOException {
        this.a = datainputstream.readUTF();
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeUTF(this.a);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return this.a.length();
    }
}
