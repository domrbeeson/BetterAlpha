package net.minecraft.server.network;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.handler.NetLoginHandler;
import net.minecraft.server.network.handler.NetServerHandler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkListenThread {

    public static final Logger LOGGER = Logger.getLogger("Minecraft");
    private ServerSocket d;
    private Thread e;
    public volatile boolean b = false;
    private int f = 0;
    private ArrayList g = new ArrayList();
    private ArrayList h = new ArrayList();
    public MinecraftServer c;

    public NetworkListenThread(MinecraftServer minecraftserver, InetAddress inetaddress, int i) throws IOException {
        this.c = minecraftserver;
        this.d = new ServerSocket(i, 0, inetaddress);
        this.d.setPerformancePreferences(0, 2, 1);
        this.b = true;
        this.e = new NetworkAcceptThread(this, "Listen thread", minecraftserver);
        this.e.start();
    }

    public void a(NetServerHandler netserverhandler) {
        this.h.add(netserverhandler);
    }

    private void a(NetLoginHandler netloginhandler) {
        if (netloginhandler == null) {
            throw new IllegalArgumentException("Got null pendingconnection!");
        } else {
            this.g.add(netloginhandler);
        }
    }

    public void a() {
        int i;

        for (i = 0; i < this.g.size(); ++i) {
            NetLoginHandler netloginhandler = (NetLoginHandler) this.g.get(i);

            try {
                netloginhandler.a();
            } catch (Exception exception) {
                netloginhandler.b("Internal server error");
                LOGGER.log(Level.WARNING, "Failed to handle packet: " + exception, exception);
            }

            if (netloginhandler.c) {
                this.g.remove(i--);
            }
        }

        for (i = 0; i < this.h.size(); ++i) {
            NetServerHandler netserverhandler = (NetServerHandler) this.h.get(i);

            try {
                netserverhandler.a();
            } catch (Exception exception1) {
                netserverhandler.c("Internal server error");
                LOGGER.log(Level.WARNING, "Failed to handle packet: " + exception1, exception1);
            }

            if (netserverhandler.c) {
                this.h.remove(i--);
            }
        }
    }

    static ServerSocket a(NetworkListenThread networklistenthread) {
        return networklistenthread.d;
    }

    static int b(NetworkListenThread networklistenthread) {
        return networklistenthread.f++;
    }

    static void a(NetworkListenThread networklistenthread, NetLoginHandler netloginhandler) {
        networklistenthread.a(netloginhandler);
    }
}
