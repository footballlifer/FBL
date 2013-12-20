package nanofuntas.fbl;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Utils {
	private final static boolean DEBUG = true;
	private final static String TAG = "Utils";
	
	private static SharedPreferences settings;
	private static SharedPreferences.Editor editor;
	
	public static void setMyUid(Context context, long uid) {
		if (DEBUG) Log.d(TAG, "setMyUid(), uid = " + uid);
		
		initSharedPreference(context);
		editor.putLong(Config.KEY_UID, uid);
		editor.commit();
	}

	public static void setMyTid(Context context, long tid) {
		if (DEBUG) Log.d(TAG, "setMyTid(), uid = " + tid);
		
		initSharedPreference(context);
		editor.putLong(Config.KEY_TID, tid);
		editor.commit();
	}
	
	private static void initSharedPreference(Context context) {
		if (DEBUG) Log.d(TAG, "initSharedPreference()");
		
		settings = context.getSharedPreferences(Config.FBL_SETTINGS, 0);
		editor = settings.edit();
	}	
}
