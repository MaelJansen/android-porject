package com.example.pluviaux_garnier_jansen.labyrinthe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class LabyrintheGameView extends View {
    Paint paint = new Paint();
    Paint entryPaint = new Paint();
    Paint outPaint = new Paint();

    public Labyrinthe labyrinthe;

    public LabyrintheGameView(Context context, Labyrinthe lab) {
        super(context);
        this.labyrinthe = lab;
    }

    @Override
    public void onDraw(Canvas canvas) {

        //paints
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

        entryPaint.setColor(Color.RED);
        entryPaint.setStrokeWidth(3);

        outPaint.setColor(Color.GREEN);
        outPaint.setStrokeWidth(3);

        //draw entry and exit
        canvas.drawRect(labyrinthe.getEntree().getX() * 80, labyrinthe.getEntree().getY() * 80, (labyrinthe.getEntree().getX() + 1) * 80, (labyrinthe.getEntree().getY() + 1) * 80, entryPaint);
        canvas.drawRect(labyrinthe.getSortie().getX() * 80, labyrinthe.getSortie().getY() * 80, (labyrinthe.getSortie().getX() + 1) * 80, (labyrinthe.getSortie().getY() + 1) * 80, outPaint);



        for (ISalle salle : labyrinthe) {
            canvas.drawRect(salle.getX() * 80, salle.getY() * 80, (salle.getX() + 1) * 80, (salle.getY() + 1) * 80, paint);
        }
    }
}


