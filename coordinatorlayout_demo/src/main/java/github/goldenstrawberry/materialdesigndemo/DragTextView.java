package github.goldenstrawberry.materialdesigndemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by Ryan.Hoo
 * Date : 2018/7/8.10:48
 * Motto : Working hard to write logically clear code
 *
 * 拖移View时的抖动问题:
 * https://blog.csdn.net/u010335298/article/details/51891653
 * 我的理解 是由于getX()和getY()在第一次是getLeft和getTop，
 * 之后加上了TranslationX和TranslationY导致的
 * 使用getRawX()、getRawY()是pointer相对于整个screen的绝对坐标。
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
        init(context);
    }

    private void init(Context context) {
        // 最小滑动距离
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = event.getRawX();
                downY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int distanceX = (int) (event.getRawX() - downX);
                int distanceY = (int) (event.getRawY() - downY);
                if(Math.abs(distanceX) > touchSlop || Math.abs(distanceY) > touchSlop){
                    ViewCompat.offsetTopAndBottom(this,distanceY);
                    ViewCompat.offsetLeftAndRight(this,distanceX);
                    downX = event.getRawX() ;
                    downY = event.getRawY() ;
                }
                break;
            case MotionEvent.ACTION_UP:
                downX = event.getRawX();
                downY = event.getRawY();
                break;
        }
        return true;
    }
}
