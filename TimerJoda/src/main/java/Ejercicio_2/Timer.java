package Ejercicio_2;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;

public class Timer {

    private DateTime start;
    private DateTime end;
    private Period period;
    private boolean hasFinished = false;
    private String representation;

    public Timer(){
        this.start = DateTime.now();
    }

    public void Stop(){
        this.end = DateTime.now();
        this.hasFinished = true;
        this.period = new Period(this.start, this.end);
        this.representation = GetRepresentation();
    }

    private String GetRepresentation(){
        StringBuilder strBuilder = new StringBuilder();
        long millis = this.period.toStandardDuration().getMillis();
        this.period = this.period.normalizedStandard();
        String str = String.format("%02d days %02d hours %02d minutes %02d,%03d seconds ", period.getDays(), period.getHours(), period.getMinutes(), period.getSeconds(), period.getMillis());
        strBuilder.append("( " + millis + " ms)").append(str);
        return strBuilder.toString();
    }

    @Override
    public String toString() {
        if (!this.hasFinished)
            throw new RuntimeException("Timer wasn´t stopped.");
        return this.representation;
    }
}
