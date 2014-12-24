package im.ene.lab.resume.widgets;


public interface TabHolderScrollingContent {

	/**
     * Adjust content scroll position based on sticky tab bar position.
     */
    void adjustScroll(int tabBarTop, int maxSroll);

}
