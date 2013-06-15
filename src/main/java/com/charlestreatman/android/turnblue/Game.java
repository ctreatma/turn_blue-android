package com.charlestreatman.android.turnblue;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private ArrayList<Square> board;
    private int boardSize, clicksRemaining, level, lossDifferential;

    public Game(int boardSize, int lossDifferential) {
        this(boardSize, lossDifferential, 1);
    }
    public Game(int boardSize, int lossDifferential, int level) {
        this.boardSize = boardSize;
        this.level = level;
        this.lossDifferential = lossDifferential;
        createBoard();
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getClicksRemaining() {
        return clicksRemaining;
    }

    public int getLevel() {
        return level;
    }

    public List<Square> getBoardState() {
        return board;
    }

    public boolean isOver() {
        return clicksRemaining <= 0;
    }

    public boolean isVictory() {
        for(Square square : board) {
            if (square.isOn()) {
                return false;
            }
        }
        return clicksRemaining >= 0;
    }

    public void click(int i) {
        board.get(i).toggle();
        // Toggle the squares above and below
        if (i >= boardSize)
            board.get(i-boardSize).toggle();
        if (i < (boardSize * (boardSize - 1)))
            board.get(i+boardSize).toggle();
        // If the clicked square is not on the left
        // edge, toggle the square to the left
        if (i % boardSize > 0)
            board.get(i-1).toggle();
        // If the clicked square is not on the right
        // edge, toggle the square to the right
        if (i % boardSize < boardSize - 1)
            board.get(i+1).toggle();
        clicksRemaining--;
    }

    public String reset() {
        level = 1;
        createBoard();
        return "You lost!";
    }

    public String levelUp() {
        level++;
        createBoard();
        return "You made it to level " + level + "!";
    }

    private void createBoard() {
        board = new ArrayList<Square>();
        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
                board.add(new Square());
            }
        }
        initializeBoard();
    }

    private void initializeBoard() {
        List<Integer> clicked = new ArrayList<Integer>();

        // Click random squares to set up the board for play
        while (clicked.size() < level) {
            int index = (int) Math.floor(Math.random() * (board.size()));
            if (!clicked.contains(index)) {
                click(index);
                clicked.add(index);
            }
        }

        this.clicksRemaining = level + lossDifferential;
    }
}
