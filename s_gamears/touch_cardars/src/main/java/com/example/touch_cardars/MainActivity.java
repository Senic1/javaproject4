package com.example.touch_cardars;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    class MyView extends View {
        // координаты для рисования квадрата
        float x = 100;
        float y = 200;
        float x_pic = 200;
        float y_pic = 300;
        int side = 100;
        int radius = 40;


        // переменные для перетаскивания
        boolean drag = false, move_pic, move_circle;
        float dragX = 0;
        float dragY = 0;
        int x1, y1, x2, y2;
        String result = "aa";

        Paint p1, p2, p3, p4;
        Rect rect1, rect2;

        Bitmap bitmap;

        public MyView(Context context) {
            super(context);

            p1 = new Paint();
            p1.setColor(Color.MAGENTA);
            p2 = new Paint();
            p2.setColor(Color.rgb(0, 80, 255));
            p3 = new Paint();
            p3.setColor(Color.GREEN);
            p4 = new Paint();
            p4.setColor(Color.BLUE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            x1 = canvas.getWidth() - side - 20;
            y1 = canvas.getHeight() / 5;
            x2 = canvas.getWidth() - side - 20;
            y2 = canvas.getHeight() / 2;
canvas.drawColor(Color.RED);
            rect1 = new Rect(x1, y1, x1 + side, y1 + side);
            canvas.drawRect(rect1, p1);
            rect2 = new Rect(x2, y2, x2 + side, y2 + side);
            canvas.drawRect(rect2, p2);
            canvas.drawCircle(x, y, radius*2, p1);
            p4.setTextSize(30);
            canvas.drawText("Hello",canvas.getWidth()/2,50,p4);

            bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.rybka);
            canvas.drawBitmap(bitmap,x_pic,y_pic,p1);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float evX = event.getX();
            float evY = event.getY();
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    move_circle=(evX >= x-radius && evX <= x + radius && evY >= y-radius && evY <= y + radius);
                    if(move_circle){
                    drag = true;
                    dragX = evX-x;
                    dragY = evY -y;
                    }
                    break;
                    case MotionEvent.ACTION_MOVE:
                        if(drag){
                        x = evX-dragX;
                        y = evY - dragY;
                        invalidate();
                        }
                        break;
                        case MotionEvent.ACTION_UP:
                            drag = false;
                            break;

            }
            return true;
        }
    }
}