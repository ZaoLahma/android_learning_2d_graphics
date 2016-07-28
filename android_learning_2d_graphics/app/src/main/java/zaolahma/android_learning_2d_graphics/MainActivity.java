package zaolahma.android_learning_2d_graphics;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View drawingActivity = new DrawingActivity(this);
        setContentView(drawingActivity);
        drawingActivity.setBackgroundColor(Color.GRAY);
    }
}
