package im.ene.lab.resume;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.crashlytics.android.Crashlytics;
import com.squareup.picasso.Picasso;

import im.ene.lab.resume.adapters.ResumePagerAdapter;
import im.ene.lab.resume.backend.myApi.MyApi;
import im.ene.lab.resume.fragments.NavigationDrawerFragment;
import im.ene.lab.resume.fragments.NavigationDrawerFragment.NavigationDrawerCallbacks;
import im.ene.lab.resume.utils.Utils;
import im.ene.lab.resume.widgets.AlphaForegroundColorSpan;
import im.ene.lab.resume.widgets.CircleTransform;
import im.ene.lab.resume.widgets.KenBurnsView;
import im.ene.lab.resume.widgets.ObservableHorizontalScrollView;
import im.ene.lab.resume.widgets.ObservableScrollView.OnScrollChangedListener;
import im.ene.lab.resume.widgets.ScrimInsetsFrameLayout;
import im.ene.lab.resume.widgets.TabHolderScrollingContent;
import io.fabric.sdk.android.Fabric;

@SuppressLint("NewApi")
public class MyResumeActivity extends ActionBarActivity implements
        OnScrollChangedListener, NavigationDrawerCallbacks, ScrimInsetsFrameLayout.OnInsetsCallback {

    private int mActionBarTitleColor;

    private View mHeader;
    private static int mHeaderHeight, mActionBarHeight, mStatusBarHeight;
    private KenBurnsView mHeaderPicture;
    private ImageView mHeaderLogo;

    private AlphaForegroundColorSpan mAlphaForegroundColorSpan;
    private SpannableString mSpannableString;

    private ViewPager mViewPager;
    private static PagerSlidingTabStrip mPagerTabs;
    private ResumePagerAdapter mPagerAdapter;

    private DrawerLayout mDrawerLayout;
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private ObservableHorizontalScrollView mSocialButton;

    private Toolbar mToolbar;
    private Window mWindows;

    private float mLastAlphaRatio;

    private TextView text_name, text_short_description, text_location;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_cv);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);

            mActionBarHeight = mToolbar.getHeight();
        }

        getSupportActionBar().setTitle("");
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setHomeButtonEnabled(true);

        // dealing with status bar:
        mWindows = getWindow();
        mWindows.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        mWindows.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ScrimInsetsFrameLayout scrimInsetsFrameLayout = (ScrimInsetsFrameLayout)
                findViewById(R.id.capture_insets_frame_layout);
        scrimInsetsFrameLayout.setOnInsetsCallback(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, mDrawerLayout,
                mToolbar);

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

        // mHeaderPicture.setResourceIds(R.drawable.img_nakama,
        // R.drawable.facebook_avatar);

        mHeaderPicture.setResourceIds(R.drawable.img_nakama,
                R.drawable.facebook_avatar, R.drawable.simple,
                R.drawable.code_for_fun, R.drawable.tedx, R.drawable.titech);

        mHeaderLogo = (ImageView) findViewById(R.id.image_avatar);

        mActionBarTitleColor = getResources().getColor(
                R.color.actionbar_title_color);

        mSpannableString = new SpannableString(
                getString(R.string.noboringactionbar_title));
        mAlphaForegroundColorSpan = new AlphaForegroundColorSpan(
                mActionBarTitleColor);

        mLastAlphaRatio = 0.0f;
        ColorDrawable toolbarColor = new ColorDrawable(getResources().getColor(
                R.color.tool_bar_want));
        toolbarColor.setAlpha(0);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            mToolbar.setBackground(toolbarColor);
        else
            mToolbar.setBackgroundDrawable(toolbarColor);

        ColorDrawable statusBarColor = new ColorDrawable(getResources().getColor(R.color.tool_bar_want_dark));
        statusBarColor.setAlpha(0);

        if (Utils.isLollipop())
            mWindows.setStatusBarColor(statusBarColor.getColor());

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

            if (content != null)
                content.adjustScroll(
                        (int) (mHeaderHeight - mPagerTabs.getHeight() + ViewCompat
                                .getTranslationY(mHeader)), mHeaderHeight
                                - mPagerTabs.getHeight());

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
                mSocialButton.smoothScrollTo(mSocialButton.getRight(), 0);
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

        mLastAlphaRatio = ratio;

        ViewCompat.setTranslationY(mHeader,
                Math.max(-t, -(mPagerTabs.getHeight() + headerHeight - mStatusBarHeight)));

        ColorDrawable toolbarColor = new ColorDrawable(getResources().getColor(
                R.color.tool_bar_want));
        toolbarColor.setAlpha((int) (ratio * 255));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            mToolbar.setBackground(toolbarColor);
        else
            mToolbar.setBackgroundDrawable(toolbarColor);

        ColorDrawable statusBarColor = new ColorDrawable(getResources().getColor(R.color.tool_bar_want_dark));
        statusBarColor.setAlpha((int) (ratio * 255));

        if (Utils.isLollipop())
            mWindows.setStatusBarColor(statusBarColor.getColor());

        setTitleAlpha(ratio);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if (position == 1)
            Utils.showLisence(this);
    }

    @Override
    public void onNavigationPanelOpened() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onNavigationPanelSliding(float slideOffset) {
        // Toast.makeText(this, slideOffset + "", Toast.LENGTH_SHORT).show();

        ColorDrawable toolbarColor = new ColorDrawable(getResources().getColor(
                R.color.tool_bar_want));

        float ratio = Math.max(slideOffset, mLastAlphaRatio);

        toolbarColor.setAlpha((int) (ratio * 255));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            mToolbar.setBackground(toolbarColor);
        else
            mToolbar.setBackgroundDrawable(toolbarColor);

        ColorDrawable statusBarColor = new ColorDrawable(getResources().getColor(R.color.tool_bar_want_dark));
        statusBarColor.setAlpha((int) (ratio * 255));

        if (Utils.isLollipop())
            mWindows.setStatusBarColor(statusBarColor.getColor());

        setTitleAlpha(ratio);
    }

    @Override
    public void onInsetsChanged(Rect insets) {
        mStatusBarHeight = insets.top;
        Toolbar toolbar = mToolbar;
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams)
                toolbar.getLayoutParams();
        lp.topMargin = insets.top;
        int top = insets.top;
        insets.top += mToolbar.getHeight();
        toolbar.setLayoutParams(lp);
        insets.top = top;
    }
}