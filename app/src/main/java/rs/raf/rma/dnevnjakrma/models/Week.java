package rs.raf.rma.dnevnjakrma.models;

import java.util.List;

public class Week {

    private List<Day> week;
    private Month month1, month2;

    public Week(List<Day> week) {
        this.week = week;
        int cnt = 0;
        Month m = null;


        //u kojim mesecima je nedelja
        for (Day d: week){
            if(week.get(0).equals(d))
                m = d.getMonth();
            if(d.getMonth().equals(m))
                cnt++;
        }

        this.month1 = m;
        if(cnt == 7)
            this.month2 = null;
        else
            this.month2 = week.get(6).getMonth();
    }
}
