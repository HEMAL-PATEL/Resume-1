package im.ene.lab.resume.adapters;

import im.ene.lab.resume.fragments.ResumeEduFragment;
import im.ene.lab.resume.fragments.ResumeIntroFragment;
import im.ene.lab.resume.fragments.ResumeReasonFragment;
import im.ene.lab.resume.fragments.ResumeSkillFragment;
import im.ene.lab.resume.widgets.TabHolderScrollingContent;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.SparseArrayCompat;

public class ResumePagerAdapter extends SmartFragmentStatePagerAdapter {

	private final SparseArrayCompat<TabHolderScrollingContent> mTabHolderScrollingContent;

	public ResumePagerAdapter(FragmentManager fragmentManager, Activity activity) {
		super(fragmentManager);
		mTabHolderScrollingContent = new SparseArrayCompat<TabHolderScrollingContent>();
	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment fragment;

		switch (arg0) {
		case 0:
			fragment = ResumeIntroFragment.newInstance();
			break;
		case 1:
			fragment = ResumeSkillFragment.newInstance();
			break;
		case 2:
			fragment = ResumeEduFragment.newInstance();
			break;
		case 3:
			fragment = ResumeReasonFragment.newInstance();
			break;
		default:
			fragment = ResumeIntroFragment.newInstance();
			break;
		}

		if (fragment instanceof TabHolderScrollingContent)
			mTabHolderScrollingContent.put(arg0,
					(TabHolderScrollingContent) fragment);

		return fragment;
	}

	@Override
	public int getCount() {
		return mTitle.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mTitle[position];
	}

	public SparseArrayCompat<TabHolderScrollingContent> getTabHolderScrollingContent() {
		return this.mTabHolderScrollingContent;
	}

	private static final String[] mTitle = { "プロフィル", "スキル", "資格・学歴・職歴", "志望動機" };
	// private static final String[] mTitle = { "プロフィル", "スキル", "資格・学歴・職歴" };
}
