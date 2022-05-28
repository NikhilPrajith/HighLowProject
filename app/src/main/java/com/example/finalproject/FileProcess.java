package com.example.finalproject;
import android.content.Context;
import android.os.Build;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileProcess {
    private List<String> sentences;
    private Map<Integer,Data> filtered;
    private Context context;
    public FileProcess(String str,Context context) {
        this.context = context;
        sentences = linesreadFile(str);
        filter();
    }
    public List<String> linesreadFile(String str) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(str+".txt")));
            System.out.println("Correct Reader");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                System.out.println("Correct Version");
                return reader.lines().collect(Collectors.toList());
            }
            reader.close();
        }catch(Exception e){
            System.out.println("error in File path");
            return new ArrayList<String>();
        }
        return new ArrayList<String>();


    }
    public List<String> getData(){
        return sentences;
    }
    public Map<Integer,Data> getFiltered(){
        return filtered;
    }
    public void filter(){
        filtered = new HashMap<>();
        Integer count =0;
        for(String sen:sentences){
            count++;
            String[] temp = sen.split("\\|");
            Double a = Double.parseDouble(temp[1].replaceAll(",", ""));
            Data data = new Data(temp[0],a,temp[2]);
            filtered.put(count,data);
        }
    }
}
