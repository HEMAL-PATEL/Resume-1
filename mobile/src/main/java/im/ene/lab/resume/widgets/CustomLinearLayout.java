package im.ene.lab.resume.widgets;

import im.ene.lab.resume.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class CustomLinearLayout extends LinearLayout {
	private Drawable touchFeedbackDrawable;

	public CustomLinearLayout(Context context) {
		super(context);
	}

	public CustomLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		this.touchFeedbackDrawable = getResources().getDrawable(
				R.drawable.selector_feedback);
	}

	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		this.touchFeedbackDrawable.setBounds(0, 0, getWidth(), getHeight());
		this.touchFeedbackDrawable.draw(canvas);
	}

	protected void drawableStateChanged() {
		if (this.touchFeedbackDrawable != null) {
			this.touchFeedbackDrawable.setState(getDrawableState());
			invalidate();
		}
		super.drawableStateChanged();
	}
}
