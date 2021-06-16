package com.example.help;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class numbers extends AppCompatActivity {

    String tag,name1,name2,name3,name4,num1,num2,num3,num4,callingnumber,jsonobj;

    Button firstnum,secondnum,thirdnum,fourthnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.numbers);

        //retrieving value from intent
        Intent i = getIntent();
        jsonobj = i.getStringExtra("title");
        Log.e(tag, jsonobj);


        //hooks

        firstnum = findViewById(R.id.firstnum);
        secondnum = findViewById(R.id.secondnum);
        thirdnum = findViewById(R.id.thirdnum);
        fourthnum = findViewById(R.id.fourthnum);

        jsonextract();

        //assigning values
        firstnum.setText(name1);
        secondnum.setText(name2);
        thirdnum.setText(name3);
        fourthnum.setText(name4);


        firstnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callingnumber = num1;
                callAtRuntime();

            }
        });

        secondnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callingnumber = num2;
                callAtRuntime();

            }
        });

        thirdnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callingnumber = num3;
                callAtRuntime();

            }
        });

        fourthnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callingnumber = num4;
                callAtRuntime();

            }
        });
    }

    //iterating through the json file
    public void jsonextract(){

        try
        {
            String jsonLocation = loadJSONFromAsset();
            JSONObject jsonobject = new JSONObject(jsonLocation);
            JSONArray jarray = (JSONArray) jsonobject.getJSONArray(jsonobj);
            for(int i=0;i<jarray.length();i++)
            {
                JSONObject jb =(JSONObject) jarray.get(i);
                name1 = jb.getString("name1");
                name2 = jb.getString("name2");
                name3 = jb.getString("name3");
                name4 = jb.getString("name4");
                num1 = jb.getString("num1");
                num2 = jb.getString("num2");
                num3 = jb.getString("num3");
                num4 = jb.getString("num4");


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }




    }


    //loading json file
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("sample.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }



    private void callAtRuntime() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
        else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + callingnumber));
            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


            PackageManager packageManager = getApplicationContext().getPackageManager();
            List activities = packageManager.queryIntentActivities(callIntent, PackageManager.MATCH_DEFAULT_ONLY);

            for(int j = 0 ; j < activities.size() ; j++)
            {

                if(activities.get(j).toString().toLowerCase().contains("com.android.phone"))
                {
                    callIntent.setPackage("com.android.phone");
                }
                else if(activities.get(j).toString().toLowerCase().contains("call"))
                {
                    String pack = (activities.get(j).toString().split("[ ]")[1].split("[/]")[0]);
                    callIntent.setPackage(pack);
                }
            }

            getApplicationContext().startActivity(callIntent);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callAtRuntime();
            } else {
                Toast.makeText(this, "Permission DeniedTry Again", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

