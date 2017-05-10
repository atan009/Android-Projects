package com.example.tan.a8puzzlesolver;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import java.lang.reflect.Array;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Queue;


//Integer by reference
class MyInteger
{
    int value;

    public MyInteger(int newValue)
    {
        value = newValue;
    }
}

class node
{
    int g = 0;
    int h = 0;
    node next = null;
    ArrayList<ArrayList<Integer>> cur = new ArrayList<ArrayList<Integer>>();

    public node(ArrayList<ArrayList<Integer>> copy)
    {
        for (ArrayList<Integer> item : copy)
        {
            cur.add((ArrayList<Integer>) item.clone());
        }
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


    //counts misplaced tiles
    int misplaced(ArrayList<ArrayList<Integer>> cur)
    {
        int misplaced = 0;
        if (cur.get(0).get(0) != 1)
        {
            misplaced += 1;
        }
        if (cur.get(0).get(1) != 2)
        {
            misplaced += 1;
        }
        if (cur.get(0).get(2) != 3)
        {
            misplaced += 1;
        }
        if (cur.get(1).get(0) != 4)
        {
            misplaced += 1;
        }
        if (cur.get(1).get(1) != 5)
        {
            misplaced += 1;
        }
        if (cur.get(1).get(2) != 6)
        {
            misplaced += 1;
        }
        if (cur.get(2).get(0) != 7)
        {
            misplaced += 1;
        }
        if (cur.get(2).get(1) != 8)
        {
            misplaced += 1;
        }
        if (cur.get(2).get(2) != 0)
        {
            misplaced += 1;
        }
        return misplaced;
    }



    //counts Manhattan distance of all tiles
    int Manhattan(ArrayList<ArrayList<Integer>> cur)
    {
        int Manhattan = 0;

        if (cur.get(0).get(0) == 1)
        {
            Manhattan += 0;
        }

        else if (cur.get(0).get(0) == 2 || cur.get(0).get(0) == 4)
        {
            Manhattan += 1;
        }

        else if (cur.get(0).get(0) == 3 || cur.get(0).get(0) == 7 || cur.get(0).get(0) == 5)
        {
            Manhattan += 2;
        }

        else if (cur.get(0).get(0) == 6 || cur.get(0).get(0) == 8)
        {
            Manhattan += 3;
        }

        else if (cur.get(0).get(0) == 0)
        {
            Manhattan += 4;
        }


        if (cur.get(0).get(1) == 2)
        {
            Manhattan += 0;
        }

        else if (cur.get(0).get(1) == 1 || cur.get(0).get(1) == 3 || cur.get(0).get(1) == 5)
        {
            Manhattan += 1;
        }

        else if (cur.get(0).get(1) == 4 || cur.get(0).get(1) == 6 || cur.get(0).get(1) == 8)
        {
            Manhattan += 2;
        }

        else if (cur.get(0).get(1) == 7 || cur.get(0).get(1) == 0)
        {
            Manhattan += 3;
        }


        if (cur.get(0).get(2) == 3)
        {
            Manhattan += 0;
        }

        else if (cur.get(0).get(2) == 2 || cur.get(0).get(2) == 6)
        {
            Manhattan += 1;
        }

        else if (cur.get(0).get(2) == 1 || cur.get(0).get(2) == 5 || cur.get(0).get(2) == 0)
        {
            Manhattan += 2;
        }

        else if (cur.get(0).get(2) == 4 || cur.get(0).get(2) == 8)
        {
            Manhattan += 3;
        }

        else if (cur.get(0).get(2) == 7)
        {
            Manhattan += 4;
        }


        if (cur.get(1).get(0) == 4)
        {
            Manhattan += 0;
        }

        else if (cur.get(1).get(0) == 1 || cur.get(1).get(0) == 5 || cur.get(1).get(0) == 7)
        {
            Manhattan += 1;
        }

        else if (cur.get(1).get(0) == 2 || cur.get(1).get(0) == 6 || cur.get(1).get(0) == 8)
        {
            Manhattan += 2;
        }

        else if (cur.get(1).get(0) == 3 || cur.get(1).get(0) == 0)
        {
            Manhattan += 3;
        }


        if (cur.get(1).get(1) == 5)
        {
            Manhattan += 0;
        }

        else if (cur.get(1).get(1) == 2 || cur.get(1).get(1) == 4 || cur.get(1).get(1) == 6 || cur.get(1).get(1) == 8)
        {
            Manhattan += 1;
        }

        else if (cur.get(1).get(1) == 1 || cur.get(1).get(1) == 3 || cur.get(1).get(1) == 7 || cur.get(1).get(1) == 0)
        {
            Manhattan += 2;
        }


        if (cur.get(1).get(2) == 6)
        {
            Manhattan += 0;
        }

        else if (cur.get(1).get(2) == 5 || cur.get(1).get(2) == 3 || cur.get(1).get(2) == 0)
        {
            Manhattan += 1;
        }

        else if (cur.get(1).get(2) == 2 || cur.get(1).get(2) == 4 || cur.get(1).get(2) == 8)
        {
            Manhattan += 2;
        }

        else if (cur.get(1).get(2) == 1 || cur.get(1).get(2) == 7)
        {
            Manhattan += 3;
        }


        if (cur.get(2).get(0) == 7)
        {
            Manhattan += 0;
        }

        else if (cur.get(2).get(0) == 4 || cur.get(2).get(0) == 8)
        {
            Manhattan += 1;
        }

        else if (cur.get(2).get(0) == 1 || cur.get(2).get(0) == 5 || cur.get(2).get(0) == 0)
        {
            Manhattan += 2;
        }

        else if (cur.get(2).get(0) == 2 || cur.get(2).get(0) == 6)
        {
            Manhattan += 3;
        }

        else if (cur.get(2).get(0) == 3)
        {
            Manhattan += 4;
        }


        if (cur.get(2).get(1) == 8)
        {
            Manhattan += 0;
        }

        else if (cur.get(2).get(1) == 7 || cur.get(2).get(1) == 5 || cur.get(2).get(1) == 0)
        {
            Manhattan += 1;
        }

        else if (cur.get(2).get(1) == 2 || cur.get(2).get(1) == 4 || cur.get(2).get(1) == 6)
        {
            Manhattan += 2;
        }

        else if (cur.get(2).get(1) == 1 || cur.get(2).get(1) == 3)
        {
            Manhattan += 3;
        }

        if (cur.get(2).get(2) == 0)
        {
            Manhattan += 0;
        }

        else if (cur.get(2).get(2) == 8 || cur.get(2).get(2) == 6)
        {
            Manhattan += 1;
        }

        else if (cur.get(2).get(2) == 3 || cur.get(2).get(2) == 5 || cur.get(2).get(2) == 7)
        {
            Manhattan += 2;
        }

        else if (cur.get(2).get(2) == 2 || cur.get(2).get(2) == 4)
        {
            Manhattan += 3;
        }

        else if (cur.get(2).get(2) == 1)
        {
            Manhattan += 4;
        }

        return Manhattan;

    }



    boolean solvable(ArrayList<ArrayList<Integer>> cur)
    {
        ArrayList<Integer> linear = new ArrayList<Integer>();
        int s = 0;
        int zero = 0, one = 0, two = 0, three = 0, four = 0, five = 0, six = 0, seven = 0, eight = 0;

        for (int i = 0; i < 3; i++)
        {
            for (int k = 0; k < 3; k++)
            {
                linear.add(cur.get(i).get(k));
            }
        }

        for (int i = 0; i < 9; i++)
        {
            if (linear.get(i) == 0)
            {
                zero = 1;
            }

            else if (linear.get(i) == 1)
            {
                one = 1;
            }

            else if (linear.get(i) == 2)
            {
                two = 1;
            }

            else if (linear.get(i) == 3)
            {
                three = 1;
            }

            else if (linear.get(i) == 4)
            {
                four = 1;
            }

            else if (linear.get(i) == 5)
            {
                five = 1;
            }

            else if (linear.get(i) == 6)
            {
                six = 1;
            }

            else if (linear.get(i) == 7)
            {
                seven = 1;
            }

            else if (linear.get(i) == 8)
            {
                eight = 1;
            }
        }

        for (int l = 0; l < 8; l++)
        {
            for (int m = l + 1; m < 9; m++)
            {

                if (linear.get(m) < linear.get(l) && linear.get(m) != 0)
                {
                    s += 1;
                }
            }

        }



        if (s % 2 == 0 && zero == 1 && one == 1 && two == 1 && three == 1 && four == 1 && five == 1 && six == 1 && seven == 1 && eight == 1)
        {
            return true;
        }

        else
        {
            return false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<ArrayList<Integer>> goal = new ArrayList<ArrayList<Integer>>();
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


                boolean solve = solvable(eight_puzzle);
                if (solve == false)
                {
                    Toast.makeText(getApplicationContext(), "unsolvable", Toast.LENGTH_LONG).show();
                }

                else
                {
                    node given = new node(eight_puzzle);


                    Z_pos(verPos,horPos,eight_puzzle);
                    given.cur = mv_down(verPos, horPos, given.cur);
                    Toast.makeText(getApplicationContext(), Integer.toString(eight_puzzle.get(1).get(1)), Toast.LENGTH_LONG).show();
                    /*
                    node init = new node();
                    init.cur = eight_puzzle;

                    node current = new node();
                    current = init;

                    ArrayList<ArrayList<ArrayList<Integer>>> traversed = new ArrayList<ArrayList<ArrayList<Integer>>>();
                    traversed.add(current.cur);

                    ArrayList<Queue<node>>a_misplaced = new ArrayList<Queue<node>>(31+(4*9));
                    a_misplaced.get(misplaced(init.cur)).add(init);

                    while(current.cur != goal)
                    {
                        for (int i = 0; i < 67; i++)
                        {
                            if (!a_misplaced.get(i).isEmpty())
                            {
                                current = a_misplaced.get(i).poll();
                            }
                        }
                    }
                    */
                }
            }
        });
    }
}
