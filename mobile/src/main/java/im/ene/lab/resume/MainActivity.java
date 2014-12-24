package im.ene.lab.resume;

import im.ene.lab.resume.adapters.HomePagerAdapter;
import im.ene.lab.resume.fragments.NavigationDrawerFragment;
import im.ene.lab.resume.fragments.PickupFragment;
import im.ene.lab.resume.fragments.PickupFragment.OnContentScrolledListener;
import im.ene.lab.resume.utils.Utils;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import com.androidquery.AQuery;
import com.viewpagerindicator.UnderlinePageIndicator;


public class MainActivity extends ActionBarActivity implements
        NavigationDrawerFragment.NavigationDrawerCallbacks,
        OnContentScrolledListener, OnClickListener {
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private View mTopBarContainer;
    private CharSequence mTitle;

    private DrawerLayout mDrawerLayout;
    private View mHeaderPlaceHolder;

    private Toolbar mToolbar;

    /**
     * Main Content related items
     */
    private ViewPager mViewPager;
    private UnderlinePageIndicator mIndicator;
    private HomePagerAdapter adapter;

    private static final int[] mTabAreaIds;
    private static final int[] mTabIconIds;
    private static final int[] mTabIconsBlue;
    private static final int[] mTabIconsGray;
    private static final String[] mPageTitles;

    static {
        mTabAreaIds = new int[] { R.id.layout_tab_pickup,
                R.id.layout_tab_search, R.id.layout_tab_info,
                R.id.layout_tab_message, R.id.layout_tab_profile };
        mTabIconIds = new int[] { R.id.image_pickup, R.id.image_search,
                R.id.image_info, R.id.image_message, R.id.image_profile };
        mTabIconsBlue = new int[] { R.drawable.ic_tab_pickup,
                R.drawable.ic_tab_search, R.drawable.ic_tab_info,
                R.drawable.ic_tab_message, R.drawable.ic_tab_profile };
        mTabIconsGray = new int[] { R.drawable.ic_tab_pickup_gray,
                R.drawable.ic_tab_search_gray, R.drawable.ic_tab_info_gray,
                R.drawable.ic_tab_message_gray, R.drawable.ic_tab_profile_gray };
        mPageTitles = new String[] { "今日のトピック", "シゴトを探す", "お知らせ", "メッセージ",
                "プロフィール" };
    }

    /*
     * (non-Javadoc)
     *
     * @see android.support.v7.app.ActionBarActivity#onCreate(android.os.Bundle)
     *
     * headerview transition helper items
     */
    private int mHeaderHeight;
    private int mMinHeaderTranslation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null)
            setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navigation_drawer);
        mTitle = getString(R.string.app_name);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, mDrawerLayout,
                mToolbar);

        adapter = new HomePagerAdapter(getSupportFragmentManager(), this);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(adapter);
        mIndicator = (UnderlinePageIndicator) findViewById(R.id.indicator);

        mTopBarContainer = findViewById(R.id.top_bar_container);
        mHeaderHeight = Utils.getActionBarHeight(this);
        mMinHeaderTranslation = getResources().getDimensionPixelSize(
                R.dimen.toolbar_height);

        LayoutParams topMarginParams = (LayoutParams) mDrawerLayout
                .getLayoutParams();
        topMarginParams.topMargin = mHeaderHeight + mMinHeaderTranslation;

        mIndicator.setViewPager(mViewPager);
        mIndicator.setFades(false);
        mIndicator.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                setTabImage(position);
                onPageChanged(position);

                Fragment currentFragment = adapter.getItem(position);
                if (currentFragment instanceof PickupFragment) {
                    ((PickupFragment) currentFragment)
                            .setOnContentScrolledListener(MainActivity.this);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        for (int id : mTabAreaIds) {
            findViewById(id).setOnClickListener(this);
        }

        mIndicator.setCurrentItem(0);
        onPageChanged(0);
        setTabImage(0);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void setTabImage(int position) {
        AQuery aq = new AQuery(this);
        for (int i = 0; i < mTabIconIds.length; i++) {
            ((AQuery) aq.id(mTabIconIds[i])).image(mTabIconsGray[i]);
        }

        ((AQuery) aq.id(mTabIconIds[position])).image(mTabIconsBlue[position]);
    }

    @Override
    public void onClick(View v) {
        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
        }

        for (int i = 0; i < mTabAreaIds.length; i++) {
            if (v.getId() == mTabAreaIds[i]) {
                this.mViewPager.setCurrentItem(i);
                return;
            }
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if (position == 1) {
            Intent intent = new Intent(this, MyResumeActivity.class);
            startActivity(intent);
        } else if (position == 2) {
            Utils.showLisence(this);
        } else {
            Utils.act(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            setTitle(mTitle);
            return true;
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onPageChanged(int pagePos) {
        try {
            mTitle = mPageTitles[pagePos];
        } catch (Exception er) {
            er.printStackTrace();
            mTitle = getString(R.string.app_name);
        } finally {
            setTitle(mTitle);
        }
    }

    @Override
    public void onNavigationPanelOpened() {
        setTitle(getString(R.string.app_name));
    }

    private int mHeaderDiffToTal = 0;

    @Override
    public void onListScrolled(int dx, int dy) {
        if (dy >= 0) {
            mHeaderDiffToTal = Math.max(mHeaderDiffToTal - dy,
                    -mMinHeaderTranslation);
        } else {
            mHeaderDiffToTal = Math.min(
                    Math.max(mHeaderDiffToTal - dy, -mMinHeaderTranslation), 0);
        }

        Log.d("mHeaderDiffToTal", dy + " | " + mHeaderDiffToTal + " | "
                + (mHeaderHeight + mMinHeaderTranslation + mHeaderDiffToTal));

        ViewCompat.setTranslationY(mTopBarContainer, mHeaderDiffToTal);
        mDrawerLayout.post(new Runnable() {

            @Override
            public void run() {
                FrameLayout.LayoutParams topMarginParams = (LayoutParams) mDrawerLayout
                        .getLayoutParams();
                int newTop = mHeaderHeight + mMinHeaderTranslation + mHeaderDiffToTal;
                topMarginParams.topMargin = newTop;
                mDrawerLayout.setLayoutParams(topMarginParams);
            }
        });

        // mTopBarContainer.setTranslationY(mFooterDiffTotal);
    }

    @Override
    public void onFinishScrolled() {
        if (mHeaderDiffToTal > -mMinHeaderTranslation)
            mHeaderDiffToTal = 0;

        ObjectAnimator
                .ofFloat(mTopBarContainer, "translationY", mHeaderDiffToTal)
                .setDuration(200).start();

        mDrawerLayout.post(new Runnable() {

            @Override
            public void run() {
                FrameLayout.LayoutParams topMarginParams = (FrameLayout.LayoutParams) mDrawerLayout
                        .getLayoutParams();
                int newTop = mHeaderHeight + mMinHeaderTranslation + mHeaderDiffToTal;
                topMarginParams.topMargin = newTop;
                mDrawerLayout.setLayoutParams(topMarginParams);
            }
        });
    }

}
