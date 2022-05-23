package com.example.game;

public class Solution {

    private int[] values = new int[15] ;

    public Solution(){
        for (int i = 0 ; i < values.length ; i++){
            values[i] = 0 ;
        }
    }

    public void setSolution(int[] tab){
        for (int i = 0 ; i < values.length ; i++){
            values[i] = tab[i] ;
        }
    }

    public int[] getSolution(){
        return values ;
    }
}
