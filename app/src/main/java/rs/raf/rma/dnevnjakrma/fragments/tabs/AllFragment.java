package rs.raf.rma.dnevnjakrma.fragments.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import rs.raf.rma.dnevnjakrma.R;
import rs.raf.rma.dnevnjakrma.models.Task;
import rs.raf.rma.dnevnjakrma.recycler.adapter.TaskAdapter;
import rs.raf.rma.dnevnjakrma.recycler.differ.TaskDiffItemCallback;
import rs.raf.rma.dnevnjakrma.viewmodels.RecyclerViewModel;
import rs.raf.rma.dnevnjakrma.viewpager.PagerAdapter;

public class AllFragment extends Fragment {

    private RecyclerViewModel recyclerViewModel;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    public AllFragment() {
        super(R.layout.fragment_all);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.tasksRv);
        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);

        initRecycler();
        initObservers();

    }

    private void initObservers() {
        recyclerViewModel.getSelectedDay().observe(this, day -> {

            taskAdapter.submitList(recyclerViewModel.getSelectedDay().getValue().getTasks());
        });
    }

    private void initRecycler() {
        taskAdapter = new TaskAdapter( new TaskDiffItemCallback(), task -> {
            Toast.makeText(getContext(), task.getName() + " " + task.getDay().toString() +" " +  task.getStart(), Toast.LENGTH_SHORT).show();


        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(taskAdapter);
    }
}
