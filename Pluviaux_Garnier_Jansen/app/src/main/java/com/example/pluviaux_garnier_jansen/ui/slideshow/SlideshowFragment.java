package com.example.pluviaux_garnier_jansen.ui.slideshow;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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

        /*binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
        */

        AssetManager am = this.getContext().getAssets();
        Labyrinthe lab = new Labyrinthe();
        System.out.println("Labyrinthe qui court : " + HomeFragment.laby);
        if (HomeFragment.laby != "") {
            lab.creerLabyrinthe(HomeFragment.laby, am);
            super.onCreate(savedInstanceState);
            getActivity().setContentView(new LabyrintheView(this.getActivity(), lab));
        }
        return this.getView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}