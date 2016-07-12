package www.bode.net.cachenews.ui.welcome;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 自定义圆形图片控件 Created by Liu on 2016-07-11.
 */
public class CircleImage extends ImageView {
    private Paint paint;
    
    private Xfermode xfermode;
    
    /**
     * 构造方法,初始化画笔与位图叠加模式
     */
    public CircleImage(Context context) {
        this(context, null);
    }
    
    public CircleImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public CircleImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        paint.setXfermode(xfermode);
        paint.setColor(Color.BLACK);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            super.onDraw(canvas);
        }
        // 根据控件的约定值缩放照片的大小
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int rectWidth;
        int rectHeight;
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        if (bitmapWidth > width && bitmapHeight <= height) {
            rectWidth = width;
            double temp = (width * bitmapHeight) / bitmapWidth;
            rectHeight = (int) temp;
        }
        else if (bitmapHeight > height && bitmapWidth <= width) {
            rectHeight = height;
            double temp = (height * bitmapWidth) / bitmapHeight;
            rectWidth = (int) temp;
        }
        else if (bitmapWidth > width && bitmapHeight > height) {
            double tempWidth = width * 1f / bitmapWidth;
            double tempHeight = height * 1f / bitmapHeight;
            rectWidth = (int) (Math.min(tempWidth, tempHeight) * bitmapWidth);
            rectHeight = (int) (Math.min(tempWidth, tempHeight) * bitmapHeight);
            
        }
        else {
            rectWidth = bitmapWidth;
            rectHeight = bitmapHeight;
        }
        // 根据缩放后的尺寸确定照片的大小
        Rect rect = new Rect((width - rectWidth) / 2,
                             (height - rectHeight) / 2,
                             rectWidth + (width - rectWidth) / 2,
                             rectHeight + (height - rectHeight) / 2);
        RectF rectCircle = new RectF((width - rectWidth) / 2,
                                     (height - rectWidth) / 2,
                                     rectWidth + (width - rectWidth) / 2,
                                     rectWidth + (height - rectWidth) / 2);
        int layerId = canvas.saveLayer(rectCircle, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(bitmap,
                          new Rect(0, 0, bitmapWidth, bitmapHeight),
                          rect,
                          null);
        Path path = new Path();
        path.addOval(rectCircle, Path.Direction.CCW);
        canvas.drawPath(path, paint);
        canvas.restoreToCount(layerId);
        drawRing(canvas, rectCircle);
    }
    
    /**
     * 画圆形图片周围的圆环
     */
    
    private void drawRing(Canvas canvas, RectF rectCircle) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        RectF ring = new RectF(rectCircle.left + 5,
                               rectCircle.top + 5,
                               rectCircle.right - 5,
                               rectCircle.bottom - 5);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(ring, paint);
    }
    
}
