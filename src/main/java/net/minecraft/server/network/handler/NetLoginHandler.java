package net.minecraft.server.network.handler;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.NetworkManager;
import net.minecraft.server.network.thread.ThreadLoginVerifier;
import net.minecraft.server.packet.*;
import net.minecraft.server.world.WorldServer;
import net.minecraft.server.world.entity.impl.EntityPlayer;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Logger;

public class NetLoginHandler extends NetHandler {

    public static final Logger LOGGER = Logger.getLogger("Minecraft");
    public static final Random RANDOM = new Random();
    public static final int MINIMUM_VERSION = 6; // Alpha 1.2.6

    public NetworkManager b;
    public boolean c = false;
    private final MinecraftServer e;
    private int f = 0;
    private String g = null;
    private Packet1Login h = null;
    private String i = "";

    public NetLoginHandler(MinecraftServer minecraftserver, Socket socket, String s) throws IOException {
        this.e = minecraftserver;
        this.b = new NetworkManager(socket, s, this);
    }

    public void a() {
        if (this.h != null) {
            this.b(this.h);
            this.h = null;
        }

        if (this.f++ == 100) {
            this.b("Took too long to log in");
        } else {
            this.b.a();
        }
    }

    public void b(String s) {
        LOGGER.info("Disconnecting " + this.b() + ": " + s);
        this.b.a(new Packet255KickDisconnect(s));
        this.b.c();
        this.c = true;
    }

    public void a(Packet2Handshake packet2handshake) {
        if (this.e.l) {
            this.i = Long.toHexString(RANDOM.nextLong());
            this.b.a(new Packet2Handshake(this.i));
        } else {
            this.b.a(new Packet2Handshake("-"));
        }
    }

    public void a(Packet1Login packet1login) {
        this.g = packet1login.b;
        LOGGER.info("Client connected with version: " + packet1login.a);
        if (packet1login.a != MINIMUM_VERSION) {
            if (packet1login.a > MINIMUM_VERSION) {
                this.b("Outdated server!");
            } else {
                this.b("Outdated client!");
            }
        } else {
            if (!this.e.l) {
                this.b(packet1login);
            } else {
                (new ThreadLoginVerifier(this, packet1login)).start();
            }
        }
    }

    public void b(Packet1Login packet1login) {
        EntityPlayer entityplayer = this.e.f.a(this, packet1login.b, packet1login.c);
        WorldServer worldserver = this.e.getWorldByDimension(entityplayer.dimension);

        if (entityplayer != null) {
            LOGGER.info(this.b() + " logged in");
            NetServerHandler netserverhandler = new NetServerHandler(this.e, this.b, entityplayer);

            netserverhandler.b(new Packet1Login("", "", 0, worldserver.u, (byte) worldserver.q.e));
            netserverhandler.b(new Packet6SpawnPosition(worldserver.m, worldserver.n, worldserver.o));
            this.e.f.a(entityplayer);
            netserverhandler.a(entityplayer.p, entityplayer.q, entityplayer.r, entityplayer.v, entityplayer.w);
            netserverhandler.d();
            this.e.c.a(netserverhandler);
            netserverhandler.b(new Packet4UpdateTime(worldserver.lastUpdate));
        }

        this.c = true;
    }

    public void a(String s) {
        LOGGER.info(this.b() + " lost connection");
        this.c = true;
    }

    public void a(Packet packet) {
        this.b("Protocol error");
    }

    public String b() {
        return this.g != null ? this.g + " [" + this.b.b().toString() + "]" : this.b.b().toString();
    }

    public static String a(NetLoginHandler netloginhandler) {
        return netloginhandler.i;
    }

    public static Packet1Login a(NetLoginHandler netloginhandler, Packet1Login packet1login) {
        return netloginhandler.h = packet1login;
    }
}
