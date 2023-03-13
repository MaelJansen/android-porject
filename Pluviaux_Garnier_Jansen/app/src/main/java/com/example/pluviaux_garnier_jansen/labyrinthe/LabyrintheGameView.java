package com.example.pluviaux_garnier_jansen.labyrinthe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.pluviaux_garnier_jansen.personnages.Heros;

public class LabyrintheGameView extends View {
    Paint paint = new Paint();
    Paint herosPaint = new Paint();
    Paint entryPaint = new Paint();
    Paint outPaint = new Paint();

    public Labyrinthe labyrinthe;
    public Heros heros;

    public LabyrintheGameView(Context context, Labyrinthe lab) {
        super(context);
        this.labyrinthe = lab;
        heros = new Heros(lab.getEntree());
    }

    @Override
    public void onDraw(Canvas canvas) {

        int zoom = 15;

        //paints
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

        herosPaint.setColor(Color.BLUE);

        entryPaint.setColor(Color.RED);
        entryPaint.setStrokeWidth(3);

        outPaint.setColor(Color.GREEN);
        outPaint.setStrokeWidth(3);

        float heroXInView = heros.getPosition().getX() * zoom - getWidth() / 2;
        float heroYInView = heros.getPosition().getY() * zoom - getHeight() / 2;

        canvas.save(); // sauvegarde l'état du canvas
        canvas.translate(-heroXInView, -heroYInView); // déplace le canvas

        //draw entry and exit
        canvas.drawRect(labyrinthe.getEntree().getX() * zoom, labyrinthe.getEntree().getY() * zoom, (labyrinthe.getEntree().getX() + 1) * zoom, (labyrinthe.getEntree().getY() + 1) * zoom, entryPaint);
        canvas.drawRect(labyrinthe.getSortie().getX() * zoom, labyrinthe.getSortie().getY() * zoom, (labyrinthe.getSortie().getX() + 1) * zoom, (labyrinthe.getSortie().getY() + 1) * zoom, outPaint);

        //draw heros
        canvas.drawRect(heros.getPosition().getX() * zoom, heros.getPosition().getY() * zoom, (heros.getPosition().getX() + 1) * zoom, (heros.getPosition().getY() + 1) * zoom, herosPaint);


        for (ISalle salle : labyrinthe) {
            canvas.drawRect(salle.getX() * zoom, salle.getY() * zoom, (salle.getX() + 1) * zoom, (salle.getY() + 1) * zoom, paint);
        }

        canvas.restore(); // restaure l'état du canvas

    }
}


