package com.example.pluviaux_garnier_jansen.labyrinthe;

import com.example.pluviaux_garnier_jansen.outils.Fichier;
import com.example.pluviaux_garnier_jansen.personnages.IPersonnage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
/**
 *
 * @author INFO Professors team
 */
public class Labyrinthe extends ArrayList<ISalle> implements ILabyrinthe {

    protected ISalle entree;
    protected ISalle sortie;
    private int largeur;
    private int hauteur;
    private HashSet hash = new HashSet<ISalle>();
    
    /**
     * Permet de générer un labyrinthe en fonction du fichier passer en paramètre
     * @param file : le fichier qui correspond au labyrinthe
     */
    @Override
    public void creerLabyrinthe(String file) {
        Fichier f = new Fichier(file);
        // dimensions
        largeur = f.lireNombre();
        hauteur = f.lireNombre();
        this.add(entree = new Salle(f.lireNombre(), f.lireNombre()));
        this.add(sortie = new Salle(f.lireNombre(), f.lireNombre()));

        int prochaineValeur = f.lireNombre();
        while (prochaineValeur != -1) {
            this.add(new Salle(prochaineValeur, f.lireNombre()));
            prochaineValeur = f.lireNombre();
        }
    }

    /**
     * Permet d'ajouter une salle seulement si il n'y a pas de salle doublons et si les coordonnées sont cohérantes
     * @param s : La salle que l'ont souhaite ajouter
     * @return True si la salle à bien été ajoutée
     */
    @Override
    public boolean add(ISalle s) {
        if ((s.getX() > this.getLargeur() || s.getX() < 0 || s.getY() > this.getHauteur() || s.getY() < 0) || !hash.add(s)) {
            return false;
        } else {
            super.add(s);
            return true;
        }
    }
    
    /**
     * Permet de connaître toutes les salles accessibles à partir d'un personnage
     * @param bobs : Les salles accessibles pour cette personne
     * @return Une collection des salles accessibles
     */
    @Override
    public Collection<ISalle> sallesAccessibles(IPersonnage bobs) {
        ArrayList sallesAccess = new ArrayList<ISalle>();
        for (ISalle s : this) {
            if (bobs.getPosition().estAdjacente(s)) {
                sallesAccess.add(s);
            }
        }
        return sallesAccess;
    }

    @Override
    public ISalle getEntree() {
        return entree;
    }

    @Override
    public ISalle getSortie() {
        return sortie;
    }

    @Override
    public Collection<ISalle> chemin(ISalle u, ISalle v
    ) {
        return null;
    }

    @Override
    public int getLargeur() {
        return largeur;
    }

    @Override
    public int getHauteur() {
        return hauteur;
    }

}
