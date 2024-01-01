package de.lifesimulator.world.time;

public class Time {

    private static final Week[] WEEK = { Week.MONDAY, Week.TUESDAY, Week.WEDNESDAY, Week.THURSDAY, Week.FRIDAY, Week.SATURDAY, Week.SUNDAY };
    private static long CURRENT_TIME = System.currentTimeMillis();
    private static Week DAY = Week.MONDAY;
    private static boolean RUN = true;

    private static int
            DAY_ID = 0,

            SECONDS = 0,
            MINUTES = 0,
            HOURS = 0,

            DAYS = 1,
            MONTHS = 1,
            YEARS = 0;

    static {
        new Thread(() -> {
            while(true) if(RUN)
                if(CURRENT_TIME - System.currentTimeMillis() >= 1000) {
                    CURRENT_TIME = System.currentTimeMillis();
                    updateClock();
                }
        }).run();
    }

    public static Week getWeekDay() { return DAY; }
    public static int getCalenderDay() { return DAYS; }
    public static int getCalenderMonth() { return MONTHS; }
    public static int getCalenderYear() { return YEARS; }

   protected static void updateClock() {
        if(SECONDS >= 60) {
            SECONDS = 0;
            MINUTES++;
        }
        if(MINUTES >= 60) {
            MINUTES = 0;
            HOURS++;
        }
        if(HOURS >= 24) {
            HOURS = 0;
            nextDay();
        }
    }

    protected static void nextDay() {
        nextDate();
        if(DAY_ID >= WEEK.length) DAY_ID = 0;
        else DAY_ID = DAY_ID++;
        DAY = WEEK[DAY_ID];
    }

    protected static void nextDate() {
        DAYS++;
        if(DAYS >= 31) {
            DAYS = 1;
            MONTHS++;
        }
        if(MONTHS >= 13) {
            MONTHS = 1;
            YEARS++;
        }
    }

    @Override
    public String toString() {
        return "Day=" + DAY.name() + "," + "Date=" + DAYS + "." + MONTHS + "." + YEARS + ",Clock=" + SECONDS + ":" + MINUTES + ":" + HOURS;
    }

    public enum Week { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }
}
