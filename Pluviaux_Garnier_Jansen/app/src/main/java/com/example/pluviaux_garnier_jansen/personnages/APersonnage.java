/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.pluviaux_garnier_jansen.personnages;

import com.example.pluviaux_garnier_jansen.labyrinthe.ISalle;

/**
 *
 * @author pluviaux
 */
public abstract class APersonnage implements IPersonnage{
    
    private int x;
    private int y;
    private ISalle salleCourante;
    
    // renvoie sa position courante
    @Override
    public ISalle getPosition(){
        return salleCourante;
    }
    
    // definit sa position courante
    @Override
    public void setPosition( ISalle s){
        this.x = s.getX();
        this.y = s.getY();
        salleCourante = s;
    }
}
