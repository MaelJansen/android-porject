package com.example.pluviaux_garnier_jansen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.example.pluviaux_garnier_jansen.personnages.Joueur;

public class DrawView extends View {
    Paint paint = new Paint();
    Joueur j = new Joueur(50,50);
    Button[] bs = new Button[4];

    public DrawView(MainActivity context){
        super(context);
        bs[0] = new Button(context);
        bs[1] = new Button(context);
        bs[2] = new Button(context);
        bs[3] = new Button(context);
    }

    public DrawView(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRect(j.getPosX()*2, j.getPosY()*2, 80, 80, paint);
    }

    public void setJ(int x, int y){
        j.setPosX(x);
        j.setPosY(y);
    }
}