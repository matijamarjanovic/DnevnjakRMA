package rs.raf.rma.dnevnjakrma.fragments;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import rs.raf.rma.dnevnjakrma.R;
import rs.raf.rma.dnevnjakrma.viewmodels.RecyclerViewModel;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    private Button chgpas;
    private Button logout;
    private View image;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chgpas = view.findViewById(R.id.chpassBtn);
        chgpas.setText("Change Password");
        logout = view.findViewById(R.id.logoutBtn);
        logout.setText("Log out");
        image = view.findViewById(R.id.imageView);
        image.setBackground((ContextCompat.getDrawable(getContext(),R.drawable.customborder)));
    }
}
