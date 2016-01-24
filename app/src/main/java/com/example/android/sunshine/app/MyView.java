package com.example.android.sunshine.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;

/**
 * Created by Kata on 31.08.2015.
 */
public class MyView extends View {
    private final String VIEW_WIDTH = "width";
    private final String VIEW_HEIGHT = "height";
    private Paint mPaint;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defaultStyle) {
        super (context, attrs, defaultStyle);
        init();
    }

    private void init() {
        AccessibilityManager accessibilityManager =
                (AccessibilityManager) this.getContext().getSystemService(Context.ACCESSIBILITY_SERVICE);
        if (accessibilityManager.isEnabled()) {
            sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);
        }
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure (int wMeasureSpec, int hMeasureSpec) {
        setMeasuredDimension(mySize(wMeasureSpec, VIEW_WIDTH), mySize(hMeasureSpec, VIEW_HEIGHT));
    }

    private int mySize(int measureSpec, String dim) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int mySize = 0;

        if (specMode == MeasureSpec.EXACTLY) {
            mySize = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            switch (dim) {
                case VIEW_HEIGHT:
                    mySize = 100;
                    break;
                case VIEW_WIDTH:
                    mySize = 200;
                    break;
            }
        }
        return mySize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas. drawColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(100 / 2, 100 / 2, 100 / 2, mPaint);
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        super.dispatchPopulateAccessibilityEvent(event);
        // Call the super implementation to populate its text to the event, which
        // calls onPopulateAccessibilityEvent() on API Level 14 and up.

        // In case this is running on a API revision earlier that 14, check
        // the text content of the event and add an appropriate text
        // description for this custom view:
        CharSequence text = "event text";
        if (!TextUtils.isEmpty(text)) {
            event.getText().add(text);
        }
        return true;
    }
}

