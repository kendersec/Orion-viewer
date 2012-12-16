package universe.constellation.orion.viewer;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;

@TargetApi(Build.VERSION_CODES.FROYO)
public class OrionGestureDetector {
    
    private static final String TAG = OrionGestureDetector.class.getSimpleName();
    
    private static ScaleGestureDetector mScaleGestureDetector;
    private Controller mController;

    public OrionGestureDetector(Context context) {
        mScaleGestureDetector = new ScaleGestureDetector(context, new OnScaleGestureListener() {
            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {
            }
            
            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                Log.d(TAG, "scaleBegin: zoom ongoing, scale: " + detector.getScaleFactor());
                return true;
            }
            
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                Log.d(TAG, "onScale: zoom ongoing, scale: " + detector.getScaleFactor());
                if (mController != null) {
                    mController.changeZoom((int)(mController.getCurrentPageZoom() * 10000 * detector.getScaleFactor()));
                }
                return true;
            }
        });
    }
    
    public void setController(Controller controller) {
        mController = controller;
    }
    
    public boolean onTouchEvent(MotionEvent me) {
        mScaleGestureDetector.onTouchEvent(me);
        
        return mScaleGestureDetector.isInProgress();
    }
}
