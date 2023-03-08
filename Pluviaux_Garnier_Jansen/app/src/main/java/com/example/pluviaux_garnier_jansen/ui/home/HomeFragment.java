package com.example.pluviaux_garnier_jansen.ui.home;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}