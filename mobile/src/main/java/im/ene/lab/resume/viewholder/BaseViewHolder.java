package im.ene.lab.resume.viewholder;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseViewHolder extends ViewHolder {

	protected final View mView;

	public BaseViewHolder(View view) {
		super(view);
		mView = view;
	}
	
	public BaseViewHolder(ViewGroup parent) {
		super(parent);
		mView = LayoutInflater.from(parent.getContext()).inflate(
				getViewLayoutID(), parent, false);
	}
	
	public View getView() {
		return mView;
	}

	abstract int getViewLayoutID();

}
