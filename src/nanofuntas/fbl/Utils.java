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
	
	private static SharedPreferences mSettings;
	private static SharedPreferences.Editor mEditor;
	
	public static void setMyUid(long uid) {
		if (DEBUG) Log.d(TAG, "setMyUid(), uid = " + uid);
		mEditor.putLong(Config.KEY_UID, uid);
		mEditor.commit();
	}
	
	public static long getMyUid() {
    	long uid = mSettings.getLong(Config.KEY_UID, 0);
    	if (DEBUG) Log.d(TAG, "getMyUid() = " + uid);
    	return uid;
	}
	
	public static void setMyTid(long tid) {
		if (DEBUG) Log.d(TAG, "setMyTid(), uid = " + tid);
		mEditor.putLong(Config.KEY_TID, tid);
		mEditor.commit();
	}
	
	public static long getMyTid() {
		long tid = mSettings.getLong(Config.KEY_TID, 0);	
		if (DEBUG) Log.d(TAG, "getMyTid() = " + tid);
		return tid;
	}
	
	public static void setMyLoginID(String loginId) {
		if (DEBUG) Log.d(TAG, "setMyLoginId(), loginId = " + loginId);
		mEditor.putString(Config.KEY_LOGIN_ID, loginId);
		mEditor.commit();
	}
	
	public static String getMyLoginID() {
		String  loginId = mSettings.getString(Config.KEY_LOGIN_ID, "NULL");	
		if (DEBUG) Log.d(TAG, "getMyLoginId() = " + loginId);
		return loginId;
	}
	
	public static void setMyLoginPW(String pw) {
		if (DEBUG) Log.d(TAG, "setMyLoginPW(), pw = " + pw);
		mEditor.putString(Config.KEY_LOGIN_PW, pw);
		mEditor.commit();
	}
	
	public static String getMyLoginPW() {
		String  pw = mSettings.getString(Config.KEY_LOGIN_PW, "NULL");	
		if (DEBUG) Log.d(TAG, "getMyLoginPW() = " + pw);
		return pw;
	}
	
	public static void removeLoginIdPw() {
		if (DEBUG) Log.d(TAG, "removeLoginIdPw()");
		mEditor.remove(Config.KEY_LOGIN_ID);
		mEditor.remove(Config.KEY_LOGIN_PW);
		mEditor.commit();
	}
	
	public static void initSharedPreference(Context context) {
		if (DEBUG) Log.d(TAG, "initSharedPreference()");
		mSettings = context.getSharedPreferences(Config.FBL_SETTINGS, 0);
		mEditor = mSettings.edit();
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
