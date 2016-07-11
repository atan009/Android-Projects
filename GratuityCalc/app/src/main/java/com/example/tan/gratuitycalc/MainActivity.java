package com.example.tan.gratuitycalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Calculate = (Button) findViewById(R.id.Calculate);
        final Button RoundTip = (Button)findViewById(R.id.roundTip);
        Button RoundTotal = (Button)findViewById(R.id.roundTotal);

        final int[] canRound = {0};

        setTitle("Gratuity Calculator");

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
                    double Total = Double.parseDouble(t);
                    double PercentTip = Double.parseDouble(PTip);
                    int numPeople = Integer.parseInt(Ppl);




                    double Subtotal = Total / numPeople;

                    double tip = Total * PercentTip / 100;
                    double singleTip = tip / numPeople;

                    double singleTotal = Subtotal + singleTip;



                    TextView sTot = (TextView)findViewById(R.id.sTotal);
                    TextView sTip = (TextView)findViewById(R.id.sTip);
                    TextView pTip = (TextView)findViewById(R.id.pTotal);

                    sTot.setText(String.format("$%.2f", Subtotal));
                    sTip.setText(String.format("$%.2f", singleTip));
                    pTip.setText(String.format("$%.2f", singleTotal));


                    canRound[0] = 1;
                    //Toast.makeText(getApplicationContext(), Double.toString(GroupTotal), Toast.LENGTH_LONG).show();
                }

            }
        });


        RoundTip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (canRound[0] == 1)
                {
                    TextView rTip = (TextView)findViewById(R.id.sTip);
                    TextView rTotal = (TextView)findViewById(R.id.pTotal);
                    TextView rsTotal = (TextView)findViewById(R.id.sTotal);


                    String nTip = rTip.getText().toString();
                    nTip = nTip.substring(1,nTip.length());
                    double newTip = Double.valueOf(nTip);


                    int roundTip = (int)Math.round(newTip);
                    rTip.setText("$" + Integer.toString(roundTip));

                    String RSTot = rsTotal.getText().toString();
                    RSTot = RSTot.substring(1,RSTot.length());
                    double RSTotal = Double.valueOf(RSTot);

                    String STot = rTotal.getText().toString();
                    STot = STot.substring(1,STot.length());
                    double STotal = Double.valueOf(STot);

                    double value = RSTotal + roundTip;
                    rTotal.setText("$" + Double.toString(value));

                }
            }
        });


        RoundTotal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (canRound[0] == 1)
                {
                    TextView rTip = (TextView)findViewById(R.id.sTip);
                    TextView rTotal = (TextView)findViewById(R.id.pTotal);
                    TextView rsTotal = (TextView)findViewById(R.id.sTotal);


                    String nTip = rTip.getText().toString();
                    nTip = nTip.substring(1,nTip.length());
                    double newTip = Double.valueOf(nTip);


                    String RSTot = rsTotal.getText().toString();
                    RSTot = RSTot.substring(1,RSTot.length());
                    double RSTotal = Double.valueOf(RSTot);

                    String STot = rTotal.getText().toString();
                    STot = STot.substring(1,STot.length());
                    double STotal = Double.valueOf(STot);

                    int roundTot = (int)Math.round(STotal);
                    rTotal.setText("$" + Integer.toString(roundTot));

                    double value = roundTot - RSTotal;
                    rTip.setText(String.format("$%.2f",value));

                }
            }
        });

    }


}
