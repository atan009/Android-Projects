package com.example.tan.a8puzzlesolver;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ArrayList<Integer>> goal = new ArrayList<ArrayList<Integer>>();
        goal.add(new ArrayList<Integer>(Arrays.asList(1,2,3)));
        goal.add(new ArrayList<Integer>(Arrays.asList(4,5,6)));
        goal.add(new ArrayList<Integer>(Arrays.asList(7,8,0)));

        Button solve = (Button) findViewById(R.id.solve_button);
        solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<ArrayList<Integer>> eight_puzzle = new ArrayList<ArrayList<Integer>>();

                EditText box1 = (EditText) findViewById(R.id.box1);
                EditText box2 = (EditText) findViewById(R.id.box2);
                EditText box3 = (EditText) findViewById(R.id.box3);
                EditText box4 = (EditText) findViewById(R.id.box4);
                EditText box5 = (EditText) findViewById(R.id.box5);
                EditText box6 = (EditText) findViewById(R.id.box6);
                EditText box7 = (EditText) findViewById(R.id.box7);
                EditText box8 = (EditText) findViewById(R.id.box8);
                EditText box9 = (EditText) findViewById(R.id.box9);

                ArrayList<Integer> row1 = new ArrayList<Integer>();
                row1.add(Integer.parseInt(box1.getText().toString()));
                row1.add(Integer.parseInt(box2.getText().toString()));
                row1.add(Integer.parseInt(box3.getText().toString()));

                ArrayList<Integer> row2 = new ArrayList<Integer>();
                row2.add(Integer.parseInt(box4.getText().toString()));
                row2.add(Integer.parseInt(box5.getText().toString()));
                row2.add(Integer.parseInt(box6.getText().toString()));

                ArrayList<Integer> row3 = new ArrayList<Integer>();
                row3.add(Integer.parseInt(box7.getText().toString()));
                row3.add(Integer.parseInt(box8.getText().toString()));
                row3.add(Integer.parseInt(box9.getText().toString()));

                eight_puzzle.add(row1);
                eight_puzzle.add(row2);
                eight_puzzle.add(row3);

                Toast.makeText(getApplicationContext(), Integer.toString(eight_puzzle.get(0).get(0)), Toast.LENGTH_LONG).show();
            }
        });
    }
}
