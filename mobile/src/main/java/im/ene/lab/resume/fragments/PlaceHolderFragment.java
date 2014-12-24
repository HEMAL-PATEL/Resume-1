package im.ene.lab.resume.fragments;

import im.ene.lab.resume.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PlaceHolderFragment extends Fragment {

	public PlaceHolderFragment() {
	}

	public static PlaceHolderFragment newInstance() {
		PlaceHolderFragment fragment = new PlaceHolderFragment();
		return fragment;
	}

	private ImageView mErrorImage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		mErrorImage = (ImageView) rootView.findViewById(R.id.error_image);

		return rootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Picasso.with(getActivity()).load(R.drawable.error).into(mErrorImage);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
}
