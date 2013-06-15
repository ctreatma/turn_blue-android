package com.charlestreatman.android.turnblue;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.view.View;

public class TurnBlueBoardView extends GridView {
    public TurnBlueBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
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
        int height = getHeight() - 2;
        int width = getWidth() - 2;
        return (Math.min(height, width) / getNumColumns()) - 2;
    }
}
