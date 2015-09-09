package com.itheima.quickindexbar.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 快速索引
 * 
 * @author poplar
 * 
 */
public class QuickIndexBar extends View {

	private Rect bounds;

	public interface OnLetterUpdateListener {
		void onLetterUpdate(String letter);
	}
	private OnLetterUpdateListener onLetterUpdateListener;

	public OnLetterUpdateListener getOnLetterUpdateListener() {
		return onLetterUpdateListener;
	}

	public void setOnLetterUpdateListener(
			OnLetterUpdateListener onLetterUpdateListener) {
		this.onLetterUpdateListener = onLetterUpdateListener;
	}

	private static final String[] LETTERS = new String[] { "A", "B", "C", "D",
			"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
			"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	private Paint paint;

	private int cellWidth;

	private int mHeight;

	private float cellHeight;

	public QuickIndexBar(Context context) {
		this(context, null);
	}

	public QuickIndexBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public QuickIndexBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		bounds = new Rect();
		paint.setColor(Color.BLACK);
		paint.setTextSize(40);
		// 字体加粗
		paint.setTypeface(Typeface.DEFAULT_BOLD);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		// 将A-Z绘制到界面上
		for (int i = 0; i < LETTERS.length; i++) {
			String letter = LETTERS[i];

			// 计算x ， y坐标
			float x = cellWidth / 2.0f - paint.measureText(letter) / 2.0f;


			paint.getTextBounds(letter, 0, letter.length(), bounds);
			int textHeight = bounds.height();

			float y = cellHeight * 0.5f + textHeight * 0.5f + i * cellHeight;

			paint.setColor(i == currentIndex ? Color.GRAY : Color.BLACK);
			
			canvas.drawText(letter, x, y, paint);
		}
	}

	private int currentIndex = -1;

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int index = -1;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			setBackgroundColor(Color.GRAY);
			index = (int) (event.getY() / cellHeight);
			if (index >= 0 && index < LETTERS.length) {
				if(currentIndex != index){
					String letter = LETTERS[index];
					System.out.println("letter : " + letter);
					if(onLetterUpdateListener != null){
						onLetterUpdateListener.onLetterUpdate(letter);
					}
					
					currentIndex = index;
				}
			}

			break;
		case MotionEvent.ACTION_MOVE:
			setBackgroundColor(Color.GRAY);
			index = (int) (event.getY() / cellHeight);
			if (index >= 0 && index < LETTERS.length) {
				if(currentIndex != index){
					String letter = LETTERS[index];
					System.out.println("letter : " + letter);
					if(onLetterUpdateListener != null){
						onLetterUpdateListener.onLetterUpdate(letter);
					}
					
					currentIndex = index;
				}
			}

			break;
		case MotionEvent.ACTION_UP:
			currentIndex = -1;
			setBackgroundColor(Color.TRANSPARENT);
			break;

		default:
			break;
		}
		
		invalidate();

		return true;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		cellWidth = getMeasuredWidth();
		mHeight = getMeasuredHeight();

		cellHeight = mHeight * 1.0f / LETTERS.length;
	}
}
