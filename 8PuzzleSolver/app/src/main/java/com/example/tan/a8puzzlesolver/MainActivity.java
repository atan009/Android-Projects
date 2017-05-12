package com.example.tan.a8puzzlesolver;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.Switch;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.lang.reflect.Array;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.LinkedList;
        import java.util.Queue;
        import java.util.Stack;


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
    String direction = "";
    int g = 0;
    int h = 0;
    node prev = null;
    ArrayList<ArrayList<Integer>> cur = new ArrayList<ArrayList<Integer>>();

    public node(ArrayList<ArrayList<Integer>> copy)
    {
        for (ArrayList<Integer> item : copy)
        {
            cur.add((ArrayList<Integer>) item.clone());
        }
    }

    public node(node copy)
    {
        for (ArrayList<Integer> item : copy.cur)
        {
            cur.add((ArrayList<Integer>) item.clone());
        }
        g = copy.g;
        h = copy.h;
        prev = copy.prev;
        direction = copy.direction;
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



    boolean equalStates(ArrayList<ArrayList<Integer>>current, ArrayList<ArrayList<Integer>>comp)
    {
        if (current.get(0).get(0) == comp.get(0).get(0) && current.get(0).get(1) == comp.get(0).get(1) && current.get(0).get(2) == comp.get(0).get(2)
                && current.get(1).get(0) == comp.get(1).get(0) && current.get(1).get(1) == comp.get(1).get(1) && current.get(1).get(2) == comp.get(1).get(2)
                && current.get(2).get(0) == comp.get(2).get(0) && current.get(2).get(1) == comp.get(2).get(1) && current.get(2).get(2) == comp.get(2).get(2))
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

        final Stack<node> solution = new Stack<node>();

        final Switch method = (Switch) findViewById(R.id.Solution_Switch);

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



                if (box1.getText().toString().isEmpty() || box2.getText().toString().isEmpty() || box3.getText().toString().isEmpty() ||
                        box4.getText().toString().isEmpty() || box5.getText().toString().isEmpty() || box6.getText().toString().isEmpty() ||
                        box7.getText().toString().isEmpty() || box8.getText().toString().isEmpty() || box9.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Not all boxes are filled", Toast.LENGTH_LONG).show();
                    return;
                }



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

                    node init = new node(eight_puzzle);

                    node current = new node(eight_puzzle);

                    ArrayList<ArrayList<ArrayList<Integer>>> traversed = new ArrayList<ArrayList<ArrayList<Integer>>>();
                    traversed.add(current.cur);

                    ArrayList<Queue<node>>a_misplaced = new ArrayList<Queue<node>>(31+(4*9));
                    for (int i = 0; i < (31+(4*9)); i++)
                    {
                        Queue<node> myNumbers = new LinkedList<node>();
                        a_misplaced.add(myNumbers);
                    }
                    a_misplaced.get(misplaced(init.cur)).add(init);

                    while(!equalStates(current.cur, goal))
                    {
                        for (int i = 0; i < 67; i++)
                        {
                            if (!a_misplaced.get(i).isEmpty() && a_misplaced.get(i) != null)
                            {
                                current = a_misplaced.get(i).poll();
                                //Toast.makeText(getApplicationContext(), Integer.toString(current.cur.get(1).get(1)), Toast.LENGTH_LONG).show();
                                break;
                            }
                        }


                        Z_pos(verPos, horPos, current.cur);


                        if (verPos.value == 0 && horPos.value == 0)
                        {
                            node right_filler = new node(current);
                            node right = new node(current);
                            right.prev = right_filler;
                            right.cur = mv_right(verPos, horPos, right.cur);
                            right.direction = "right";
                            if (!traversed.contains(right.cur))
                            {
                                right.g = current.g + 1;
                                right.h = Manhattan(right.cur);
                                if (right.g + right.h < 67)
                                {
                                    a_misplaced.get(right.g + right.h).add(right);
                                    traversed.add(right.cur);
                                }
                            }


                            node down_filler = new node(current);
                            node down = new node(current);
                            down.prev = down_filler;
                            down.cur = mv_down(verPos, horPos, down.cur);
                            down.direction = "down";
                            if (!traversed.contains(down.cur))
                            {
                                down.g = current.g + 1;
                                down.h = Manhattan(down.cur);
                                if (down.g + down.h < 67)
                                {
                                    a_misplaced.get(down.g + down.h).add(down);
                                    traversed.add(down.cur);
                                }
                            }
                        }


                        else if (verPos.value == 0 && horPos.value == 1)
                        {
                            node left_filler = new node(current);
                            node left = new node(current);
                            left.prev = left_filler;
                            left.cur = mv_left(verPos, horPos, left.cur);
                            left.direction = "left";
                            if (!traversed.contains(left.cur))
                            {
                                left.g = current.g + 1;
                                left.h = Manhattan(left.cur);
                                if (left.g + left.h < 67)
                                {
                                    a_misplaced.get(left.g + left.h).add(left);
                                    traversed.add(left.cur);
                                }
                            }



                            node right_filler = new node(current);
                            node right = new node(current);
                            right.prev = right_filler;
                            right.cur = mv_right(verPos, horPos, right.cur);
                            right.direction = "right";
                            if (!traversed.contains(right.cur))
                            {
                                right.g = current.g + 1;
                                right.h = Manhattan(right.cur);
                                if (right.g + right.h < 67)
                                {
                                    a_misplaced.get(right.g + right.h).add(right);
                                    traversed.add(right.cur);
                                }
                            }



                            node down_filler = new node(current);
                            node down = new node(current);
                            down.prev = down_filler;
                            down.cur = mv_right(verPos, horPos, down.cur);
                            down.direction = "down";
                            if (!traversed.contains(down.cur))
                            {
                                down.g = current.g + 1;
                                down.h = Manhattan(down.cur);
                                if (down.g + down.h < 67)
                                {
                                    a_misplaced.get(down.g + down.h).add(down);
                                    traversed.add(down.cur);
                                }
                            }
                        }


                        else if (verPos.value == 0 && horPos.value == 2)
                        {
                            node left_filler = new node(current);
                            node left = new node(current);
                            left.prev = left_filler;
                            left.cur = mv_left(verPos, horPos, left.cur);
                            left.direction = "left";
                            if (!traversed.contains(left.cur))
                            {
                                left.g = current.g + 1;
                                left.h = Manhattan(left.cur);
                                if (left.g + left.h < 67)
                                {
                                    a_misplaced.get(left.g + left.h).add(left);
                                    traversed.add(left.cur);
                                }
                            }



                            node down_filler = new node(current);
                            node down = new node(current);
                            down.prev = down_filler;
                            down.cur = mv_down(verPos, horPos, down.cur);
                            down.direction = "down";
                            if (!traversed.contains(down.cur))
                            {
                                down.g = current.g + 1;
                                down.h = Manhattan(down.cur);
                                if (down.g + down.h < 67)
                                {
                                    a_misplaced.get(down.g + down.h).add(down);
                                    traversed.add(down.cur);
                                }
                            }
                        }



                        else if (verPos.value == 1 && horPos.value == 0)
                        {
                            node up_filler = new node(current);
                            node up = new node(current);
                            up.prev = up_filler;
                            up.cur = mv_up(verPos, horPos, up.cur);
                            up.direction = "up";
                            if (!traversed.contains(up.cur))
                            {
                                up.g = current.g + 1;
                                up.h = Manhattan(up.cur);
                                if (up.g + up.h < 67)
                                {
                                    a_misplaced.get(up.g + up.h).add(up);
                                    traversed.add(up.cur);
                                }
                            }



                            node right_filler = new node(current);
                            node right = new node(current);
                            right.prev = right_filler;
                            right.cur = mv_right(verPos, horPos, right.cur);
                            right.direction = "right";
                            if (!traversed.contains(right.cur))
                            {
                                right.g = current.g + 1;
                                right.h = Manhattan(right.cur);
                                if (right.g + right.h < 67)
                                {
                                    a_misplaced.get(right.g + right.h).add(right);
                                    traversed.add(right.cur);
                                }
                            }



                            node down_filler = new node(current);
                            node down = new node(current);
                            down.prev = down_filler;
                            down.cur = mv_down(verPos, horPos, down.cur);
                            down.direction = "down";
                            if (!traversed.contains(down.cur))
                            {
                                down.g = current.g + 1;
                                down.h = Manhattan(down.cur);
                                if (down.g + down.h < 67)
                                {
                                    a_misplaced.get(down.g + down.h).add(down);
                                    traversed.add(down.cur);
                                }
                            }
                        }



                        else if (verPos.value == 1 && horPos.value == 1)
                        {
                            node up_filler = new node(current);
                            node up = new node(current);
                            up.prev = up_filler;
                            up.cur = mv_up(verPos, horPos, up.cur);
                            up.direction = "up";
                            if (!traversed.contains(up.cur))
                            {
                                up.g = current.g + 1;
                                up.h = Manhattan(up.cur);
                                if (up.g + up.h < 67)
                                {
                                    a_misplaced.get(up.g + up.h).add(up);
                                    traversed.add(up.cur);
                                }
                            }



                            node right_filler = new node(current);
                            node right = new node(current);
                            right.prev = right_filler;
                            right.cur = mv_right(verPos, horPos, right.cur);
                            right.direction = "right";
                            if (!traversed.contains(right.cur))
                            {
                                right.g = current.g + 1;
                                right.h = Manhattan(right.cur);
                                if (right.g + right.h < 67)
                                {
                                    a_misplaced.get(right.g + right.h).add(right);
                                    traversed.add(right.cur);
                                }
                            }



                            node down_filler = new node(current);
                            node down = new node(current);
                            down.prev = down_filler;
                            down.cur = mv_down(verPos, horPos, down.cur);
                            down.direction = "down";
                            if (!traversed.contains(down.cur))
                            {
                                down.g = current.g + 1;
                                down.h = Manhattan(down.cur);
                                if (down.g + down.h < 67)
                                {
                                    a_misplaced.get(down.g + down.h).add(down);
                                    traversed.add(down.cur);
                                }
                            }



                            node left_filler = new node(current);
                            node left = new node(current);
                            left.prev = left_filler;
                            left.cur = mv_left(verPos, horPos, left.cur);
                            left.direction = "left";
                            if (!traversed.contains(left.cur))
                            {
                                left.g = current.g + 1;
                                left.h = Manhattan(left.cur);
                                if (left.g + left.h < 67)
                                {
                                    a_misplaced.get(left.g + left.h).add(left);
                                    traversed.add(left.cur);
                                }
                            }
                        }




                        else if (verPos.value == 1 && horPos.value == 2)
                        {
                            node down_filler = new node(current);
                            node down = new node(current);
                            down.prev = down_filler;
                            down.cur = mv_down(verPos, horPos, down.cur);
                            down.direction = "down";
                            if (!traversed.contains(down.cur))
                            {
                                down.g = current.g + 1;
                                down.h = Manhattan(down.cur);
                                if (down.g + down.h < 67)
                                {
                                    a_misplaced.get(down.g + down.h).add(down);
                                    traversed.add(down.cur);
                                }
                            }



                            node up_filler = new node(current);
                            node up = new node(current);
                            up.prev = up_filler;
                            up.cur = mv_up(verPos, horPos, up.cur);
                            up.direction = "up";
                            if (!traversed.contains(up.cur))
                            {
                                up.g = current.g + 1;
                                up.h = Manhattan(up.cur);
                                if (up.g + up.h < 67)
                                {
                                    a_misplaced.get(up.g + up.h).add(up);
                                    traversed.add(up.cur);
                                }
                            }



                            node left_filler = new node(current);
                            node left = new node(current);
                            left.prev = left_filler;
                            left.cur = mv_left(verPos, horPos, left.cur);
                            left.direction = "left";
                            if (!traversed.contains(left.cur))
                            {
                                left.g = current.g + 1;
                                left.h = Manhattan(left.cur);
                                if (left.g + left.h < 67)
                                {
                                    a_misplaced.get(left.g + left.h).add(left);
                                    traversed.add(left.cur);
                                }
                            }
                        }



                        else if (verPos.value == 2 && horPos.value == 0)
                        {
                            node up_filler = new node(current);
                            node up = new node(current);
                            up.prev = up_filler;
                            up.cur = mv_up(verPos, horPos, up.cur);
                            up.direction = "up";
                            if (!traversed.contains(up.cur))
                            {
                                up.g = current.g + 1;
                                up.h = Manhattan(up.cur);
                                if (up.g + up.h < 67)
                                {
                                    a_misplaced.get(up.g + up.h).add(up);
                                    traversed.add(up.cur);
                                }
                            }



                            node right_filler = new node(current);
                            node right = new node(current);
                            right.prev = right_filler;
                            right.cur = mv_right(verPos, horPos, right.cur);
                            right.direction = "right";
                            if (!traversed.contains(right.cur))
                            {
                                right.g = current.g + 1;
                                right.h = Manhattan(right.cur);
                                if (right.g + right.h < 67)
                                {
                                    a_misplaced.get(right.g + right.h).add(right);
                                    traversed.add(right.cur);
                                }
                            }
                        }



                        if (verPos.value == 2 && horPos.value == 1)
                        {
                            node up_filler = new node(current);
                            node up = new node(current);
                            up.prev = up_filler;
                            up.cur = mv_up(verPos, horPos, up.cur);
                            up.direction = "up";
                            if (!traversed.contains(up.cur))
                            {
                                up.g = current.g + 1;
                                up.h = Manhattan(up.cur);
                                if (up.g + up.h < 67)
                                {
                                    a_misplaced.get(up.g + up.h).add(up);
                                    traversed.add(up.cur);
                                }
                                //Toast.makeText(getApplicationContext(), Integer.toString(up.g + up.h), Toast.LENGTH_LONG).show();
                            }



                            node left_filler = new node(current);
                            node left = new node(current);
                            left.prev = left_filler;
                            left.cur = mv_left(verPos, horPos, left.cur);
                            left.direction = "left";
                            if (!traversed.contains(left.cur))
                            {
                                left.g = current.g + 1;
                                left.h = Manhattan(left.cur);
                                if (left.g + left.h < 67)
                                {
                                    a_misplaced.get(left.g + left.h).add(left);
                                    traversed.add(left.cur);
                                }
                                //Toast.makeText(getApplicationContext(), Integer.toString(left.g + left.h), Toast.LENGTH_LONG).show();
                            }



                            node right_filler = new node(current);
                            node right = new node(current);
                            right.prev = right_filler;
                            right.cur = mv_right(verPos, horPos, right.cur);
                            right.direction = "right";
                            if (!traversed.contains(right.cur))
                            {
                                right.g = current.g + 1;
                                right.h = Manhattan(right.cur);
                                if (right.g + right.h < 67)
                                {
                                    a_misplaced.get(right.g + right.h).add(right);
                                    traversed.add(right.cur);
                                }
                                //Toast.makeText(getApplicationContext(), Integer.toString(right.g + right.h), Toast.LENGTH_LONG).show();
                            }
                        }



                        else if (verPos.value == 2 && horPos.value == 2)
                        {
                            node left_filler = new node(current);
                            node left = new node(current);
                            left.prev = left_filler;
                            left.cur = mv_left(verPos, horPos, left.cur);
                            left.direction = "left";
                            if (!traversed.contains(left.cur))
                            {
                                left.g = current.g + 1;
                                left.h = Manhattan(left.cur);
                                if (left.g + left.h < 67)
                                {
                                    a_misplaced.get(left.g + left.h).add(left);
                                    traversed.add(left.cur);
                                }
                            }



                            node up_filler = new node(current);
                            node up = new node(current);
                            up.prev = up_filler;
                            up.cur = mv_up(verPos, horPos, up.cur);
                            up.direction = "up";
                            if (!traversed.contains(up.cur))
                            {
                                up.g = current.g + 1;
                                up.h = Manhattan(up.cur);
                                if (up.g + up.h < 67)
                                {
                                    a_misplaced.get(up.g + up.h).add(up);
                                    traversed.add(up.cur);
                                }
                            }
                        }
                    }


                    if (!method.isChecked()) {
                        //Stack<node> solution = new Stack<node>();
                        ArrayList<Integer> solution = new ArrayList<Integer>();
                        while (!equalStates(current.cur, given.cur)) {
                            solution.add(current.cur.get(0).get(0));
                            solution.add(current.cur.get(0).get(1));
                            solution.add(current.cur.get(0).get(2));
                            solution.add(current.cur.get(1).get(0));
                            solution.add(current.cur.get(1).get(1));
                            solution.add(current.cur.get(1).get(2));
                            solution.add(current.cur.get(2).get(0));
                            solution.add(current.cur.get(2).get(1));
                            solution.add(current.cur.get(2).get(2));
                            current = current.prev;
                        }
                        //solution.add(given);
                        solution.add(given.cur.get(0).get(0));
                        solution.add(given.cur.get(0).get(1));
                        solution.add(given.cur.get(0).get(2));
                        solution.add(given.cur.get(1).get(0));
                        solution.add(given.cur.get(1).get(1));
                        solution.add(given.cur.get(1).get(2));
                        solution.add(given.cur.get(2).get(0));
                        solution.add(given.cur.get(2).get(1));
                        solution.add(given.cur.get(2).get(2));

                        //s1.setText("Your text");

                        Intent Solve_puzzle = new Intent(MainActivity.this, solved_puzzle_movement.class);
                        Bundle B = new Bundle();
                        B.putIntegerArrayList("solution_stack", solution);
                        Solve_puzzle.putExtras(B);
                        startActivity(Solve_puzzle);
                        //Toast.makeText(getApplicationContext(), Integer.toString(solution.size()), Toast.LENGTH_LONG).show();
                    }

                    else if (method.isChecked())
                    {
                        ArrayList<String> solution = new ArrayList<String>();
                        ArrayList<String> reverse = new ArrayList<String>();
                        int num_steps = 0;
                        while (!equalStates(current.cur, given.cur))
                        {
                            num_steps += 1;
                            solution.add(current.direction);
                            current = current.prev;
                        }

                        while (!solution.isEmpty())
                        {
                            reverse.add("" + num_steps + " " + solution.remove(0));
                            num_steps -= 1;
                        }

                        //Toast.makeText(getApplicationContext(), solution.get(0), Toast.LENGTH_LONG).show();

                        Intent Solve_puzzle = new Intent(MainActivity.this, solved_puzzle_steps.class);
                        Bundle B = new Bundle();
                        B.putStringArrayList("solution_stack", reverse);
                        Solve_puzzle.putExtras(B);
                        startActivity(Solve_puzzle);


                    }

                }
            }
        });
    }
}
