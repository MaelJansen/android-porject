package com.example.pluviaux_garnier_jansen.personnages;

import com.example.pluviaux_garnier_jansen.labyrinthe.ISalle;

public abstract class APersonnage implements IPersonnage {
    ISalle position;

    @Override
    public ISalle getPosition(){
        return position;
    }

    @Override
    public void setPosition(ISalle s){
        position = s;
    }
}
