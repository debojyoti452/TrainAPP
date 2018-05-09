package dev452.app.trainapp.libs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Base TextView
 */

public abstract class HTextView extends android.support.v7.widget.AppCompatTextView {
    public HTextView(Context context) {
        this(context, null);
    }

    public HTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void setAnimationListener(AnimationListener listener);

    public abstract void setProgress(float progress);

    public abstract void animateText(CharSequence text);
}
