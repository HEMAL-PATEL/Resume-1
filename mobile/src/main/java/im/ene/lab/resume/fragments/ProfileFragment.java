package im.ene.lab.resume.fragments;

import im.ene.lab.resume.R;
import im.ene.lab.resume.widgets.CircleTransform;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

	public ProfileFragment() {

	}

	public static ProfileFragment newInstance() {
		ProfileFragment fragment = new ProfileFragment();
		return fragment;
	}

	private View mRootView;
	private ImageView image_cover, image_avatar;
	private TextView text_name, text_score, text_short_description,
			text_friends_count, text_support_projects_count,
			text_bookmark_projects_count;
	private LinearLayout layout_profile;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_profile, container,
				false);

		image_cover = (ImageView) mRootView.findViewById(R.id.image_cover);
		image_avatar = (ImageView) mRootView.findViewById(R.id.image_avatar);
		text_name = (TextView) mRootView.findViewById(R.id.text_name);
		text_score = (TextView) mRootView.findViewById(R.id.text_score);
		text_short_description = (TextView) mRootView
				.findViewById(R.id.text_short_description);
		text_friends_count = (TextView) mRootView
				.findViewById(R.id.text_friends_count);
		text_support_projects_count = (TextView) mRootView
				.findViewById(R.id.text_support_projects_count);
		text_bookmark_projects_count = (TextView) mRootView
				.findViewById(R.id.text_bookmark_projects_count);

		layout_profile = (LinearLayout) mRootView
				.findViewById(R.id.layout_profile);

		return mRootView;
	}

	@SuppressLint("InflateParams")
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Picasso.with(getActivity()).load(R.drawable.code_for_fun)
				.into(image_cover);
		Picasso.with(getActivity()).load(R.drawable.facebook_avatar)
				.transform(new CircleTransform()).into(image_avatar);

		text_name.setText("Nguyen Hoai Nam");
		text_score.setText("33");
		text_short_description.setText("eneim labs");
		text_friends_count.setText("39");
		text_support_projects_count.setText("2");
		text_bookmark_projects_count.setText("1");

		TextView profile = (TextView) LayoutInflater.from(getActivity())
				.inflate(android.R.layout.simple_list_item_1, null);
		profile.setText("the simple, the best");
		layout_profile.addView(profile);

	}
}
