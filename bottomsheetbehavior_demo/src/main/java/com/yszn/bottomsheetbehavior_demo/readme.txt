    xml中的属性                         代码中设置
//折叠的高度
app:behavior_peekHeight="10dp"          setPeekHeight
//是否可以隐藏
app:behavior_hideable="true"            setHideable
//是否跳过折叠状态
app:behavior_skipCollapsed="true"       setSkipCollapsed

------------------------------图片资源放哪里-------------------------------
drawable/
For bitmap files (PNG, JPEG, or GIF), 9-Patch image files, and XML files
that describe Drawable shapes or Drawable objects that contain
multiple states (normal, pressed, or focused). See the Drawable resource type.

mipmap/
For app launcher icons. The Android system retains the resources in this folder
(and density-specific folders such as mipmap-xxxhdpi) regardless of the screen resolution of
the device where your app is installed. This behavior allows launcher apps to pick
the best resolution icon for your app to display on the home screen.
也就是说一般将APP的icon放在minmap文件夹下，其他图片资源放在drawable文件夹下。