package com.charlestreatman.android.turnblue;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class TurnBlue extends Activity implements AdapterView.OnItemClickListener {
    private GameAdapter adapter;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        adapter.click(i);
        updateReadouts();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        TurnBlueBoardView board = (TurnBlueBoardView) findViewById(R.id.board);
        adapter = new GameAdapter(board.getContext());
        board.setAdapter(adapter);
        board.setNumColumns(adapter.getBoardSize());
        board.setOnItemClickListener(this);

        updateReadouts();
    }

    private void updateReadouts() {
        TextView level = (TextView) findViewById(R.id.level);
        level.setText("Level " + adapter.getLevel());

        TextView clicks = (TextView) findViewById(R.id.clicks);
        clicks.setText("Clicks remaining: " + adapter.getClicksRemaining());
    }

}
