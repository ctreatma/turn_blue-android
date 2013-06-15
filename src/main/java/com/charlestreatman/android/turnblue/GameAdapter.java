package com.charlestreatman.android.turnblue;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

public class GameAdapter extends BaseAdapter {
    private Game game;
    private Context context;

    public GameAdapter(Context context) {
        int boardSize = Integer.valueOf(context.getString(R.string.board_size));
        int lossDifferential = Integer.valueOf(context.getString(R.string.loss_differential));
        game = new Game(boardSize, lossDifferential);
        this.context = context;
    }

    public int getBoardSize() {
        return game.getBoardSize();
    }

    public int getLevel() {
        return game.getLevel();
    }

    public int getClicksRemaining() {
        return game.getClicksRemaining();
    }

    public void click(int i) {
        game.click(i);
        if (game.isVictory()) {
            Toast.makeText(context, game.levelUp(), Toast.LENGTH_SHORT).show();
        }
        else if (game.isOver()) {
            Toast.makeText(context, game.reset(), Toast.LENGTH_SHORT).show();
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return game.getBoardState().size();
    }

    @Override
    public Object getItem(int i) {
        return game.getBoardState().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View v = view;
        if (v == null) {
            v = new View(context);
            v.setId(i);
        }
        if (((Square) getItem(i)).isOn()) {
            v.setBackgroundColor(Color.RED);
        }
        else {
            v.setBackgroundColor(Color.BLUE);
        }
        return v;
    }
}
