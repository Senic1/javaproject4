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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class myButton extends AppCompatActivity {
String s;
int numlevel=3, k=0, _color, tag1, tag2, flag=1, x1,x2,id;
TextView _lbl2;
Button _btn21;
Intent _intent;
Button[] _btn;
TableLayout _tab;
TableRow _row;
TableLayout.LayoutParams _tablay;
TableRow.LayoutParams _rowlay;
Random rnd = new Random();
int[] n = {R.drawable.blue,R.drawable.green,R.drawable.red,R.drawable.yellow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_button);

        _lbl2 = (TextView) findViewById(R.id.lbl2);
        _btn21 = (Button) findViewById(R.id.btn21);
        _tab = (TableLayout) findViewById(R.id.tab3);

        numlevel = (Integer)getIntent().getExtras().get("k");
        _lbl2.setText(String.valueOf(numlevel));

        _btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _intent = new Intent();
                _intent.putExtra("result", "aa");
                setResult(RESULT_OK,_intent);
                finish();

            }
        });

        _tablay = new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,6);
        _tab.setLayoutParams(_tablay);
        _btn = new Button[numlevel*numlevel];

        k=0;
        
        for (int i = 0;i<numlevel;i++){
            _row = new TableRow(this);
            _tablay = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            _row.setLayoutParams(_tablay);
            _tab.addView(_row);
            for (int j = 0; j<numlevel;j++){
                _btn[k]=new Button(this);
                _rowlay = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
                _btn[k].setLayoutParams(_rowlay);
                _btn[k].setId(k);
                _color = rnd.nextInt(4);
                _btn[k].setTag(_color);
                if(MainActivity.check) {
                    _btn[k].setBackgroundResource(n[_color]);
                }
                else {
                    _btn[k].setBackgroundResource(R.drawable.red);
                }


                _row.addView(_btn[k]);

                _btn[k].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button b = (Button) v;
                        _color = Integer.valueOf(b.getTag().toString());
                        Toast.makeText(getApplication(),"color = "+_color+ " "+b.getId(), Toast.LENGTH_SHORT).show();


                        if(flag == 1){
                            tag1 = _color;
                            x1 = b.getId();
                            flag = 0;
                        }
                        else{
                            tag2 = _color;
                            x2 = b.getId();
                            if(tag1==tag2){
                                _btn[x1].setVisibility(View.INVISIBLE);
                                _btn[x2].setVisibility(View.INVISIBLE);
                                _lbl2.setText("yes");
                            }
                            else {
                             _lbl2.setText("nonono");
                             flag = 1;
                            }

                        }
                    }
                });


                k++;

            }

        }

    }
}