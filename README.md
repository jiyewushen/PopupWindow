# PopupWindow
![image](https://github.com/jiyewushen/PopupWindow/blob/master/screen.gif)

#### setFocusable(boolean focusable)、setOutsideTouchable(boolean touchable)和 setTouchable(boolean touchable)区别和联系
* setFocusable(boolean focusable)
* setOutsideTouchable(boolean touchable)
* setTouchable(boolean touchable)











#### 显示方式
 * showAtLocation(View parent, int gravity, int x, int y)
  * parent 为了获取窗口令牌的View对象
  * gravity 控制x,y的原点的位置（例如Gravity.NO_GRAVITY:Gravity.LEFT | Gravity.TOP,相当于将屏幕左上角看做原点，与之相反的方向右,下分别为x,y的增加方向）
 * showAtLocation(IBinder token, int gravity, int x, int y)

 * showAsDropDown(View anchor)
 * showAsDropDown(View anchor, int xoff, int yoff)
 * showAsDropDown(View anchor, int xoff, int yoff, int gravity)



#### 弹出以及退出时的动画设置
* 使用setAnimationStyle(R.style.xxx)改变动画效果;
 * 默认实现（二选一）
   com.android.internal.R.style.Animation_DropDownUp
  ```
  <style name="Animation.DropDownUp">
        <item name="windowEnterAnimation">@anim/grow_fade_in_from_bottom</item>
        <item name="windowExitAnimation">@anim/shrink_fade_out_from_bottom</item>
    </style>
  ```
   com.android.internal.R.style.Animation_DropDownDown
  ```
    <style name="Animation.DropDownDown">
          <item name="windowEnterAnimation">@anim/grow_fade_in</item>
          <item name="windowExitAnimation">@anim/shrink_fade_out</item>
      </style>
  ```
  * 自由发挥 对anim文件下自定义视图动画的XML中进行设计，并在styles.xml中进行声明


  #### PopupWindow弹出后窗口的透明度
  ```java
  private void changeWindowAlpha(float alpha) {
         WindowManager.LayoutParams lp = getWindow().getAttributes();
         lp.alpha = alpha;
         getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
         getWindow().setAttributes(lp);
     }
  ```
