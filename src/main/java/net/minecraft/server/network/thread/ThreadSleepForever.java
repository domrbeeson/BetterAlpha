package net.minecraft.server.network.thread;

import net.minecraft.server.MinecraftServer;

public class ThreadSleepForever extends Thread {

    final MinecraftServer a;

    public ThreadSleepForever(MinecraftServer minecraftserver) {
        this.a = minecraftserver;
        this.setDaemon(true);
        this.start();
    }

    public void run() {
        while (true) {
            try {
                while (true) {
                    Thread.sleep(2147483647L);
                }
            } catch (InterruptedException interruptedexception) {
                ;
            }
        }
    }
}
