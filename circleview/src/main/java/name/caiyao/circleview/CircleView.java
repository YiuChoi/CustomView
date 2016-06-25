package name.caiyao.circleview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    private Paint circlePaint,mArcPaint,mTextPaint;

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
        mRectF = new RectF((float) (getMeasuredWidth() * 0.1), (float) (getMeasuredWidth() * 0.1), (float) (getMeasuredWidth() * 0.9), (float) (getMeasuredWidth() * 0.9));
        circlePaint = new Paint();
        circlePaint.setColor(Color.YELLOW);
        mArcPaint = new Paint();
        mArcPaint.setColor(Color.BLUE);
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(mCircleXY,mCircleXY,mRadius,circlePaint);
        canvas.drawArc(mRectF,270,25,false,mArcPaint);
        canvas.drawText("sdsd",0,4,mCircleXY,mCircleXY+(4/4),mTextPaint);
        super.onDraw(canvas);
    }
}
