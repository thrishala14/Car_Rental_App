package com.example.mad1;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;

public class Booking extends AppCompatActivity {
    EditText dateto, datefrom;
    Button find_cars;
    FirebaseDatabase firebaseDatabase;
    Spinner location;
    private DatePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        location = findViewById(R.id.location);
        firebaseDatabase= FirebaseDatabase.getInstance();
        dateto = findViewById(R.id.dateto);
        datefrom = findViewById(R.id.datefrom);
        find_cars = findViewById(R.id.find_cars);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.cities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        find_cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location1 = location.getSelectedItem().toString();
                SharedPreferences sp = getSharedPreferences("abc", MODE_PRIVATE);
                SharedPreferences.Editor editor= sp.edit();
                editor.putString("dateto",dateto.getText().toString());
                editor.putString("datefrom",datefrom.getText().toString());
                editor.putString("location",location1);
                editor.apply();
                Intent intent = new Intent(Booking.this,Cars.class);
                startActivity(intent);
            }
        });

        dateto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(Booking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int Year, int monthOfYear, int dayOfMonth) {
                        dateto.setText(dayOfMonth + "/" + (monthOfYear + 1)+ "/" + (Year));
                    }
                },year,month,day);
                picker.show();
            }
        });

        datefrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(Booking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int Year, int monthOfYear, int dayOfMonth) {
                        datefrom.setText(dayOfMonth + "/" + (monthOfYear + 1)+ "/" + (Year));
                    }
                },year,month,day);
                picker.show();
            }
        });

    }
}