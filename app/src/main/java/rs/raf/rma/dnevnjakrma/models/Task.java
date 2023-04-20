package rs.raf.rma.dnevnjakrma.models;

import java.io.CharArrayReader;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Task {

    private Time start;
    private Time end;
    private int durationMinutes;
    private String name;
    private int priority; //3 crveno, 2 zuto, 1 zeleno
    private Day day;
    private boolean passed;
    private String description;

    public Task(Time start, Time end, String name, int priority, Day day) {
        this.start = start;
        this.end = end;

        this.durationMinutes = (end.getHours()*60 + end.getMinutes())
                                    - (start.getHours()*60 + start.getMinutes());
        this.name = name;
        this.priority = priority;
        this.day = day;
        this.description = "";

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat df2 = new SimpleDateFormat("HH:mm:ss");

        Calendar c = Calendar.getInstance();

        String[] dmy = df.format(c.getTime()).split("/");
        String[] hms = df2.format(c.getTime()).split(":");

        Time curr = new Time(Integer.parseInt(hms[0]), Integer.parseInt(hms[0]), 0);


        if(Integer.parseInt(dmy[2]) < this.day.getYear()){
            this.passed = false;
        } else if (Integer.parseInt(dmy[2]) == this.day.getYear() % 2000) {

            if(Integer.parseInt(dmy[1]) < Month.valueOf(this.day.getMonth().toString().toUpperCase()).ordinal() + 1){
                this.passed = false;
            } else if (Integer.parseInt(dmy[1]) < Month.valueOf(this.day.getMonth().toString().toUpperCase()).ordinal() + 1) {

                if(Integer.parseInt(dmy[0]) < this.day.getDate()){
                    this.passed = false;
                }else if (Integer.parseInt(dmy[0]) == this.day.getDate()){

                    if (start.getTime() > curr.getTime()){
                        this.passed = false;
                    }else{
                        this.passed = true;
                    }
                }else
                    this.passed = true;
            }else
                this.passed = true;
        }else
            this.passed = true;

    }

    public boolean isPassed() {
        return passed;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
