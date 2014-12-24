package im.ene.lab.resume.widgets;

import im.ene.lab.resume.R;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class ResumeSampleProject extends LinearLayout implements
		OnClickListener {

	public ResumeSampleProject(Context context) {
		super(context);
		init(null);
	}

	public ResumeSampleProject(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public ResumeSampleProject(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);
	}

	public ResumeSampleProject(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(attrs);
	}

	private KenBurnsView mCover;

	private TextView mName;

	String name, url;
	int cover;

	private void init(AttributeSet attrs) {
		inflate(getContext(), R.layout.widget_sample_project, this);

		mCover = (KenBurnsView) this.findViewById(R.id.project_cover);
		mName = (TextView) this.findViewById(R.id.project_name);

		mName.setOnClickListener(this);
		mCover.setOnClickListener(this);
		
		if (attrs == null) {
			return; // quick exit
		}

		TypedArray typed_array = null;

		try {
			typed_array = getContext().obtainStyledAttributes(attrs,
					R.styleable.ResumeSampleProject);

			name = typed_array
					.getString(R.styleable.ResumeSampleProject_project_name);
			url = typed_array
					.getString(R.styleable.ResumeSampleProject_project_url);

			cover = typed_array.getResourceId(
					R.styleable.ResumeSampleProject_project_cover,
					R.drawable.code_for_fun);

			mName.setText(name);
			mCover.setResourceIds(cover, cover);
		} catch (Exception er) {
			er.printStackTrace();
		} finally {
			if (typed_array != null) {
				typed_array.recycle(); // ensure this is always called
			}
		}
		

	}

	public void setProject(String name, int coverId) {
		mName.setText(name);
		mCover.setResourceIds(coverId, coverId);
	}

	@Override
	public void onClick(View v) {
		if (url != null)
			openUrl(url);
	}

	private void openUrl(String url) {
		Intent internetIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		internetIntent.setComponent(new ComponentName("com.android.browser",
				"com.android.browser.BrowserActivity"));
		internetIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		getContext().startActivity(internetIntent);
	}
}
