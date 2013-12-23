package nanofuntas.fbl;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class SplashScreenActivity extends Activity {
	private final boolean DEBUG = true;
	private final String TAG = "SplashScreenActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		FblSQLiteHelper db = new FblSQLiteHelper(this);
		
		db.addPlayerRating(makePlayerRating(12));
		
		PlayerRating pr = db.getPlayerRating(12);
	}

	//TODO: test method
	private PlayerRating makePlayerRating(int i) {
		PlayerRating pr = new PlayerRating();
		pr.setUid(i);
		pr.setAttack(i);
		pr.setDefense(i);
		pr.setTeamwork(i);
		pr.setMental(i);
		pr.setPower(i);
		pr.setSpeed(i);
		pr.setStamina(i);
		pr.setBallControl(i);
		pr.setPass(i);
		pr.setShot(i);
		pr.setHeader(i);
		pr.setCutting(i);
		pr.setOverall(i);
		return pr;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

}
