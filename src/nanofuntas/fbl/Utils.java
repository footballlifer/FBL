package nanofuntas.fbl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

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
    	if (DEBUG) Log.d(TAG, "getMyUid() = " + uid);
    	return uid;
	}
	
	public static void setMyTid(long tid) {
		if (DEBUG) Log.d(TAG, "setMyTid(), uid = " + tid);
		editor.putLong(Config.KEY_TID, tid);
		editor.commit();
	}
	
	public static long getMyTid() {
		long tid = settings.getLong(Config.KEY_TID, 0);	
		if (DEBUG) Log.d(TAG, "getMyTid() = " + tid);
		return tid;
	}
	
	public static void setMyLoginID(String loginId) {
		if (DEBUG) Log.d(TAG, "setMyLoginId(), loginId = " + loginId);
		editor.putString(Config.KEY_LOGIN_ID, loginId);
		editor.commit();
	}
	
	public static String getMyLoginID() {
		String  loginId = settings.getString(Config.KEY_LOGIN_ID, "NULL");	
		if (DEBUG) Log.d(TAG, "getMyLoginId() = " + loginId);
		return loginId;
	}
	
	public static void setMyLoginPW(String pw) {
		if (DEBUG) Log.d(TAG, "setMyLoginPW(), pw = " + pw);
		editor.putString(Config.KEY_LOGIN_PW, pw);
		editor.commit();
	}
	
	public static String getMyLoginPW() {
		String  pw = settings.getString(Config.KEY_LOGIN_PW, "NULL");	
		if (DEBUG) Log.d(TAG, "getMyLoginPW() = " + pw);
		return pw;
	}
	
	public static void removeLoginIdPw() {
		if (DEBUG) Log.d(TAG, "removeLoginIdPw()");
		editor.remove(Config.KEY_LOGIN_ID);
		editor.remove(Config.KEY_LOGIN_PW);
		editor.commit();
	}
	
	public static void initSharedPreference(Context context) {
		if (DEBUG) Log.d(TAG, "initSharedPreference()");
		settings = context.getSharedPreferences(Config.FBL_SETTINGS, 0);
		editor = settings.edit();
	}
	
	public static Bitmap getProfileImage(long uid) {
		if (DEBUG) Log.d(TAG, "getProfileImage(), uid=" + uid);
		String root = Environment.getExternalStorageDirectory().toString();
    	String myAppName = "FBL";
    	String imagePath = root + "/" + myAppName + "/" + uid + ".png";
    	File image = new File(imagePath);
    	FileInputStream fis = null;
    	try {
			fis = new FileInputStream(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	Bitmap bitmap = BitmapFactory.decodeStream(fis);
    	return bitmap;
	}
		
	public static void setTextAndColor(TextView tv, int rating) {
    	if( 0<= rating && rating <= 79) {
    		tv.setTextColor(Color.WHITE);
    	} else if (80 <= rating && rating <= 89) {
    		tv.setTextColor(Color.YELLOW);
    	} else if (90 <= rating && rating <= 100) {
    		tv.setTextColor(Color.RED);
    	}

    	tv.setText( Integer.toString(rating));

    }
}
