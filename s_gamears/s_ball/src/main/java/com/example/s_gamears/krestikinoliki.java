package com.example.s_gamears;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Random;

public class krestikinoliki extends AppCompatActivity {
    Intent _intent;
    Button[] _btn;
    Button _btn1;
    TableLayout _tab;
    TableRow _row;
    TableLayout.LayoutParams _tablay;
    TableRow.LayoutParams _rowlay;
    Random rnd = new Random();
    int[] n = {R.drawable.krest_1, R.drawable.krest_2};
    int numlevel = 3,tag1=0,tag2=0,x1=0,x2=0,flag=3;
    int _color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_krestikinoliki);

        _btn1 = (Button) findViewById(R.id.btn1);
        _tab = (TableLayout) findViewById(R.id.tab3);
        _btn = new Button[numlevel * numlevel];

        _btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _intent = new Intent();
                _intent.putExtra("result", "aa");
                setResult(RESULT_OK, _intent);
                finish();
            }
        });
        int k = 0;
        _tablay = new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 6);
        _tab.setLayoutParams(_tablay);
        for (int i = 0; i < numlevel; i++) {
            _row = new TableRow(this);
            _tablay = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            _row.setLayoutParams(_tablay);
            _tab.addView(_row);
            for (int j = 0; j < numlevel; j++) {
                _btn[k] = new Button(this);
                _rowlay = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                _btn[k].setLayoutParams(_rowlay);
                _btn[k].setId(k);
                _color = rnd.nextInt(2);
                _btn[k].setTag(_color);
                _btn[k].setBackgroundResource(R.drawable.yellow);


                _row.addView(_btn[k]);

                _btn[k].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button b = (Button) v;
                        _color = Integer.valueOf(b.getTag().toString());
                        if (flag == 3) {
                            b.setBackgroundResource(R.drawable.krest_1);
                            x1 = b.getId();
                            flag = 2;
                        } else {
                            if (flag == 2) {
                                b.setBackgroundResource(R.drawable.krest_2);
                                x2 = b.getId();
                                flag = 1;
                            }else {
                                if (flag == 1) {
                                    b.setBackgroundResource(R.drawable.krest_1);
                                    int x3 = b.getId();
                                    flag = 0;
                                _btn[x1].setVisibility(View.INVISIBLE);
                                _btn[x3].setVisibility(View.INVISIBLE);
                                }
                                else {
                                    if (flag == 0) {
                                        b.setBackgroundResource(R.drawable.krest_2);
                                        int x4 = b.getId();
                                        flag = 3;
                                _btn[x2].setVisibility(View.INVISIBLE);
                                _btn[x4].setVisibility(View.INVISIBLE);
                                    }
                                }
                            }
                        }
                    }
                });
                k++;
            }
        }
    }
}