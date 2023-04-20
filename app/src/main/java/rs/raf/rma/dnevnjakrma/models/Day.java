package rs.raf.rma.dnevnjakrma.models;

import java.util.ArrayList;
import java.util.Observable;

public class Day extends Observable {

    private Weekday day;
    private Month month;

    private int year;
    private int date;
    private int priority; //3 crveno, 1 zuto, 0 zeleno, 0 belo(no task)

    private ArrayList<Task> tasks = new ArrayList<>();


    public Day(Weekday day, Month month, int date, int year) {
        this.day = day;
        this.month = month;
        this.date = date;
        this.year = year;

    }

    public Weekday getDay() {
        return day;
    }

    public Month getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }
    public int getPriority() {
        return priority;
    }
    public int getYear() {
        return year;
    }

    public void addTask(Task task) {
        this.tasks.add(task);

        int highestPriority = 0;
        for(Task t: this.tasks){
            if (t.getPriority() > highestPriority)
                highestPriority = t.getPriority();
        }

        this.priority = highestPriority;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        String m = (month.toString()).substring(0, 1) + ((month.toString()).substring(1)).toLowerCase();
        return m + " " + this.date + ". " + this.year + ".";
    }
}
