package com.kodilla.tictactoe;

import javafx.scene.control.Label;

import static com.kodilla.tictactoe.TicTacToe2.filledCells;
import static com.kodilla.tictactoe.TicTacToe2.cells;

public class CellMethods {
    public Label statusLabel;


    public static void fillCell(String player, int index) {
        cells.get(index).setText(player);
        filledCells.set(index, true);
    }

    public static void clearBoardArray() {
        for (int i = 0; i < 9; i++) {
            filledCells.set(i, false);
        }
        releaseBoard();
    }

    public void clearBoard() {
        cells.forEach(cell -> cell.setText(""));
        clearBoardArray();
        statusLabel.setText("");
    }

    public static void blockBoard() {
        cells.forEach(cell -> cell.setDisable(true));
    }

    public static void releaseBoard() {
        cells.forEach(cell -> cell.setDisable(false));
    }

    public static boolean isBoardFull() {
        for (boolean cellFilled : filledCells) {
            if (!cellFilled)
                return false;
        }
        return true;
    }

    public static boolean winningCheck(String playerString) {
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
}
