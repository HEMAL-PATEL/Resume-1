package im.ene.lab.resume.viewholder;

import im.ene.lab.resume.R;
import im.ene.lab.resume.widgets.ResumeAchievementHolder;
import android.view.ViewGroup;
import android.widget.TextView;

public class CVEducationItemViewHolder extends BaseViewHolder {

	public final TextView place, duration, description;
	
	public final ResumeAchievementHolder achievements;

	public CVEducationItemViewHolder(ViewGroup parent) {
		super(parent);
		place = (TextView) mView.findViewById(R.id.text_cv_education_place);
		duration = (TextView) mView
				.findViewById(R.id.text_cv_education_duration);
		description = (TextView) mView
				.findViewById(R.id.text_cv_education_description);
		
		achievements = (ResumeAchievementHolder) mView.findViewById(R.id.cv_achievements);
	}

	@Override
	int getViewLayoutID() {
		return R.layout.widget_cv_education_item;
	}

}
