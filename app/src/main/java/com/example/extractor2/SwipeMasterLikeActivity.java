package com.example.extractor2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;
import java.util.Set;

public class SwipeMasterLikeActivity extends AppCompatActivity {
    TextView adHit,markRuf,markTwa,markZu,susanCh;
    TextView tvLikeOrDislike;
    private static final String TAG="Swipemaster Like activity:";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_master_like);


        adHit=(TextView) findViewById( R.id.adHit);
        markRuf=(TextView)findViewById(R.id.markRuf);
        markTwa=(TextView)findViewById(R.id.markTwa);
        markZu=(TextView)findViewById(R.id.markZu);
        susanCh=(TextView)findViewById(R.id.susanCh);

        tvLikeOrDislike=(TextView)findViewById(R.id.tvLikeOrDislike);
        Intent iin=getIntent();
        Bundle b=iin.getExtras();
        if(b!=null){
            Boolean flagSet= (Boolean) b.get("flag");
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().build();
            db.setFirestoreSettings(settings);
            CollectionReference collectionReference=db.collection("likes");

            if(flagSet==true){

                collectionReference.get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @SuppressLint("LongLogTag")
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        //Log.d(TAG, document.getId() + " => " + document.getData());

                                        String id = document.getId();


                                        if(id.equals("adolfHitler")){
                                            Map<String, Object> data = document.getData();
                                            Set<String> keysFromData=data.keySet();
                                            String ans="";
                                            for(String k:keysFromData){
                                                if(k.equals("like"))
                                                    ans=ans+" liked by" +" : "+data.get(k).toString();
                                            }
                                            Log.d(TAG,ans);
                                            adHit.setText(adHit.getText().toString()+ans);
                                        }
                                        else if(id.equals("markRuffalo")){
                                            Map<String, Object> data = document.getData();
                                            Set<String> keysFromData=data.keySet();
                                            String ans="";
                                            for(String k:keysFromData){
                                                if(k.equals("like"))
                                                    ans=ans+" liked by" +" : "+data.get(k).toString();
                                            }
                                            markRuf.setText(markRuf.getText().toString()+ans);
                                        }
                                        else if(id.equals("markTwain")){
                                            Map<String, Object> data = document.getData();
                                            Set<String> keysFromData=data.keySet();
                                            String ans="";
                                            for(String k:keysFromData){
                                                if(k.equals("like"))
                                                    ans=ans+" liked by" +" : "+data.get(k).toString();
                                            }
                                            markTwa.setText(markTwa.getText().toString()+ans);
                                        }
                                        else if(id.equals("markZuckerberg")){
                                            Map<String, Object> data = document.getData();
                                            Set<String> keysFromData=data.keySet();
                                            String ans="";
                                            for(String k:keysFromData){
                                                if(k.equals("like"))
                                                    ans=ans+" liked by" +" : "+data.get(k).toString();
                                            }
                                            markZu.setText(markZu.getText().toString()+ans);
                                        }
                                        else if(id.equals("susanChoi")){
                                            Map<String, Object> data = document.getData();
                                            Set<String> keysFromData=data.keySet();
                                            String ans="";
                                            for(String k:keysFromData){
                                                if(k.equals("like"))
                                                    ans=ans+" liked by" +" : "+data.get(k).toString();
                                            }
                                            susanCh.setText(susanCh.getText().toString()+ans);

                                        }
                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
            }
            else {
                tvLikeOrDislike.setText("Total Dislikes :-");
                collectionReference.get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @SuppressLint("LongLogTag")
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        //Log.d(TAG, document.getId() + " => " + document.getData());

                                        String id = document.getId();


                                        if(id.equals("adolfHitler")){
                                            Map<String, Object> data = document.getData();
                                            Set<String> keysFromData=data.keySet();
                                            String ans="";
                                            for(String k:keysFromData){
                                                if(k.equals("dislike"))
                                                    ans=ans+" :"+"dislike" +" : "+data.get(k).toString();
                                            }
                                            Log.d(TAG,ans);
                                            adHit.setText(adHit.getText().toString()+ans);
                                        }
                                        else if(id.equals("markRuffalo")){
                                            Map<String, Object> data = document.getData();
                                            Set<String> keysFromData=data.keySet();
                                            String ans="";
                                            for(String k:keysFromData){
                                                if(k.equals("dislike"))
                                                    ans=ans+" :"+"dislike" +" : "+data.get(k).toString();
                                            }
                                            markRuf.setText(markRuf.getText().toString()+ans);
                                        }
                                        else if(id.equals("markTwain")){
                                            Map<String, Object> data = document.getData();
                                            Set<String> keysFromData=data.keySet();
                                            String ans="";
                                            for(String k:keysFromData){
                                                if(k.equals("dislike"))
                                                    ans=ans+" :"+"dislike" +" : "+data.get(k).toString();
                                            }
                                            markTwa.setText(markTwa.getText().toString()+ans);
                                        }
                                        else if(id.equals("markZuckerberg")){
                                            Map<String, Object> data = document.getData();
                                            Set<String> keysFromData=data.keySet();
                                            String ans="";
                                            for(String k:keysFromData){
                                                if(k.equals("dislike"))
                                                    ans=ans+" :"+"dislike" +" : "+data.get(k).toString();
                                            }
                                            markZu.setText(markZu.getText().toString()+ans);
                                        }
                                        else if(id.equals("susanChoi")){
                                            Map<String, Object> data = document.getData();
                                            Set<String> keysFromData=data.keySet();
                                            String ans="";
                                            for(String k:keysFromData){
                                                if(k.equals("dislike"))
                                                    ans=ans+" :"+"dislike" +" : "+data.get(k).toString();
                                            }
                                            susanCh.setText(susanCh.getText().toString()+ans);

                                        }
                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
            }
        }





    }
//    public void backToGame(View v){
//        Intent intent;
//        intent = new Intent(SwipeMasterLikeActivity.this,SwipeMasterActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//        finish();
//
//    }
}