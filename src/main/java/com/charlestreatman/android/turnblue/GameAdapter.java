package com.charlestreatman.android.turnblue;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Toast;

/**
 * Created by ctreatma on 6/8/13.
 */
public class GameAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private int level, boardSize, lossDifferential;
    private Game game;
    private Context context;

    public GameAdapter(Context context) {
        level = 1;
        boardSize = Integer.valueOf(context.getString(R.string.board_size));
        lossDifferential = Integer.valueOf(context.getString(R.string.loss_differential));
        createGame();
        this.context = context;
    }

    public int getBoardSize() {
        return boardSize;
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        game.click(i);
        notifyDataSetChanged();
        if (game.isVictory()) {
            level++;
            Toast.makeText(context, "You made it to level " + level + "!", Toast.LENGTH_SHORT).show();
            createGame();
        }
        else if (game.isOver()) {
            level = 1;
            Toast.makeText(context, "You lost!", Toast.LENGTH_SHORT).show();
            createGame();
        }
        notifyDataSetChanged();
    }

    private void createGame() {
        this.game = new Game(boardSize, level, lossDifferential);
    }
}
