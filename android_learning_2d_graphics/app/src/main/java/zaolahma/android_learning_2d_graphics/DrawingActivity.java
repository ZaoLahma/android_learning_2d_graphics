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
    private RectF gravBounds;
    private int screenHeight;
    private int screenWidth;
    private float figRadius;
    private float figX;
    private float figY;
    private float deltaX;
    private float deltaY;
    private String text;
    private boolean userInteracting;
    private float arrowX;
    private float arrowY;

    public DrawingActivity(Context context) {
        super(context);
        paint = new Paint();
        paint.setTypeface(Typeface.MONOSPACE);
        paint.setTextSize(40);
        figBounds = new RectF();
        gravBounds = new RectF();
        figRadius = 50;
        screenHeight = 0;
        screenWidth = 0;
        figX = 50;
        figY = 50;
        deltaX = 0;
        deltaY = 0;
        text = new String("2D graphics in Android");
        this.setFocusableInTouchMode(true);
        userInteracting = false;
        arrowX = 0;
        arrowY = 0;
    }

    protected void onDraw(Canvas canvas) {
        update();

        figBounds.set(figX - figRadius, figY - figRadius,
                      figX + figRadius, figY + figRadius);

        paint.setColor(Color.YELLOW);

        canvas.drawOval(figBounds, paint);

        gravBounds.set(screenWidth / 2 - 50, screenHeight / 2 - 50,
                       screenWidth / 2 + 50, screenHeight / 2 + 50);

        paint.setColor(Color.RED);

        canvas.drawOval(gravBounds, paint);

        paint.setColor(Color.BLACK);
        canvas.drawText(text.toString(), 10, 30, paint);

        if(userInteracting) {
            paint.setColor(Color.GREEN);
            canvas.drawLine(figX, figY, arrowX, arrowY, paint);
        }

        try {
            Thread.sleep(30);
        } catch(InterruptedException e) {}

        invalidate();
    }

    void update() {
        if(userInteracting) {
            return;
        }
        double angle = Math.atan2((figY - screenHeight / 2), (figX - screenWidth / 2));

        deltaX -= (float) (Math.cos(angle) * 9.82);
        deltaY -= (float) (Math.sin(angle) * 9.82);

        figY += deltaY / 5;
        figX += deltaX / 5;
    }

    public boolean onTouchEvent (MotionEvent event) {
        deltaX = 0;
        deltaY = 0;

        userInteracting = true;

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                figX = event.getX();
                figY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float distX = event.getX() - figX;
                float distY = event.getY() - figY;

                arrowX = figX - distX;
                arrowY = figY - distY;
                break;
            case MotionEvent.ACTION_UP:
                deltaX += (figX - event.getX());
                deltaY += (figY - event.getY());
                userInteracting = false;
                break;
        }

        //System.out.println("onTouchEvent called (" + figX + ", " + figY + ")");

        return true;
    }

    public void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        screenWidth = width;
        screenHeight = height;
    }
}
