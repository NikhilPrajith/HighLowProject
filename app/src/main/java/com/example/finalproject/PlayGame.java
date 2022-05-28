package com.example.finalproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.text.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Double.parseDouble;
import static java.util.regex.Pattern.compile;

public class PlayGame {
    private Map<Integer,Data> dataSet;
    private List<Integer> removedItemNumbers = new ArrayList<>();
    private Integer OriginalMapSize;
    private Integer score;
    private Boolean gameEnd;
    private static String[] suffix = new String[]{"","k", "m", "b", "t"};
    private static int MAX_LENGTH = 4;
    private static final Pattern REGEX = compile("(\\d+(?:\\.\\d+)?)([KMB]?)");
    private static final String[] KMG = new String[] {"", "K", "M", "B"};


    public PlayGame(Map<Integer,Data> dataSet){
        this.dataSet = dataSet;
        OriginalMapSize = dataSet.size();
        score = 0;
        gameEnd = false;
    }

    public Data getASingleStartingData(){
        Integer mapSize= dataSet.size();
        int random_int = (int)Math.floor(Math.random()*(mapSize-1+1)+1);
        removedItemNumbers.add(random_int);
        Data temp = dataSet.get(random_int);
        dataSet.remove(random_int);
        return temp;
    }

    public Data getComparingData(Data dataA){
        Integer mapSize= getOriginialMapSize();
        int random_int = (int)Math.floor(Math.random()*(mapSize-1+1)+1);

        Data dataB = dataSet.get(random_int);
        System.out.println();
    /*
Also if a number is already remove dataA.getData() is null so this will crash*/
        while(removedItemNumbers.contains(random_int)){
            random_int = (int)Math.floor(Math.random()*(mapSize-1+1)+1);
            dataB = dataSet.get(random_int);
        }
        //System.out.println("Random Value: "+random_int);
        //System.out.println(dataA);
        //System.out.println(dataA.getData());
        //System.out.println(dataB);
        //System.out.println(dataB.getData());
        if(dataA.getData().equals(dataB.getData())){
            System.out.println("Same:");
            dataB.setData(dataA.getData()+0.3);
        }
        removedItemNumbers.add(random_int);
        dataSet.remove(random_int);
        return dataB;
    }
    public Boolean compare(String guess,Data A, Data B){
        if(gameEnd ==true){
            return false;
        }
        if(guess.equals("G")&& A.getData()<B.getData()){
            score +=1;
            return true;
        }
        if(guess.equals("L") && A.getData()>B.getData()){
            score +=1;
            return true;
        }
        gameEnd=true;
        return false;
    }
    public Integer getScore(){
        return score;
    }


    public Integer getOriginialMapSize(){
        return OriginalMapSize;
    }
    public Integer getCurrentMapSize(){
        return dataSet.size();
    }
    public String format(double number) {
        int i = 0;
        while (number >= 1000) { i++; number /= 1000; }
        return number + KMG[i];
        /*
        String r = new DecimalFormat("##0E0").format(number);
        r = r.replaceAll("E[0-9]", suffix[Character.getNumericValue(r.charAt(r.length() - 1)) / 3]);
        while(r.length() > MAX_LENGTH || r.matches("[0-9]+\\.[a-z]")){
            r = r.substring(0, r.length()-2) + r.substring(r.length() - 1);
        }
        return r;*/
    }
}
