package www.bode.net.cachenews.ui.main;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import www.bode.net.cachenews.R;
import www.bode.net.cachenews.util.DensityUtil;

/**
 * 水平进度条 Created by Liu on 2016-06-29.
 */
public class HorizontalProgressBar extends ProgressBar {
    private int completedBarHeight;

    private int completedBarColor;

    private int uncompletedBarColor;

    private int uncompletedBarHeight;

    private int progressTextSize;

    private int progressTextColor;

    private int progressTextPadding;

    private int progressHeight;

    private int progressWidth;

    private Paint paint;

    private int progress;

    private int currentX;

    private int textWidth;

    public HorizontalProgressBar(Context context) {
        this(context, null);

    }

    public HorizontalProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgressBar(Context context,
                                 AttributeSet attrs,
                                 int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainAttrs(attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    /**
     * 获取自定义属性
     */
    private void obtainAttrs(AttributeSet attrs) {
        TypedArray ta =
                getContext().obtainStyledAttributes(attrs,
                        R.styleable.HorizontalProgressBar);
        completedBarHeight =
                (int) ta.getDimension(R.styleable.HorizontalProgressBar_completed_bar_height,
                        10);
        completedBarColor =
                ta.getColor(R.styleable.HorizontalProgressBar_completed_bar_color,
                        0xffff0000);
        uncompletedBarHeight =
                (int) ta.getDimension(R.styleable.HorizontalProgressBar_uncompleted_bar_height,
                        10);
        uncompletedBarColor =
                ta.getColor(R.styleable.HorizontalProgressBar_uncompleted_bar_color,
                        0x66ff0000);
        progressTextSize =
                (int) ta.getDimension(R.styleable.HorizontalProgressBar_progress_text_size,
                        12);
        progressTextPadding =
                (int) ta.getDimension(R.styleable.HorizontalProgressBar_progress_text_padding,
                        20);
        progressTextColor =
                ta.getColor(R.styleable.HorizontalProgressBar_progress_text_color,
                        0xff00ff00);
        ta.recycle();
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec,
                                          int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureWidth(widthMeasureSpec);
        measureHeight(heightMeasureSpec);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        progress = getProgress();
        canvas.translate(0, progressHeight / 2);
        drawCompletedBar(canvas);
        drawProgressText(canvas);
        drawUncompletedBar(canvas);
    }

    /**
     * 绘制提示进度的文字
     */
    private void drawProgressText(Canvas canvas) {
        String text = progress + "%";
        textWidth = (int) paint.measureText(text);
        paint.setColor(progressTextColor);
        paint.setTextSize(DensityUtil.dip2px(getContext(), progressTextSize));
        canvas.drawText(text,
                currentX + progressTextPadding,
                progressTextSize,
                paint);
        currentX = currentX + progressTextPadding + textWidth;
    }

    /**
     * 绘制未完成部分的bar
     */
    private void drawUncompletedBar(Canvas canvas) {
        paint.setColor(uncompletedBarColor);
        paint.setStrokeWidth(uncompletedBarHeight);
        canvas.drawLine(currentX + progressTextPadding,
                0,
                getPaddingLeft() + progressWidth,
                0,
                paint);
    }

    /**
     * 绘制表示完成部分的bar
     */
    private void drawCompletedBar(Canvas canvas) {
        paint.setColor(completedBarColor);
        paint.setStrokeWidth(completedBarHeight);
        int startX = getPaddingLeft();
        int offsetX = progress
                * (progressWidth - progressTextPadding * 2 - textWidth)
                / 100;
        canvas.drawLine(startX, 0, offsetX, 0, paint);
        currentX = startX + offsetX;
    }

    /**
     * 获取宽度的测量模式与测量值
     */
    private void measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            progressWidth = MeasureSpec.getSize(widthMeasureSpec)
                    - getPaddingLeft() - getPaddingRight();
        } else {
            progressWidth = 0;
        }
    }

    /**
     * 获取高度的测量模式与测量值
     */
    private void measureHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        progressHeight =
                Math.max(Math.max(completedBarHeight, progressTextSize),
                        size);
    }
}
