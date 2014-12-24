package im.ene.lab.resume.adapters;

import im.ene.lab.resume.fragments.PickupFragment;
import im.ene.lab.resume.fragments.PickupFragment.OnContentScrolledListener;
import im.ene.lab.resume.fragments.PlaceHolderFragment;
import im.ene.lab.resume.fragments.ProfileFragment;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class HomePagerAdapter extends SmartFragmentStatePagerAdapter {

	private final Activity mActivity;

	public HomePagerAdapter(FragmentManager fragmentManager, Activity activity) {
		super(fragmentManager);
		this.mActivity = activity;
	}

	@Override
	public Fragment getItem(int arg0) {
		if (arg0 == 0) {
			PickupFragment fragment = PickupFragment.newInstance();
			fragment.setOnContentScrolledListener((OnContentScrolledListener) mActivity);
			return fragment;
		} else if (arg0 == 4)
			return ProfileFragment.newInstance();

		return PlaceHolderFragment.newInstance();
	}

	@Override
	public int getCount() {
		return 5;
	}

}
