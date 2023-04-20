package rs.raf.rma.dnevnjakrma.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import rs.raf.rma.dnevnjakrma.R;
import rs.raf.rma.dnevnjakrma.viewpager.TabsPagerAdapter;

public class OuterTabsFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    public OuterTabsFragment() {
        super(R.layout.fragment_tabs);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        viewPager = getActivity().findViewById(R.id.viewPagerTabs);
        tabLayout = getActivity().findViewById(R.id.tabLayout);

        TabsPagerAdapter tpa = new TabsPagerAdapter(getChildFragmentManager());
        tpa.setRes(getResources());

        viewPager.setAdapter(tpa);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
