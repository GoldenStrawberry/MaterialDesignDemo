/----------------------------------------------AppBarLayout--------------------------------------------------------------------------/
AppBarLayout 配合 NestedScrollView 一起使用 :

Scroll:  滑动状态-向上滑动：AppBarLayout里的内容先向上滑动，滑动完成后NestedScrollView里的内容再向上滑动
         滑动状态-向下滑动：NestedScrollView里的内容先向下滑动，滑动完成后AppBarLayout里的内容再向下滑动

scroll|enterAlways : 滑动状态-向下滑动 : AppBarLayout里的内容先向上滑动，滑动完成后NestedScrollView里的内容再向上滑动
                     滑动状态-向下滑动：AppBarLayout里的内容和NestedScrollView一起向下滑动，滑动完成后NestedScrollView里的内容再向下滑动

scroll|enterAlways|enterAlwaysCollapsed :  滑动状态-向上滑动：AppBarLayout里的内容先向上滑动，滑动完成后NestedScrollView里的内容再向上滑动
                                           滑动状态-向下滑动：AppBarLayout里的内容和NestedScrollView一起向下滑动到minHeight，然后NestedScrollView的内容向下滑动，
                                           滑动完成后AppBarLayout里的内容再向下滑动。

scroll|exitUntilCollapsed :  滑动状态-向上滑动：AppBarLayout里的内容先向上滑动直到压缩到minHeight，滑动完成后NestedScrollView里的内容再向上滑动
 滑动状态-向下滑动：NestedScrollView里的内容先向下滑动，滑动完成后AppBarLayout里的内容再向下滑动

scroll|snap : 代表一种吸附的行为，以AppBarLayout内容的一半为分界点，向上吸附或者向下吸附
/---------------------------------------------CollapsingToolbarLayout----------------------------------------------------------------------/
CollapsingToolbarLayout 出现的目的只是为了增强 Toolbar

1、Collapsing title 可折叠的标题:
使用：此时去掉了ToolBar里的android:minHeight="" ，将AppBarLayout里的高度固定android:layout_height="300dp" ; app:layout_scrollFlags属性也要放在CollapsingToolbarLayout里。
      CollapsingToolbarLayout 与 Toolbar 都定义了 title ,显然，CollapsingToolbarLayout 中的 title 覆盖了 Toolbar 中的 title。
需要注意的是 Collapsing title 有两种状态，分别是 展开(Expanded) 和 折叠(Collapsed)。
setTitle(CharSequence title)
.....
setCollapsedTitleTextColor(int color)
setCollapsedTitleTypeface(Typeface typeface)
setExpandedTitleColor(int color)
......
2、Content scrim 内容纱布
指定 Contetn scrim 后，CollapsingToolbarLayout 会在折叠状态显示指定的颜色或者是图片，它就像是一块纱布一样遮住 title 下面的内容，所以被称为内容纱布。
注意我的措辞，我说的是 Content scrim 会遮住 title 下方的内容部分。如果一个 CollapsingToolbarLayout 中只有 Toolbar 的话，那么它就不起作用。
CollapsingToolbarLayout 本质上是一个 FrameLayout，所以需要在 Toolbar 的前面位置加入其它的 View 作为内容，Content scrim 才会起作用。

使用：在CollapsingToolbarLayout添加属性app:contentScrim="#000"

Content scrim 除了在 xml 属性中配置外，还可以通过代码设置
void setContentScrimColor (int color)
void setContentScrimResource (int resId)
void setContentScrim (Drawable drawable)

3、Status bar scrim 状态栏纱布
作用的对象却是在系统的 Statusbar 的位置。它还有一个特别的地方就是，它只作用在 SDK 5.0 版本之后，并且需要正确配置。
使用：包裹 CollapsingToolbarLayout 的 AppbarLayout 需要设置 fitsSystemWindows 为 true。同时CollapsingToolbarLayout
      设置属性app:statusBarScrim="#f00"  (!自己在P20上测试没效果！在向下fling时会出现，停止后消失)
 除了在 xml 属性中配置外，还可以通过代码设置。

4、Parallax scrolling children 子 View 的视差滚动行为
CollapsingToolbarLayout 可以控制的子 View 滚动模式有 3 种：
    none 默认，无任何效果
    Parallax 视差滚动
    pin 固定某个 View

它通过 xml 属性 app:layout_collapseMode 来设置。需要注意的是，
这个属性作用对象是 CollapsingToolbarLayout 中的子 View 并不是 CollapsingToolbarLayout。

4.1、如何理解视差？就是滚动的速度不同，造成的视觉差异效果。也就是说 CollapsingToolbarLayout 中有的 view 滚动的快一些，其它的滚动的慢一些。
它滚动的快慢受 Parallax multiplier 这个因子的影响，默认值为 DEFAULT_PARALLAX_MULTIPLIER。也就是 0.5f。也就是正常速度的一半。
可以通过 setParallaxMultiplier(float) 方法来设置滚动的速度因子

4.2、Pinned position children 子类的位置固定行为
将 CollapsingToolbarLayout 中某个子 View 固定，无论是否存在滚动事件，只要设置 app:layout_collapseMode=”pin”

5、如果你想监听 AppBarLayout 中的滑动位移信息，那么添加相应的监听器就好了。
OnOffsetChangedListener
这是 AppBarLayout 定义的监听器。
void onOffsetChanged (AppBarLayout appBarLayout, int verticalOffset)
verticalOffset 是 AppBarLayout 相对于完全展开时没有滑动的距离。它在初始位置为 0，其它时候都为负数。
它绝对值的最大值为 AppBarLayout 的 TotalScollRange。