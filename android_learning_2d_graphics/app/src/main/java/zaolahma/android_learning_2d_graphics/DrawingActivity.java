package zaolahma.android_learning_2d_graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

public class DrawingActivity extends View {
    private Paint paint;
    private RectF figBounds;
    private int screenHeight;
    private int screenWidth;
    private float figRadius;
    private float figX;
    private float figY;
    private float deltaX;
    private float deltaY;
    private String text;

    public DrawingActivity(Context context) {
        super(context);
        paint = new Paint();
        paint.setTypeface(Typeface.MONOSPACE);
        paint.setTextSize(40);
        figBounds = new RectF();
        figRadius = 50;
        screenHeight = 0;
        screenWidth = 0;
        figX = 50;
        figY = 50;
        deltaX = 0;
        deltaY = 0;
        text = new String("2D graphics in Android");
        this.setFocusableInTouchMode(true);
    }

    protected void onDraw(Canvas canvas) {
        update();

        figBounds.set(figX - figRadius, figY - figRadius,
                      figX + figRadius, figY + figRadius);

        paint.setColor(Color.YELLOW);

        canvas.drawOval(figBounds, paint);

        paint.setColor(Color.BLACK);
        canvas.drawText(text.toString(), 10, 30, paint);

        try {
            Thread.sleep(100);
        } catch(InterruptedException e) {}

        invalidate();
    }

    void update() {
        figX += deltaX;

        if(figX - figRadius < 1 || figX + figRadius > screenWidth - 1) {
            deltaX = -deltaX;
        }

        float acceleration = 9.82f;

        deltaY += acceleration * 0.1;

        if(figY + figRadius > screenHeight - 1) {
            deltaX = 0;
            deltaY = 0;
        }
        figY += deltaY;
    }

    public boolean onTouchEvent (MotionEvent event) {
        deltaX = 0;
        deltaY = 0;

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                figX = event.getX();
                figY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                deltaX -= (figX - event.getX()) / 10;
                break;
        }

        System.out.println("onTouchEvent called (" + figX + ", " + figY + ")");

        return true;
    }

    public void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        screenWidth = width;
        screenHeight = height;
    }
}
