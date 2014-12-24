package im.ene.lab.resume.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

@SuppressLint("NewApi")
public class ObservableHorizontalScrollView extends HorizontalScrollView {

	public static interface OnScrollChangedListener {
		void onScrollChanged(HorizontalScrollView who, int l, int t, int oldl,
                             int oldt);
	}

	private OnScrollChangedListener mOnScrollChangedListener;

	public ObservableHorizontalScrollView(Context context) {
		super(context);
	}

	public ObservableHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ObservableHorizontalScrollView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ObservableHorizontalScrollView(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (mOnScrollChangedListener != null) {
			mOnScrollChangedListener.onScrollChanged(this, l, t, oldl, oldt);
		}
	}

	public void setOnScrollChangedListener(OnScrollChangedListener listener) {
		mOnScrollChangedListener = listener;
	}

}
