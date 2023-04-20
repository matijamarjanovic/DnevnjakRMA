package rs.raf.rma.dnevnjakrma.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.transition.FragmentTransitionSupport;

import rs.raf.rma.dnevnjakrma.R;
import rs.raf.rma.dnevnjakrma.viewmodels.RecyclerViewModel;

public class DailyFragment extends Fragment {

    private RecyclerViewModel recyclerViewModel;
    private CheckBox cb;

    public DailyFragment() {
        super(R.layout.fragment_daily);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);
        cb = view.findViewById(R.id.checkBox);

        if(cb.isChecked()){
            recyclerViewModel.setChecked(true);
        }else{
            recyclerViewModel.setChecked(false);
        }

        initFragment(view);
        TextView date = view.findViewById(R.id.dateTv);
        date.setText(recyclerViewModel.getSelectedDay().getValue().toString());

    }

    private void initFragment(View view) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.fragTabFcv, new OuterTabsFragment());
        transaction.commit();

        recyclerViewModel.getSelectedDay().observe(requireActivity(), day -> {
            TextView date = view.findViewById(R.id.dateTv);
            date.setText(recyclerViewModel.getSelectedDay().getValue().toString());
        });

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    recyclerViewModel.setChecked(true);
                    Toast.makeText(getContext(), "CHECKED " + recyclerViewModel.isChecked(), Toast.LENGTH_SHORT).show();
                }else{
                    recyclerViewModel.setChecked(false);
                    Toast.makeText(getContext(), "NOT CHECKED", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
