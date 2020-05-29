package com.example.extractor2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity4 extends AppCompatActivity {
    TextView tvMultiplication;
    EditText etMNum, etMNum2;
    Button multiplicationBtn;



    public void showTable(View v){
        tvMultiplication=findViewById(R.id.tvMultiplication);
        etMNum=findViewById(R.id.etMNum);
        etMNum2=findViewById(R.id.etMNum2);
       //multiplicationBtn=findViewById(R.id.multiplicationBtn);
        String a=etMNum.getText().toString().trim();
        String b=etMNum2.getText().toString().trim();
        if(a.isEmpty() || b.isEmpty()){
            Toast.makeText(Activity4.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
        }
        else{

            Integer n=Integer.parseInt(a);
            Integer m=Integer.parseInt(b);
            String ans="";
            for(int i=0;i<=m;i++){
                Integer res=n*i;
                 ans=ans + n.toString() + "*" + i+"=";
                ans=ans+res.toString()+"\n";
            }
            tvMultiplication.setText(ans);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);




    }
}
