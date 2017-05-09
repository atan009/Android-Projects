package com.example.tan.a8puzzlesolver;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.Arrays;


//Integer by reference
class MyInteger
{
    int value;

    public MyInteger(int newValue)
    {
        value = newValue;
    }
}



public class MainActivity extends AppCompatActivity {

    //detects where 0 is located
    public void Z_pos(MyInteger verPos, MyInteger horPos, ArrayList<ArrayList<Integer>> cur)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int k = 0; k < 3; k++)
            {
                if (cur.get(i).get(k) == 0) {
                    verPos.value = i;
                    horPos.value = k;
                }
            }
        }
    }



    public ArrayList<ArrayList<Integer>>mv_up(MyInteger verPos, MyInteger horPos, ArrayList<ArrayList<Integer>> cur)
    {
        ArrayList<ArrayList<Integer>> up = cur;
        up.get(verPos.value).set(horPos.value, up.get(verPos.value-1).get(horPos.value));
        up.get(verPos.value-1).set(horPos.value,0);
        return up;
    }



    public ArrayList<ArrayList<Integer>>mv_down(MyInteger verPos, MyInteger horPos, ArrayList<ArrayList<Integer>> cur)
    {
        ArrayList<ArrayList<Integer>> down = cur;
        down.get(verPos.value).set(horPos.value, down.get(verPos.value+1).get(horPos.value));
        down.get(verPos.value+1).set(horPos.value,0);
        return down;
    }



    public ArrayList<ArrayList<Integer>>mv_left(MyInteger verPos, MyInteger horPos, ArrayList<ArrayList<Integer>> cur)
    {
        ArrayList<ArrayList<Integer>> left = cur;
        left.get(verPos.value).set(horPos.value, left.get(verPos.value).get(horPos.value-1));
        left.get(verPos.value).set(horPos.value-1,0);
        return left;
    }



    public ArrayList<ArrayList<Integer>>mv_right(MyInteger verPos, MyInteger horPos, ArrayList<ArrayList<Integer>> cur)
    {
        ArrayList<ArrayList<Integer>> right = cur;
        right.get(verPos.value).set(horPos.value, right.get(verPos.value).get(horPos.value+1));
        right.get(verPos.value).set(horPos.value+1,0);
        return right;
    }

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
                MyInteger verPos = new MyInteger(0);
                MyInteger horPos = new MyInteger(0);


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


                Z_pos(verPos, horPos, eight_puzzle);
                eight_puzzle = mv_right(verPos,horPos,eight_puzzle);
                Z_pos(verPos, horPos, eight_puzzle);
                Toast.makeText(getApplicationContext(), Integer.toString(horPos.value), Toast.LENGTH_LONG).show();
            }
        });
    }
}
