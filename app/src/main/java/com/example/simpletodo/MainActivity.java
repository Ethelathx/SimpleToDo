package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    Button btnAdd, btnClear, btnDelete;
    ListView lvList;
    ArrayList<String> aActivities;
    ArrayAdapter<String> abActivities;
    Spinner spnTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnTask = findViewById(R.id.spinner);
        etName = findViewById(R.id.editTextActi);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        lvList = findViewById(R.id.lvActi);

        aActivities = new ArrayList<String>();
        abActivities = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, aActivities);
        lvList.setAdapter(abActivities);


       //Spinner
        spnTask.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        etName.setHint("Type in a new task here");
                        break;

                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etName.setHint("Type in the index of the task to be removed");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //Button Add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                aActivities.add(name);
                etName.setText("");
                abActivities.notifyDataSetChanged();

            }
        });


        //Delete Button
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(aActivities.size() == 0){
                    Toast.makeText(getApplicationContext(), "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    int index = Integer.parseInt(etName.getText().toString());
                    if(aActivities.size() <= index){
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else{
                        aActivities.remove(index+1);
                        abActivities.notifyDataSetChanged();
                        etName.setText(null);
                    }
                }

            }
        });



        //Clear Button
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvList.setAdapter(null);
                abActivities.notifyDataSetChanged();
            }
        });
    }
}
