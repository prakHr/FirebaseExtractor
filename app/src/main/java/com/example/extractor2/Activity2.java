package com.example.extractor2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import static android.app.PendingIntent.getActivity;

public class Activity2 extends AppCompatActivity implements PersonAdapter.ItemClicked {
    private static final String TAG="Message";
    RecyclerView recyclerView;
    //RecyclerView.Adapter myAdapter,myAdapter1;
    PersonAdapter myAdapter1;

    RecyclerView.LayoutManager layoutManager,layoutManager1_horizontal,layoutManager2_reverse_horizontal,layoutManager3_grid,getLayoutManager3_grid_vertical;

    //ArrayList<Person> people,people1;
    ArrayList<Person1> people_360;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_2);
        setContentView(R.layout.activity_2);
        Log.d(TAG,"Here is fine");

        recyclerView=findViewById(R.id.list);
        Log.d(TAG,"Reached here");
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        //layoutManager1_horizontal= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        //layoutManager2_reverse_horizontal= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
        //layoutManager3_grid=new GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL,false);
        //layoutManager3_grid_vertical=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        //people = new ArrayList<Person>();

        //people.add(new Person("John","Rambo","bus"));
        //people.add(new Person("Chuck","Norris","bus"));
        //people.add(new Person("Peter","Pan","bus"));
        //people.add(new Person("Tom","Wellings","bus"));
        //people.add(new Person("John","Rambo","bus"));
        //people.add(new Person("Chuck","Norris","bus"));
        //people.add(new Person("Peter","Pan","bus"));
        //people.add(new Person("Tom","Wellings","bus"));
        //people.add(new Person("John","Rambo","bus"));
        //people.add(new Person("Chuck","Norris","bus"));
        //people.add(new Person("Peter","Pan","bus"));
        //people.add(new Person("Tom","Wellings","bus"));
        //Context context=getBaseContext();
        //myAdapter = new PersonAdapter(this,people);
        //recyclerView.setAdapter(myAdapter);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .build();
        db.setFirestoreSettings(settings);
        Log.d(TAG,  "here");



        Log.d(TAG,  "function called upto here from main activity2");


        //final String[] stringArrayList = {"{"+"key"+":"+"value"};
        //final ArrayList<ArrayList<String>>stringArrayList1=new ArrayList<ArrayList<String>>();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {



                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int idx1=0;
                            //people1 = new ArrayList<Person>();
                            people_360 = new ArrayList<Person1>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                               // ArrayList<String> newlyCreated=new ArrayList<String>();

                                String id=document.getId();
                                Map<String,Object> data=document.getData();
                                Set<String> keysFromData=data.keySet();
                                String ans="";
                                for(String k:keysFromData){
                                    if(k.equals("name"))
                                        ans=ans+"name" +" : "+data.get(k).toString()+"\n";
                                    else if(k.equals("phone"))
                                        ans=ans+"phone" +" : "+data.get(k).toString()+"\n";

                                }
                                String dataToString=data.toString();
                                //people1.add(new Person(id,dataToString,"bus"));
                                people_360.add(new Person1(id,ans));
                                //Log.d(TAG,  id + " => " + data);
                                //stringArrayList[0] = stringArrayList[0] +","+id+":"+dataToString;
                                //Log.d(TAG,"Adding data to newly created");
                                //newlyCreated.add(id);
                                //newlyCreated.add(dataToString);
                                //Log.d(TAG,newlyCreated.toString());
                                //Log.d(TAG,"Data added");
                                //stringArrayList1.add(newlyCreated);
                                //Log.d(TAG,"StringArrayList1=>"+stringArrayList1.toString());


                                idx1=idx1+1;
                            }


                            //for(int i=0;i<stringArrayList1.size();i++){

                                //Log.d(TAG,"At index i:"+i);
                                //Log.d(TAG,"VALUES ARE");
                                //ArrayList<String> s=stringArrayList1.get(i);
                                //for(int j=0;j<s.size();j++){
                                   // Log.d(TAG,s.get(j));

                                //}
                            //}


                        } else {
                            int idx2=0;
                            //people1=new ArrayList<Person>();
                            people_360=new ArrayList<Person1>();
                            //people1.add(new Person("Error getting documents","Please try again later","bus"));
                            people_360.add(new Person1("Error getting documents","Please try again later"));

                            //myAdapter1 = new RecyclerAdapter(Activity2.this,people_360);
                            //recyclerView.setAdapter(myAdapter1);

                            //ArrayList<String> newlyCreated=new ArrayList<String>();
                            //Log.w(TAG, "Error getting documents.", task.getException());
                            //stringArrayList[0] = stringArrayList[0] +"Error getting json data";
                            //newlyCreated.add("Error getting json data");
                            //stringArrayList1.add(newlyCreated);
                        }
                        myAdapter1 = new PersonAdapter(Activity2.this,people_360);
                        recyclerView.setAdapter(myAdapter1);

                        //stringArrayList[0] = stringArrayList[0] +"}";

                        //Log.d(TAG,"returning string");
                        //Log.d(TAG, stringArrayList[0]);

                        //for(int i=0;i<stringArrayList1.size();i++){
                            //Log.d(TAG,"At index i:"+i);
                            //Log.d(TAG,"VALUES ARE");
                            //ArrayList<String> s=stringArrayList1.get(i);
                            //for(int j=0;j<s.size();j++){
                                //Log.d(TAG,s.get(j));

                            //}
                        //}
                    }

                });


        //Log.d(TAG, "after listener happened: string is => \n "+stringArrayList[0]);

    };

    @Override
    public void onItemClicked(int index) {
        //Toast.makeText(this,"Surname:"+people.get(index).getSurname(),Toast.LENGTH_SHORT).show();
        //Toast.makeText(this,"Surname:"+people1.get(index).getSurname(),Toast.LENGTH_SHORT).show();
        Toast.makeText(this,people_360.get(index).getName(),Toast.LENGTH_SHORT).show();

    };


}
