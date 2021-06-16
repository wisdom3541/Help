package com.example.help;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class home extends AppCompatActivity {

    AlertDialog.Builder alert;
    RecyclerView.Adapter adapter;
    RecyclerView card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.home);

        //hooks
        card = findViewById(R.id.card_view);

        cardview();

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void cardview() {

       card.setHasFixedSize(true);
       card.setLayoutManager(new GridLayoutManager(this,2));




        ArrayList<cardAdapter> carddisplay = new ArrayList<>();
        carddisplay.add(new cardAdapter(R.drawable.hospital,"Hospital"));
        carddisplay.add(new cardAdapter(R.drawable.policeman,"Police"));
        carddisplay.add(new cardAdapter(R.drawable.fire_truck,"Fire fighters"));
        carddisplay.add(new cardAdapter(R.drawable.safety_suit,"Quarantine"));
        carddisplay.add(new cardAdapter(R.drawable.therapist,"Therapist"));
        carddisplay.add(new cardAdapter(R.drawable.disaster,"Disaster agency"));
        carddisplay.add(new cardAdapter(R.drawable.snail,"Animal control"));
        carddisplay.add(new cardAdapter(R.drawable.road_work,"Public worker"));




        adapter = new Adapter(carddisplay);
        card.setAdapter(adapter);

    }
}
