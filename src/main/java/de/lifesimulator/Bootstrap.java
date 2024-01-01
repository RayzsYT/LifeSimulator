package de.lifesimulator;

import de.lifesimulator.world.World;

import java.io.IOException;

public class Bootstrap {

    public static void main(String[] args) throws IOException {
        World.createWorld("Earth#1");
    }
}
