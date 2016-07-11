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

                    sTot.setText(String.format("$ %.2f", Subtotal));
                    sTip.setText(String.format("$ %.2f", singleTip));
                    pTip.setText(String.format("$ %.2f", singleTotal));

                    //Toast.makeText(getApplicationContext(), Double.toString(GroupTotal), Toast.LENGTH_LONG).show();
                }

            }
        });

    }


}
