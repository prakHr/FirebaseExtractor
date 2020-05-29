package com.example.extractor2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class SwipeMasterActivity<zzu> extends SwipeActivityClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_master);
    }
    public void showCustomToast(String message) {
        View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.linlay));
        TextView tvToast = (TextView) toastView.findViewById(R.id.tvToast);
        tvToast.setText(message);
        FragmentManager manager = this.getSupportFragmentManager();

        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        //toast.setMargin(-10,0);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();

    }
    @Override
    protected void onSwipeRight() {
        //on Swiping right we liked the guy
        //add incrementor to db
        //show custom Toast
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().build();
        db.setFirestoreSettings(settings);

        //db.collection("likes").document("adolfHitler").get();
        DocumentReference adolfHitlerRef=db.collection("likes").document("adolfHitler");
        adolfHitlerRef.update("like",FieldValue.increment(1)) ;

        showCustomToast("Hail Hitler! ");
        Intent intent;
        intent = new Intent(SwipeMasterActivity.this, SwipeMasterActivityFour.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @Override
    protected void onSwipeLeft() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().build();
        db.setFirestoreSettings(settings);
        DocumentReference adolfHitlerRef=db.collection("likes").document("adolfHitler");

        adolfHitlerRef.update("dislike",FieldValue.increment(-1)) ;

        //on Swiping right we disliked the guy
        showCustomToast("Hope Hitler got hit by red angry bird! ");
        Intent intent;
        intent = new Intent(SwipeMasterActivity.this,com.example.extractor2.SwipeMasterActivityOne.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
