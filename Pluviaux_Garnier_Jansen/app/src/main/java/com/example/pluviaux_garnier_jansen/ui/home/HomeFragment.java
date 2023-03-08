package com.example.pluviaux_garnier_jansen.ui.home;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pluviaux_garnier_jansen.R;
import com.example.pluviaux_garnier_jansen.databinding.FragmentHomeBinding;
import com.example.pluviaux_garnier_jansen.labyrinthe.ISalle;
import com.example.pluviaux_garnier_jansen.labyrinthe.Labyrinthe;
import com.example.pluviaux_garnier_jansen.labyrinthe.LabyrintheView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private LabyrintheView LabyrintheView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        textView.setText("Choix du labyrinthes");

        for (int i=1; i<=countFilesInAssetFolder(this,"labys"); i++){
            Button button = new Button(this.getContext());
            button.setText("Labys " + i);
            button.setId(100 + i);
            button.setTop(i*10);
            LinearLayout layout = (LinearLayout)binding.labysLayout;
            layout.addView(button);
        }
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public int countFilesInAssetFolder(Fragment fragment, String folderPath) {
        AssetManager assetManager = fragment.requireActivity().getAssets();
        int count = 0;
        try {
            String[] fileNames = assetManager.list(folderPath);
            for (String fileName : fileNames) {
                if (!fileName.contains(".")) { // ignore directories
                    count += countFilesInAssetFolder(fragment, folderPath + File.separator + fileName);
                } else {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}