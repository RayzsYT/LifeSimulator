package de.lifesimulator.utils.helper;

public class FormatHelper {

    public static String formatInteger(int integer) {
        if(integer < 10) return 0 + "" + integer;
        else return String.valueOf(integer);
    }
}
