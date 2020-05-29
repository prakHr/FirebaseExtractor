package com.example.extractor2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.example.extractor2.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="Message";

    CardView cv;
    FloatingActionButton fab;
    public void goToOtherActivity(View v){
       Intent intent;
        intent = new Intent(MainActivity.this,com.example.extractor2.Activity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);


    }
    public void goToOtherActivity1(View v){
        Intent intent;
        intent = new Intent(MainActivity.this,com.example.extractor2.Activity3.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);


    }
    public void goToOtherActivity2(View v){
        Intent intent;
        intent = new Intent(MainActivity.this,com.example.extractor2.Activity4.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);


    }
    public void goToOtherActivity3(View v){
        Intent intent;
        intent = new Intent(MainActivity.this,com.example.extractor2.SearchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.extractor2.R.layout.activity_main);
        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent intent;
                intent = new Intent(MainActivity.this,SwipeMasterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                .setTimestampsInSnapshotsEnabled(true)
//                .build();
//        db.setFirestoreSettings(settings);
        // Create a new user with a first and last name
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Ada");
//        user.put("last", "Lovelace");
//        user.put("born", 1815);
//
//        // Add a new document with a generated ID
//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });



//        Log.d(TAG,  "here");
//
//        getAllDocumentsFromCollection(db);
//        Log.d(TAG,  "function called upto here");
//        Log.d(TAG,"printing return value to string");



//        for(int counter=0;counter<returnValue.size();counter++){
//            String s=returnValue.get(counter);
//            Log.d(TAG, s);
//            JSONObject obj = null;
//
//            int idx=s.indexOf(':');
//            if(idx!=-1) {
//                try {
//                    obj = new JSONObject(s);
//                    int born = obj.getJSONObject(s.substring(1, idx)).getInt("born");
//                    Log.d(TAG, String.valueOf(born));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            Log.d(TAG,  "loop ended here");
//        }
    }

//    public void getAllDocumentsFromCollection(FirebaseFirestore db) {
//        final String[] stringArrayList = {"{"};
//        db.collection("users")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//
//
//
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            int idx1=0;
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                String id=document.getId();
//                                Map<String,Object> data=document.getData();
//                                String dataToString=data.toString();
//                                //Log.d(TAG,  id + " => " + data);
//                                stringArrayList[0] = stringArrayList[0] +id+":"+dataToString+",";
//                                idx1=idx1+1;
//                            }
//
//
//                        } else {
//                            int idx2=0;
//                            //Log.w(TAG, "Error getting documents.", task.getException());
//                            stringArrayList[0] = stringArrayList[0] +"Error getting json data";
//                        }
//                        stringArrayList[0] = stringArrayList[0] +"}";
//
//                        Log.d(TAG,"returning string");
//                        Log.d(TAG, stringArrayList[0]);
//
//                    }
//
//                });
//
//
//    }
}
