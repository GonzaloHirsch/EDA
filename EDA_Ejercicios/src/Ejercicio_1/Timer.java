package Ejercicio_1;

public class Timer {

    private long start;
    private long end;
    private long length;
    private String stringRepresentation;
    private boolean hasFinished = false;

    private final long DAYS = 1000 * 60 * 60 * 24;
    private final long HOURS = 1000 * 60 * 60;
    private final long MINUTES = 1000 * 60;
    private final long SECONDS = 1000;

    public Timer(){
        this.start = System.currentTimeMillis();
    }

    public void Stop(){
        this.end = System.currentTimeMillis();
        this.length = this.end - this.start;
        this.hasFinished = true;
        this.stringRepresentation = GenerateToString();
    }

    public void Restart(){
        this.start = System.currentTimeMillis();
        this.hasFinished = false;
    }

    private String toTime(long millis){
        StringBuilder result = new StringBuilder();

        long days = millis/DAYS;
        millis -= days*DAYS;
        long hours = millis/HOURS;
        millis -= hours*HOURS;
        long minutes = millis/MINUTES;
        millis -= minutes*MINUTES;
        double seconds = (double)millis/SECONDS;
        //millis -= seconds*SECONDS;

        result.append(days).append(" dia ").append(hours).append(" hs ").append(minutes).append(" min ").append(seconds).append(" s");
        return result.toString();
    }

    private String GenerateToString(){
        StringBuilder result = new StringBuilder();
        result.append(" ( ").append(this.length).append(" ms) ").append(toTime(this.length));
        return result.toString();
    }

    @Override
    public String toString(){
        if (!hasFinished)
            throw new RuntimeException();
        return this.stringRepresentation;
    }


}
