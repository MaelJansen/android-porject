package com.example.pluviaux_garnier_jansen.personnages;

import com.example.pluviaux_garnier_jansen.labyrinthe.ISalle;
import com.example.pluviaux_garnier_jansen.labyrinthe.Labyrinthe;

import java.util.Collection;

public class Joueur extends APersonnage{
    private ISalle salleChoisie;
    private int posX;
    private int posY;

    public Joueur(ISalle entree) {
        setPosition(entree);
    }

    public Joueur(int x, int y) {
        setPosition(null);
        posX = x;
        posY = y;
    }

    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        if(sallesAccessibles.contains(salleChoisie)){
            return salleChoisie;
        } else
            return getPosition();
    }

    public void setSalleChoisie(ISalle s){
        this.salleChoisie = s;
        this.setPosition(s);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
