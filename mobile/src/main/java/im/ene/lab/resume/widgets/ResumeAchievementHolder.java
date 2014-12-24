package im.ene.lab.resume.widgets;

import im.ene.lab.resume.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
public class ResumeAchievementHolder extends LinearLayout {

	public ResumeAchievementHolder(Context context) {
		super(context, null);
	}

	public ResumeAchievementHolder(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
	}

	public ResumeAchievementHolder(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr, 0);
	}

	public ResumeAchievementHolder(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		init();
	}

	private LinearLayout mAchievements;

	private void init() {
		inflate(getContext(), R.layout.widget_cv_achievement, this);
		mAchievements = (LinearLayout) this.findViewById(R.id.achievement_list);

		this.setVisibility(View.GONE);
	}

	public void addAchievement(String year, String name, String result) {
		ResumeAchievementItem aItem = new ResumeAchievementItem(
				this.getContext());
		aItem.setAchievement(year, name, result);
		mAchievements.addView(aItem);

		if (this.getVisibility() == View.GONE
				|| this.getVisibility() == View.INVISIBLE) {
			this.setVisibility(View.VISIBLE);
		}
	}
}
