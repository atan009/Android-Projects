package com.example.tan.a8puzzlesolver;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;



public class solved_puzzle_movement extends AppCompatActivity {


    public static TextView s1, s2, s3, s4, s5, s6, s7, s8, s9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solved_puzzle_movement);

        s1 = (TextView) findViewById(R.id.spot1);
        s2 = (TextView) findViewById(R.id.spot2);
        s3 = (TextView) findViewById(R.id.spot3);
        s4 = (TextView) findViewById(R.id.spot4);
        s5 = (TextView) findViewById(R.id.spot5);
        s6 = (TextView) findViewById(R.id.spot6);
        s7 = (TextView) findViewById(R.id.spot7);
        s8 = (TextView) findViewById(R.id.spot8);
        s9 = (TextView) findViewById(R.id.spot9);



        Intent test = getIntent();
        Bundle bund = test.getExtras();
        final ArrayList<Integer> solution_array = bund.getIntegerArrayList("solution_stack");
        Collections.reverse(solution_array);


        if (!solution_array.isEmpty())
        {
            s9.setText(Integer.toString(solution_array.remove(0)));
            s8.setText(Integer.toString(solution_array.remove(0)));
            s7.setText(Integer.toString(solution_array.remove(0)));
            s6.setText(Integer.toString(solution_array.remove(0)));
            s5.setText(Integer.toString(solution_array.remove(0)));
            s4.setText(Integer.toString(solution_array.remove(0)));
            s3.setText(Integer.toString(solution_array.remove(0)));
            s2.setText(Integer.toString(solution_array.remove(0)));
            s1.setText(Integer.toString(solution_array.remove(0)));
        }


        Button show = (Button) findViewById(R.id.Play);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Thread t = new Thread() {

                    @Override
                    public void run() {
                        try {
                            while (!solution_array.isEmpty()) {
                                Thread.sleep(1000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (!solution_array.isEmpty())
                                        {
                                            s9.setText(Integer.toString(solution_array.remove(0)));
                                            s8.setText(Integer.toString(solution_array.remove(0)));
                                            s7.setText(Integer.toString(solution_array.remove(0)));
                                            s6.setText(Integer.toString(solution_array.remove(0)));
                                            s5.setText(Integer.toString(solution_array.remove(0)));
                                            s4.setText(Integer.toString(solution_array.remove(0)));
                                            s3.setText(Integer.toString(solution_array.remove(0)));
                                            s2.setText(Integer.toString(solution_array.remove(0)));
                                            s1.setText(Integer.toString(solution_array.remove(0)));
                                        }
                                    }
                                });
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                };


                if (!solution_array.isEmpty())
                {
                    t.start();
                }
                //Toast.makeText(getApplicationContext(), Integer.toString(solution_array.size()), Toast.LENGTH_LONG).show();
            }
        });
    }
}
