package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<String> tasks;
    ArrayAdapter<String> adapter;

    EditText itemText;
    Button addBtn;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.items_list);
        addBtn = findViewById(R.id.btnAdd);
        itemText = findViewById(R.id.item_edit_text);

        tasks = new ArrayList<>();

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_multiple_choice, tasks);


        View.OnClickListener addListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                tasks.add(itemText.getText().toString());
                itemText.setText("");
                adapter.notifyDataSetChanged();

            }


        };


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                SparseBooleanArray positionCheck= listView.getCheckedItemPositions();

                int counter= listView.getCount();

                for (int j = counter-1; j>=0;j--){
                    if (positionCheck.get(j)){
                        adapter.remove(tasks.get(j));

                        Toast.makeText(MainActivity.this,"Task deleted!",Toast.LENGTH_SHORT).show();
                    }
                }

                positionCheck.clear();
                adapter.notifyDataSetChanged();


                return false;
            }
        });

        addBtn.setOnClickListener(addListener);
        listView.setAdapter(adapter);
    }
}