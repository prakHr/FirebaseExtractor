package com.example.extractor2;

import android.media.CamcorderProfile;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFrag extends Fragment {
    private static final String TAG="List Fragment Message:";

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    View view;
    public static ArrayList<Person> people;


    public ListFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView=view.findViewById(R.id.LIST);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .build();
        db.setFirestoreSettings(settings);
        Log.d(TAG,  "reached till here in list fragment");
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int idx1 = 0;
                            people = new ArrayList<Person>();
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String id = document.getId();
                                Map<String, Object> data = document.getData();
                                Set<String> keysFromData=data.keySet();
                                String ans="";
                                for(String k:keysFromData){
                                    if(k.equals("name"))
                                        ans=ans+"name" +" : "+data.get(k).toString()+"\n";
                                    else if(k.equals("phone"))
                                        ans=ans+"phone" +" : "+data.get(k).toString()+"\n";

                                }

                                String dataToString = data.toString();
                                people.add(new Person(id,ans, dataToString));

                                idx1 = idx1 + 1;
                            }
                            myAdapter = new NewPersonAdapter(ListFrag.this.getActivity(), people);
                            recyclerView.setAdapter(myAdapter);


                        } else {
                            int idx2 = 0;
                            people = new ArrayList<Person>();
                            people.add(new Person("404","Error getting documents", "{Please try again later}"));
                            myAdapter = new NewPersonAdapter(ListFrag.this.getActivity(), people);
                            recyclerView.setAdapter(myAdapter);


                        }
                    }

                });




        //myAdapter = new NewPersonAdapter(this.getActivity(),ApplicationClass.people);
        //recyclerView.setAdapter(myAdapter);
        //if(this.getActivity().findViewById(R.id.layout_portrait)!=null){
            //activity.onItemSelected(0);

        //}
    }
    public void notifyDataChanged(){
        myAdapter.notifyDataSetChanged();
    }
}
