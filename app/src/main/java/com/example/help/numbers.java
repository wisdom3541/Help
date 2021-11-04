package com.example.help;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class numbers extends AppCompatActivity implements View.OnClickListener {

    String name1, name2, name3, name4, num1, num2, num3, num4, callingnumber, jsonobj;

    Button firstnum, secondnum, thirdnum, fourthnum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.numbers);

        //retrieving value from intent
        Intent i = getIntent();
        jsonobj = i.getStringExtra("title");

        //hooks
        firstnum = findViewById(R.id.firstnum);
        secondnum = findViewById(R.id.secondnum);
        thirdnum = findViewById(R.id.thirdnum);
        fourthnum = findViewById(R.id.fourthnum);

        //calling necessary methods
        jsonExtract();
        setValues();

        //listeners
        firstnum.setOnClickListener(this);
        secondnum.setOnClickListener(this);
        thirdnum.setOnClickListener(this);
        fourthnum.setOnClickListener(this);


    }

    public void setValues() {
        firstnum.setText(name1);
        secondnum.setText(name2);
        thirdnum.setText(name3);
        fourthnum.setText(name4);
    }

    //iterating through the json file
    public void jsonExtract() {

        try {
            String jsonLocation = loadJSONFromAsset();
            JSONObject jsonobject = new JSONObject(jsonLocation);
            JSONArray jArray = jsonobject.getJSONArray(jsonobj);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jb = (JSONObject) jArray.get(i);
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
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    //method handling calling
    private void callAtRuntime(String callingNumber) {

        this.callingnumber = callingNumber;
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + callingnumber));
            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


            PackageManager packageManager = getApplicationContext().getPackageManager();
            List activities = packageManager.queryIntentActivities(callIntent, PackageManager.MATCH_DEFAULT_ONLY);

            for (int j = 0; j < activities.size(); j++) {

                if (activities.get(j).toString().toLowerCase().contains("com.android.phone")) {
                    callIntent.setPackage("com.android.phone");
                } else if (activities.get(j).toString().toLowerCase().contains("call")) {
                    String pack = (activities.get(j).toString().split("[ ]")[1].split("[/]")[0]);
                    callIntent.setPackage(pack);
                }
            }

            getApplicationContext().startActivity(callIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callAtRuntime(callingnumber);
            } else {
                Toast.makeText(this, "Permission DeniedTry Again", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onClick(View view) {
        Button b = (Button) view;

        switch (b.getId()) {

            case R.id.firstnum:
                callingnumber = num1;
                callAtRuntime(callingnumber);
                break;

            case R.id.secondnum:
                callingnumber = num2;
                callAtRuntime(callingnumber);
                break;

            case R.id.thirdnum:
                callingnumber = num3;
                callAtRuntime(callingnumber);
                break;

            case R.id.fourthnum:
                callingnumber = num4;
                callAtRuntime(callingnumber);
                break;


        }

    }
}



