package com.example.mad1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class allbooks extends AppCompatActivity {
    TextView td, fd, loc, cost, car;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allbooks);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("javadb");
        td = findViewById(R.id.td);
        fd = findViewById(R.id.fd);
        loc = findViewById(R.id.loc);
        cost = findViewById(R.id.cost);
        car = findViewById(R.id.car);
        getData();
    }
    private void getData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                javadb javadb = snapshot.getValue(javadb.class);
                td.setText(javadb.getDateTo());
                fd.setText(javadb.getDateFrom());
                loc.setText(javadb.getLocation());
                cost.setText(javadb.getCarPrice());
                car.setText(javadb.getCarName());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(allbooks.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}