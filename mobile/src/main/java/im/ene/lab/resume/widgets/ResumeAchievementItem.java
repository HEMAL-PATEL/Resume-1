package im.ene.lab.resume.widgets;

import im.ene.lab.resume.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class ResumeAchievementItem extends LinearLayout {

	public ResumeAchievementItem(Context context) {
		super(context, null);
		init();
	}

	public ResumeAchievementItem(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
	}

	public ResumeAchievementItem(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr, 0);
	}

	public ResumeAchievementItem(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		init();
	}

	private TextView mTime, mName, mResult;

	private void init() {
		inflate(getContext(), R.layout.widget_cv_achievement_item, this);

		mTime = (TextView) this.findViewById(R.id.achievement_time);
		mName = (TextView) this.findViewById(R.id.achievement_name);
		mResult = (TextView) this.findViewById(R.id.achievement_result);
	}

	public void setAchievement(String time, String name, String result) {
		this.mTime.setText(time + "");
		this.mName.setText(name + "");
		this.mResult.setText(result + "");
	}
}
