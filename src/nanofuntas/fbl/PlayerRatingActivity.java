package nanofuntas.fbl;

import org.json.simple.JSONObject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerRatingActivity extends Activity {
	private final String TAG = "PlayerRatingActivity";
	private final boolean DEBUG = true;

	private final int TEN = 10;

	private Button submitButton = null;
	private TextView nameRating = null;

	private RatingBar attackRatingBar = null;
	private RatingBar defenseRatingBar = null;
	private RatingBar teamworkRatingBar = null;
	private RatingBar mentalRatingBar = null;
	private RatingBar powerRatingBar = null;
	private RatingBar speedRatingBar = null;
	private RatingBar staminaRatingBar = null;
	private RatingBar ballControlRatingBar = null;
	private RatingBar passRatingBar = null;
	private RatingBar shotRatingBar = null;
	private RatingBar headerRatingBar = null;
	private RatingBar cuttingRatingBar = null;

	private TextView attackRatingValue = null;
	private TextView defenseRatingValue = null;
	private TextView teamworkRatingValue = null;
	private TextView mentalRatingValue = null;
	private TextView powerRatingValue = null;
	private TextView speedRatingValue = null;
	private TextView staminaRatingValue = null;
	private TextView ballControlRatingValue = null;
	private TextView passRatingValue = null;
	private TextView shotRatingValue = null;
	private TextView headerRatingValue = null;
	private TextView cuttingRatingValue = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_rating);
		initViews();

		submitButton.setOnClickListener(submitOnClickListener);
	}

	OnClickListener submitOnClickListener = new OnClickListener() {
		@SuppressWarnings("unchecked")
		@Override
		public void onClick(View v) {
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

			SharedPreferences settings = getSharedPreferences(Config.FBL_SETTINGS, 0);
			long uid = settings.getLong(Config.KEY_UID, 0);

			String result = ServerIface.ratePlayer(uid, jsonRating);
			if (result.equals(Config.KEY_OK)) {
				Toast.makeText(getApplicationContext(), "Submit successful", Toast.LENGTH_SHORT).show();
			}
		}

	};

	private void initViews() {
		submitButton = (Button) findViewById(R.id.submit);
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

	private RatingBar.OnRatingBarChangeListener mRatingBarListener = new RatingBar.OnRatingBarChangeListener() {

		public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
			
			if (ratingBar.getId() == attackRatingBar.getId()) {
				setTextAndColor(attackRatingValue, (int)rating*TEN);
			} else if (ratingBar.getId() == defenseRatingBar.getId()) {
				setTextAndColor(defenseRatingValue, (int)rating*TEN);
			} else if (ratingBar.getId() == teamworkRatingBar.getId()) {
				//teamworkRatingValue.setText(String.valueOf((int) (rating * TEN)));
				setTextAndColor(teamworkRatingValue, (int)rating*TEN);
			} else if (ratingBar.getId() == mentalRatingBar.getId()) {
				//mentalRatingValue.setText(String.valueOf((int) (rating * TEN)));
				setTextAndColor(mentalRatingValue, (int)rating*TEN);
			} else if (ratingBar.getId() == powerRatingBar.getId()) {
				//powerRatingValue.setText(String.valueOf((int) (rating * TEN)));
				setTextAndColor(powerRatingValue, (int)rating*TEN);
			} else if (ratingBar.getId() == speedRatingBar.getId()) {
				//speedRatingValue.setText(String.valueOf((int) (rating * TEN)));
				setTextAndColor(speedRatingValue, (int)rating*TEN);
			} else if (ratingBar.getId() == staminaRatingBar.getId()) {
				//staminaRatingValue.setText(String.valueOf((int) (rating * TEN)));
				setTextAndColor(staminaRatingValue, (int)rating*TEN);
			} else if (ratingBar.getId() == ballControlRatingBar.getId()) {
				//ballControlRatingValue.setText(String.valueOf((int) (rating * TEN)));
				setTextAndColor(ballControlRatingValue, (int)rating*TEN);
			} else if (ratingBar.getId() == passRatingBar.getId()) {
				//passRatingValue.setText(String.valueOf((int) (rating * TEN)));
				setTextAndColor(passRatingValue, (int)rating*TEN);
			} else if (ratingBar.getId() == shotRatingBar.getId()) {
				//shotRatingValue.setText(String.valueOf((int) (rating * TEN)));
				setTextAndColor(shotRatingValue, (int)rating*TEN);
			} else if (ratingBar.getId() == headerRatingBar.getId()) {
				//headerRatingValue.setText(String.valueOf((int) (rating * TEN)));
				setTextAndColor(headerRatingValue, (int)rating*TEN);
			} else if (ratingBar.getId() == cuttingRatingBar.getId()) {
				//cuttingRatingValue.setText(String.valueOf((int) (rating * TEN)));
				setTextAndColor(cuttingRatingValue, (int)rating*TEN);
			} else {
				if (DEBUG) Log.e(TAG, "no ratingBar ID matches");
			}
		}
	};

	private void setTextAndColor(TextView tv, int rating) {
    	if( 0<= rating && rating <= 79) {
    		tv.setTextColor(Color.WHITE);
    	} else if (80 <= rating && rating <= 89) {
    		tv.setTextColor(Color.YELLOW);
    	} else if (90 <= rating && rating <= 100) {
    		tv.setTextColor(Color.RED);
    	}

    	tv.setText( Integer.toString(rating));

    }

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_player_rating, menu);
		return true;
	}

}
