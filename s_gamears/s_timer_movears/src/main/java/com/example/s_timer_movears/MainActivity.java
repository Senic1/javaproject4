    package com.example.s_timer_movears;

import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

    public class MainActivity extends AppCompatActivity {
    TextView _lbl1;
    Button _btnStart, _btnStop;
    ImageView _img;
    ConstraintLayout _holst;
    int sek =0, n,a,b;
    float y, dY=10;

    Timer _timer1, _timer2;
    TimerTask _task1, _task2;

    Random rnd=new Random();
    int[] arrImage={R.drawable.blue,R.drawable.green, R.drawable.yellow, R.drawable.red};

    ConstraintLayout.LayoutParams   _layparam, _lp;

    Display display;
    ArrayList<ImageView> shar = new ArrayList<>();
    Animation anim;
    LayoutInflater _inf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        _lbl1=(TextView) findViewById(R.id.lbl1);
        _btnStart=(Button)findViewById(R.id.btnStart);
        _btnStop=(Button)findViewById(R.id.btnStop);
        _holst=(ConstraintLayout) findViewById(R.id.holst);


_btnStart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(_timer1!=null)_timer1.cancel();
        _timer1 = new Timer();
        _task1 = new Task1();
        _timer1.schedule(_task1,0,500);

        if(_timer2!=null)_timer2.cancel();
        _timer2 = new Timer();
        _task2 = new Task2();
        _timer2.schedule(_task2,0,30);
    }
});
_btnStop.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(_timer1!=null){

            _timer1.cancel();
            _timer1 = null;
        }
        _holst.removeAllViews();
    }
});
    }

    class Task1 extends TimerTask{
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
_inf = LayoutInflater.from(MainActivity.this);
_img = (ImageView) _inf.inflate(R.layout.kartinka, null);
_img.setImageResource(arrImage[rnd.nextInt(4)]);
a = _holst.getWidth();
b = _holst.getHeight() / 2;
_lp = new ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.WRAP_CONTENT,
        ConstraintLayout.LayoutParams.WRAP_CONTENT
);
_lp.width= a /10;
_img.setLayoutParams(_lp);
_img.setX(rnd.nextInt(a-_lp.width));
_img.setY(rnd.nextInt(b-50)+25);
_holst.addView(_img);

_img.setOnClickListener(imgClick);
                }
            });
        }
    }

    View.OnClickListener imgClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        _holst.removeView(v);
        }
    };

    class Task2 extends TimerTask{

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    _lbl1.setText("child = "+_holst.getChildCount());
                    for(int i = 0;i<_holst.getChildCount();i++){
                    y = _holst.getChildAt(i).getY();
                    y+=dY;
                    _holst.getChildAt(i).setY(y);
                    if(y>_holst.getHeight()-50){
                        _holst.removeViewAt(i);
                    }

                    }
                }
            });
        }
    }

}