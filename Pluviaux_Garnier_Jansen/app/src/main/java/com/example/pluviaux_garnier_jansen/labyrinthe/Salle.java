/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.pluviaux_garnier_jansen.labyrinthe;


/**
 *
 * @author rpluviaux
 */
public class Salle implements ISalle {

    private int x;
    private int y;
    
    Salle(int x, int y){
        this.x = x;
        this.y=y;
    }
    
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    /**
     * Permte de savoir si une salle est adjacente à une autre salle
     * @param autre : la salle que l'on test pour savoir si elle est adjacente
     * @return true si la salle en paramètre est bien adjacente
     */
    @Override
    public boolean estAdjacente(ISalle autre) {
        if((x-1 == autre.getX()&& y==autre.getY())||(y-1 == autre.getY() && x==autre.getX()) 
                ||(x+1 == autre.getX() && y==autre.getY())||(y+1 == autre.getY() && x==autre.getX())){
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Permet de savoir si deux objets sont égaux d'un point de vue des attributs X et Y
     * @return un code généré à partir de X et Y de la salle
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.x;
        hash = 53 * hash + this.y;
        return hash;
    }

    /**
     * Permet de savoir si 2 objet sont égaux
     * @param obj : l'objet de la comparaison
     * @return true si il sont égaux
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Salle other = (Salle) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }
    

    

}
