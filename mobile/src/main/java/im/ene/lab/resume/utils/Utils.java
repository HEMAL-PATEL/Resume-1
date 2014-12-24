package im.ene.lab.resume.utils;

import im.ene.lab.resume.R;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.util.Linkify;
import android.util.TypedValue;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

public class Utils {

	private Utils() {

	}

	public static boolean isLollipop() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
	}

	public static void showLisence(Context context) {
        String html_license =
                // Picasso
                "<p><strong>Picasso<strong></p>"
                        + "<p>Copyright 2013 Square, Inc."
                        + "<p>Licensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License. You may obtain a copy of the License at</p>"
                        + "<p>http://www.apache.org/licenses/LICENSE-2.0</p>"
                        + "<p>Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.</p>"

                        // Material Dialog
                        + "<p>Material Dialog</p>"
                        + "<p>Copyright 2014 drakeet</p>"
                        + "<p>Licensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License. You may obtain a copy of the License at</p>"
                        + "<p>http://www.apache.org/licenses/LICENSE-2.0</p>"
                        + "<p>Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.</p>"

                        // Android PagerSlidingTabStrip
                        + "<p>Android PagerSlidingTabStrip</p>"
                        + "<p>Copyright 2013 Andreas Stuetz</p>"
                        + "<p>Licensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License. You may obtain a copy of the License at</p>"
                        + "<p>http://www.apache.org/licenses/LICENSE-2.0</p>"
                        + "<p>Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.</p>"

                        // KenBurnsView
                        + "<p>Copyright 2013 Flavien Laurent</p>"
                        + "<p>Licensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License. You may obtain a copy of the License at</p>"
                        + "<p>http://www.apache.org/licenses/LICENSE-2.0</p>"
                        + "<p>Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.</p>"

                ;
        Spanned text_license = Html.fromHtml(html_license);
        TextView mess = new TextView(context);
        mess.setMovementMethod(new ScrollingMovementMethod());
        mess.setText(text_license);
        mess.setAutoLinkMask(Linkify.WEB_URLS);

        new MaterialDialog.Builder(context)
                .title("License")
                .customView(mess)
                .negativeText("Close")
                .show();
	}

	public static void act(Context context) {
        new MaterialDialog.Builder(context)
                .title("Hi resume <3")
                .content("Nam hasn't finished me yet. But I think he wants to. What do you think?\n\nThanks for using me.\nHave a nice day ^^")
                .negativeText("Close")
                .show();

	}

	public static int getActionBarHeight(Activity activity) {
		int actionBarHeight = 0;

		// Calculate ActionBar height
		TypedValue mTypedValue = new TypedValue();

		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
			activity.getTheme().resolveAttribute(android.R.attr.actionBarSize,
					mTypedValue, true);
		} else {
			activity.getTheme().resolveAttribute(R.attr.actionBarSize,
					mTypedValue, true);
		}

		actionBarHeight = TypedValue.complexToDimensionPixelSize(
				mTypedValue.data, activity.getResources().getDisplayMetrics());

		return actionBarHeight;
	}

	public static int getPixelFromDP(Context context, float dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				context.getResources().getDisplayMetrics());
	}

}
