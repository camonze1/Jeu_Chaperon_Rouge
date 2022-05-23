package com.example.game;

import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.io.*;

public class Path {
    private int rotation ;
    private int type;

    private MainVue mv;
    double gridX = 0 , gridY = 0 ;

    private int scaleMax = 140;
    private int scaleMin = 70;

    public Rectangle getTile() {
        return tile;
    }
    private boolean onGrid;
    private Rectangle tile;
    private boolean succes ;

    Image path1_r0 = new Image("file:src/main/resources/com/example/game/path1_R0.png");
    Image path1_r1 = new Image("file:src/main/resources/com/example/game/path1_R1.png");
    Image path1_r2 = new Image("file:src/main/resources/com/example/game/path1_R2.png");
    Image path1_r3 = new Image("file:src/main/resources/com/example/game/path1_R3.png");

    Image path2_r0 = new Image("file:src/main/resources/com/example/game/path2_R0.png");
    Image path2_r1 = new Image("file:src/main/resources/com/example/game/path2_R1.png");
    Image path2_r2 = new Image("file:src/main/resources/com/example/game/path2_R2.png");
    Image path2_r3 = new Image("file:src/main/resources/com/example/game/path2_R3.png");

    Image path3_r0 = new Image("file:src/main/resources/com/example/game/path3_R0.png");
    Image path3_r1 = new Image("file:src/main/resources/com/example/game/path3_R1.png");
    Image path3_r2 = new Image("file:src/main/resources/com/example/game/path3_R2.png");
    Image path3_r3 = new Image("file:src/main/resources/com/example/game/path3_R3.png");

    Image path4_r0 = new Image("file:src/main/resources/com/example/game/path4_R0.png");
    Image path4_r1 = new Image("file:src/main/resources/com/example/game/path4_R1.png");
    Image path4_r2 = new Image("file:src/main/resources/com/example/game/path4_R2.png");
    Image path4_r3 = new Image("file:src/main/resources/com/example/game/path4_R3.png");

    Image path5_r0 = new Image("file:src/main/resources/com/example/game/path5_R0.png");
    Image path5_r1 = new Image("file:src/main/resources/com/example/game/path5_R1.png");
    Image path5_r2 = new Image("file:src/main/resources/com/example/game/path5_R2.png");
    Image path5_r3 = new Image("file:src/main/resources/com/example/game/path5_R3.png");
    public Path(int type, double x, double y, MainVue mv){
        this.rotation = 0;
        this.onGrid = false ;
        this.mv = mv;
        this.type = type;
        this.gridX = x;
        this.gridY = y;
        if (rotation == 0 || rotation == 2) {
            this.tile = new Rectangle(x , y,142, 72);
        } else {
            this.tile = new Rectangle(x , y,72, 142);
        }
        this.onGrid = false;
        tile.setOnMousePressed(
                event -> {
                    if (event.getButton() == MouseButton.SECONDARY) {
                        rotate();
                    }
                }
        );
        tile.setOnMouseDragged(
                event -> {
                    int gridXInt = (int) gridX;
                    int gridYInt = (int) gridY;
                    if (rotation == 0 || rotation == 2) {
                        Cell testCell1 = mv.getC().selectCell(gridXInt - 1, gridYInt - 1);
                        Cell testCell2 = mv.getC().selectCell(gridXInt, gridYInt - 1);
                        testCell2.setEmpty(true);
                        testCell1.setEmpty(true);
                    } else {
                        Cell testCell1 = mv.getC().selectCell(gridXInt - 1, gridYInt - 1);
                        Cell testCell2 = mv.getC().selectCell(gridXInt - 1, gridYInt);
                        testCell2.setEmpty(true);
                        testCell1.setEmpty(true);
                    }
                    tile.setX(event.getX()-35);
                    tile.setY(event.getY()-35);
                }
        );
        tile.setOnMouseReleased(
                event -> {
                    gridX = Math.round(((event.getSceneX())-15)/72);
                    gridY = Math.round(((event.getSceneY())-15)/72);
                    int gridXInt = (int) gridX;
                    int gridYInt = (int) gridY;
                    if (this.rotation == 0 || this.rotation == 2) {
                        if (gridX < 4 && gridY < 5 && gridY > 0 && gridX > 0) {
                            Cell testCell1 = mv.getC().selectCell(gridXInt - 1, gridYInt - 1);
                            Cell testCell2 = mv.getC().selectCell(gridXInt, gridYInt - 1);
                            if (testCell2.isEmpty() && testCell1.isEmpty()) {
                                tile.setX((gridX-1)*72+50);
                                tile.setY((gridY-1)*72+50);
                                testCell1.setEmpty(false);
                                testCell2.setEmpty(false);
                                this.onGrid = true ;
                            } else {
                                tile.setX(x);
                                tile.setY(y);
                                gridX = 0;
                                gridY = 0;
                                this.onGrid = false ;
                            }
                        } else {
                            tile.setX(x);
                            tile.setY(y);
                            gridX = 0;
                            gridY = 0;
                            this.onGrid = false ;
                        }
                    } else {
                        if (gridX < 5 && gridY < 4 && gridX > 0 && gridY > 0) {
                            Cell testCell1 = mv.getC().selectCell(gridXInt - 1, gridYInt - 1);
                            Cell testCell2 = mv.getC().selectCell(gridXInt - 1, gridYInt);
                            if (testCell2.isEmpty() && testCell1.isEmpty()) {
                                tile.setX((gridX-1)*72+50);
                                tile.setY((gridY-1)*72+50);
                                testCell1.setEmpty(false);
                                testCell2.setEmpty(false);
                                this.onGrid = true ;
                            } else {
                                tile.setX(x);
                                tile.setY(y);
                                gridX = 0;
                                gridY = 0;
                                this.onGrid = false ;
                            }
                        } else {
                            tile.setX(x);
                            tile.setY(y);
                            gridX = 0;
                            gridY = 0;
                            this.onGrid = false ;
                        }
                    }
                    try {
                        succes = mv.getC().compareToWin() ;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (succes){
                        int level = mv.getC().getNumNiveau() + 1 ;
                        System.out.println("bravo ! Tu passe au niveau : " + level);
                        try {
                            mv.getC().createNextLevel();
                            mv.draw() ;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    public void rotate(){
        rotation++;
        if (rotation==4){
            rotation = 0;
        }
        drawPath();
    }

    public void drawPath(){
        switch (this.type){
            case 0 :
                if(rotation == 0){
                    this.tile.setFill(new ImagePattern(path1_r0));
                }
                if(rotation == 1){
                    this.tile.setFill(new ImagePattern(path1_r1));
                }
                if(rotation == 2){
                    this.tile.setFill(new ImagePattern(path1_r2));
                }
                if(rotation == 3){
                    this.tile.setFill(new ImagePattern(path1_r3));
                }
                break;
            case 1 :
                if(rotation == 0){
                    this.tile.setFill(new ImagePattern(path2_r0));
                }
                if(rotation == 1){
                    this.tile.setFill(new ImagePattern(path2_r1));
                }
                if(rotation == 2){
                    this.tile.setFill(new ImagePattern(path2_r2));
                }
                if(rotation == 3){
                    this.tile.setFill(new ImagePattern(path2_r3));
                }
                break;
            case 2 :
                if(rotation == 0){
                    this.tile.setFill(new ImagePattern(path3_r0));
                }
                if(rotation == 1){
                    this.tile.setFill(new ImagePattern(path3_r1));
                }
                if(rotation == 2){
                    this.tile.setFill(new ImagePattern(path3_r2));
                }
                if(rotation == 3){
                    this.tile.setFill(new ImagePattern(path3_r3));
                }
                break;
            case 3 :
                if(rotation == 0){
                    this.tile.setFill(new ImagePattern(path4_r0));
                }
                if(rotation == 1){
                    this.tile.setFill(new ImagePattern(path4_r1));
                }
                if(rotation == 2){
                    this.tile.setFill(new ImagePattern(path4_r2));
                }
                if(rotation == 3){
                    this.tile.setFill(new ImagePattern(path4_r3));
                }
                break;
            case 4 :
                if(rotation == 0){
                    this.tile.setFill(new ImagePattern(path5_r0));
                }
                if(rotation == 1){
                    this.tile.setFill(new ImagePattern(path5_r1));
                }
                if(rotation == 2){
                    this.tile.setFill(new ImagePattern(path5_r2));
                }
                if(rotation == 3){
                    this.tile.setFill(new ImagePattern(path5_r3));
                }
                break;
        }
        if(rotation == 0 || rotation == 2){
            tile.setHeight(scaleMin);
            tile.setWidth(scaleMax);
        }else {
            tile.setHeight(scaleMax);
            tile.setWidth(scaleMin);
        }
        this.tile.setArcHeight(10);
        this.tile.setArcWidth(10);
    }

    public void setRotation(int r){this.rotation = r ;}

    public boolean getOnGrid(){return onGrid ;}
    public void setOnGrid(boolean o){onGrid = o ;}

    public int getRotation(){return rotation ;}
    public int getX(){return (int) gridX;}
    public int getY(){return (int) gridY;}

}
