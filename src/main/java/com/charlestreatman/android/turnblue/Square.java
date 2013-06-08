package com.charlestreatman.android.turnblue;

/**
 * Created by ctreatma on 6/8/13.
 */
public class Square {
    boolean isOn;

    public Square() {
        this(false);
    }

    public Square(boolean isOn) {
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    void toggle() {
        this.isOn = !this.isOn;
    }
}
