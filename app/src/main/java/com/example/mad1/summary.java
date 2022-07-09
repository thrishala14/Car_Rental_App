package com.example.mad1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class summary extends AppCompatActivity {
    TextView carname,costperday, fromdate, todate,location;
    Button checkout;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    javadb javadb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        carname= findViewById(R.id.carname);
        costperday= findViewById(R.id.costperday);
        todate= findViewById(R.id.todate);
        fromdate= findViewById(R.id.fromdate);
        location = findViewById(R.id.pickup);
        javadb = new javadb();
        databaseReference = firebaseDatabase.getReference().child("javadb");
        SharedPreferences sp = getSharedPreferences("abc", MODE_PRIVATE);
        todate.setText(sp.getString("dateto"," "));
        fromdate.setText(sp.getString("datefrom"," "));
        location.setText(sp.getString("location"," "));
        carname.setText(getIntent().getStringExtra("CarName"));
        costperday.setText(getIntent().getStringExtra("CarPrice"));
        checkout = findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String carname1 = carname.getText().toString();
                String costperday1 = costperday.getText().toString();
                String dateto1 = todate.getText().toString();
                String datefrom1 = fromdate.getText().toString();
                String location1 = location.getText().toString();
                Toast.makeText(summary.this, "Confirmation Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(summary.this, home.class));
                addDatatoFirebase(carname1, costperday1, dateto1, datefrom1, location1);
            }
        });
    }

    private void addDatatoFirebase(String carname1, String costperday1, String dateto1, String datefrom1, String location1) {
        javadb.setCarName(carname1);
        javadb.setCarPrice(costperday1);
        javadb.setDateTo(dateto1);
        javadb.setDateFrom(datefrom1);
        javadb.setLocation(location1);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(javadb);
                Toast.makeText(summary.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(summary.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}