package com.example.pluviaux_garnier_jansen.labyrinthe;

import android.content.res.AssetManager;

import com.example.pluviaux_garnier_jansen.personnages.IPersonnage;

import java.io.IOException;
import java.util.Collection;


/**
 *
 * @author INFO Professors team
 */
public interface ILabyrinthe extends Collection<ISalle>{
 
    // cree le labyrinthe a partir d'un fichier 
    public void creerLabyrinthe(String file, AssetManager am) throws IOException;

    // renvoie les salles accessibles pour le heros
    public Collection<ISalle> sallesAccessibles(IPersonnage heros);
    
    
    // accesseurs sur l'entree 
    public ISalle getEntree();
    
    // accesseur sur la sortie
    public ISalle getSortie();
    
    // un plus court chemin entre u et v
     public Collection<ISalle> chemin(ISalle u, ISalle v);
     
     // dimensions grille
     public int getLargeur();
     public int getHauteur();
}
