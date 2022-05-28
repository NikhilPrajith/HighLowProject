package com.example.finalproject;

public class Data {
    private String name;
    private Double comparingData;
    private String imageUrl;
    public Data(String name, Double comparingData, String imageUrl){
        this.name = name;
        this.comparingData = comparingData;
        this.imageUrl = imageUrl;
    }
    public void setData(Double newValue){
        comparingData = newValue;
    }

    public String getName(){
        return name;
    }

    public Double getData(){
        return comparingData;
    }

    public String getUrl(){
        return imageUrl;
    }
    public static String customPrint(Data data){
        String temp = data.getName()+":"+data.getData()+"["+"]";
        return temp;
    }
}
