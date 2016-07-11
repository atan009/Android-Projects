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

        final EditText total = (EditText)findViewById(R.id.TotalGroup);
        final EditText PercentageTip = (EditText)findViewById(R.id.TipGroup);
        final EditText NumberOfPeople = (EditText)findViewById(R.id.numPpl);

        total.setKeyListener(DigitsKeyListener.getInstance(true,true));
        PercentageTip.setKeyListener(DigitsKeyListener.getInstance(true,true));

        assert Calculate != null;
        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = total.getText().toString();
                String PTip = PercentageTip.getText().toString();
                String Ppl = NumberOfPeople.getText().toString();

                if (t.length() > 0 && PTip.length() > 0 && Ppl.length() > 0) {
                    int Total = Integer.parseInt(t);
                    int PercentTip = Integer.parseInt(PTip);
                    int numPeople = Integer.parseInt(Ppl);

                    int GroupTotal = Total * PercentTip / 100 + Total;

                    Toast.makeText(getApplicationContext(), Integer.toString(GroupTotal), Toast.LENGTH_LONG).show();
                }

            }
        });

    }


}
