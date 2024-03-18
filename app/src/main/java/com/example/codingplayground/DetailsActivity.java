package com.example.codingplayground;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.media.MediaSync;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.codingplayground.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    private NumberViewModel model;
    private Integer num = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent receivedIntent = getIntent();

        model = new ViewModelProvider(this).get(NumberViewModel.class);

        model.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.mainResult.setText("Main Number is: " + integer.toString());
            }
        });

        if (receivedIntent != null) {
            Bundle bundle = receivedIntent.getExtras();
            if (bundle != null){
                String numberAdapter = bundle.getString("number");
                num = bundle.getInt("main_activity_val");
                model.setNumberInView(num);
                binding.tvDetail.setText("" + numberAdapter);
            }
        }


    }

}