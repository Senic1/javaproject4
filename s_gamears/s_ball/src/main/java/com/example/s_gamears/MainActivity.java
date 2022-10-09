package com.example.s_gamears;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    Button _btn12, _btn2, _btn13;
    RadioButton _rb1, _rb2, _rb3;
    CheckBox _check1;
    Intent _intent;
    public  static  int kolvo;
    public  static  boolean check=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _btn12=(Button)findViewById(R.id.btn12);
        _btn13 = (Button)findViewById(R.id.btn31);
        _rb1=(RadioButton)findViewById(R.id.rb1);
        _rb2=(RadioButton)findViewById(R.id.rb2);
        _rb3=(RadioButton)findViewById(R.id.rb3);
        _check1=(CheckBox)findViewById(R.id.check1);

        _btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _intent = new Intent(getApplication(), myButton.class);
                if(_rb1.isChecked()) kolvo = 3;
                if(_rb2.isChecked()) kolvo = 5;
                if(_rb3.isChecked()) kolvo = 10;
                _intent.putExtra("k", kolvo);
                startActivityForResult(_intent, 0);

//                if(_check1.isChecked()) check = true;
//                else check = false;
                check = _check1.isChecked() ? true : false;
            }
        });
        _btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _intent = new Intent(getApplication(),krestikinoliki.class);
                startActivityForResult(_intent,0);
            }
        });

    }
}