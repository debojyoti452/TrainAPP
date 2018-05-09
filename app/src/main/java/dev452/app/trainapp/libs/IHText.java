package dev452.app.trainapp.libs;

import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * interface used in HTextView
 */
public interface IHText {
    void init(HTextView hTextView, AttributeSet attrs, int defStyle);

    void animateText(CharSequence text);

    void onDraw(Canvas canvas);

    void setAnimationListener(AnimationListener listener);
}
