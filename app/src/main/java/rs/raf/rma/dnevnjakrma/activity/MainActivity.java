package rs.raf.rma.dnevnjakrma.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import rs.raf.rma.dnevnjakrma.R;
import rs.raf.rma.dnevnjakrma.fragments.CalendarFragment;
import rs.raf.rma.dnevnjakrma.models.Day;
import rs.raf.rma.dnevnjakrma.models.Month;
import rs.raf.rma.dnevnjakrma.models.Weekday;
import rs.raf.rma.dnevnjakrma.recycler.adapter.DayAdapter;
import rs.raf.rma.dnevnjakrma.recycler.differ.DayDiffItemCallback;
import rs.raf.rma.dnevnjakrma.viewmodels.RecyclerViewModel;
import rs.raf.rma.dnevnjakrma.viewmodels.SplashViewModel;
import rs.raf.rma.dnevnjakrma.viewpager.PagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Day selectedDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return false;
        });
        setContentView(R.layout.activity_main);

        initViewPager();
        initNavigation();
    }

    private void initViewPager(){
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
    }


    private void initNavigation() {

        ((BottomNavigationView)findViewById(R.id.bottomNavigation)).setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.calendar:
                    viewPager.setCurrentItem(PagerAdapter.FR1, false);
                    break;
                case R.id.daily:
                    viewPager.setCurrentItem(PagerAdapter.FR2, false);
                    break;
                case R.id.profile:
                    viewPager.setCurrentItem(PagerAdapter.FR3, false);
                    break;
            }
            return true;
        });
    }

    public Day getSelectedDay() {
        return selectedDay;
    }

    public void setSelectedDay(Day selectedDay) {
        this.selectedDay = selectedDay;
    }
}

