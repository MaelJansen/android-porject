package com.example.pluviaux_garnier_jansen.ui.home;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pluviaux_garnier_jansen.R;
import com.example.pluviaux_garnier_jansen.databinding.FragmentHomeBinding;

import java.io.File;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        textView.setText("Choix du labyrinthes");



        for (int i=1; i<=nbFichier(); i++){
            Button button = new Button(this.getContext());
            button.setText("Labys " + i);
            button.setId(100 + i);
            button.setTop(i*10);
            LinearLayout layout = (LinearLayout)binding.labysLayout;
            layout.addView(button);
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public int nbFichier() {
        File repo = new File("labys/");
        File[] fichiers = repo.listFiles();
        int nb = fichiers.length;
        String[]
        return nb;
    }
}