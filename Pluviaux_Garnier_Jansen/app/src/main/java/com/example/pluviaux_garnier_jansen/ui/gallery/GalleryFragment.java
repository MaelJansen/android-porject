package com.example.pluviaux_garnier_jansen.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pluviaux_garnier_jansen.databinding.FragmentGalleryBinding;
import com.example.pluviaux_garnier_jansen.labyrinthe.ILabyrinthe;
import com.example.pluviaux_garnier_jansen.labyrinthe.ISalle;
import com.example.pluviaux_garnier_jansen.labyrinthe.Labyrinthe;
import com.example.pluviaux_garnier_jansen.labyrinthe.Salle;
import com.example.pluviaux_garnier_jansen.personnages.Joueur;

public class GalleryFragment extends Fragment implements View.OnClickListener {

    private FragmentGalleryBinding binding;
    private ImageView imageView;
    private Button btnLeft, btnRight, btnUp, btnDown;
    private ILabyrinthe l;
    private Joueur j = new Joueur(l.getEntree());

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        imageView = (ImageView) binding.imageView;
        btnLeft = (Button) binding.btnLeft;
        btnRight = (Button) binding.btnRight;
        btnUp = (Button) binding.btnUp;
        btnDown = (Button) binding.btnDown;

        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnUp.setOnClickListener(this);
        btnDown.setOnClickListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        Button id = (Button) v.findViewById(v.getId());
        final int widthImage = imageView.getWidth();
        // Récupérer les positions actuelles de l'image
        int currentX = (int) imageView.getX();
        int currentY = (int) imageView.getY();
        // Calculer les nouvelles positions en fonction du bouton cliqué
        if (binding.btnLeft.equals(id)) {
            currentX -= widthImage;
        } else if (binding.btnRight.equals(id)) {
            currentX += widthImage;
        } else if (binding.btnUp.equals(id)) {
            currentY -= widthImage;
        } else if (binding.btnDown.equals(id)) {
            currentY += widthImage;
        }
        Salle s = new Salle(currentX,currentY);
        if(l.contains(s)) {
            j.setSalleChoisie(s);
            // Mettre à jour la position de l'image
            imageView.animate()
                    .x(currentX)
                    .y(currentY)
                    .setDuration(500)
                    .start();
        }
    }
}
