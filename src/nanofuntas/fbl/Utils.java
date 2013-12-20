package nanofuntas.fbl;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Utils {
	private final static boolean DEBUG = true;
	private final static String TAG = "Utils";
	
	private static SharedPreferences settings;
	private static SharedPreferences.Editor editor;
	
	public static void setMyUid(long uid) {
		if (DEBUG) Log.d(TAG, "setMyUid(), uid = " + uid);
		editor.putLong(Config.KEY_UID, uid);
		editor.commit();
	}
	
	public static long getMyUid() {
    	long uid = settings.getLong(Config.KEY_UID, 0);
    	Log.d(TAG, "getMyUid() = " + uid);
    	return uid;
	}
	
	public static void setMyTid(long tid) {
		if (DEBUG) Log.d(TAG, "setMyTid(), uid = " + tid);
		editor.putLong(Config.KEY_TID, tid);
		editor.commit();
	}
	
	public static long getMyTid() {
		long tid = settings.getLong(Config.KEY_TID, 0);	
		Log.d(TAG, "getMyTid() = " + tid);
		return tid;
	}
	
	public static void setMyLoginID(String loginId) {
		if (DEBUG) Log.d(TAG, "setMyLoginId(), loginId = " + loginId);
		editor.putString(Config.KEY_LOGIN_ID, loginId);
		editor.commit();
	}
	
	public static String getMyLoginID() {
		String  loginId = settings.getString(Config.KEY_LOGIN_ID, "NULL");	
		Log.d(TAG, "getMyLoginId() = " + loginId);
		return loginId;
	}
	
	public static void setMyLoginPW(String pw) {
		if (DEBUG) Log.d(TAG, "setMyLoginPW(), pw = " + pw);
		editor.putString(Config.KEY_LOGIN_PW, pw);
		editor.commit();
	}
	
	public static String getMyLoginPW() {
		String  pw = settings.getString(Config.KEY_LOGIN_PW, "NULL");	
		Log.d(TAG, "getMyLoginPW() = " + pw);
		return pw;
	}
	
	public static void initSharedPreference(Context context) {
		if (DEBUG) Log.d(TAG, "initSharedPreference()");
		settings = context.getSharedPreferences(Config.FBL_SETTINGS, 0);
		editor = settings.edit();
	}
}
