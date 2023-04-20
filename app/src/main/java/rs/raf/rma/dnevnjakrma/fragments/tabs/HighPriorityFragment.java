package rs.raf.rma.dnevnjakrma.fragments.tabs;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import rs.raf.rma.dnevnjakrma.R;
import rs.raf.rma.dnevnjakrma.models.Task;
import rs.raf.rma.dnevnjakrma.recycler.adapter.TaskAdapter;
import rs.raf.rma.dnevnjakrma.recycler.differ.TaskDiffItemCallback;
import rs.raf.rma.dnevnjakrma.viewmodels.RecyclerViewModel;

public class HighPriorityFragment extends Fragment {
    private RecyclerViewModel recyclerViewModel;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    public HighPriorityFragment() {
        super(R.layout.fragment_high);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.taskshighRv);
        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);

        initRecycler();
        initObservers();

    }

    private void initObservers() {
        recyclerViewModel.getSelectedDay().observe(this, day -> {
            ArrayList<Task> tas = new ArrayList<>();

            for (Task t: recyclerViewModel.getSelectedDay().getValue().getTasks()){
                if(t.getPriority() == 3)
                    tas.add(t);
            }

            taskAdapter.submitList(tas);
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
