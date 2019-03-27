package TimerJava;

import java.time.Duration;
import java.time.Instant;

public class Timer {

    private final int HOURS_IN_DAY = 24;
    private final int MINUTES_IN_HOUR = 60;
    private final int SECONDS_IN_MINUTE = 60;
    private final int MILLIS_IN_SECOND = 1000;

    private Instant start;
    private Instant end;
    private Duration duration;
    private boolean hasFinished;
    private String representation;
    private DateData data;

    public Timer (){
        this.start = Instant.now();
        this.hasFinished = false;
    }

    public Timer (long ms){
        if (ms < 0)
            throw new RuntimeException("Start cannot be negative");
        this.start = Instant.ofEpochMilli(ms);
        this.hasFinished = false;
    }

    public void Stop(){
        if (!hasFinished){
            this.end = Instant.now();
            if (IsAfterStart(this.start.toEpochMilli(), this.end.toEpochMilli())){
                this.duration = Duration.between(start, end);
                this.hasFinished = true;
                this.representation = GetRepresentation(this.duration);
            } else {
                throw new RuntimeException("Cannot stop timer at this duration again.");
            }
        } else {
            throw new RuntimeException("Cannot stop timer again.");
        }
    }

    public void Stop(long ms){
        if (!hasFinished){
            if (IsAfterStart(this.start.toEpochMilli(), ms) && ms >= 0){
                this.end = Instant.ofEpochMilli(ms);
                this.duration = Duration.between(start, end);
                this.hasFinished = true;
                this.representation = GetRepresentation(this.duration);
            } else {
                throw new RuntimeException("Invalid millisecond amount");
            }
        } else {
            throw new RuntimeException("Cannot stop timer again.");
        }
    }

    /**
     * To get days, hours, etc data, call this method and access DateData through this class
     * @return
     */
    public DateData getData() {
        if (!hasFinished)
            throw new RuntimeException("Timer was not stopped.");
        return data;
    }

    private String GetRepresentation(Duration duration){
        this.data = new DateData();
        StringBuilder result = new StringBuilder();
        result.append("( ").append(this.duration.toMillis()).append(" ms) ")
                .append((this.data.days = duration.toDays())).append(" days ")
                .append((this.data.hours = duration.toHours() - (duration.toDays() * HOURS_IN_DAY))).append(" hours ")
                .append((this.data.minutes = duration.toMinutes() - ( duration.toHours() * MINUTES_IN_HOUR))).append(" minutes ")
                .append((this.data.seconds = duration.getSeconds() - ( duration.toMinutes() * SECONDS_IN_MINUTE))).append(",").append(this.data.milliseconds = duration.toMillis() % MILLIS_IN_SECOND).append(" seconds");
        return result.toString();
    }

    private boolean IsAfterStart(long msStart, long msEnd){
        return msEnd >= msStart;
    }

    public long GetMillis(){
        if (!hasFinished)
            throw new RuntimeException("Timer was not stopped.");
        return this.duration.toMillis();
    }

    public long GetNanos(){
        if (!hasFinished)
            throw new RuntimeException("Timer was not stopped.");
        return this.duration.toNanos();
    }

    @Override
    public String toString() {
        if (!hasFinished)
            throw new RuntimeException("Timer was not stopped.");
        return this.representation;
    }

    public class DateData {
        private long days;
        private long hours;
        private long minutes;
        private long seconds;
        private long milliseconds;

        public DateData(long days, long hours, long minutes, long seconds, long milliseconds) {
            this.days = days;
            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
            this.milliseconds = milliseconds;
        }

        public DateData() {
        }

        public void setDays(long days) {
            this.days = days;
        }

        public void setHours(long hours) {
            this.hours = hours;
        }

        public void setMinutes(long minutes) {
            this.minutes = minutes;
        }

        public void setSeconds(long seconds) {
            this.seconds = seconds;
        }

        public void setMilliseconds(long milliseconds) {
            this.milliseconds = milliseconds;
        }

        public long getDays() {
            return days;
        }

        public long getHours() {
            return hours;
        }

        public long getMinutes() {
            return minutes;
        }

        public long getSeconds() {
            return seconds;
        }

        public long getMilliseconds() {
            return milliseconds;
        }
    }
}
