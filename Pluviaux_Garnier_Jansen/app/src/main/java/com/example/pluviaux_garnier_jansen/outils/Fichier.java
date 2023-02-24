package com.example.pluviaux_garnier_jansen.outils;

import android.content.res.AssetManager;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author INFO Professors team
 */
public class Fichier {
    Scanner sc=null;
    
    public Fichier(String nomFichier, AssetManager assets){
        try{
            InputStream is = assets.open(nomFichier);
	        sc = new Scanner(is);
	}
	catch(Exception e){ System.out.println(e);}     
    }
    
  // retourne le prochain entier dans le fichier
  // retourne -1 s'il n'y en a pas
    public int lireNombre(){
        if (sc.hasNextInt()){
            return sc.nextInt();
        }
        return -1;
    }
    
}
