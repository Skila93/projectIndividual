package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToe2 extends Application {
    private static List<Button> cells = new ArrayList<>();

    private static List<Boolean> filledCells = new ArrayList<>(9);

    private computerMoves aiLogic;

    private Label statusLabel;

    private Image background = new Image("file:src/main/resources/Table_tictactoe.jpg");
    private Image notebookPage = new Image("file:src/main/resources/Notebook_page.jpg");

    public TicTacToe2() {
        statusLabel = new Label("");
        aiLogic = new Computer();

        for (int i = 0; i < 9; i++) {
            filledCells.add(false);
        }
        clearBoardArray();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
            ColumnConstraints c1 = new ColumnConstraints(125);
            RowConstraints r1 = new RowConstraints(125);
            pane.getColumnConstraints().addAll(c1, c1, c1);
            pane.getRowConstraints().addAll(r1, r1, r1);
            for (int i = 0; i < 9; i++) {
                pane.add(Cell(), i % 3, i / 3);
            }

        BackgroundSize backgroundSize2 = new BackgroundSize(1000, 800, true, true, true, true);
        BackgroundImage backgroundTable = new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize2);
        Button startButton = new Button("Start game");
        startButton.setFont(new Font(40));


            startButton.setFont(new Font(20));
            startButton.setOnAction(ae -> clearBoard());
        Label labelTittle = new Label("Tic Tac Toe");
        labelTittle.setStyle("-fx-text-fill: white;");
        labelTittle.setFont(new Font(40));

        BorderPane root = new BorderPane();
            VBox bottomPanel = new VBox(5, statusLabel, startButton);
        statusLabel.setFont(new Font(40));
        statusLabel.setStyle("-fx-text-fill: white;");
            bottomPanel.setAlignment(Pos.CENTER);
            BorderPane.setMargin(bottomPanel, new Insets(20));
            root.setBackground(new Background(backgroundTable));

            VBox topPannel = new VBox(labelTittle);
            topPannel.setAlignment(Pos.CENTER);

        root.setCenter(pane);
        root.setBottom(bottomPanel);
        root.setTop(topPannel);

        root.setMargin(pane, new Insets(25));
        root.setMaxWidth(750);
        root.setMaxHeight(900);

        Scene scene = new Scene(root, 425, 700);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic Tac Toe game");
        primaryStage.show();
    }

    private Button Cell() {
        Button cell = new Button("");
            cell.setPrefHeight(125);
            cell.setPrefWidth(125);
            cell.setFont(new Font(30));
            BackgroundSize backgroundSize = new BackgroundSize(100, 50, true, true, true, true);
            BackgroundImage backgroundImage = new BackgroundImage(notebookPage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            cell.setBackground(new Background(backgroundImage));
            cell.setStyle("-fx-border-color: black");


            cell.setOnAction(ae -> {
            int clickedCellIndex = cells.indexOf(cell);
            if (filledCells.get(clickedCellIndex)) {
                return;
            } else {
                fillCell("X", clickedCellIndex);
            }

            if (winningCheck("X")) {
                statusLabel.setText("Victory");
                blockBoard();
                return;
            }
            if (isBoardFull()) {
                statusLabel.setText("Stalemate ");
                blockBoard();
                return;
            }

            int aiIndex = aiLogic.compMove();
            fillCell("O", aiIndex);

            if (winningCheck("O")) {
                statusLabel.setText("Computer's victory");
                blockBoard();
            }
        });

        cells.add(cell);
        return cell;
    }

    private static void fillCell(String player, int index) {
        cells.get(index).setText(player);
        filledCells.set(index, true);
    }

    private static void clearBoardArray() {
        for (int i = 0; i < 9; i++) {
            filledCells.set(i, false);
        }
        releaseBoard();
    }

    private void clearBoard() {
        cells.forEach(cell -> cell.setText(""));
        clearBoardArray();
        statusLabel.setText("");
    }

    private static void blockBoard() {
        cells.forEach(cell -> cell.setDisable(true));
    }

    private static void releaseBoard() {
        cells.forEach(cell -> cell.setDisable(false));
    }

    private static boolean isBoardFull() {
        for (boolean cellFilled : filledCells) {
            if (!cellFilled)
                return false;
        }
        return true;
    }
    private static boolean winningCheck(String playerString) {
        for (int r = 0; r < 3; r++) {
            if (cells.get(r * 3).getText().equals(playerString) &&
                    cells.get(r * 3 + 1).getText().equals(playerString) &&
                    cells.get(r * 3 + 2).getText().equals(playerString))
                return true;
        }

        for (int c = 0; c < 3; c++) {
            if (cells.get(c).getText().equals(playerString) &&
                    cells.get(3 + c).getText().equals(playerString) &&
                    cells.get(6 + c).getText().equals(playerString))
                return true;
        }

        if (cells.get(0).getText().equals(playerString) &&
                cells.get(4).getText().equals(playerString) &&
                cells.get(8).getText().equals(playerString))
            return true;

        if (cells.get(2).getText().equals(playerString) &&
                cells.get(4).getText().equals(playerString) &&
                cells.get(6).getText().equals(playerString))
            return true;

        return false;
    }

    public interface computerMoves {
        int compMove();
    }

    class Computer implements computerMoves {
        @Override
        public int compMove() {
            Random r1 = new Random();

            int aiIndex = 0;
            while (filledCells.get(aiIndex)) {
                aiIndex = r1.nextInt(8);
            }
            return aiIndex;
        }
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}