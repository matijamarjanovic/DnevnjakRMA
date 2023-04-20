package rs.raf.rma.dnevnjakrma.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

import rs.raf.rma.dnevnjakrma.models.Day;
import rs.raf.rma.dnevnjakrma.models.Month;
import rs.raf.rma.dnevnjakrma.models.Task;
import rs.raf.rma.dnevnjakrma.models.Weekday;

public class RecyclerViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<Day>> days = new MutableLiveData<ArrayList<Day>>();
    private final MutableLiveData<Day> selectedDay = new MutableLiveData<>();
    private ArrayList<Day> dayList = new ArrayList<>();
    private ArrayList<Task> taskList = new ArrayList<>();
    private String[] taskNames;
    private boolean checked = false;

    public RecyclerViewModel(){

        this.taskNames = new String[]{
//                resources.getString(R.string.dentist),
//                resources.getString(R.string.walkdog),
//                resources.getString(R.string.algebra),
//                resources.getString(R.string.meeting),
                "Robotics course",
                "Training",
                "Nails",
                "Self defence course"
        };


        Weekday weekday;
        Month month;
        int date;
        int year;


        for (int i = 1 + 25; i <= 365 + 31; i++){
            weekday = Weekday.values()[(i + 6) % 7];  //1. januar 2023 je bila nedelja (poslednji ponedeljak u 2022 je bio 26. otuda 25+1)
            year = 2023;

            if (i <= (31 + 31)) {
                month = Month.JANUARY;
                date = i % 31;
                if (date == 0)
                    date = 31;
            }else if (i <= (31 + 31 + 28)){
                month = Month.FEBRUARY;
                date = i % (31 + 31);
            }else if (i <= (31 + 31 + 28 + 31)) {
                month = Month.MARCH;
                date = i % (31 + 31 + 28);
            }else if (i <= (31 + 31 + 28 + 31 + 30)) {
                month = Month.APRIL;
                date = i % (31 + 31 + 28 + 31);
            }else if (i <= (31 + 31 + 28 + 31 + 30 + 31)) {
                month = Month.MAY;
                date = i % (31 + 31 + 28 + 31 + 30);
            }else if (i <= (31 + 31 + 28 + 31 + 30 + 31 + 30)) {
                month = Month.JUNE;
                date = i % (31 + 31 + 28 + 31 + 30 + 31);
            }else if (i <= (31 + 31 + 28 + 31 + 30 + 31 + 30 + 31)) {
                month = Month.JULY;
                date = i % (31 + 31 + 28 + 31 + 30 + 31 + 30);
            }else if (i <= (31 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31)) {
                month = Month.AUGUST;
                date = i % (31 + 31 + 28 + 31 + 30 + 31 + 30 + 31);
            }else if (i <= (31 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30)) {
                month = Month.SEPTEMBER;
                date = i % (31 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31);
            }else if (i <= (31 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31)) {
                month = Month.OCTOBER;
                date = i % (31 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30);
            }else if (i <= (31 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30)) {
                month = Month.NOVEMBER;
                date = i % (31 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31);
            }else{
                month = Month.DECEMBER;
                date = i % (31 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30);
            }

            if(i <= 31){
                month = Month.DECEMBER;
                date = i;
                year = 2022;
            }

            Day day = new Day(weekday, month, date, year);
            dayList.add(day);

        }

        int hour, minute;
        String name;
        int priority;
        Random rand = new Random();
        Day dan;
        boolean add;

        for (int i = 0; i < 600; i++){
            add = true;
            hour = rand.nextInt(25);
            minute = rand.nextInt(12) * 5;
            dan = dayList.get(rand.nextInt(dayList.size()));

            name = taskNames[rand.nextInt(taskNames.length)];
            priority = rand.nextInt(3) + 1;

            for (Task t: taskList){
                if(t.getDay().equals(dan)){
                    if (hour > t.getEnd().getHours())
                        continue;
                    else if (hour == t.getEnd().getHours()) {
                        if (minute >= t.getEnd().getMinutes()) {
                            continue;
                        }
                    }else if (hour < t.getEnd().getHours()){
                        if (t.getEnd().getHours() - hour > 1)
                            continue;
                        else  {
                            if (t.getEnd().getMinutes() - minute >= 30)
                                continue;
                        }
                    }
                    add = false;
                }


            }
            if (add){
                Task task = new Task(new Time(hour, minute, 0), new Time(hour + 1, minute + 30, 0), name, priority, dan);
                taskList.add(task);
            }

        }

        for (Task t: taskList){
            for (Day d: dayList){
                if (t.getDay().equals(d))
                    d.addTask(t);
            }
        }

        ArrayList<Day> listToSubmit = new ArrayList<>(dayList);
        days.setValue(listToSubmit);
        selectedDay.setValue(dayList.get(140));
    }

    public MutableLiveData<ArrayList<Day>> getDays() {
        return days;
    }

    public ArrayList<Day> getDayList() {
        return dayList;
    }

    public void setSelectedDay(Day day) {
        this.selectedDay.setValue(day);
    }

    public MutableLiveData<Day> getSelectedDay() {
        return selectedDay;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
