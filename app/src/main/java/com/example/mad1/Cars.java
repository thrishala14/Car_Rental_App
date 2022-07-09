package com.example.mad1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

public class Cars extends AppCompatActivity {
    Button book;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CarData[] carData = new CarData[]{
                new CarData("Rs 2000/day", "Alto", R.drawable.alto),
                new CarData("Rs 3000/day", "Baleno", R.drawable.baleno),
                new CarData("Rs 2500/day", "Honda", R.drawable.honda),
                new CarData("Rs 2200/day", "i10", R.drawable.i10),
                new CarData("Rs 3000/day", "i20", R.drawable.i20),
                new CarData("Rs 1800/day", "Wagon R", R.drawable.wagonr),
        };
        CarAdapter carAdapter = new CarAdapter(carData, Cars.this);
        recyclerView.setAdapter(carAdapter);
    }
    public void sum(View view) {
        book.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(Cars.this, summary.class);
                startActivity(i);

            }
        });
    }


}