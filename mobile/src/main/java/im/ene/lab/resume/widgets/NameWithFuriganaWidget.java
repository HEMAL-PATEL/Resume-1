package im.ene.lab.resume.widgets;

import im.ene.lab.resume.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class NameWithFuriganaWidget extends LinearLayout {

	public NameWithFuriganaWidget(Context context) {
		super(context);
		init(null);
	}

	public NameWithFuriganaWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public NameWithFuriganaWidget(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);
	}

	public NameWithFuriganaWidget(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(attrs);
	}

	public TextView mRomanji, mFurigana;

	private void init(AttributeSet attrs) {
		inflate(getContext(), R.layout.widget_name_with_furigana, this);

		mRomanji = (TextView) this.findViewById(R.id.romanji);
		mFurigana = (TextView) this.findViewById(R.id.furigara);

		if (attrs == null) {
			return; // quick exit
		}

		TypedArray typed_array = null;

		String romanji, furigana;

		try {
			typed_array = getContext().obtainStyledAttributes(attrs,
					R.styleable.NameWithFuriganaWidget);

			romanji = typed_array
					.getString(R.styleable.NameWithFuriganaWidget_name_romanji);
			furigana = typed_array
					.getString(R.styleable.NameWithFuriganaWidget_name_furigana);

			mRomanji.setText(romanji + "");
			mFurigana.setText(furigana + "");
		} finally {
			if (typed_array != null) {
				typed_array.recycle(); // ensure this is always called
			}
		}

	}
}
