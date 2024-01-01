package de.lifesimulator.world;

import de.lifesimulator.world.time.Time;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final String worldName;
    private final Time time;
    private final List<Thread> threads = new ArrayList<>();

    public static World createWorld(String worldName) {
        return new World(worldName);
    }

    public World(String worldName) {
        this.worldName = worldName;
        this.time = new Time();
        this.threads.add(time.createThread());
        this.threads.add(new Thread(() -> {
            while(true) {
                System.out.println(time);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }));

        time.setTimeSpeed(0.00000001);

        this.threads.forEach(Thread::start);
    }

    public Time getTime() {
        return time;
    }

    public String getWorldName() {
        return worldName;
    }
}
