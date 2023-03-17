package com.example.pluviaux_garnier_jansen.labyrinthe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.pluviaux_garnier_jansen.R;
import com.example.pluviaux_garnier_jansen.personnages.Heros;

public class LabyrintheGameView extends View {

    private float mHeightPercentage = 0.5f;

    Paint paint = new Paint();
    Paint herosPaint = new Paint();
    Paint entryPaint = new Paint();
    Paint outPaint = new Paint();

    public Labyrinthe labyrinthe;
    public Heros heros;


    public static int heroPosX;
    public static int heroPosY;

    /**
     * Constructeur de la view
     *
     * @param context : le contexte
     * @param lab : le labyrinthe du jeu
     */
    public LabyrintheGameView(Context context, Labyrinthe lab) {
        super(context);
        this.labyrinthe = lab;
        heros = new Heros(lab.getEntree());
    }

    /**
     * Cette méthode est appelée lorsque les dimensions de la vue personnalisée doivent être mesurées.
     *
     *
     * @param widthMeasureSpec les spécifications de mesure de largeur
     * @param heightMeasureSpec les spécifications de mesure de hauteur
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // Obtenir la hauteur de l'écran en pixels
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int screenHeight = displayMetrics.heightPixels;
        // Ajuster la hauteur de la vue personnalisée à 80% de la hauteur de l'écran
        int newHeight = (int) (screenHeight * mHeightPercentage);
        setMeasuredDimension(getMeasuredWidth(), newHeight);
    }

    /**
     * Permet de dessin le labyrinthe et le joueur
     *
     * @param canvas
     */
    @Override
    public void onDraw(Canvas canvas) {

        int zoom = 40;

        //Les pinceaux qui serviront pour l'affichage (le dessin) des salles du labyronthe
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

        herosPaint.setColor(Color.BLUE);

        entryPaint.setColor(Color.RED);
        entryPaint.setStrokeWidth(3);

        outPaint.setColor(Color.GREEN);
        outPaint.setStrokeWidth(3);

        // Permet d'avoir accès a la position du heros pour l'affichage de sa position sur la map
        float heroXInView = heros.getPosition().getX() * zoom - getWidth() / 2;
        float heroYInView = heros.getPosition().getY() * zoom - getHeight() / 2;

        heroPosX = heros.getPosition().getX();
        heroPosY = heros.getPosition().getY();

        canvas.save(); // sauvegarde l'état du canvas
        canvas.translate(-heroXInView, -heroYInView); // déplace le canvas

        for (ISalle salle : labyrinthe) {
            // Définir la couleur de remplissage à gris
            paint.setColor(Color.GRAY);
            paint.setStyle(Paint.Style.FILL);
            // Remplir le rectangle avec la couleur de remplissage
            canvas.drawRect(salle.getX() * zoom, salle.getY() * zoom, (salle.getX() + 1) * zoom, (salle.getY() + 1) * zoom, paint);
        }

        //draw entry and exit
        canvas.drawRect(labyrinthe.getEntree().getX() * zoom, labyrinthe.getEntree().getY() * zoom, (labyrinthe.getEntree().getX() + 1) * zoom, (labyrinthe.getEntree().getY() + 1) * zoom, entryPaint);
        canvas.drawRect(labyrinthe.getSortie().getX() * zoom, labyrinthe.getSortie().getY() * zoom, (labyrinthe.getSortie().getX() + 1) * zoom, (labyrinthe.getSortie().getY() + 1) * zoom, outPaint);


        //draw heros
        canvas.drawRect(heros.getPosition().getX() * zoom, heros.getPosition().getY() * zoom, (heros.getPosition().getX() + 1) * zoom, (heros.getPosition().getY() + 1) * zoom, herosPaint);

        canvas.restore(); // restaure l'état du canvas

    }

}


