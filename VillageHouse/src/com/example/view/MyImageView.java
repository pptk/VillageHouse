package com.example.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * * 自定义的圆形ImageView，可以直接当组件在布局中使用。
 * 
 * @author caizhiming
 * */
public class MyImageView extends ImageView {
	private Paint paint;// 新建画笔

	/**
	 * 是在java代码创建视图的时候被调用，如果是从xml填充的视图，就不会调用这个
	 * 
	 * @param context
	 */
	public MyImageView(Context context) {
		this(context, null);

	}

	/**
	 * 这个是在xml创建但是没有指定style的时候被调用。
	 * 
	 * @param context
	 * @param attrs
	 */
	public MyImageView(Context context, AttributeSet attrs) {// 直接调用下面的构造方法。
		this(context, attrs, 0);
	}

	/**
	 * 这个是在xml创建但是有指定style的时候被调用。
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public MyImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		paint = new Paint();
	}

	/**
	 * ondraw在view刷新，绘制时调用，可以在其中使用方法传入的canvas对view进行绘制。 * 绘制圆形图片
	 * 
	 * @author
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		Drawable drawable = getDrawable();
		if (null != drawable) {
			Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
			Bitmap b = getCircleBitmap(bitmap, 14);// 调用getCircleBitmap方法获取到Bitmap对象。
			final Rect rectSrc = new Rect(0, 0, b.getWidth(), b.getHeight());// rect理解成一个装参数的容器。
			final Rect rectDest = new Rect(0, 0, getWidth(), getHeight());
			paint.reset();
			canvas.drawBitmap(b, rectSrc, rectDest, paint);
		} else {
			super.onDraw(canvas);
		}
	}

	/**
	 * * 获取圆形图片方法
	 * 
	 * @param bitmap
	 * @param pixels
	 * @return Bitmap
	 * @author caizhiming
	 */
	private Bitmap getCircleBitmap(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		int x = bitmap.getWidth();

		canvas.drawCircle(x / 2, x / 2, x / 2, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;

	}
}