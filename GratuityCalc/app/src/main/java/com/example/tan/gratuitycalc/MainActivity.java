package com.example.tan.gratuitycalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Calculate = (Button) findViewById(R.id.Calculate);

        EditText total = (EditText)findViewById(R.id.Total);
        EditText PercentageTip = (EditText)findViewById(R.id.pTip);
        EditText NumberOfPeople = (EditText)findViewById(R.id.numPpl);

        total.setKeyListener(DigitsKeyListener.getInstance(true,true));
        PercentageTip.setKeyListener(DigitsKeyListener.getInstance(true,true));

        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                int Total = Integer.parseInt(total.getText().toString());
//                int PercentTip = Integer.parseInt(PercentageTip.getText().toString());
//                int numPeople = Integer.parseInt(NumberOfPeople.getText().toString());

                Toast.makeText(getApplicationContext(),"Successfully sent!", Toast.LENGTH_LONG).show();

            }
        });

    }


}
