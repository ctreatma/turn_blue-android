package com.charlestreatman.android.turnblue;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

// TODO:  Handle getting a parcelable that says what the level was, how many clicks, and what the colors were.
public class TurnBlueBoardView extends GridView {
    public TurnBlueBoardView(Context context) {
        this(context, null);
    }

    public TurnBlueBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        GameAdapter adapter = new GameAdapter(context);
        setAdapter(adapter);
        this.setNumColumns(adapter.getBoardSize());
        this.setOnItemClickListener(adapter);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = Math.min(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(size, size);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        int size = Math.min(w, h);
        super.onSizeChanged(size, size, oldw, oldh);
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        for (int i = 0; i < getChildCount(); ++i) {
            View v = getChildAt(i);
            int size = getSquareSize();
            v.setLayoutParams(new LayoutParams(size, size));
        }
    }

    private int getSquareSize() {
        int height = getHeight();
        int width = getWidth();
        GameAdapter adapter = (GameAdapter) getAdapter();
        return (Math.min(height, width) / adapter.getBoardSize()) - 2;
    }
}
