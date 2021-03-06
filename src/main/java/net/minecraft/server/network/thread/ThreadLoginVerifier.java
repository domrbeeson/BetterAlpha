package net.minecraft.server.network.thread;

import net.minecraft.server.network.handler.NetLoginHandler;
import net.minecraft.server.packet.Packet1Login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ThreadLoginVerifier extends Thread {

    final Packet1Login a;

    final NetLoginHandler b;

    public ThreadLoginVerifier(NetLoginHandler netloginhandler, Packet1Login packet1login) {
        this.b = netloginhandler;
        this.a = packet1login;
    }

    public void run() {
        try {
            String s = NetLoginHandler.a(this.b);
            URL url = new URL("http://www.minecraft.net/game/checkserver.jsp?user=" + this.a.b + "&serverId=" + s);
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(url.openStream()));
            String s1 = bufferedreader.readLine();

            bufferedreader.close();
            System.out.println("THE REPLY IS " + s1);
            if (s1.equals("YES")) {
                NetLoginHandler.a(this.b, this.a);
            } else {
                this.b.b("Failed to verify username!");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
