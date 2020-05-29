package com.example.extractor2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NewPersonAdapter extends RecyclerView.Adapter<NewPersonAdapter.ViewHolder> {

    //private ArrayList<Person> people;
    public static ArrayList<Person> people;

    ItemClicked1 activity;



    public interface ItemClicked1{
        void onItemClicked(int index);
    }
    public NewPersonAdapter(Context context, ArrayList<Person> list){
        people=list;

        activity=(ItemClicked1)context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNAME2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNAME2=itemView.findViewById(R.id.tvNAME2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(people.indexOf((Person) view.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public NewPersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewPersonAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(people.get(position));
        holder.tvNAME2.setText(people.get(position).getAns());

    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
