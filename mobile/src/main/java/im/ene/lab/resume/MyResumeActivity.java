package im.ene.lab.resume;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.squareup.picasso.Picasso;

import im.ene.lab.resume.adapters.ResumePagerAdapter;
import im.ene.lab.resume.widgets.AlphaForegroundColorSpan;
import im.ene.lab.resume.widgets.CircleTransform;
import im.ene.lab.resume.widgets.KenBurnsView;
import im.ene.lab.resume.widgets.ObservableHorizontalScrollView;
import im.ene.lab.resume.widgets.ObservableScrollView.OnScrollChangedListener;
import im.ene.lab.resume.widgets.TabHolderScrollingContent;

@SuppressLint("NewApi")
public class MyResumeActivity extends ActionBarActivity implements
        OnScrollChangedListener {

    private int mActionBarTitleColor;

    private View mHeader;
    private static int mHeaderHeight;
    private KenBurnsView mHeaderPicture;
    private ImageView mHeaderLogo;

    private AlphaForegroundColorSpan mAlphaForegroundColorSpan;
    private SpannableString mSpannableString;

    private ViewPager mViewPager;
    private static PagerSlidingTabStrip mPagerTabs;
    private ResumePagerAdapter mPagerAdapter;

    private ObservableHorizontalScrollView mSocialButton;

    private Toolbar mToolbar;

    private TextView text_name, text_short_description, text_location;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            mToolbar.setLogo(R.drawable.ic_menu_home);
        }

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        text_name = (TextView) findViewById(R.id.text_name);
        text_short_description = (TextView) findViewById(R.id.text_short_description);
        text_location = (TextView) findViewById(R.id.text_location);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerTabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        mViewPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        mPagerAdapter = new ResumePagerAdapter(getSupportFragmentManager(),
                this);
        mViewPager.setAdapter(mPagerAdapter);

        mViewPager.setOnPageChangeListener(mPageChangeListener);
        mPagerTabs.setOnPageChangeListener(mPageChangeListener);
        mPagerTabs.setViewPager(mViewPager);

        mSocialButton = (ObservableHorizontalScrollView) findViewById(R.id.social_buttons_group);

        /**
         * actionbar magic
         */
        // mHeaderPlaceHolder = findViewById(R.id.view_header_placeholder);
        mHeader = findViewById(R.id.header);
        mHeaderHeight = getResources().getDimensionPixelSize(
                R.dimen.height_cv_cover_image)
                + mPagerTabs.getHeight();

        mHeaderPicture = (KenBurnsView) findViewById(R.id.image_cover);

//		mHeaderPicture.setResourceIds(R.drawable.img_nakama,
//				R.drawable.facebook_avatar);

        mHeaderPicture.setResourceIds(R.drawable.img_nakama,
                R.drawable.facebook_avatar, R.drawable.simple,
                R.drawable.code_for_fun, R.drawable.tedx,
                R.drawable.titech);

        mHeaderLogo = (ImageView) findViewById(R.id.image_avatar);

        mActionBarTitleColor = getResources().getColor(
                R.color.actionbar_title_color);

        mSpannableString = new SpannableString(
                getString(R.string.noboringactionbar_title));
        mAlphaForegroundColorSpan = new AlphaForegroundColorSpan(
                mActionBarTitleColor);

        ColorDrawable toolbarColor = new ColorDrawable(getResources().getColor(
                R.color.tool_bar_want));
        toolbarColor.setAlpha(0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            mToolbar.setBackground(toolbarColor);
        else
            mToolbar.setBackgroundDrawable(toolbarColor);

        setTitleAlpha(0.0f);

    }

    private OnPageChangeListener mPageChangeListener = new OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            mViewPager.getParent().requestDisallowInterceptTouchEvent(true);
        }

        @Override
        public void onPageSelected(int position) {
            // Adjust the the ViewPager's Fragments' scroll position
            SparseArrayCompat<TabHolderScrollingContent> fragmentContent = mPagerAdapter
                    .getTabHolderScrollingContent();
            TabHolderScrollingContent content = fragmentContent
                    .valueAt(position);
//            content.adjustScroll(
//                    (int) (mHeaderHeight - mPagerTabs.getHeight() + ViewCompat
//                            .getTranslationY(mHeader)), mHeaderHeight
//                            - mPagerTabs.getHeight());

        }

    };

    @Override
    protected void onStart() {
        super.onStart();
        Picasso.with(this).load(R.drawable.head)
                .transform(new CircleTransform()).into(mHeaderLogo);

        text_name.setText("Nguyen Hoai Nam");
        text_short_description.setText("the simple, the best.");
        text_location.setText("Tokyo, Japan");

        mSocialButton
                .setOnScrollChangedListener(new ObservableHorizontalScrollView.OnScrollChangedListener() {

                    @Override
                    public void onScrollChanged(final HorizontalScrollView who,
                                                int l, int t, int oldl, int oldt) {
                        Log.d("scroll_finished", l + " | " + oldl);
                    }
                });

        mSocialButton.postDelayed(new Runnable() {

            @Override
            public void run() {
                mSocialButton.smoothScrollTo(
                        mSocialButton.getRight(), 0);
            }
        }, 500);

    }

    private void setTitleAlpha(float alpha) {
        mAlphaForegroundColorSpan.setAlpha(alpha);
        mSpannableString.setSpan(mAlphaForegroundColorSpan, 0,
                mSpannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(mSpannableString);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
        final int headerHeight = mHeaderHeight - mPagerTabs.getHeight()
                - mToolbar.getHeight();
        final float ratio = (float) Math.min(Math.max(t, 0), headerHeight)
                / headerHeight;

//        ViewCompat.setTranslationY(mHeader,
//                Math.max(-t, -(mPagerTabs.getHeight() + headerHeight)));

        ColorDrawable toolbarColor = new ColorDrawable(getResources().getColor(
                R.color.tool_bar_want));
        toolbarColor.setAlpha((int) (ratio * 255));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            mToolbar.setBackground(toolbarColor);
        else
            mToolbar.setBackgroundDrawable(toolbarColor);

        setTitleAlpha(ratio);
    }
}
