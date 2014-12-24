package im.ene.lab.resume.fragments;

import im.ene.lab.resume.R;
import im.ene.lab.resume.viewholder.CVEducationItemViewHolder;
import im.ene.lab.resume.widgets.ObservableScrollView;
import im.ene.lab.resume.widgets.ObservableScrollView.OnScrollChangedListener;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResumeEduFragment extends ResumeBaseFragment {

	public ResumeEduFragment() {

	}

	public static ResumeBaseFragment newInstance() {
		ResumeBaseFragment fragment = new ResumeEduFragment();
		return fragment;
	}

	private ObservableScrollView mScrollView;
	private OnScrollChangedListener mListener;

	private LinearLayout mContentContainer;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_cv_education,
				container, false);
		mScrollView = (ObservableScrollView) rootView
				.findViewById(R.id.content_scroll);
		mScrollView.setOnScrollChangedListener(mListener);

		mContentContainer = (LinearLayout) rootView
				.findViewById(R.id.content_holder);

		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mListener = (OnScrollChangedListener) activity;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		TextView mQulifyHead = (TextView) LayoutInflater.from(
				mContentContainer.getContext()).inflate(
				android.R.layout.simple_list_item_1, mContentContainer, false);
		mQulifyHead.setText("資格");
		((LinearLayout.LayoutParams) mQulifyHead.getLayoutParams()).bottomMargin = 12;
		mContentContainer.addView(mQulifyHead);

		CVEducationItemViewHolder qlf_toeic = new CVEducationItemViewHolder(
				mContentContainer);
		qlf_toeic.place.setText("TOEIC");
		qlf_toeic.duration.setText("2013年6月");
		qlf_toeic.description.setText("TOEIC 840");
		mContentContainer.addView(qlf_toeic.getView());

		TextView mEduHead = (TextView) LayoutInflater.from(
				mContentContainer.getContext()).inflate(
				android.R.layout.simple_list_item_1, mContentContainer, false);
		mEduHead.setText("学歴");
		((LinearLayout.LayoutParams) mEduHead.getLayoutParams()).bottomMargin = 12;
		mContentContainer.addView(mEduHead);

		CVEducationItemViewHolder edu_titech = new CVEducationItemViewHolder(
				mContentContainer);
		edu_titech.place.setText("東京工業大学");
		edu_titech.duration.setText("2013年４月 - 2015年３月（見込み）");
		edu_titech.description.setText("情報工学");
		mContentContainer.addView(edu_titech.getView());

		CVEducationItemViewHolder edu_anan = new CVEducationItemViewHolder(
				mContentContainer);
		edu_anan.place.setText("阿南工業高等専門学校");
		edu_anan.duration.setText("2010年４月 - 2013年３月");
		edu_anan.description.setText("制御情報工学科");
		mContentContainer.addView(edu_anan.getView());

		CVEducationItemViewHolder edu_japanese = new CVEducationItemViewHolder(
				mContentContainer);
		edu_japanese.place.setText("東京日本語教育センター");
		edu_japanese.duration.setText("2009年4月 - 2010年3月");
		edu_japanese.description.setText("日本語");
		mContentContainer.addView(edu_japanese.getView());

		CVEducationItemViewHolder edu_bka = new CVEducationItemViewHolder(
				mContentContainer);
		edu_bka.place.setText("Hanoi University of Science and Technology");
		edu_bka.duration.setText("2007年9月 - 2009年1月");
		edu_bka.description
				.setText("Center for training of Talented Engineers.\nhttp://en.hust.edu.vn/home");
		mContentContainer.addView(edu_bka.getView());

		CVEducationItemViewHolder edu_high = new CVEducationItemViewHolder(
				mContentContainer);
		edu_high.place.setText("Vinh Phuc Gifted High School");
		edu_high.duration.setText("2004年9月 - 2007年5月");
		edu_high.description
				.setText("School for Gifted Students, Located in Vinh Phuc Province - Vietnam.\n"
						+ "I was in the Mathematical Class.");
		edu_high.achievements.addAchievement("2007",
				"Vietnam National Mathematical Olympiad", "Competitor");
		edu_high.achievements.addAchievement("2007",
				"Vinhphuc Province Mathematical Olympiad", "1st Prize");
		edu_high.achievements.addAchievement("2006",
				"Vinhphuc Province Mathematical Olympiad", "2st Prize");
		edu_high.achievements.addAchievement("2006",
				"Vinhphuc Province Mathematical Olympiad", "3st Prize");
		edu_high.achievements.addAchievement("2005",
				"Vietnam Northern Area Mathematical Olympiad", "Gold Medal");
		edu_high.achievements.addAchievement("2005",
				"Open Singapore Mathematical Olympiad", "Gold Medal");
		mContentContainer.addView(edu_high.getView());

		TextView mEmploymentHead = (TextView) LayoutInflater.from(
				mContentContainer.getContext()).inflate(
				android.R.layout.simple_list_item_1, mContentContainer, false);
		mEmploymentHead.setText("職歴");
		((LinearLayout.LayoutParams) mEmploymentHead.getLayoutParams()).bottomMargin = 12;
		mContentContainer.addView(mEmploymentHead);

		CVEducationItemViewHolder emp_leon = new CVEducationItemViewHolder(
				mContentContainer);
		emp_leon.place.setText("レオンテック株式会社");
		emp_leon.duration.setText("2013年9月 - 現在");

		String leon_des = "<p>レオンテック株式会社 (英文表記： Leontec Co., Ltd.)</p>"
				+ "<p>Homepage: http://leontec.co.jp</p>"
				+ "<p><strong>Title: Part-time developer.</strong></p>"
				+ "<p>I am main on Android Enterprise/End-User Application developing. "
				+ "Beside, I take part in UI-Design, tester. I re-create/re-design the homepage.</p>"
				+ "<p>Released End-User Application:</p>"
				+ "<p><strong><font color='red'>ISBN Scan</fonr></strong></p>"
				+ "<p><strong><font color='red'>漢字百景</font></strong></p>"
				+ "<p>Play Store reference: https://play.google.com/store/search?q=leontec</p>";
		Spanned leon_des_text = Html.fromHtml(leon_des);
		emp_leon.description.setText(leon_des_text);
		mContentContainer.addView(emp_leon.getView());

		CVEducationItemViewHolder emp_tedx = new CVEducationItemViewHolder(
				mContentContainer);
		emp_tedx.place.setText("TEDxTitech 2013");
		emp_tedx.duration.setText("2013年6月 - 2013年12月");

		String tedx_des = "<p>TEDxTitech (2013)</p>"
				+ "<p>Homepage: http://tedxtitech.com</p>"
				+ "<p><strong>Title: Speaker Team Leader.</strong></p>"
				+ "<p>TEDxTitech is a TED-independent event with the purpose of sharing IDEAS about Technology-Entertainment-Design. "
				+ "I was in the position of Speaker Team Leader. "
				+ "We invite speakers for the event, co-oporate with them for speaking-training and other process.</p>";
		Spanned tedx_des_text = Html.fromHtml(tedx_des);
		emp_tedx.description.setText(tedx_des_text);
		mContentContainer.addView(emp_tedx.getView());

		CVEducationItemViewHolder emp_fyt = new CVEducationItemViewHolder(
				mContentContainer);
		emp_fyt.place.setText("FYT");
		emp_fyt.duration.setText("2009年10月 - 2011年12月");

		String fyt_des = "<p>FYT - FPT Young Talents</p>"
				+ "<p>Homepage: http://fyt.vn</p>"
				+ "<p>Facebook Official Group: https://www.facebook.com/groups/568694043146990/</p>"
				+ "<p><strong>FYT: Talents with Enthusiastice Hearts</strong></p>"
				+ "<p>FYT is a group for Young Talents, founded in 1999 by CEO Truong Gia Binh, FPT Vietnam Corporation. "
				+ "We are selected from Youngsters with excellent abilities in English, IQ, Mathematics and Social/Soft Skills. "
				+ "Joining FYT, each of us has an oppotunity to communicate with other Talents, as well as improve/sharpen our skills (team-work, self-management ...). </p>";
		Spanned fyt_des_text = Html.fromHtml(fyt_des);
		emp_fyt.description.setText(fyt_des_text);
		mContentContainer.addView(emp_fyt.getView());
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
