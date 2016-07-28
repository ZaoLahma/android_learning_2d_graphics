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
        text = new String("2D graphics in Android");
        this.setFocusableInTouchMode(true);
    }

    protected void onDraw(Canvas canvas) {
        figBounds.set(figX - figRadius, figY - figRadius,
                      figX + figRadius, figY + figRadius);

        paint.setColor(Color.YELLOW);

        canvas.drawOval(figBounds, paint);

        paint.setColor(Color.BLACK);
        canvas.drawText(text.toString(), 10, 30, paint);

        try {
            Thread.sleep(30);
        } catch(InterruptedException e) {}

        invalidate();
    }

    public boolean onTouchEvent (MotionEvent event) {
        figX = event.getX();
        figY = event.getY();

        System.out.println("onTouchEvent called (" + figX + ", " + figY + ")");

        return true;
    }

    public void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        screenWidth = width;
        screenHeight = height;
    }
}
