package github.goldenstrawberry.materialdesigndemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * 参考:
 * https://blog.csdn.net/briblue/article/details/73076458
 * 对CoordinatorLayout解释详细透彻，叹为观止!
 */

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2); //R.layout.activity_main
        /*button = (Button) findViewById(R.id.button);
        //如果一个 View 符合嵌套滑动的条件。也就是通过调用 setNestedScrollingEnabled(true)
        *//*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button.setNestedScrollingEnabled(true);
        }*//*
        //为了兼容我们可用
        ViewCompat.setNestedScrollingEnabled(button,true);
        //然后调用它的 startNestedScroll() 方法，它理论上是应该可以产生嵌套滑动事件的
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //button.startNestedScroll(View.SCROLL_AXIS_HORIZONTAL);
                    // 兼容
                    ViewCompat.startNestedScroll(button,View.SCROLL_AXIS_HORIZONTAL);
                }
            }
        });*/
        /**
         * Behavior 中 onStartNestedScroll()
         * --> 它是在 CoordinatorLayout 中被调用 ---> 最上层 由View 和 ViewParentCompat调用  -->要保证ViewParentCompat是NestedScrollingParent 对象。
         * --> CoordinatorLayout 获取子 View 的 Behavior,然后调用 Behavior 的 onStartNestedScroll() 方法
         *
         * 如果在 5.0 的系统版本以下，如果一个 View 想发起嵌套滑动事件，你得保证这个 View 实现了 NestedScrollingChild 接口。
         * 如果在 5.0 的系统版本以上，我们要 setNestedScrollingEnabled(true)
         * NestedScrollingChild 接口有 4 个实现类：NavigationMenuView、NestedScrollView、RecyclerView、SwipleRefreshLayout。
         * 所以 NestedScrollView 下的子View不用在去setNestedScrollingEnabled 即可实现嵌套滑动
         *
         *
         * CoordinatorLayout 能处理嵌套滑动 --》因为它本身是一个NestedScrollingParent
         * NestedScrollView 它有两种身份，它同时实现了 NestedScrollingChild 和 NestedScrollParent 接口
         *
         *  CoordinatorLayout 的锚定

         LayoutParam 中有个 mAnchorView，Anchor 是锚点的意思，比如 View A 锚定了 View B，
         那么 View A 的 mAnchorView 就是 View B，布局的时候 View A 将参考 View B 的坐标。
         并且 layoutDirection 是参考的方向。它们都可以通过 xml 配置。
         app:layout_anchor="@id/btn_coord"
         app:layout_anchorGravity="bottom"
         */
    }
}
