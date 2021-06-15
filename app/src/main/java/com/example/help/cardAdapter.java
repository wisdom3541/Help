package com.example.help;

public class cardAdapter {

    int image;
    String title;

    public  cardAdapter(int image, String title){
        this.image = image;
        this.title = title;
    }

    public int getImage(){
        return image;
    }

    public String getTitle(){
        return title;
    }

}
