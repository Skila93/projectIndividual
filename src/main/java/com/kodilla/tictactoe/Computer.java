package com.kodilla.tictactoe;

import java.util.Random;

import static com.kodilla.tictactoe.TicTacToe2.filledCells;

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