package im.ene.lab.resume.adapters;

import im.ene.lab.resume.R;
import im.ene.lab.resume.utils.Utils;
import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PickupContentAdapter extends Adapter<ViewHolder> {

	private final Context mContext;

	public PickupContentAdapter(Context context) {
		super();
		this.mContext = context;
	}

	@Override
	public int getItemCount() {
		return 13;
	}

	@Override
	public void onBindViewHolder(ViewHolder vh_, int position) {
		if (!(vh_ instanceof PickupContentViewHolder)) {
			return;
		}

		PickupContentViewHolder vh = (PickupContentViewHolder) vh_;
		vh.text_looking_for.setText("その他");
		vh.text_title.setText("the simple, the best.");
		vh.text_company_name.setText("Eneim Labs");

		Picasso.with(mContext).load(R.drawable.code_for_fun)
				.into(vh.image_project);
		vh.text_support_count.setText("10");
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.item_pickup, parent, false);
		PickupContentViewHolder vh = new PickupContentViewHolder(view);
		vh.setOnPickupItemClickListener(new PickupContentViewHolder.OnPickupItemCLickListener() {

			@Override
			public void onPickupItemClick(View v) {
				Utils.act(mContext);
			}
		});

		return vh;
	}

	public static class PickupContentViewHolder extends ViewHolder implements
			OnClickListener {

		public TextView text_looking_for, text_label_new, text_title,
				text_company_name, text_support_count;

		public ImageView image_project;

		private OnPickupItemCLickListener mListener;

		public PickupContentViewHolder(View view) {
			super(view);

			text_looking_for = (TextView) view
					.findViewById(R.id.text_looking_for);
			text_label_new = (TextView) view.findViewById(R.id.text_label_new);
			text_title = (TextView) view.findViewById(R.id.text_title);
			text_company_name = (TextView) view
					.findViewById(R.id.text_company_name);
			text_support_count = (TextView) view
					.findViewById(R.id.text_support_count);

			image_project = (ImageView) view.findViewById(R.id.image_project);

			view.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			if (this.mListener != null)
				mListener.onPickupItemClick(v);
		}

		public void setOnPickupItemClickListener(
				OnPickupItemCLickListener listener) {
			this.mListener = listener;
		}

		public interface OnPickupItemCLickListener {
			void onPickupItemClick(View v);
		}
	}
}
