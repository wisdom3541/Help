package com.example.help;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class numbers extends AppCompatActivity {

    Button firstnum;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.numbers);

        //hooks

        firstnum = findViewById(R.id.firstnum);



        firstnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callAtRuntime();

            }
        });
    }

    private void callAtRuntime() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
        else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + "08023120368"));
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

