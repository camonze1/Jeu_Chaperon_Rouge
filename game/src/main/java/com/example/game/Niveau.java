package com.example.game;

public class Niveau {

    private int[] values = new int[16] ;

    public Niveau(){
        for (int i = 0 ; i < values.length ; i++){
            values[i] = 0 ;
        }
    }

    public void setNiveau(int[] tab){
        for (int i = 0 ; i < values.length ; i++){
            values[i] = tab[i] ;
        }
    }

    public int[] getNiveau(){
        return values ;
    }
}
