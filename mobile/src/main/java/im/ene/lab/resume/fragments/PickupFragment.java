package im.ene.lab.resume.fragments;

import im.ene.lab.resume.R;
import im.ene.lab.resume.adapters.PickupContentAdapter;
import im.ene.lab.resume.utils.Utils;
import im.ene.lab.resume.widgets.DividerItemDecoration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PickupFragment extends Fragment {

	public PickupFragment() {

	}

	public static PickupFragment newInstance() {
		PickupFragment fragment = new PickupFragment();
		return fragment;
	}

	private View mRootView;
	private SwipeRefreshLayout mSwipeWidget;
	private RecyclerView mContentRecyclerView;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.page_pickup, container, false);

		mSwipeWidget = (SwipeRefreshLayout) mRootView
				.findViewById(R.id.swipe_refresh_widget);
		mSwipeWidget.setColorSchemeColors(getResources().getColor(
				R.color.tool_bar_want));
		mContentRecyclerView = (RecyclerView) mRootView
				.findViewById(R.id.page_content);

		return mRootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mContentRecyclerView.setLayoutManager(new LinearLayoutManager(
				getActivity()));
		mContentRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mContentRecyclerView.addItemDecoration(new DividerItemDecoration(
				getActivity(), DividerItemDecoration.VERTICAL_LIST));

		PickupContentAdapter contentAdapter = new PickupContentAdapter(
				getActivity());

		mContentRecyclerView.setAdapter(contentAdapter);

		mSwipeWidget.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				(new Handler()).postDelayed(new Runnable() {

					@Override
					public void run() {
						mSwipeWidget.setRefreshing(false);
						Utils.act(getActivity());
					}
				}, 2500);
			}
		});

		mContentRecyclerView.setOnScrollListener(new OnScrollListener() {

			int mDx, mDy;

			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				mDx = dx;
				mDy = dy;
				if (mListener != null)
					mListener.onListScrolled(mDx, mDy);
			}

			@Override
			public void onScrollStateChanged(RecyclerView recyclerView,
					int newState) {

				switch (newState) {
				case RecyclerView.SCROLL_STATE_DRAGGING:
					Log.d("scrollstate", "SCROLL_STATE_DRAGGING");
					break;

				case RecyclerView.SCROLL_STATE_IDLE:
					Log.d("scrollstate", "SCROLL_STATE_IDLE");
					if (mListener != null)
						mListener.onFinishScrolled();
					break;

				case RecyclerView.SCROLL_STATE_SETTLING:
					Log.d("scrollstate", "SCROLL_STATE_SETTLING");
					break;

				default:
					break;
				}
			}
		});
	}

	public void setOnContentScrolledListener(OnContentScrolledListener listener) {
		this.mListener = listener;
	}

	private OnContentScrolledListener mListener;

	public interface OnContentScrolledListener {
		void onListScrolled(int dx, int dy);

		void onFinishScrolled();
	}
}
