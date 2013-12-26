package nanofuntas.fbl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.simple.JSONObject;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;

public class SplashScreenActivity extends Activity {
	private final boolean DEBUG = true;
	private final String TAG = "SplashScreenActivity";
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (DEBUG) Log.d(TAG, "onCreate()");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		FblSQLiteHelper db = new FblSQLiteHelper(this);
		db.dropAllTables();
		db.createAllTables();

		long tid = Utils.getMyTid();
    	final JSONObject jsonMembersStatus = ServerIface.getMembersStatus(tid);

    	new LoadImageThread(jsonMembersStatus).start();    	
    	savePlayerStatusToDB(jsonMembersStatus, db);
		
    	Intent i = new Intent(SplashScreenActivity.this, TabViewActivity.class);
		startActivity(i);
		finish();
	}
	
	private class LoadImageThread extends Thread {
		private JSONObject jsonMembersStatus;
		LoadImageThread(JSONObject jsonMembersStatus) {
			this.jsonMembersStatus = jsonMembersStatus;
		}
		
		@Override
		public void run() {
			if (DEBUG) Log.d(TAG, "start loadImage thread");
			final long count = (Long) jsonMembersStatus.get(Config.KEY_MEMBERS_COUNT);    	
	    	for (long i = 1; i <= count; i++) {
				JSONObject status = (JSONObject) jsonMembersStatus.get(Long.toString(i));
				long uid = (Long)status.get(Config.KEY_UID);
				byte[] byteArray = ServerIface.downloadImage(uid);
				Bitmap bitmap = null;
				if (byteArray != null) {
					bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
				}
				if (bitmap != null)
					saveProfileBitmap(bitmap, uid);
			}
		}
	}
	
	private void savePlayerStatusToDB(JSONObject jsonMembersStatus, FblSQLiteHelper db) {
    	if (DEBUG) Log.d(TAG, "savePlayerStatusToDB()");
		final long count = (Long) jsonMembersStatus.get(Config.KEY_MEMBERS_COUNT);    	
		for (long i = 1; i <= count; i++) {
    		JSONObject status = (JSONObject) jsonMembersStatus.get(Long.toString(i));
    		
    		long uid = (Long)status.get(Config.KEY_UID);
        	long atkRating = (Long)status.get(Config.KEY_ATTACK);
        	long dfsRating = (Long)status.get(Config.KEY_DEFENSE);
        	long twkRating = (Long)status.get(Config.KEY_TEAMWORK);
        	long mtlRating = (Long)status.get(Config.KEY_MENTAL);
        	long powRating = (Long)status.get(Config.KEY_POWER);
        	long spdRating = (Long)status.get(Config.KEY_SPEED);
        	long staRating = (Long)status.get(Config.KEY_STAMINA);
        	long blcRating = (Long)status.get(Config.KEY_BALL_CONTROL);
        	long pasRating = (Long)status.get(Config.KEY_PASS);
        	long shtRating = (Long)status.get(Config.KEY_SHOT);
        	long hdrRating = (Long)status.get(Config.KEY_HEADER);
        	long cutRating = (Long)status.get(Config.KEY_CUTTING);
        	long ovrRating = (Long)status.get(Config.KEY_OVERALL);
        	
        	PlayerRating pr = new PlayerRating();
        	pr.setUid(uid);
        	pr.setAttack(atkRating);
    		pr.setDefense(dfsRating);
    		pr.setTeamwork(twkRating);
    		pr.setMental(mtlRating);
    		pr.setPower(powRating);
    		pr.setSpeed(spdRating);
    		pr.setStamina(staRating);
    		pr.setBallControl(blcRating);
    		pr.setPass(pasRating);
    		pr.setShot(shtRating);
    		pr.setHeader(hdrRating);
    		pr.setCutting(cutRating);
    		pr.setOverall(ovrRating);
    		
    		db.addPlayerRating(pr);
    		
    		String name = (String)status.get(Config.KEY_NAME);
    		String position = (String)status.get(Config.KEY_POSITION);
    		
    		PlayerProfile pp = new PlayerProfile();
    		pp.setUid(uid);
    		pp.setName(name);
    		pp.setPosition(position);
    		
    		db.addPlayerProfile(pp);
    	}
	}
	
	private void saveProfileBitmap(Bitmap bitmap, long uid) {
		if (DEBUG) Log.d(TAG, "saveProfileBitmap(), uid=" + uid);
		
		String root = Environment.getExternalStorageDirectory().toString();
		String myAppName = "FBL";

		File imageDir = new File(root+ "/" + myAppName);
		if (!imageDir.exists()) 
			imageDir.mkdir();
		
		File imagePath = new File(root+ "/" + myAppName + "/" + uid + ".png");
		if (imagePath.exists ()) 
			imagePath.delete(); 
		
		try {
			FileOutputStream fos = new FileOutputStream(imagePath);
			bitmap.compress(CompressFormat.PNG, 100, fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}
}
