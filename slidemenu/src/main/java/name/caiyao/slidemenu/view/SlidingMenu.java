package name.caiyao.slidemenu.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import name.caiyao.slidemenu.R;

/**
 * Created by xiaomu on 2017/3/14.
 */

public class SlidingMenu extends HorizontalScrollView {
    private LinearLayout mWrapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;
    private int mMenuWidth;
    private int mScreenWidth;
    private int mMenuRightPadding = 50;

    private boolean once = false;

    private boolean isOpen;

    public SlidingMenu(Context context) {
        this(context, null);
    }

    /**
     * 未使用自定义属性使使用
     *
     * @param context
     * @param attrs
     */
    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    /**
     * 当使用了自定义属性时使用
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取我们定义的属性
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlidingMenu, defStyleAttr, 0);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.SlidingMenu_rightPadding:
                    mMenuRightPadding = array.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()));
                    break;
                default:
                    break;
            }
        }
        array.recycle();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        mScreenWidth = displayMetrics.widthPixels;
        //把DP转换为PX
    }

    /**
     * 设置子View的宽和高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!once) {
            mWrapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mWrapper.getChildAt(0);
            mContent = (ViewGroup) mWrapper.getChildAt(1);
            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            mContent.getLayoutParams().width = mScreenWidth;
            once = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 通过设置偏移量，将menu隐藏
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed)
            this.scrollTo(mMenuWidth, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                //隐藏在左边的宽度
                int scrollX = getScrollX();
                if (scrollX >= mMenuWidth / 2) {
                    this.smoothScrollTo(mMenuWidth, 0);
                    isOpen = false;
                } else {
                    smoothScrollTo(0, 0);
                    isOpen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 打开菜单
     */
    public void openMenu() {
        if (isOpen) return;
        this.smoothScrollTo(0, 0);
        isOpen = true;
    }

    public void closeMenu() {
        if (!isOpen) return;
        this.smoothScrollTo(mMenuWidth, 0);
        isOpen = false;
    }

    /**
     * 切换菜单
     */
    public void toggle() {
        if (isOpen)
            closeMenu();
        else
            openMenu();
    }


    /**
     * 滚动发生时
     *
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //调用属性动画，设TranslationX
        float scale = l * 1.0f / mMenuWidth;//1->0
        mMenu.setTranslationX(mMenuWidth * scale * 0.7f);

        float rightScale = 0.7f + 0.3f * scale;
        //设置content的缩放中心点
        mContent.setPivotX(0);
        mContent.setPivotY(mContent.getHeight() / 2);
        mContent.setScaleX(rightScale);
        mContent.setScaleY(rightScale);

        float leftScale = 1.0f - scale * 0.3f;
        float leftAlpha = 0.6f + 0.4f * (1 - scale);
        mMenu.setPivotX(0);
        mMenu.setPivotY(mMenu.getHeight() / 2);
        mMenu.setScaleX(leftScale);
        mMenu.setScaleY(leftScale);
        mMenu.setAlpha(leftAlpha);

    }
}
