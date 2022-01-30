package com.example.cinemabookingapp;

public class Item {
    private String mtext1;
    private String mtext2;
    private String mtext3;

    public Item(String text1, String text2, String text3)
    {
        mtext1 = text1;
        mtext2 = text2;
        mtext3 = text3;
    }

    public String getText1()
    {
        return mtext1;
    }
    public String getText2()
    {
        return mtext2;
    }
    public String getText3()
    {
        return mtext3;
    }
}
