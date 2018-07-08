package github.goldenstrawberry.materialdesigndemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ryan.Hoo
 * Date : 2018/7/8.10:37
 * Motto : Working hard to write logically clear code
 * 目的是当 NestedScrollView 内容滑动时，
 * MyBehavior 规定关联的 ImageView 对象进行相应的位移，
 * 这主要是在 Y 轴方向上。
 *
 * tips:
 *
 * 1、确定 CoordinatorLayout 中 View 与 View 之间的依赖关系，通过 layoutDependsOn() 方法，返回值为 true 则依赖，否则不依赖。
 * 2、当一个被依赖项 dependency 尺寸或者位置发生变化时，依赖方会通过 Byhavior 获取到，然后在 onDependentViewChanged 中处理。
 *    如果在这个方法中 child 尺寸或者位置发生了变化，则需要 return true。
 * 3、当 Behavior 中的 View 准备响应嵌套滑动时，它不需要通过 layoutDependsOn() 来进行依赖绑定(感觉绑定与不绑定都行)。
 *    只需要在 onStartNestedScroll() 方法中通过返回值告知 ViewParent，它是否对嵌套滑动感兴趣,以及什么方向上的嵌套滑动。
 *    返回值为 true 时，后续的滑动事件才能被响应。
 * 4、嵌套滑动包括滑动(scroll) 和 快速滑动(fling) 两种情况。开发者根据实际情况运用就好了。
 * 5、Behavior 通过 3 种方式绑定：1. xml 布局文件。2. 代码设置 layoutparam。3. 自定义 View 的注解。
 */

public class MyBehavior2 extends CoordinatorLayout.Behavior<View> {

    public static final String TAG = MyBehavior2.class.getSimpleName();

    // 必须重写带双参的构造器，因为从xml反射需要调用。
    public MyBehavior2(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof DragTextView;
//        return false ;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        int bottom = dependency.getBottom();
        child.setX(dependency.getLeft());
        child.setY(bottom+10);
        return true;
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, View child, View dependency) {
        super.onDependentViewRemoved(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
//        Log.d(TAG, "onStartNestedScroll: ");
        // child 是 ImageView 类型，并且滑动的方向是 Y 轴时才响应
        return child instanceof ImageView && axes == View.SCROLL_AXIS_VERTICAL;
    }

    // 针对滑动事件产生的位移对 child 进行操作
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Log.d(TAG, "onNestedPreScroll: "+dx+" dy:"+dy+" consumed :"+consumed[1]);
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        ViewCompat.offsetTopAndBottom(child,dy);
    }
    /*两个方法的不同在于顺序的先(onNestedPreScroll)后(onNestedScroll)，o
      nNestedPreScroll() 在滑动事件准备作用的时候先行调用，注意是准备作用，
      然后把已经消耗过的距离传递给 consumed 这个数组当中。而 onNestedScroll() 是滑动事件作用时调用的。
      它的参数包括位移信息，以及已经在 onNestedPreScroll() 消耗过的位移数值。
      我们一般实现 onNestedPreScroll() 方法就好了
      */
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        Log.d(TAG, "onNestedScroll: "+dxConsumed+" dy:"+dyConsumed);
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
    }

    @Override
    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY) {
//        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
//        return  true ;child 自己处理了这次 fling 意图，那么 NestedScrollView 反而操作不了这个动作
        if ( velocityY > 0 ) {
            child.animate().scaleX(2.0f).scaleY(2.0f).setDuration(2000).start();
        } else {
            child.animate().scaleX(1.0f).scaleY(1.0f).setDuration(2000).start();
        }

        return false;
    }

    @Override
    public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY, boolean consumed) {
//        Log.d(TAG, "onNestedFling: ");
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }


}
