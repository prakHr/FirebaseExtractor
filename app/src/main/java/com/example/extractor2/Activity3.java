package com.example.extractor2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.example.extractor2.NewPersonAdapter.ItemClicked1;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

class randomString {

    // function to generate a random string of length n
    public static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}

    public class Activity3 extends AppCompatActivity implements ItemClicked1 {
        private static final String TAG = "Message";
        TextView tvNAME, tvTEL;
        EditText etName, etTel;
        Button btnAdd, btnDelete;
        ListFrag listFrag;
        FragmentManager fragmentManager;

        public boolean isOnline() {
            ConnectivityManager cm =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                return true;
            }
            return false;
        }
        public void showCustomToast(String message) {
            View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.linlay));
            TextView tvToast = (TextView) toastView.findViewById(R.id.tvToast);
            tvToast.setText(message);
            FragmentManager manager = this.getSupportFragmentManager();

            Toast toast = new Toast(Activity3.this);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(toastView);
            //toast.setMargin(-10,0);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();

        }

        protected void sendEmail(String s) {
            Log.i(TAG,"Send email" );
            String[] TO = {"gprakhar0@gmail.com","firstmatezorro360@gmail.com"};
            String[] CC = {""};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Called from Delete Function");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "User"+s+":"+"You have been unsubscribed");

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                finish();
                Log.i(TAG,"Finished sending email...");
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(Activity3.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_3);
            if (ApplicationClass.connectionAvailable(getApplicationContext())) {
                //do nothing in this application
                //RemoteViews views=new RemoteViews("com.example.extractor2",R.layout.new_app_widget);
                //views.setTextViewText(R.id.tvTitle,"Busy retrieving data ...");
                //views.setTextViewText(R.id.tvDescription,"Busy retrieving data ...");
            } else {
                showCustomToast("Please make sure your phone has an active internet connection");
                stopService(this.getIntent());
            }
            tvNAME = findViewById(R.id.tvNAME);
            tvTEL = findViewById(R.id.tvTEL);
            etName = findViewById(R.id.etName);
            etTel = findViewById(R.id.etTel);
            btnAdd = findViewById(R.id.btnAdd);
            btnDelete = findViewById(R.id.btnDelete);

            //the phone is in portrait mode
            if (findViewById(R.id.layout_portrait) != null) {
                FragmentManager manager = this.getSupportFragmentManager();
                manager.beginTransaction()
                        .hide(manager.findFragmentById(R.id.detailFrag))
                        .hide(manager.findFragmentById(R.id.addPersonFrag))
                        .show(manager.findFragmentById(R.id.listFrag))
                        .commit();

            }
            //the phone is in landscape mode
            if (findViewById(R.id.layout_land) != null) {
                FragmentManager manager = this.getSupportFragmentManager();
                manager.beginTransaction()
                        .show(manager.findFragmentById(R.id.detailFrag))
                        .show(manager.findFragmentById(R.id.addPersonFrag))
                        .show(manager.findFragmentById(R.id.listFrag))
                        .commit();
                //onItemClicked(0);
            }
            fragmentManager = this.getSupportFragmentManager();
            listFrag = (ListFrag) fragmentManager.findFragmentById(R.id.listFrag);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                            .build();
                    db.setFirestoreSettings(settings);
                    db.collection("users").document(tvNAME.getText().toString().trim()).
                            delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully deleted!");
                            showCustomToast("DocumentSnapshot successfully deleted!");
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error deleting document", e);
                                    showCustomToast("Error deleting document");
                                }
                            });
                    listFrag.notifyDataChanged();
                    sendEmail(tvNAME.getText().toString().trim());
                    //ListFrag.people.remove(new Person(tvNAME.getText().toString().trim(),tvTEL.getText().toString().trim(),"bus"));
                }
            });
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (etName.getText().toString().isEmpty() || etTel.getText().toString().isEmpty()) {
                        //Toast.makeText(this,"Please enter all fields",Toast.LENGTH_SHORT).show();
                        //Toast.makeText(Activity3.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
                        showCustomToast("Please enter all fields");
                    } else {
                        //ApplicationClass.people.add(new Person(etName.getText().toString().trim(),etTel.getText().toString().trim(),"bus"));
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                                .build();
                        db.setFirestoreSettings(settings);
                        Map<String, Object> city = new HashMap<>();
                        city.put("name", etName.getText().toString().trim());
                        city.put("phone", etTel.getText().toString().trim());

                        String r=randomString.getAlphaNumericString(20);
                        db.collection("users").document(r)
                                .set(city)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        showCustomToast("DocumentSnapshot successfully written!");
                                        Log.d(TAG, "DocumentSnapshot successfully written!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        showCustomToast("Error writing document");
                                        Log.w(TAG, "Error writing document", e);
                                    }
                                });
                        ListFrag.people.add(new Person(r, "name"+ etName.getText().toString().trim()+"\n"+"phone"+etTel.getText().toString().trim(),"{"+"name"+":"+etName.getText().toString().trim()+","+"phone"+":"+etTel.getText().toString().trim()+"}"));

                        Toast.makeText(Activity3.this, "Person successfully added", Toast.LENGTH_SHORT).show();
                        etName.setText(null);
                        etTel.setText(null);
                        listFrag.notifyDataChanged();
                    }

                }
            });
            //onItemClicked(0);
        }


        @Override
        public void onItemClicked(int index) {
            tvNAME.setText(ListFrag.people.get(index).getRandom_id());
            tvTEL.setText(ListFrag.people.get(index).getAns());
            if (findViewById(R.id.layout_portrait) != null) {
                FragmentManager manager = this.getSupportFragmentManager();
                manager.beginTransaction()
                        .show(manager.findFragmentById(R.id.detailFrag))
                        .show(manager.findFragmentById(R.id.addPersonFrag))
                        .hide(manager.findFragmentById(R.id.listFrag))
                        .addToBackStack(null)
                        .commit();

            }

        }
    }
