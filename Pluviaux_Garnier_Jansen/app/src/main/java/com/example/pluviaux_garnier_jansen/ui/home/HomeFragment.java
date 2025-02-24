package com.example.pluviaux_garnier_jansen.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.pluviaux_garnier_jansen.labyrinthe.LabyrintheGameView;
import com.example.pluviaux_garnier_jansen.labyrinthe.LabyrintheView;
import com.example.pluviaux_garnier_jansen.ui.gallery.GalleryFragment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public static String laby = "";
    private int index = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        textView.setText("Choix du labyrinthes");

        /*
        Permet de créer les boutons en fonction du nombre de fichier labyrinthe que l'on donne
         */
        for (int i=1; i<=countFilesInAssetFolder(this,"labys"); i++){
            Button button = new Button(this.getContext());
            button.setText("Labys " + i);
            button.setId(i);
            button.setTop(i*10);
            index = i;
           button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    laby = "labys/level" + button.getId() + ".txt";
                    // Permet d'afficher nue pop up lors du chargement du labyrinthe
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Labyrinthe" + button.getId()).setTitle("Chargement de labyrinthe");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
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

    /**
     * une fonction qui permet de compter le nombre de ficher présents
     * @param fragment le fragment actif (ici homeFragment)
     * @param folderPath le chemin du dossier contenant les labyrinthes
     * @return
     */
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