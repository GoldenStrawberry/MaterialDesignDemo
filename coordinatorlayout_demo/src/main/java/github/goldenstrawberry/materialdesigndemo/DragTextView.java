package github.goldenstrawberry.materialdesigndemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;

/**
 * Created by Ryan.Hoo
 * Date : 2018/7/8.10:48
 * Motto : Working hard to write logically clear code
 */

public class DragTextView extends AppCompatTextView {

    private float downX;
    private float downY;
    private int touchSlop;

    public DragTextView(Context context) {
        this(context,null);
    }

    public DragTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DragTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 最小滑动距离
        touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int distanceX = (int) (event.getX() - downX);
                int distanceY = (int) (event.getY() - downY);
                if(Math.abs(distanceX) > touchSlop || Math.abs(distanceY) > touchSlop){
                    ViewCompat.offsetTopAndBottom(this,distanceY);
                    ViewCompat.offsetLeftAndRight(this,distanceX);
                    downX = event.getX() ;
                    downY = event.getY() ;
                }
                break;
            case MotionEvent.ACTION_UP:
                downX = event.getX();
                downY = event.getY();
                break;
        }
        return true;
    }
}
