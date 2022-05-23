package com.example.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Cell {

    private int x,y;

    private Rectangle rectFront;
    private Rectangle rectBack;

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    private boolean isEmpty;

    //Type de cellule :
    //0 = vide
    //1 = arbre
    //2 = maison
    //3 = chaperon
    //4 = loup
    private int type_cell;

    Image img_wolf = new Image("file:src/main/resources/com/example/game/wolf.png");
    Image img_tree = new Image("file:src/main/resources/com/example/game/tree.png");
    Image img_chaperon = new Image("file:src/main/resources/com/example/game/chaperon.png");
    Image img_house = new Image("file:src/main/resources/com/example/game/house.png");

    public Cell(int a,int b, Rectangle front, Rectangle back){
        this.x = a;
        this.y = b;
        this.rectFront = front;
        this.rectBack = back;
    }

    public void drawCell(){
        strokeCell();
        switch (type_cell){
            case 1 :
                rectBack.setFill(Color.GREEN);
                rectBack.setArcHeight(10);
                rectBack.setArcWidth(10);
                rectFront.setFill(new ImagePattern(img_tree));
                isEmpty = false;
                break;
            case 2 :
                rectBack.setFill(Color.GREEN);
                rectBack.setArcHeight(10);
                rectBack.setArcWidth(10);
                rectFront.setFill(new ImagePattern(img_house));
                isEmpty = false;
                break;
            case 3 :
                rectBack.setFill(Color.GREEN);
                rectBack.setArcHeight(10);
                rectBack.setArcWidth(10);
                rectFront.setFill(new ImagePattern(img_chaperon));
                isEmpty = false;
                break;
            case 4 :
                rectBack.setFill(Color.GREEN);
                rectBack.setArcHeight(10);
                rectBack.setArcWidth(10);
                rectFront.setFill(new ImagePattern(img_wolf));
                isEmpty = false;
                break;
            default:
                //back
                rectBack.setFill(Color.GREEN);
                rectBack.setArcHeight(10);
                rectBack.setArcWidth(10);
                //front
                rectFront.setFill(Color.TRANSPARENT);
                rectFront.setArcHeight(10);
                rectFront.setArcWidth(10);
                isEmpty = true;
                break;
        }
    }

    public void strokeCell(){
        rectFront.setStroke(Color.BLACK);
        rectBack.setStroke(Color.BLACK);
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    public void setType_cell(int type_cell) {
        this.type_cell = type_cell;
    }
}
