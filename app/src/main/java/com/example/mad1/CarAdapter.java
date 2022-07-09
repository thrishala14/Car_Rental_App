package com.example.mad1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    CarData[] carData;
    Context context;


    public CarAdapter(CarData[] carData, Cars cars){
        this.carData = carData;
        this.context = cars;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.car_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CarData carData1 = carData[position];

        holder.CarName.setText(carData1.getName());
        holder.CarPrice.setText(carData1.getPrice());
        holder.ImageView.setImageResource(carData1.getImageview());
        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, summary.class);

                intent.putExtra("CarName",carData1.getName());
                intent.putExtra("CarPrice",carData1.getPrice());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,carData1.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return carData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ImageView;
        TextView CarName;
        TextView CarPrice;
        Button book;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageView = itemView.findViewById((R.id.imageview));
            CarName = itemView.findViewById(R.id.name);
            CarPrice = itemView.findViewById(R.id.price);
            book = itemView.findViewById(R.id.book);
        }




    }

}
