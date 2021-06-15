package com.example.help;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.CategoryHolder> {

    ArrayList<cardAdapter> card;


    public Adapter(ArrayList<cardAdapter> card) {
        this.card = card;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carddesign,parent,false);
        CategoryHolder categoryHolder = new CategoryHolder(view);
        return categoryHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

        cardAdapter categoryadapter = card.get(position);
        holder.image.setImageResource(categoryadapter.getImage());
        holder.title.setText(categoryadapter.getTitle());

    }

    @Override
    public int getItemCount() {
        return card.size();
    }

    public static class CategoryHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;
        String tag;


      public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = getPosition();
                    Log.e(tag, String.valueOf(index));
                    Intent i = new Intent(v.getContext(), numbers.class);
                    i.putExtra("number",index);
                    v.getContext().startActivity(i);
                }
            });

            //hooks

            image = itemView.findViewById(R.id.card_image);
            title = itemView.findViewById(R.id.card_title);

        }
    }
}
