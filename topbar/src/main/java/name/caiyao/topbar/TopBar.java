package name.caiyao.topbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by 蔡小木 on 2016/6/13 0013.
 */

public class TopBar extends RelativeLayout {

    private int mTitleTextColor;
    private Drawable mLeftBackground, mRightBackground;
    private String mLeftText, mRightText, mTitle;
    private int mTitleTextSize;

    private Button mLeftButton, mRightButton;
    private TextView mTitleTextView;

    private TopBarClickListener mTopBarClickListener;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TopBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        int leftTextColor = typedArray.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = typedArray.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = typedArray.getString(R.styleable.TopBar_leftText);
        int rightTextColor = typedArray.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = typedArray.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = typedArray.getString(R.styleable.TopBar_rightText);
        mTitleTextSize = (int) typedArray.getDimension(R.styleable.TopBar_topBarTitleTextSize, 10);
        mTitleTextColor = typedArray.getColor(R.styleable.TopBar_topBarTitleTextColor, 0);
        mTitle = typedArray.getString(R.styleable.TopBar_topBarTitle);
        typedArray.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleTextView = new TextView(context);

        mLeftButton.setTextColor(leftTextColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mLeftButton.setBackground(mLeftBackground);
        } else {
            mLeftButton.setBackgroundDrawable(mLeftBackground);
        }
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(rightTextColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mRightButton.setBackground(mRightBackground);
        } else {
            mRightButton.setBackgroundDrawable(mRightBackground);
        }
        mRightButton.setText(mRightText);

        mTitleTextView.setText(mTitle);
        mTitleTextView.setTextColor(mTitleTextColor);
        mTitleTextView.setTextSize(mTitleTextSize);
        mTitleTextView.setGravity(Gravity.CENTER);

        LayoutParams mLeftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(ALIGN_PARENT_LEFT, TRUE);
        addView(mLeftButton, mLeftParams);

        LayoutParams mRightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRightParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton, mRightParams);

        LayoutParams mTitleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(CENTER_IN_PARENT, TRUE);
        addView(mTitleTextView, mTitleParams);

        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopBarClickListener.leftClick();
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopBarClickListener.rightClick();
            }
        });
    }

    public void setOnTopBarClickListener(TopBarClickListener topBarClickListener) {
        this.mTopBarClickListener = topBarClickListener;
    }

    public interface TopBarClickListener {
        void leftClick();

        void rightClick();
    }
}
