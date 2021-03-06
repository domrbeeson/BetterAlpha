package net.minecraft.server.packet;

import net.minecraft.server.nbt.CompressedStreamTools;
import net.minecraft.server.nbt.NBTTagCompound;
import net.minecraft.server.network.handler.NetHandler;
import net.minecraft.server.world.block.tile.TileEntity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet59ComplexEntity extends Packet {

    public int a;
    public int b;
    public int c;
    public byte[] d;
    public NBTTagCompound e;

    public Packet59ComplexEntity() {
        this.j = true;
    }

    public Packet59ComplexEntity(int i, int j, int k, TileEntity tileentity) {
        this.j = true;
        this.a = i;
        this.b = j;
        this.c = k;
        this.e = new NBTTagCompound();
        tileentity.b(this.e);

        try {
            this.d = CompressedStreamTools.a(this.e);
        } catch (Exception ioexception) {
            ioexception.printStackTrace();
        }
    }

    public void a(DataInputStream datainputstream) throws IOException {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readShort();
        this.c = datainputstream.readInt();
        int i = datainputstream.readShort() & '\uffff';

        this.d = new byte[i];
        datainputstream.readFully(this.d);
        this.e = CompressedStreamTools.a(this.d);
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeShort(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeShort((short) this.d.length);
        dataoutputstream.write(this.d);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return this.d.length + 2 + 10;
    }
}
