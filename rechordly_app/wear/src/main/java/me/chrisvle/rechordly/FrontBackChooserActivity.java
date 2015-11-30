package me.chrisvle.rechordly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class FrontBackChooserActivity extends Activity {

    private ImageButton mImageButton;
    private GestureDetector mDetector;
    private static final String DEBUG_TAG = "Gestures";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_back_chooser);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mImageButton = (ImageButton) stub.findViewById(R.id.imageButton);
                mImageButton.setImageResource(R.drawable.crop_f_b);
                mImageButton.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (mDetector.onTouchEvent(event)) {
                            Log.d("Event: ", "onTouchEvent Fired!");
//                            finish();
                            return true;
                        }
                        return false;
                    }
                });
            }
        });

        // Configure a gesture detector
        mDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public void onLongPress(MotionEvent event) {
//                mDismissOverlay.show();
                Log.d(DEBUG_TAG, " onLongPress: " + event.toString());
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                Log.d("Event: ", "onSingleTapEvent Fired!");
                Intent intent = new Intent(getBaseContext(), EffectsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                                    float distanceY) {
                Log.d(DEBUG_TAG, "onScroll: Distance: " + String.valueOf(distanceX) + ", " + String.valueOf(distanceY));
//                if (distanceX > 5.0) {
//                    Log.d("Event: ", "onScrollEvent Fired!");
//                    Intent intent = new Intent(getBaseContext(), doneActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                    return true;
//                }
                if (distanceX < -5.0) {
                    Log.d("Event: ", "onScrollEvent Fired!");
                    Intent intent = new Intent(getBaseContext(), VolumeChooserActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    return true;
                }
                Log.d(DEBUG_TAG, " onScroll: " + e1.toString()+e2.toString());
                return false;
            }
        });
    }
}
