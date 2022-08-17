package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;

import java.util.Random;

public class TicTacToe2 extends Application {
    private Image background = new Image("file:src/main/resources/Table_tictactoe.jpg");
    private Image notebookPage = new Image("file:src/main/resources/Notebook_page.jpg");
    private char whichPlayersTurn = 'X';//Początek gry, zaczyna

    public Cell[][] cell =  new Cell[3][3];

    Label lblStatus = new Label("Player X turn");


    @Override
    public void start(Stage primaryStage) {

        Label labelTittle = new Label("Tic Tac Toe");
        Button chooseX = new Button("Choose X");
        Button chooseO = new Button("Choose O");
        Button exit = new Button("Exit");
        Button play = new Button("START GAME");//Przyciski na górze ekranu, stworzenie i czcionka
        play.setMinSize(400,120);
        play.setStyle("-fx-background-color: transparent;");
        Button scoreBoard = new Button("Scoreboard");
        chooseX.setFont(new Font(25));
        chooseO.setFont(new Font(25));
        exit.setFont(new Font(25));
        play.setFont(new Font(50));
        scoreBoard.setFont(new Font(25));
        labelTittle.setFont(new Font(40));
        labelTittle.setStyle("-fx-text-fill: white;");

        exit.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e) {//USTAWIENIE AKCJI NA PRZYCISK EXIT
                Platform.exit();
            }
        });
        GridPane pane = new GridPane();//Dodanie wszystkich komórek
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                pane.add(cell[i][j] = new Cell(), j, i);
            }
        }
        BackgroundSize backgroundSize = new BackgroundSize(100, 50, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(notebookPage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        pane.setBackground(new Background(backgroundImage));

        BackgroundSize backgroundSize2 = new BackgroundSize(1000, 800, true, true, true, true);
        BackgroundImage backgroundImage2 = new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize2);

        BorderPane borderPane = new BorderPane();//Stworzenie BorderPane(Layout z center, top, bottom, left i right)
        borderPane.setCenter(pane);//Ustawienie pane czyli GridPane(pola gry) jako center
        lblStatus.setStyle("-fx-text-fill: white;");
        borderPane.setBottom(lblStatus);//Ustawienie bottom jako komunikatu czyja kolej
        borderPane.setBackground(new Background(backgroundImage2));

        VBox buttonBar = new VBox();//Stworzenie belki z przyciskami
        buttonBar.setSpacing(35);//Ustawienie spacingu
        buttonBar.getChildren().addAll(chooseX, chooseO, play, scoreBoard, exit);//Dodanie przycisków do bara na górze

        HBox topBar = new HBox();
        topBar.getChildren().add(labelTittle);
        topBar.setAlignment(Pos.CENTER);// Stworzenie i dodanie labela na dole

        borderPane.setRight(buttonBar);//Ustawienie paska z przyciskami na górze ekranu
        borderPane.setTop(topBar);

        Scene scene = new Scene(borderPane, 800, 600);//Stworzenie sceny zawierającej borderPane czyli całej tej planszy z przyciskami na górze i gridem na środku
        primaryStage.setTitle("Wild TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);//Zablokowanie możliwości resize

    }
//Sprawdzenie zapełnienia planszy
    public boolean boardFilled() {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                if (cell[i][j].getPlayersIdentifier() == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isWon(char playersIdentifier) {//Sprawdzenie wygranych
        for (int i = 0; i < 3; i++)
            if (cell[i][0].getPlayersIdentifier() == playersIdentifier
                    && cell[i][1].getPlayersIdentifier() == playersIdentifier
                    && cell[i][2].getPlayersIdentifier() == playersIdentifier) {
                return true;
            }
        for (int j = 0; j < 3; j++)
            if (cell[0][j].getPlayersIdentifier() ==  playersIdentifier
                    && cell[1][j].getPlayersIdentifier() == playersIdentifier
                    && cell[2][j].getPlayersIdentifier() == playersIdentifier) {
                return true;
            }
        if (cell[0][0].getPlayersIdentifier() == playersIdentifier
                && cell[1][1].getPlayersIdentifier() == playersIdentifier
                && cell[2][2].getPlayersIdentifier() == playersIdentifier) {
            return true;
        }
        if (cell[0][2].getPlayersIdentifier() == playersIdentifier
                && cell[1][1].getPlayersIdentifier() == playersIdentifier
                && cell[2][0].getPlayersIdentifier() == playersIdentifier) {
            return true;
        }
        return false;
    }
    public class Cell extends StackPane {
        private char playersIdentifier = ' ';//Stworzenie pustego znaku
        public Text text = new Text();
        public Cell() {//Tworzę metodę Cell która w konstruktorze ustawia kolor granic, rozmiar i akcję wywołaną po kliknięciu myszką
            setStyle("-fx-border-color: black");
            this.setPrefSize(2000, 2000);
            this.setOnMouseClicked(e -> handleMouseClick());
        }
        public char getPlayersIdentifier() {//Zwracanie identyfikatora gracza
            return playersIdentifier;
        }
        public void playersMove(char c) {
            playersIdentifier = c;
            text.setFont(Font.font(96));
            if (playersIdentifier == 'X') {
                text.setText("X");
                getChildren().add(text);
            }
        }
        private void handleMouseClick() {
            System.out.println("Token poczatkowy w komorce 0,0: " + cell[0][0].getPlayersIdentifier());
            System.out.println("Tekst poczatkowy w komorce 0,0: " + cell[0][0].text.getText());
            System.out.println("Token poczatkowy w komorce 1,1: " + cell[1][1].getPlayersIdentifier());
            System.out.println("Tekst poczatkowy w komorce 1,1: " + cell[1][1].text.getText());
            System.out.println("Kolej gracza: " + whichPlayersTurn);
            System.out.println("Identyfikator gracza poczatkowy : " + playersIdentifier);
            System.out.println("--------------------CLICK---------------------");
            if (playersIdentifier == ' ' && whichPlayersTurn != ' ') {
                playersMove(whichPlayersTurn);
                System.out.println("Kolej gracza: " + whichPlayersTurn);
                System.out.println("Identyfikator gracza: " + playersIdentifier);
                System.out.println("pierwszy if się wykonał");
            }
            System.out.println("Token w komorce 0,0: " + cell[0][0].getPlayersIdentifier());
            System.out.println("Tekst w komorce 0,0: " + cell[0][0].text.getText());
            System.out.println("Token w komorce 1,1: " + cell[1][1].getPlayersIdentifier());
            System.out.println("Tekst w komorce 1,1: " + cell[1][1].text.getText());
            if (isWon(whichPlayersTurn)) {
                lblStatus.setText(whichPlayersTurn + " Victory! Game is over.");
                whichPlayersTurn = ' ';
                System.out.println("Kolej gracza: " + whichPlayersTurn);
                System.out.println("Identyfikator gracza: " + playersIdentifier);
                System.out.println("drugi if się wykonał");
            }
            else if (boardFilled()) {
                lblStatus.setText("Stalemate ! Game is over.");
                whichPlayersTurn = ' ';
                System.out.println("Kolej gracza: " + whichPlayersTurn);
                System.out.println("Identyfikator gracza: " + playersIdentifier);
                System.out.println("trzeci else if się wykonał");
            }
            else {
                whichPlayersTurn = (whichPlayersTurn == 'X') ? 'O' : 'X';//Jeśli czyj ruch to X to zmiana na 'O', jeśli false to zmiana na 'X'
                lblStatus.setText("Player " + whichPlayersTurn + " turn.");//Wyświetla czyj ruch
                System.out.println("Kolej gracza: " + whichPlayersTurn);
                System.out.println("Identyfikator gracza: " + playersIdentifier);
                System.out.println("czwarty else się wykonał");
            }

            if (playersIdentifier == ' ' && whichPlayersTurn != ' '){
                ComputersMove computersMove = new ComputersMove();
                computersMove.move(playersIdentifier);
                System.out.println("Kolej gracza: " + whichPlayersTurn);
                System.out.println("Identyfikator gracza: " + playersIdentifier);
                System.out.println("piąty if się wykonał");
            }
        }
    }
    public class ComputersMove extends Cell{
        private void move(char identifierFromCellMethod){
            if(identifierFromCellMethod == '0'){
                System.out.println("poczatek computersMove");
                Random random = new Random();
                int r1 = random.nextInt(3);
                int r2 = random.nextInt(3);
                text.setFont(Font.font(96));
                cell[r1][r2].text.setText("0");
                getChildren().add(text);
                System.out.println("koniec computersMove");
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}

