/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.pluviaux_garnier_jansen.personnages;

import com.example.pluviaux_garnier_jansen.labyrinthe.ISalle;

import java.util.Collection;

/**
 *
 * @author pluviaux
 */
public class Heros extends APersonnage {
    
    ISalle salleChoisie;
    
    public Heros(ISalle salleDepart){
        this.setPosition(salleDepart);
        salleChoisie = salleDepart;
    }
    
    /**
     * Permet de changer de salle
     * @param sallesAccessibles : les salles qui sont accessibles pour le héros
     * @return La salle choisie
     */
    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        if(sallesAccessibles.contains(salleChoisie)){
            return salleChoisie;
        }else{
            return this.getPosition();
        }
    }
    
    
    
}
