package rs.raf.rma.dnevnjakrma.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import rs.raf.rma.dnevnjakrma.fragments.CalendarFragment;
import rs.raf.rma.dnevnjakrma.fragments.DailyFragment;
import rs.raf.rma.dnevnjakrma.fragments.ProfileFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private final int ITEM_COUNT = 3;
    public static  final int FR1 = 0;
    public static  final int FR2 = 1;
    public static  final int FR3 = 2;

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment f;
        switch (position){
            case FR1: f = new CalendarFragment(); break;
            case FR2: f = new DailyFragment(); break;
            default: f = new ProfileFragment(); break;
        }
        return f;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

}
