package com.example.codingplayground;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.codingplayground.databinding.ActivityMainBinding;


import java.util.ArrayList;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private NumberViewModel model;

    private ArrayList<Integer> arrayList;
    private CustomAdapter arrayAdapter;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        model = new ViewModelProvider(this).get(NumberViewModel.class);

        arrayList = new ArrayList<Integer>();
        arrayAdapter = new CustomAdapter(this,
                android.R.layout.simple_list_item_1,
                arrayList);
        binding.listView.setAdapter(arrayAdapter);


        View rootView = findViewById(android.R.id.content);

        model.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                setNumber(integer, binding.numView, rootView);
                arrayList.add(integer);
                arrayAdapter.notifyDataSetChanged();
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.increaseNumber();
            }
        });

        binding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                model.decreaseNumber();
            }
        });

        binding.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                arrayAdapter.notifyDataSetChanged();

                return false;
            }
        });


        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);

                Integer number = model.getNumber().getValue();

                Bundle bundle = new Bundle();
                bundle.putString("number", arrayList.get(position).toString());
                bundle.putInt("main_activity_val", number);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void setNumber(Integer number, TextView numView, View view) {
        if (number != null) {
            String numberText = String.valueOf(number);

            numView.setText(numberText);
            Snackbar.make(view, "Number set to: " + numberText, Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(view, "Number is not available", Snackbar.LENGTH_SHORT).show();
        }
    }

}