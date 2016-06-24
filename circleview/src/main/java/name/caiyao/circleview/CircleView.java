package name.caiyao.circleview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 蔡小木 on 2016/6/13 0013.
 */

public class CircleView extends View {

    private int mCircleXY;
    private float mRadius;
    private RectF mRectF;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void init() {
        mRadius = (float) (getMeasuredWidth() * 0.5 / 2);
        mCircleXY = getMeasuredWidth() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRectF = new RectF((float) (getMeasuredWidth() * 0.1), (float) (getMeasuredWidth() * 0.1), (float) (getMeasuredWidth() * 0.9), (float) (getMeasuredWidth() * 0.9));
    }
}
