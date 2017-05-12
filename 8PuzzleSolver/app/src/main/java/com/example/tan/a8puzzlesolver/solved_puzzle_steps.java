package com.example.tan.a8puzzlesolver;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class solved_puzzle_steps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solved_puzzle_steps);



        Intent test = getIntent();
        Bundle bund = test.getExtras();
        final ArrayList<String> solution_array = bund.getStringArrayList("solution_stack");
        Collections.reverse(solution_array);



        //Toast.makeText(getApplicationContext(), solution_array.size(), Toast.LENGTH_LONG).show();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, solution_array);

        ListView steps = (ListView) findViewById(R.id.sbs);
        steps.setAdapter(adapter);

    }
}
