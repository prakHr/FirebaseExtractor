package com.example.extractor2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class SearchActivity extends AppCompatActivity {
    Button buttonSearch;
    TextView tvSearch4;
    EditText searchFieldEntry,searchViaField;
    private static final String TAG = " Search Activity: \n";

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        buttonSearch=findViewById(R.id.buttonSearch);
        searchFieldEntry=findViewById(R.id.searchFieldEntry);
        searchViaField=findViewById(R.id.searchViaField);
        tvSearch4=findViewById(R.id.tvSearch4);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=searchFieldEntry.getText().toString().trim();
                String b=searchViaField.getText().toString().trim();
                if(a.isEmpty() || b.isEmpty()){
                    Toast.makeText(SearchActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                            .setTimestampsInSnapshotsEnabled(true)
                            .build();
                    db.setFirestoreSettings(settings);
                    Throwable e;
                    db.collection("users").whereEqualTo(a,b).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful())
                            {
                                String ans="";
                                for(QueryDocumentSnapshot document:task.getResult()){
                                    ans=ans+"\n\n\n\n"+"ID:"+document.getId().toString()+"\n"+
                                            "Dictionary Items:"+document.getData().toString()+"\n\n\n\n";

                                }
                                tvSearch4.setText(ans);
                            }
                        }
                    });
                }

            }
        });
    }
}
