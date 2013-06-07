package com.charlestreatman.android.turnblue;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;

public class TurnBlue extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        TurnBlueBoardView board = new TurnBlueBoardView(this, Math.min(metrics.heightPixels, metrics.widthPixels));

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        board.setGravity(Gravity.CENTER);
        
        this.addContentView(board,lp);
    }

}
