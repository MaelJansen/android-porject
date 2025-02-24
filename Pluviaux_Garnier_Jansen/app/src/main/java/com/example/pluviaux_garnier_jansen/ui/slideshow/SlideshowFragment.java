package com.example.pluviaux_garnier_jansen.ui.slideshow;

import android.content.res.AssetManager;
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
import com.example.pluviaux_garnier_jansen.databinding.FragmentSlideshowBinding;
import com.example.pluviaux_garnier_jansen.labyrinthe.Labyrinthe;
import com.example.pluviaux_garnier_jansen.labyrinthe.LabyrintheView;
import com.example.pluviaux_garnier_jansen.ui.home.HomeFragment;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;

        AssetManager am = this.getContext().getAssets();
        Labyrinthe lab = new Labyrinthe();
        // Permet d'afficher la map quand un labyrinthe est selectionner
        if (HomeFragment.laby != "") {
            lab.creerLabyrinthe(HomeFragment.laby, am);
            super.onCreate(savedInstanceState);
            binding.getRoot().addView(new LabyrintheView(this.getActivity(), lab));
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}