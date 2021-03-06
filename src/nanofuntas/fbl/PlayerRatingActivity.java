package nanofuntas.fbl;

import org.json.simple.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerRatingActivity extends Activity {
	private final boolean DEBUG = true;
	private final String TAG = "PlayerRatingActivity";

	private final int TEN = 10;

	private TextView nameRating;

	private RatingBar attackRatingBar;
	private RatingBar defenseRatingBar;
	private RatingBar teamworkRatingBar;
	private RatingBar mentalRatingBar;
	private RatingBar powerRatingBar;
	private RatingBar speedRatingBar;
	private RatingBar staminaRatingBar;
	private RatingBar ballControlRatingBar;
	private RatingBar passRatingBar;
	private RatingBar shotRatingBar;
	private RatingBar headerRatingBar;
	private RatingBar cuttingRatingBar;

	private TextView attackRatingValue;
	private TextView defenseRatingValue;
	private TextView teamworkRatingValue;
	private TextView mentalRatingValue;
	private TextView powerRatingValue;
	private TextView speedRatingValue;
	private TextView staminaRatingValue;
	private TextView ballControlRatingValue;
	private TextView passRatingValue;
	private TextView shotRatingValue;
	private TextView headerRatingValue;
	private TextView cuttingRatingValue;

	private long mUid = -1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_player_rating);
		initViews();

		mUid = getIntent().getExtras().getLong(Config.KEY_UID);
		
		FblSQLiteHelper db = new FblSQLiteHelper(this);
		PlayerInfo.PlayerProfile pp = db.getPlayerProfile(mUid);    	    	
		nameRating.setText(pp.getName());		
	}
	
	@SuppressWarnings("unchecked")
	private void submit() {
		if (DEBUG) Log.d(TAG, "SubmitButton onClick()");
		
		JSONObject jsonRating = new JSONObject();
		
		jsonRating.put(Config.KEY_ATTACK, Long.valueOf(attackRatingValue.getText().toString()));
		jsonRating.put(Config.KEY_DEFENSE, Long.valueOf(defenseRatingValue.getText().toString()));
		jsonRating.put(Config.KEY_TEAMWORK, Long.valueOf(teamworkRatingValue.getText().toString()));
		jsonRating.put(Config.KEY_MENTAL, Long.valueOf(mentalRatingValue.getText().toString()));
		jsonRating.put(Config.KEY_POWER, Long.valueOf(powerRatingValue.getText().toString()));
		jsonRating.put(Config.KEY_SPEED, Long.valueOf(speedRatingValue.getText().toString()));
		jsonRating.put(Config.KEY_STAMINA, Long.valueOf(staminaRatingValue.getText().toString()));
		jsonRating.put(Config.KEY_BALL_CONTROL, Long.valueOf(ballControlRatingValue.getText().toString()));
		jsonRating.put(Config.KEY_PASS, Long.valueOf(passRatingValue.getText().toString()));
		jsonRating.put(Config.KEY_SHOT, Long.valueOf(shotRatingValue.getText().toString()));
		jsonRating.put(Config.KEY_HEADER, Long.valueOf(headerRatingValue.getText().toString()));
		jsonRating.put(Config.KEY_CUTTING, Long.valueOf(cuttingRatingValue.getText().toString()));
		
		String result = ServerIface.ratePlayer(mUid, jsonRating);
		if (result.equals(Config.KEY_OK)) {
			Toast.makeText(getApplicationContext(), "Submit successful", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void initViews() {
		if (DEBUG) Log.d(TAG, "initViews()");
		
		nameRating = (TextView) findViewById(R.id.name_rating);

		attackRatingBar = (RatingBar) findViewById(R.id.attack_rating_bar);
		defenseRatingBar = (RatingBar) findViewById(R.id.defense_rating_bar);
		teamworkRatingBar = (RatingBar) findViewById(R.id.teamwork_rating_bar);
		mentalRatingBar = (RatingBar) findViewById(R.id.mental_rating_bar);
		powerRatingBar = (RatingBar) findViewById(R.id.power_rating_bar);
		speedRatingBar = (RatingBar) findViewById(R.id.speed_rating_bar);
		staminaRatingBar = (RatingBar) findViewById(R.id.stamina_rating_bar);
		ballControlRatingBar = (RatingBar) findViewById(R.id.ball_control_rating_bar);
		passRatingBar = (RatingBar) findViewById(R.id.pass_rating_bar);
		shotRatingBar = (RatingBar) findViewById(R.id.shot_rating_bar);
		headerRatingBar = (RatingBar) findViewById(R.id.header_rating_bar);
		cuttingRatingBar = (RatingBar) findViewById(R.id.cutting_rating_bar);

		attackRatingBar.setOnRatingBarChangeListener(mRatingBarListener);
		defenseRatingBar.setOnRatingBarChangeListener(mRatingBarListener);
		teamworkRatingBar.setOnRatingBarChangeListener(mRatingBarListener);
		mentalRatingBar.setOnRatingBarChangeListener(mRatingBarListener);
		powerRatingBar.setOnRatingBarChangeListener(mRatingBarListener);
		speedRatingBar.setOnRatingBarChangeListener(mRatingBarListener);
		staminaRatingBar.setOnRatingBarChangeListener(mRatingBarListener);
		ballControlRatingBar.setOnRatingBarChangeListener(mRatingBarListener);
		passRatingBar.setOnRatingBarChangeListener(mRatingBarListener);
		shotRatingBar.setOnRatingBarChangeListener(mRatingBarListener);
		headerRatingBar.setOnRatingBarChangeListener(mRatingBarListener);
		cuttingRatingBar.setOnRatingBarChangeListener(mRatingBarListener);
		
		attackRatingValue = (TextView) findViewById(R.id.attack_rating_value);
		defenseRatingValue = (TextView) findViewById(R.id.defense_rating_value);
		teamworkRatingValue = (TextView) findViewById(R.id.teamwork_rating_value);
		mentalRatingValue = (TextView) findViewById(R.id.mental_rating_value);
		powerRatingValue = (TextView) findViewById(R.id.power_rating_value);
		speedRatingValue = (TextView) findViewById(R.id.speed_rating_value);
		staminaRatingValue = (TextView) findViewById(R.id.stamina_rating_value);
		ballControlRatingValue = (TextView) findViewById(R.id.ball_control_rating_value);
		passRatingValue = (TextView) findViewById(R.id.pass_rating_value);
		shotRatingValue = (TextView) findViewById(R.id.shot_rating_value);
		headerRatingValue = (TextView) findViewById(R.id.header_rating_value);
		cuttingRatingValue = (TextView) findViewById(R.id.cutting_rating_value);

	}

	private RatingBar.OnRatingBarChangeListener mRatingBarListener = 
			new RatingBar.OnRatingBarChangeListener() {

		public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
			if (ratingBar.getId() == attackRatingBar.getId()) {
				Utils.setTextAndColor(attackRatingValue, (int) (rating*TEN));
			} else if (ratingBar.getId() == defenseRatingBar.getId()) {
				Utils.setTextAndColor(defenseRatingValue, (int) (rating*TEN));
			} else if (ratingBar.getId() == teamworkRatingBar.getId()) {
				Utils.setTextAndColor(teamworkRatingValue, (int) (rating*TEN));
			} else if (ratingBar.getId() == mentalRatingBar.getId()) {
				Utils.setTextAndColor(mentalRatingValue, (int) (rating*TEN));
			} else if (ratingBar.getId() == powerRatingBar.getId()) {
				Utils.setTextAndColor(powerRatingValue, (int) (rating*TEN));
			} else if (ratingBar.getId() == speedRatingBar.getId()) {
				Utils.setTextAndColor(speedRatingValue, (int) (rating*TEN));
			} else if (ratingBar.getId() == staminaRatingBar.getId()) {
				Utils.setTextAndColor(staminaRatingValue, (int) (rating*TEN));
			} else if (ratingBar.getId() == ballControlRatingBar.getId()) {
				Utils.setTextAndColor(ballControlRatingValue, (int) (rating*TEN));
			} else if (ratingBar.getId() == passRatingBar.getId()) {
				Utils.setTextAndColor(passRatingValue, (int) (rating*TEN));
			} else if (ratingBar.getId() == shotRatingBar.getId()) {
				Utils.setTextAndColor(shotRatingValue, (int) (rating*TEN));
			} else if (ratingBar.getId() == headerRatingBar.getId()) {
				Utils.setTextAndColor(headerRatingValue, (int) (rating*TEN));
			} else if (ratingBar.getId() == cuttingRatingBar.getId()) {
				Utils.setTextAndColor(cuttingRatingValue, (int) (rating*TEN));
			} else {
				if (DEBUG) Log.e(TAG, "no ratingBar ID matches");
			}
		}
	};


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_player_rating, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {        
        switch (item.getItemId()) {
        case R.id.menu_submit:
        	submit();
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

}
