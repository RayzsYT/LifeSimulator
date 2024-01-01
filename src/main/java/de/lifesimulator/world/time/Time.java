package de.lifesimulator.world.time;

import de.lifesimulator.utils.helper.FormatHelper;

import java.text.NumberFormat;

public class Time {

    private final Week[] week = { Week.MONDAY, Week.TUESDAY, Week.WEDNESDAY, Week.THURSDAY, Week.FRIDAY, Week.SATURDAY, Week.SUNDAY };
    private long currentTimeMillis = System.currentTimeMillis();
    private Week day = Week.MONDAY;
    private boolean run = true;

    private int
            dayId = 0,

    seconds = 0,
            minutes = 0,
            hours = 23,

    days = 1,
            months = 1,
            years = 0;

    private double speed = 1.0;

    public Thread createThread() {
        return new Thread(() -> {
            while(true) {
                if (run && System.currentTimeMillis() - currentTimeMillis >= 1000 * speed) {
                    currentTimeMillis = System.currentTimeMillis();
                    updateClock();
                }
            }
        });
    }

    public boolean isStopped() {
        return !run;
    }

    public boolean isRunning() {
        return run;
    }

    public void stop() {
        run = false;
    }

    public void resume() {
        run = true;
    }

    public void setTimeSpeed(double speed) {
        if(speed <= 0.0 || speed > 1.0) return;
        this.speed = speed;
    }

    public Week getWeekDay() { return day; }
    public int getCalenderDay() { return days; }
    public int getCalenderMonth() { return months; }
    public int getCalenderYear() { return years; }

    protected void updateClock() {
        seconds++;

        if(seconds >= 60) {
            seconds = 0;
            minutes++;
        }
        if(minutes >= 60) {
            minutes = 0;
            hours++;
        }
        if(hours >= 24) {
            hours = 0;
            nextWeekDay();
        }
    }

    protected void nextWeekDay() {
        dayId++;
        if(dayId >= week.length) dayId = 0;

        day = week[dayId];
        nextDate();
    }

    protected void nextDate() {
        days++;
        if(days >= 31) {
            days = 1;
            months++;
        }
        if(months >= 13) {
            months = 1;
            years++;
        }
    }

    @Override
    public String toString() {
        return "Day=" + day.name() + "," + "Date=" + FormatHelper.formatInteger(days) + "." + FormatHelper.formatInteger(months) + "." + FormatHelper.formatInteger(years) + ",Clock=" + FormatHelper.formatInteger(seconds) + ":" + FormatHelper.formatInteger(minutes) + ":" + FormatHelper.formatInteger(hours);
    }

    public enum Week { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }
}
