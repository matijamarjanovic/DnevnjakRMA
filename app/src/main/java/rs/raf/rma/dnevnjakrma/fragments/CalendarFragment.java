package rs.raf.rma.dnevnjakrma.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import rs.raf.rma.dnevnjakrma.R;
import rs.raf.rma.dnevnjakrma.activity.MainActivity;
import rs.raf.rma.dnevnjakrma.models.Day;
import rs.raf.rma.dnevnjakrma.recycler.adapter.DayAdapter;
import rs.raf.rma.dnevnjakrma.recycler.differ.DayDiffItemCallback;
import rs.raf.rma.dnevnjakrma.recycler.layoutmanager.CalendarLayoutManager;
import rs.raf.rma.dnevnjakrma.viewmodels.RecyclerViewModel;

public class CalendarFragment extends Fragment {

    private DayAdapter dayAdapter;
    private RecyclerView recyclerView;
    private RecyclerViewModel recyclerViewModel;
    private TextView monthTv;
    private int firstVis;
    private Day day = null;
    private String month = null;


    public CalendarFragment() {
        super(R.layout.fragment_calendar);
    }


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.listRv);
        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);


        initRecycler();
        initObservers();

        Date date1 = new Date(2022,12,26 );
        Date date2 = new Date(2023, 4, 20);
        long difference = date2.getTime() - date1.getTime();
        long differenceDays = difference / (1000 * 60 * 60 * 24);

        recyclerView.getLayoutManager().scrollToPosition((int)differenceDays - 7);


        GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
        ArrayList<Day> days = recyclerViewModel.getDayList();


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVis = manager.findFirstCompletelyVisibleItemPosition();

                if (firstVis >= 0){
                    day = days.get(firstVis);
                    if (day.getDate() > 27)
                        month = (days.get(firstVis + 5)).getMonth().toString();
                    else
                        month = day.getMonth().toString();
                }

                String m = month.substring(0, 1) + (month.substring(1)).toLowerCase();
                monthTv.setText(m);
            }
        });

        initView(view);

    }

    private void initRecycler() {

        dayAdapter = new DayAdapter(new DayDiffItemCallback(), day -> {
            Toast.makeText(getContext(), day.getMonth() + " " + day.getDate(), Toast.LENGTH_SHORT).show();
            recyclerViewModel.setSelectedDay(day);
            ((BottomNavigationView)requireActivity().findViewById(R.id.bottomNavigation)).setSelectedItemId(R.id.daily);

        });
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 7));
        recyclerView.setAdapter(dayAdapter);
    }
    private void initObservers(){
        recyclerViewModel.getDays().observe(this, days -> {
            dayAdapter.submitList(days);
        });
    }

    private void initView(View view){
        monthTv = view.findViewById(R.id.monthTv);
        monthTv.setText("Month");

        TextView mon = view.findViewById(R.id.monTv);
        mon.setText(R.string.mon);
        TextView tue = view.findViewById(R.id.tueTv);
        tue.setText(R.string.tue);
        TextView wed = view.findViewById(R.id.wedTv);
        wed.setText(R.string.wed);
        TextView thur = view.findViewById(R.id.thurTv);
        thur.setText(R.string.thur);
        TextView fri = view.findViewById(R.id.friTv);
        fri.setText(R.string.fri);
        TextView sat = view.findViewById(R.id.satTv);
        sat.setText(R.string.sat);
        TextView sun = view.findViewById(R.id.sunTv);
        sun.setText(R.string.sun);

    }


}
