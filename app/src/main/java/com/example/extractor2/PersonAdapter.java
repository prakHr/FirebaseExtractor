package com.example.extractor2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>  {


    private static final String TAG = "RecyclerAdapter";
    public static ArrayList<Person1> people;


    public ItemClicked activity;

    public interface ItemClicked {
        void onItemClicked(int index);


    }

    public PersonAdapter(Context context, ArrayList<Person1> list) {

        people = list;

        activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPref;
        TextView tvName, tvSurname;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvSurname = itemView.findViewById(R.id.tvSurname);
            ivPref = itemView.findViewById(R.id.ivPref);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(people.indexOf((Person1) view.getTag()));

                }
            });
        }
    }

    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder viewHolder, int position) {
        viewHolder.itemView.setTag(people.get(position));
        viewHolder.tvName.setText(people.get(position).getId());
        viewHolder.tvSurname.setText(people.get(position).getName());
        //viewHolder.tvSurname.setText(people.get(position).getSurname());
//        if(people.get(position).getPreference().equals("bus")){
//            viewHolder.ivPref.setImageResource(R.drawable.bus);
//        }
//        else{viewHolder.ivPref.setImageResource(R.drawable.plane);}
    }

    @Override
    public int getItemCount() {
        //if(people==null || people.size()==0)return 0;
        return people.size();
    }


}


