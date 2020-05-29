package com.example.extractor2;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;

public class ApplicationClass extends Application {
    //a good way to persist data runs even before main activity and then everything ends gets destroyed

    public static ArrayList<Person> people;

    @Override
    public void onCreate() {
        super.onCreate();
        people = new ArrayList<Person>();
//        people.add(new Person("John","09412345","bus"));
//        people.add(new Person("Chuck","09412345231","bus"));
//        people.add(new Person("Peter","09412341315","bus"));
//        people.add(new Person("Tom","0941221312345","bus"));
//        people.add(new Person("John","0941231345","bus"));
//        people.add(new Person("Chuck","094165762345","bus"));
//        people.add(new Person("Peter","0941231345","bus"));
//        people.add(new Person("Tom","0941278979345","bus"));
//        people.add(new Person("John","0941234425","bus"));
//        people.add(new Person("Chuck","0941234645","bus"));
//        people.add(new Person("Peter","09412323445","bus"));
//        people.add(new Person("Tom","09412386645","bus"));


    }
    public static boolean connectionAvailable(Context context){
        int [] networkTypes={ConnectivityManager.TYPE_MOBILE,ConnectivityManager.TYPE_WIFI};
        try{
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            for(int networkType:networkTypes){
                NetworkInfo activeNetworkInfo=connectivityManager.getActiveNetworkInfo();
                if(activeNetworkInfo!=null && activeNetworkInfo.getType()==networkType)
                    return true;
            }

        }
        catch (Exception e){
            return false;
        }
        return false;

    }



}
