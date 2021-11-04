package com.example.help;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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

        cardView();

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void cardView() {

        card.setHasFixedSize(true);
        card.setLayoutManager(new GridLayoutManager(this, 2));


        ArrayList<cardAdapter> cardDisplay = new ArrayList<>();
        cardDisplay.add(new cardAdapter(R.drawable.hospital, "Hospital"));
        cardDisplay.add(new cardAdapter(R.drawable.policeman, "Police"));
        cardDisplay.add(new cardAdapter(R.drawable.fire_truck, "Fire fighters"));
        cardDisplay.add(new cardAdapter(R.drawable.safety_suit, "Quarantine"));
        cardDisplay.add(new cardAdapter(R.drawable.therapist, "Therapist"));
        cardDisplay.add(new cardAdapter(R.drawable.disaster, "Disaster agency"));
        cardDisplay.add(new cardAdapter(R.drawable.snail, "Animal control"));
        cardDisplay.add(new cardAdapter(R.drawable.road_work, "Public worker"));


        adapter = new Adapter(cardDisplay);
        card.setAdapter(adapter);

    }
}
