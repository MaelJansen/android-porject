package com.example.pluviaux_garnier_jansen.labyrinthe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.pluviaux_garnier_jansen.R;

public class LabyrintheView extends View {

    Paint paint = new Paint();
    Paint entryPaint = new Paint();
    Paint outPaint = new Paint();

    public Labyrinthe labyrinthe;
    public LabyrintheView(Context context, Labyrinthe lab) {
        super(context);
        this.labyrinthe = lab;
    }

    @Override
    public void onDraw(Canvas canvas) {
        int iWidth = canvas.getWidth() / labyrinthe.getLargeur(); // Largeur
        int iHeight = canvas.getHeight() / labyrinthe.getHauteur(); // Hauteur

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

        entryPaint.setColor(Color.RED);
        entryPaint.setStrokeWidth(3);

        outPaint.setColor(Color.GREEN);
        outPaint.setStrokeWidth(3);

        //draw entry
        canvas.drawRect(labyrinthe.getEntree().getX()*iWidth, labyrinthe.getEntree().getY()*iHeight, (labyrinthe.getEntree().getX()+1)*iWidth , (labyrinthe.getEntree().getY()+1)*iHeight, entryPaint);
        canvas.drawRect(labyrinthe.getSortie().getX()*iWidth, labyrinthe.getSortie().getY()*iHeight, (labyrinthe.getSortie().getX()+1)*iWidth , (labyrinthe.getSortie().getY()+1)*iHeight, outPaint);


        for (ISalle salle : labyrinthe){
            canvas.drawRect(salle.getX()*iWidth, salle.getY()*iHeight, (salle.getX()+1)*iWidth , (salle.getY()+1)*iHeight, paint);
        }
    }
}


