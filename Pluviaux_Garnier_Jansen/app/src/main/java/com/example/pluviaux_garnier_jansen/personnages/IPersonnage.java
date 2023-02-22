package com.example.pluviaux_garnier_jansen.personnages;

import com.example.pluviaux_garnier_jansen.labyrinthe.ISalle;

import java.util.Collection;
/**
*
* @author INFO Professors team
*/
public interface IPersonnage {
    
    // renvoie une salle parmi sallesAccesibles
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles);

    // renvoie sa position courante
    public ISalle getPosition();
    
    // definit sa position courante
    public void setPosition( ISalle s);
}
