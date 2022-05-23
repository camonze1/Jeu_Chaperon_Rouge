package com.example.game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class MainVue extends Application {

    private Stage primaryStage ;

    private GridPane grid_back = new GridPane(); //Le grid où il y a les chemins et les cases vertes , la maison
    private GridPane grid_front  = new GridPane(); //Le grid où il y a les personage, les arbres
    private StackPane grid_stack = new StackPane(); //Stack afin d'empiler les 2 gridPane

    private List<Cell> listCells = new ArrayList<Cell>();
    private List<Path> listPath = new ArrayList<Path>();

    private VBox selectPath = new VBox();
    private VBox selectButton = new VBox();

    private Pane cadre = new Pane();

    private Button btnNext = new Button("next level") ;

    private int SIZE_GRID = 3;
    private int SIZE_RECT = 70;

    public Controller getC() {
        return c;
    }

    private Controller c = new Controller(this);
    private Stage stage ;
    private Stage configuration ;


    //MENU ---------------------------------------------------
    private Pane headertext = new Pane(); // contient scenetitle
    private BorderPane contenaire = new BorderPane();
    private GridPane paneButton_configuration = new GridPane(); // On utilise le GridPane pour organiser les buttons des 6 niveaux
    private GridPane contenaire_configuration = new GridPane(); // contient paneButton_configuration
    private GridPane racine_configuration = new GridPane(); // contient scenetitle_Configuration
    private BorderPane contente_configuration = new BorderPane(); //contient racine_configuration; contenaire_configuration, vbox_button_configuration

    // les differents buttons
    private Button [] btns;
    private Button game = new Button("PLAY");
    //private Button parametre  = new Button("SELECT LEVEL");
    private Button quit_the_game = new Button("QUIT THE GAME");
    private Button quit  = new Button("QUIT");

    // vbox pour organiser les buttons
    private VBox vbox_button_start = new VBox(20);
    private VBox vbox_button_configuration = new VBox(20);

    // Labels des 6 niveaux
    private String[] btnLabels = {  // Labels des 6 niveaux
            "1", "2", "3",
            "4", "5", "6",
    };

    // text scenetitle
    private Text scenetitle = new Text("            Welcome to\nRed Riding Hood The GAME");
    private Text scenetitle_Configuration  = new Text("  PLEASE SELECT LEVEL");

    public MainVue() throws FileNotFoundException {
    }

    @Override
    public void start (Stage primaryStage) throws IOException {
        //Menu du jeu
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Red Riding Hood");
        scenetitle.setId("Bienvenue-text");
        primaryStage.setResizable(true);
        primaryStage.setMinWidth(500);

        createButton(); // contient les trois buttons play, select level, quit
        setContenaire_Start (); // contient les buttons et le texte
        create_Hbox_Start(); // contient les le tetxe Welcome to Red Riding Hood The GAME

        Scene scene = new Scene( contenaire,  500, 400);
        scene.getStylesheets().add("Style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void configurer () {
        Stage configuration = new Stage();
        this.configuration = configuration;
        configuration.setTitle("CONFIGURATION");
        configuration.setResizable(false);
        configuration.setMinWidth(300);
        scenetitle_Configuration.setId("CONFIGURATION-text");

        //create_button_level();
        set_racine_configuration();
        set_vbox_button_configuration();
        set_contenaire_configuration();
        set_contente_configuration() ;

        GridPane.setHalignment(scenetitle_Configuration , HPos.CENTER);
        Scene scene = new Scene( contente_configuration,  400, 400);
        scene.getStylesheets().add("Style.css");
        configuration.setScene(scene);
        configuration.show();
    }

    public void play (int b) throws IOException{
        //Fermeture du menu
        primaryStage.close();
        Stage stage = new  Stage();
        this.stage = stage;
        createGrid();
        createPathBox();
        setupGrid();
        c.loadLevel(b);

        //FXMLLoader fxmlLoader = new FXMLLoader(MainVue.class.getResource("hello-view.fxml"));

        //Lancement de la fenetre du jeu
        //cadre.getChildren().add(btnNext);
        Scene scene = new Scene(cadre, 600, 400);
        stage.setTitle("Chaperon Rouge");
        stage.setScene(scene);
        stage.show();

        draw();//attention peu etre avant

        oldMain(); // fonction provisoire pour afficher dans la console
    }

    public void createButton(){
        game.setId("button-even");
        game.setOnAction( e -> {
            try {
                play(0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

      /*  parametre.setId("button-even");
        parametre.setOnAction( e -> {
            configurer();
        });*/

        quit.setId("button-even");
        quit.setOnAction( e -> {
            Platform.exit();
        });

        vbox_button_start.getChildren().addAll(game, quit);
        vbox_button_start.setAlignment(Pos.CENTER);
        vbox_button_start.setSpacing(20);
        //parametre.setMinSize(150, 50);
        quit.setMinSize(150, 50);
        game.setMinSize(150, 50);
    }

    public void setContenaire_Start (){
        contenaire.setTop(headertext);
        contenaire.setCenter(vbox_button_start );
    }

    public void create_Hbox_Start(){
        HBox hbox_start = new HBox();
        hbox_start .setPadding(new Insets(10, 12, 10, 12));
        hbox_start .setSpacing(10);
        hbox_start .getChildren().add(scenetitle);
        hbox_start .setAlignment(Pos.CENTER);
        headertext.getChildren().add(hbox_start);
    }
/*
    public void create_button_level(){
        //créer les  6 Buttons  3x2 btn
        int nbCols = 3;
        paneButton_configuration.setGridLinesVisible(false);
        // Padding : top, right, bottom, left
        paneButton_configuration.setPadding(new Insets(10, 10, 10, 10));
        // Espacement vertical entre les noeuds (composants)
        paneButton_configuration.setVgap(20);
        // Espacement horizontal entre les noeuds (composants)
        paneButton_configuration.setHgap(20);
        // créer les  6 Botuons et les ajouter au GridPane
        btns = new Button[6];
        quit_the_game.setId("button-even");
        quit_the_game.setOnAction(e -> {
            Platform.exit();
        });

        quit_the_game.setMinSize(150, 50);
        for (int i = 0; i <6; ++i) {
            btns[i] = new Button(btnLabels[i]);
            // associer l'auditeur à tous les boutons.
            int finalI = i;
            btns[i].setOnAction(event -> {
                try {
                    play(finalI);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                configuration.close();
            });
            btns[i].setId("button-even");
            btns[i].setMinSize(50, 50);
            // Placement des boutons selon les règles du GridPane (composant, col, ligne)
            paneButton_configuration.add(btns[i], i % nbCols, i / nbCols);
        }
    }
*/
    public void set_vbox_button_configuration  (){
        vbox_button_configuration.getChildren().addAll(quit_the_game);
        vbox_button_configuration.setAlignment(Pos.CENTER);
        vbox_button_configuration.setSpacing(20);
    }

    public void set_racine_configuration (){
        racine_configuration.setGridLinesVisible(false);
        racine_configuration.setHgap(20);
        racine_configuration.setVgap(10);
        racine_configuration.add(scenetitle_Configuration , 1,1);
    }

    public void set_contenaire_configuration (){
        contenaire_configuration .setHgap(20);
        contenaire_configuration .setVgap(10);
        contenaire_configuration .add(paneButton_configuration, 3, 2);
        contenaire_configuration .setGridLinesVisible(false);
    }

    public void set_contente_configuration(){
        contente_configuration.setTop(racine_configuration);
        contente_configuration.setCenter(contenaire_configuration );
        contente_configuration.setBottom(vbox_button_configuration );
        contente_configuration.setPadding(new Insets(30, 30, 60, 30));
    }

    public void createGrid(){
        for(int i = 0; i <= SIZE_GRID; i++){
            for(int j = 0; j <= SIZE_GRID; j++){
                Rectangle square_back = new Rectangle(j, i, SIZE_RECT,SIZE_RECT);
                Rectangle square_front = new Rectangle(j, i, SIZE_RECT,SIZE_RECT);
                grid_back.add(square_back,j,i);
                grid_front.add(square_front,j,i);
                Cell c = new Cell(j,i,square_front,square_back);
                listCells.add(c);
            }
        }
    }

    public void setupGrid() {
        grid_stack.getChildren().addAll(grid_back,grid_front);
        grid_stack.setPadding(new Insets(50,10,10,50));
        grid_back.setVgap(1);
        grid_back.setHgap(1);
        grid_front.setVgap(1);
        grid_front.setHgap(1);
        cadre.getChildren().addAll(grid_stack, selectPath, selectButton);
        grid_stack.toBack();
        cadre.setStyle("-fx-background-color: #7d40af");
    }

    private void createPathBox() {
        for(int i = 0; i < 5; i++){
            Path p = new Path(i ,400, 75*(i)+15, this);
            listPath.add(p);
            cadre.getChildren().add(listPath.get(i).getTile());
        }
    }

    public void draw(){
        for (int i = 0; i < listCells.size(); i++){
            listCells.get(i).drawCell();
        }
        for (int i = 0; i < listPath.size(); i++){
            listPath.get(i).drawPath();
        }
    }

    // [TEMPORAIRE] fonction pour effectuer des test
    public void oldMain() throws IOException {
        c.lireNiveaux();
        c.createNextLevel();
        draw();

    }

    public List<Cell> getListCells() {
        return listCells;
    }

    public List<Path> getListPath() {
        return listPath;
    }

    public static void main(String[] args) {
        launch();
    }
}