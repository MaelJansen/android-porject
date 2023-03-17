package com.example.pluviaux_garnier_jansen.ui.gallery;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pluviaux_garnier_jansen.databinding.FragmentGalleryBinding;
import com.example.pluviaux_garnier_jansen.labyrinthe.ILabyrinthe;
import com.example.pluviaux_garnier_jansen.labyrinthe.Labyrinthe;
import com.example.pluviaux_garnier_jansen.labyrinthe.LabyrintheGameView;
import com.example.pluviaux_garnier_jansen.labyrinthe.Salle;
import com.example.pluviaux_garnier_jansen.personnages.Joueur;
import com.example.pluviaux_garnier_jansen.ui.home.HomeFragment;

public class GalleryFragment extends Fragment implements View.OnClickListener {

    private FragmentGalleryBinding binding;
    private LabyrintheGameView labyrinthe;
    private Button btnLeft, btnRight, btnUp, btnDown;
    private ILabyrinthe l;
    private Joueur j;

    private boolean jeuFini = false;

    /**
     * Permet de créer l'afficher (view) du jeu
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return la view entière
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (HomeFragment.laby != "") {
            AssetManager am = this.getContext().getAssets();
            Labyrinthe lab = new Labyrinthe();
            lab.creerLabyrinthe(HomeFragment.laby, am);
            l = lab;
            j = new Joueur(l.getEntree());
            super.onCreate(savedInstanceState);
            labyrinthe = new LabyrintheGameView(getContext(), lab);
            binding.getRoot().addView(labyrinthe, 0); // ajouter à l'indice 0 pour mettre en arrière-plan
        }
        return root;

    }

    /**
     * Initialise les boutons et leurs listeners
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        btnLeft = (Button) binding.btnLeft;
        btnRight = (Button) binding.btnRight;
        btnUp = (Button) binding.btnUp;
        btnDown = (Button) binding.btnDown;

        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnUp.setOnClickListener(this);
        btnDown.setOnClickListener(this);

    }

    /**
     * Detruit la view
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Appelé lors qu'on click dans une view. Elle permet
     * la gestion des boutons et du mouvement du héro
     *
     * @param v  la vue du bouton cliqué
     */
    @Override
    public void onClick(View v) {
        if(jeuFini == false) {
            Button id = (Button) v.findViewById(v.getId());
            final int widthImage = 1;
            // Récupérer les positions actuelles de l'image
            int currentX = labyrinthe.heros.getPosition().getX();
            int currentY = labyrinthe.heros.getPosition().getY();
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
            Salle s = new Salle(currentX, currentY);
            if (l.contains(s)) {
                labyrinthe.heros.setPosition(s);
                labyrinthe.invalidate();
            }
        }
        //Mis dans ce sense car il doit prendre la position après un déplacement
        if (labyrinthe.heros.getPosition().equals(labyrinthe.labyrinthe.getSortie())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Vous avez gagnée").setTitle("BRAVO !!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Action à exécuter lorsque l'utilisateur clique sur le bouton OK
                    jeuFini = true;
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        j.setPosition(labyrinthe.heros.getPosition());
    }
}
