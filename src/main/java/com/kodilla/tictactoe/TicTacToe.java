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


public class TicTacToe extends Application {
    private Image background = new Image("file:src/main/resources/Table_tictactoe.jpg");
    private Image notebookPage = new Image("file:src/main/resources/Notebook_page.jpg");
    private char whichPlayersTurn = 'X';

    private Cell[][] cell =  new Cell[3][3];

    Label lblStatus = new Label("X's turn to play");


    @Override
    public void start(Stage primaryStage) {

        Label labelTittle = new Label("Tic Tac Toe");
        Button chooseX = new Button("Choose X");
        Button chooseO = new Button("Choose O");
        Button exit = new Button("Exit");
        Button play = new Button("START GAME");
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
            public void handle(ActionEvent e) {
                Platform.exit();
            }
        });
        GridPane pane = new GridPane();
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

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        lblStatus.setStyle("-fx-text-fill: white;");
        borderPane.setBottom(lblStatus);
        borderPane.setBackground(new Background(backgroundImage2));

        VBox buttonBar = new VBox();
        buttonBar.setSpacing(35);
        buttonBar.getChildren().addAll(chooseX, chooseO, play, scoreBoard, exit);

        HBox topBar = new HBox();
        topBar.getChildren().add(labelTittle);
        topBar.setAlignment(Pos.CENTER);

        borderPane.setRight(buttonBar);

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setTitle("Wild TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

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
    public boolean isWon(char playersIdentifier) {
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

        private char playersIdentifier = ' ';
        private Text text = new Text();

        public Cell() {
            setStyle("-fx-border-color: black");
            this.setPrefSize(2000, 2000);
            this.setOnMouseClicked(e -> handleMouseClick());
        }
        public char getPlayersIdentifier() {
            return playersIdentifier;
        }

        public void setPlayersIdentifier(char c) {
            playersIdentifier = c;
            text.setFont(Font.font(96));

            if (playersIdentifier == 'X') {
                text.setText("X");
                getChildren().add(text);
            }
            else if (playersIdentifier == 'O') {
                text.setText("O");
                getChildren().add(text);
            }
        }
        private void handleMouseClick() {
            if (playersIdentifier == ' ' && whichPlayersTurn != ' ') {
                setPlayersIdentifier(whichPlayersTurn);

                if (isWon(whichPlayersTurn)) {
                    lblStatus.setText(whichPlayersTurn + " Victory! Game is over.");
                    whichPlayersTurn = ' ';
                }
                else if (boardFilled()) {
                    lblStatus.setText("Stalemate ! Game is over.");
                    whichPlayersTurn = ' ';
                }
                else {
                    whichPlayersTurn = (whichPlayersTurn == 'X') ? 'O' : 'X';
                    lblStatus.setText(whichPlayersTurn + "'s turn.");
                }
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}

