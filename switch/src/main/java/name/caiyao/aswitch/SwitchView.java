package name.caiyao.aswitch;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 蔡小木 on 2016/12/29 0029.
 */

public class SwitchView extends View {

    private Bitmap background;

    private Bitmap forground;

    public SwitchView(Context context) {
        super(context);
    }

    public SwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SwitchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setBackground(@DrawableRes int backgroundRes) {
        this.background = BitmapFactory.decodeResource(getResources(),backgroundRes);
    }

    public void setForground(@DrawableRes int forgroundRes) {
        this.forground = BitmapFactory.decodeResource(getResources(),forgroundRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
