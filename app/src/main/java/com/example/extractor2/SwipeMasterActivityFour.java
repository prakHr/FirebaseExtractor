package com.example.extractor2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.Objects;

public class SwipeMasterActivityFour extends SwipeActivityClass {

    private static final String TAG="Swipemaster activity4:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_master_four);
      }
    public void showLikes(View v){
        Intent intent;
        intent = new Intent(SwipeMasterActivityFour.this, SwipeMasterLikeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("flag",true);
        startActivity(intent);
        finish();


    }
    public void showDislikes(View v){
        Intent intent;
        intent = new Intent(SwipeMasterActivityFour.this, SwipeMasterLikeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("flag",false);
        startActivity(intent);
        finish();


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
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().build();
        db.setFirestoreSettings(settings);
        DocumentReference markZuckerbergRef=db.collection("likes").document("markZuckerberg");

        markZuckerbergRef.update("like", FieldValue.increment(1)) ;

        showCustomToast("Everyone wants to be friends @ online social media great! ");
        Intent intent;
        intent = new Intent(SwipeMasterActivityFour.this,com.example.extractor2.SwipeMasterActivityThree.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    protected void onSwipeLeft() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().build();
        db.setFirestoreSettings(settings);
        DocumentReference markZuckerbergRef=db.collection("likes").document("markZuckerberg");

        markZuckerbergRef.update("dislike", FieldValue.increment(1)) ;

        showCustomToast("You makes us sit all the time at fb!");
        Intent intent;
        intent = new Intent(SwipeMasterActivityFour.this,com.example.extractor2.SwipeMasterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
