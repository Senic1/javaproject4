package com.example.s_starikars;

import android.annotation.SuppressLint;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
Button _btnDown, _btnUp;
TextView _lbl1;
ImageView _img1, _img2, _img;
ConstraintLayout _holst;
ConstraintLayout.LayoutParams _conLay;
Random rnd = new Random();
int x, y, _width, _height;
float dx, dy, xStarik, yStarik,xFish, yFish;
boolean b_fishX, b_fishY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _lbl1 = (TextView) findViewById(R.id.lbl1);
        _btnUp = (Button) findViewById(R.id.btnUp);
        _btnDown = (Button) findViewById(R.id.btnDown);
        _img1 = (ImageView) findViewById(R.id.img1);
        _img2 = (ImageView) findViewById(R.id.img2);
        _holst = (ConstraintLayout) findViewById(R.id.holst);

        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        _width = display.getWidth();
        _height = display.getHeight();
        _lbl1.setText("w = "+_width+"   "+"h = "+_height);

        _img1.getLayoutParams().width = _width/7;
        _img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _lbl1.setText("Starik");
            }
        });
        _img1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.setVisibility(View.INVISIBLE);
                return false;
            }
        });
        _btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _img1.setY(_img1.getY()+10);
            }
        });
        _btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _img1.setY(_img1.getY()-10);
            }
        });

        image_move(_img2);

        for(int i =0;i<5;i++){
            x = rnd.nextInt(_width-100);
            y = rnd.nextInt(_height-100);
            _img = new ImageView(this);
            _conLay = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT);
            _conLay.width = _width/10;
            _img.setImageResource(R.drawable.rybka);
            _img.setLayoutParams(_conLay);
            _img.setX(x);
            _img.setY(y);
            image_move(_img);
            _holst.addView(_img);
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    public void image_move(ImageView img){
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        dx = v.getX()-event.getRawX();
                        dy = v.getY()-event.getRawY();
                        break;
                        case MotionEvent.ACTION_MOVE:
                            v.animate()
                                    .x(event.getRawX()+dx)
                                    .y(event.getRawY()+dy)
                                    .setDuration(0)
                                    .start();
                            break;
                            case MotionEvent.ACTION_UP:
                                float x1 = v.getX();
                                float x2 = _img1.getX();
                                _lbl1.setText(x1+"  "+x2);
                                b_fishX=_img1.getX()<v.getX()&& v.getX()<_img1.getX()+60;
                                b_fishY=_img1.getY()<v.getY()&& v.getY()<_img1.getY()+100;
                                if(b_fishY && b_fishX)
                                {
                                    Toast.makeText(getApplication(),"Поймал",Toast.LENGTH_SHORT).show();
                                }

                                break;
                    default:return false;
                }
                return true;
            }
        });
    }
}