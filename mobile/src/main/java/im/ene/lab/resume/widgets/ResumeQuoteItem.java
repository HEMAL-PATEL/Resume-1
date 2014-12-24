package im.ene.lab.resume.widgets;

import im.ene.lab.resume.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class ResumeQuoteItem extends LinearLayout {

	public ResumeQuoteItem(Context context) {
		super(context);
		init(null);
	}

	public ResumeQuoteItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public ResumeQuoteItem(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);
	}

	public ResumeQuoteItem(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(attrs);
	}

	private TextView mText, mAuthor;

	private void init(AttributeSet attrs) {
		inflate(getContext(), R.layout.widget_quote, this);

		mText = (TextView) this.findViewById(R.id.quote_text);
		mAuthor = (TextView) this.findViewById(R.id.quote_author);

		if (attrs == null)
			return;

		TypedArray typed_array = null;

		String text, author;

		try {
			typed_array = getContext().obtainStyledAttributes(attrs,
					R.styleable.QuoteItem);

			text = typed_array.getString(R.styleable.QuoteItem_quote_text);
			author = typed_array.getString(R.styleable.QuoteItem_quote_author);

			mText.setText("\"" + text + "\"");
			mAuthor.setText(author + "");
		} finally {
			if (typed_array != null) {
				typed_array.recycle(); // ensure this is always called
			}
		}
	}
}
