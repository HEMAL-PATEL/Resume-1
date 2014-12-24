package im.ene.lab.resume.utils;

import im.ene.lab.resume.R;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.TypedValue;

public class Utils {

	private Utils() {

	}

	public static boolean isLollipop() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
	}

	public static void showLisence(Context context) {
//		if (!Utils.isLollipop()) {
//			final MaterialDialog dialog = new MaterialDialog(context);
//			dialog.setTitle("Lisence");
//
//			TextView mess = new TextView(context);
//			mess.setMovementMethod(new ScrollingMovementMethod());
//			mess.setLineSpacing(2f, 1);
//			mess.setText("As for this application's purpose, I strongly use resume official application's resources, including its color scheme, dimension values, strings values, some item view layouts and its tab bar icon design.\n\n"
//					+ "In addition, I use Picasso to show beautiful Images efficiently, Android Query to easily inflate and update widgets. ViewPagerIndicator is used in the main ViewPager. This dialog and others were built by a simple Material Dialog library (I would like to recommend another/better one, but not now). RecyclerView's items have awesome ripple effect based on MaterialRippleLayout library. Finally, this app was built based on Google latest AppCompat/Support Library ver 7, build 21 which adds RecyclerView and other awesome features from Android Lollipop.");
//			dialog.setContentView(mess);
//
//			dialog.setPositiveButton("Close", new View.OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					dialog.dismiss();
//				}
//			});
//			dialog.show();
//		} else {
//			AlertDialog.Builder builder = new AlertDialog.Builder(context);
//			builder.setTitle("Lisence");
//			builder.setMessage("As for this application's purpose, I strongly use resume official application's resources, including its color scheme, dimension values, strings values, some item view layouts and its tab bar icon design.\n\n"
//					+ "In addition, I use Picasso to show beautiful Images efficiently, Android Query to easily inflate and update widgets. ViewPagerIndicator is used in the main ViewPager. This dialog and others were built by a simple Material Dialog library (I would like to recommend another/better one, but not now). RecyclerView's items have awesome ripple effect based on MaterialRippleLayout library. Finally, this app was built based on Google latest AppCompat/Support Library ver 7, build 21 which adds RecyclerView and other awesome features from Android Lollipop.");
//			builder.setPositiveButton("Close", new OnClickListener() {
//
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.dismiss();
//				}
//			});
//
//			builder.create().show();
//		}
	}

	public static void act(Context context) {
//		if (!Utils.isLollipop()) {
//			final MaterialDialog dialog = new MaterialDialog(context);
//			dialog.setTitle("Hi resume <3");
//			dialog.setMessage("Nam hasn't finished me yet. But I think he wants to. What do you think?\n\nThanks for using me.\nHave a nice day ^^");
//			dialog.setPositiveButton(context.getString(android.R.string.ok),
//					new View.OnClickListener() {
//
//						@Override
//						public void onClick(View v) {
//							dialog.dismiss();
//						}
//					});
//			dialog.show();
//		} else {
//			AlertDialog.Builder builder = new AlertDialog.Builder(context);
//			builder.setTitle("Hi resume <3");
//			builder.setMessage("Nam hasn't finished me yet. But I think he wants to. What do you think?\n\nThanks for using me.\nHave a nice day ^^");
//			builder.setPositiveButton("Ok", new OnClickListener() {
//
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.dismiss();
//				}
//			});
//
//			builder.create().show();
//		}
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
