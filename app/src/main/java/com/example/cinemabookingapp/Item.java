package com.example.cinemabookingapp;

public class Item {
    private String mtext1;
    private String mtext2;
    private String mtext3;
    private String mtext4;

    public void setText1(String text1){ mtext1 = text1; }
    public void setText2(String text2){ mtext2 = text2; }
    public void setText3(String text3){ mtext3 = text3; }
    public void setText4(String text4){ mtext4 = text4; }

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
    public String getText4()
    {
        return mtext4;
    }
}
