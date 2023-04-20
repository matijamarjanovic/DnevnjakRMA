package rs.raf.rma.dnevnjakrma.viewpager;

import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import rs.raf.rma.dnevnjakrma.R;
import rs.raf.rma.dnevnjakrma.fragments.CalendarFragment;
import rs.raf.rma.dnevnjakrma.fragments.DailyFragment;
import rs.raf.rma.dnevnjakrma.fragments.tabs.AllFragment;
import rs.raf.rma.dnevnjakrma.fragments.tabs.HighPriorityFragment;
import rs.raf.rma.dnevnjakrma.fragments.tabs.LowPriorityFragment;
import rs.raf.rma.dnevnjakrma.fragments.tabs.MidPriorityFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    private android.content.res.Resources res;
    private final int ITEM_COUNT = 4;
    public static  final int FR1 = 0;
    public static  final int FR2 = 1;
    public static  final int FR3 = 2;
    public static  final int FR4 = 3;

    public TabsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment f;
        switch (position){
            case FR2: f = new LowPriorityFragment(); break; //TODO LOW PRIORITY
            case FR3: f = new MidPriorityFragment(); break; //TODO MID PRIORITY
            case FR4: f = new HighPriorityFragment(); break; //TODO HIGHPRIORITY
            default: f = new AllFragment(); break; //TODO all activities
        }
        return f;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){

            case FR2: return  res.getString(R.string.low);
            case FR3: return res.getString(R.string.mid);
            case FR4: return res.getString(R.string.high);
            default: return res.getString(R.string.all);
        }

    }

    public void setRes(Resources res) {
        this.res = res;
    }
}
