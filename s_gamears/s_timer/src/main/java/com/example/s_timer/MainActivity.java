package com.example.s_timer;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button _btnStart1, _btnStop1, _btnStart2, _btnStop2, _btnStart3, _btnStop3;
    CheckBox _check1;
    TextView _lbl1,_lbl2;
    ConstraintLayout _lay;
    ImageView _img;

    Timer _timer1, _timer2, _timer3;
    TimerTask _task1,_task2, _task3;

    Random rnd=new Random();
    int sek=0, n;
    int[] arrImg = {R.drawable.cabriolet, R.drawable.minivan, R.drawable.picup, R.drawable.sedan};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _lbl1=(TextView)findViewById(R.id.lbl1);
        _lbl2=(TextView)findViewById(R.id.lbl2);
        _btnStart1=(Button)findViewById(R.id.btnStart1);
        _btnStop1=(Button)findViewById(R.id.btnStop1);
        _btnStart2=(Button)findViewById(R.id.btnStart2);
        _btnStop2=(Button)findViewById(R.id.btnStop2);
        _btnStart3=(Button)findViewById(R.id.btnStart3);
        _btnStop3=(Button)findViewById(R.id.btnStop3);
        _check1=(CheckBox)findViewById(R.id.check1);
//        _lay=(ConstraintLayout)findViewById(R.id.lay);
        _img=(ImageView)findViewById(R.id.img);

        _btnStart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_timer1!=null) _timer1.cancel();
                _timer1 = new Timer();
                _timer1.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                _lbl1.setText(String.valueOf(sek));
                                sek++;
                            }
                        });
                    }
                }, 0, 1000);
            }
        });
        _btnStop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          if(_timer1!=null)
          {
              _timer1.cancel();
              _timer1=null;
          }

            }
        });
        _btnStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (_timer2!=null){_timer2.cancel();}
            _timer2 = new Timer();
            _task2=new MyTimerTask();
            _timer2.schedule(_task2,1000,1000);
            }
        });
        _btnStop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_timer2!=null)
                {
                    _timer2.cancel();
                    _timer2=null;
                }

            }
        });
        _btnStart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_timer3!=null){_timer3.cancel();}
                _timer3 = new Timer();
                _task3 = new TimerTask() {
                    @Override
                    public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            _img.setImageResource(arrImg[rnd.nextInt(4)]);
                        }
                    });
                    }
                };
              _timer3.schedule(_task3,0,500);
            }
        });
        _btnStop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_timer3!=null)
                {
                    _timer3.cancel();
                    _timer3=null;
                }
            }
        });

    }
SimpleDateFormat bublik;
class MyTimerTask extends TimerTask{
    @Override
    public void run() {
        if(_check1.isChecked())
            bublik = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss", Locale.getDefault());
        else
            bublik = new SimpleDateFormat("dd:MM mm:ss", Locale.getDefault());
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+3"));
            String s = bublik.format(calendar.getTime());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                _lbl2.setText(s);

            }
        });
    }
}


}