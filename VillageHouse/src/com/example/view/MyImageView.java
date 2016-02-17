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
 * * �Զ����Բ��ImageView������ֱ�ӵ�����ڲ�����ʹ�á�
 * 
 * @author caizhiming
 * */
public class MyImageView extends ImageView {
	private Paint paint;// �½�����

	/**
	 * ����java���봴����ͼ��ʱ�򱻵��ã�����Ǵ�xml������ͼ���Ͳ���������
	 * 
	 * @param context
	 */
	public MyImageView(Context context) {
		this(context, null);

	}

	/**
	 * �������xml��������û��ָ��style��ʱ�򱻵��á�
	 * 
	 * @param context
	 * @param attrs
	 */
	public MyImageView(Context context, AttributeSet attrs) {// ֱ�ӵ�������Ĺ��췽����
		this(context, attrs, 0);
	}

	/**
	 * �������xml����������ָ��style��ʱ�򱻵��á�
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
	 * ondraw��viewˢ�£�����ʱ���ã�����������ʹ�÷��������canvas��view���л��ơ� * ����Բ��ͼƬ
	 * 
	 * @author
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		Drawable drawable = getDrawable();
		if (null != drawable) {
			Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
			Bitmap b = getCircleBitmap(bitmap, 14);// ����getCircleBitmap������ȡ��Bitmap����
			final Rect rectSrc = new Rect(0, 0, b.getWidth(), b.getHeight());// rect����һ��װ������������
			final Rect rectDest = new Rect(0, 0, getWidth(), getHeight());
			paint.reset();
			canvas.drawBitmap(b, rectSrc, rectDest, paint);
		} else {
			super.onDraw(canvas);
		}
	}

	/**
	 * * ��ȡԲ��ͼƬ����
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