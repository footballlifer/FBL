package nanofuntas.fbl;

import org.json.simple.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SplashScreenActivity extends Activity {
	private final boolean DEBUG = true;
	private final String TAG = "SplashScreenActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		FblSQLiteHelper db = new FblSQLiteHelper(this);
		
		db.dropPlayerRatingTable();
		db.createPlayerRatingTable();

		long tid = Utils.getMyTid();
    	JSONObject jsonMembersStatus = ServerIface.getMembersStatus(tid);
    	long count = (Long) jsonMembersStatus.get(Config.KEY_MEMBERS_COUNT);    	

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
    	}
		
    	Intent i = new Intent(SplashScreenActivity.this, TabViewActivity.class);
		startActivity(i);
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}
}
