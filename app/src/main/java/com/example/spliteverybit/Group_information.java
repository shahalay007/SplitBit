package com.example.spliteverybit;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Vector;

public class Group_information {

    public ArrayList<Pair<String,Integer>> name;

    public Group_information()
    {

    }

    public Group_information(ArrayList<Pair<String,Integer>>name) {

        this.name = name;
    }


    public ArrayList<Pair<String,Integer>>  getName() { return name; }
}
