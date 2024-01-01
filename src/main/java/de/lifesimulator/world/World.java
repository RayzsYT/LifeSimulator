package de.lifesimulator.world;

import de.lifesimulator.world.time.Time;
import java.io.IOException;
import java.util.*;

public class World {

    private final String worldName;
    private final Time time;
    private final List<Thread> threads = new ArrayList<>();

    public static World createWorld(String worldName) throws IOException {
        return new World(worldName);
    }

    public World(String worldName) {
        this.worldName = worldName;
        this.time = new Time();
        this.threads.add(time.createThread());


        this.threads.add(new Thread(() -> {
            while(true) {
                try {
                    System.out.write(("\r" + time).getBytes());
                    Thread.sleep(500);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }));

        this.threads.forEach(Thread::start);
    }

    public Time getTime() {
        return time;
    }

    public String getWorldName() {
        return worldName;
    }
}
