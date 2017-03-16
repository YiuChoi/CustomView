package name.caiyao.slidemenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by xiaomu on 2017/3/15.
 */

public class CustomSlidingMenu extends ViewGroup {

  private int screenWidth;
  private int screenHeight;
  private int menuRightPadding;
  private int menuWidth;
  private int contentWidth;

  private ViewGroup menu;
  private ViewGroup content;

  private int lastX;
  private int lastY;

  public CustomSlidingMenu(Context context) {
    this(context, null);
  }

  public CustomSlidingMenu(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  /**
   * 初始化.
   *
   * @param context      Context
   * @param attrs        attrs
   * @param defStyleAttr def
   */
  public CustomSlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    DisplayMetrics metrics = new DisplayMetrics();
    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    windowManager.getDefaultDisplay().getMetrics(metrics);
    screenHeight = metrics.heightPixels;
    screenWidth = metrics.widthPixels;
    menuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
        context.getResources().getDisplayMetrics());
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    menu = (ViewGroup) getChildAt(0);
    content = (ViewGroup) getChildAt(1);
    menuWidth = menu.getLayoutParams().width = screenWidth - menuRightPadding;
    contentWidth = content.getLayoutParams().width = screenWidth;
    measureChild(menu, widthMeasureSpec, heightMeasureSpec);
    measureChild(content, widthMeasureSpec, heightMeasureSpec);
    setMeasuredDimension(menuWidth + contentWidth, screenHeight);
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    menu.layout(-menuWidth, 0, 0, screenHeight);
    content.layout(0, 0, screenWidth, screenHeight);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();
    switch (action) {
      case MotionEvent.ACTION_DOWN:
        lastX = (int) event.getX();
        lastY = (int) event.getY();
        break;
      case MotionEvent.ACTION_MOVE:
        int currentX = (int) event.getX();
        int currentY = (int) event.getY();
        int dx = currentX - lastX;
        if (dx < 0) {
          //向左滑动
          if (getScrollX() + Math.abs(dx) >= 0) {
            scrollTo(0, 0);
          } else {
            scrollTo(-dx, 0);
          }
        } else {
          if (getScrollX() - dx <= menuWidth) {
            scrollTo(-menuWidth, 0);
          } else {
            scrollBy(-dx, 0);
          }
        }
        lastX = currentX;
        lastY = currentY;
        break;
      default:
        break;

    }
    return true;
  }
}
