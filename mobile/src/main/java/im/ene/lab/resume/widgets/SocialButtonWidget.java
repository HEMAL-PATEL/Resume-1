package im.ene.lab.resume.widgets;

import im.ene.lab.resume.R;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
public class SocialButtonWidget extends LinearLayout implements OnClickListener {

	private ImageButton github, facebook, twitter, instagram, gplus, linkedin,
			behance, flickr, blog, lab;

	public SocialButtonWidget(Context context) {
		super(context, null);
	}

	public SocialButtonWidget(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
	}

	public SocialButtonWidget(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr, 0);
	}

	public SocialButtonWidget(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		init();
	}

	private void init() {
		inflate(getContext(), R.layout.widget_social_buttons, this);

		github = (ImageButton) this.findViewById(R.id.btn_github);
		facebook = (ImageButton) this.findViewById(R.id.btn_facebook);
		twitter = (ImageButton) this.findViewById(R.id.btn_twitter);
		instagram = (ImageButton) this.findViewById(R.id.btn_instagram);
		gplus = (ImageButton) this.findViewById(R.id.btn_gplus);
		linkedin = (ImageButton) this.findViewById(R.id.btn_linkedin);
		behance = (ImageButton) this.findViewById(R.id.btn_behance);
		flickr = (ImageButton) this.findViewById(R.id.btn_flickr);
		blog = (ImageButton) this.findViewById(R.id.btn_blog);
		lab = (ImageButton) this.findViewById(R.id.btn_lab);
		
		github.setOnClickListener(this);
		facebook.setOnClickListener(this);
		twitter.setOnClickListener(this);
		instagram.setOnClickListener(this);
		gplus.setOnClickListener(this);
		linkedin.setOnClickListener(this);
		behance.setOnClickListener(this);
		flickr.setOnClickListener(this);
		blog.setOnClickListener(this);
		lab.setOnClickListener(this);
	}

	private void openUrl(String url) {
		Intent internetIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		internetIntent.setComponent(new ComponentName("com.android.browser",
				"com.android.browser.BrowserActivity"));
		internetIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		getContext().startActivity(internetIntent);
	}

	@Override
	public void onClick(View v) {
		if (v == github) {
			// TODO
			openUrl("https://github.com/eneim");
			return;
		}

		if (v == facebook) {
			// TODO
			openUrl("https://www.facebook.com/butchigo");
			return;
		}

		if (v == twitter) {
			// TODO
			openUrl("https://twitter.com/eneim_");
			return;
		}

		if (v == instagram) {
			// TODO
			openUrl("http://instagram.com/eneim");
			return;
		}

		if (v == flickr) {
			// TODO
			openUrl("https://www.flickr.com/photos/eneim/");
			return;
		}

		if (v == gplus) {
			// TODO
			openUrl("https://plus.google.com/u/0/112893993025681477916/");
			return;
		}

		if (v == linkedin) {
			// TODO
			openUrl("https://www.linkedin.com/profile/view?id=106126207");
			return;
		}

		if (v == behance) {
			// TODO
			openUrl("https://www.behance.net/eneim");
			return;
		}
		
		if (v == blog) {
			// TODO
			openUrl("http://photo.ene.im");
			return;
		}
		
		if (v == lab) {
			openUrl("http://ene.im");
			return;
		}
	}
}
