package net.minecraft.server.network.thread;

import net.minecraft.server.MinecraftServer;

public final class ThreadServerApplication extends Thread {

    final MinecraftServer a;

    public ThreadServerApplication(String s, MinecraftServer minecraftserver) {
        super(s);
        this.a = minecraftserver;
    }

    public void run() {
        this.a.run();
    }
}
