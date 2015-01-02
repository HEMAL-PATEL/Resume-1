package im.ene.lab.resume.utils;

import im.ene.lab.resume.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.util.Linkify;
import android.util.TypedValue;
import android.view.View;
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
				+ "<p>The MIT License (MIT)</p>"
				+ "<p>Copyright (c) 2014 Aidan Michael Follestad</p>"
				+ "<p>Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
                "of this software and associated documentation files (the \"Software\"), to deal\n" +
                "in the Software without restriction, including without limitation the rights\n" +
                "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
                "copies of the Software, and to permit persons to whom the Software is\n" +
                "furnished to do so, subject to the following conditions:</p>"

				+ "<p>The above copyright notice and this permission notice shall be included in all\n" +
                "copies or substantial portions of the Software.</p>"

                + "<p>THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
                "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
                "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
                "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
                "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
                "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\n" +
                "SOFTWARE.</p>"

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
