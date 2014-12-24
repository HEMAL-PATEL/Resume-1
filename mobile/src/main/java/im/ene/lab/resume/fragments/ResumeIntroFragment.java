package im.ene.lab.resume.fragments;

import im.ene.lab.resume.R;
import im.ene.lab.resume.widgets.ObservableScrollView;
import im.ene.lab.resume.widgets.ObservableScrollView.OnScrollChangedListener;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ResumeIntroFragment extends ResumeBaseFragment {

	public ResumeIntroFragment() {

	}

	public static ResumeBaseFragment newInstance() {
		ResumeBaseFragment fragment = new ResumeIntroFragment();
		return fragment;
	}
	
	private ObservableScrollView mScrollView;

	private OnScrollChangedListener mListener;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_cv_intro, container,
				false);
		mScrollView = (ObservableScrollView) rootView
				.findViewById(R.id.content_scroll);
		mScrollView.setOnScrollChangedListener(mListener);

		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mListener = (OnScrollChangedListener) activity;
	}

	@Override
	public void adjustScroll(int tabBarTop, int maxScroll) {
		if (tabBarTop == 0 && mScrollView.getScrollY() > maxScroll) {
			// ScrollView does not need to adjust scroll, as the tab bar is
			// its sticky position
			return;
		}

		int posX = mScrollView.getScrollX();
		int posY = -tabBarTop + maxScroll;

		Log.d("pos", posY + " | " + mScrollView.getScrollY());
		mScrollView.scrollTo(posX, posY);
	}

}
