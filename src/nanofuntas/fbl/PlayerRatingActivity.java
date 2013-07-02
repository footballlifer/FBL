package nanofuntas.fbl;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class PlayerRatingActivity extends Activity {
	private final String TAG = "PlayerRatingActivity";
	private final boolean DEBUG = true;

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
	private RatingBar temperRatingBar = null;
 
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
	private TextView temperRatingValue = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_rating);
		initViews();
	}

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
		temperRatingBar = (RatingBar) findViewById(R.id.temper_rating_bar);

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
		temperRatingBar.setOnRatingBarChangeListener(mRatingBarListener);
		
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
		temperRatingValue = (TextView) findViewById(R.id.temper_rating_value);

	}

	private RatingBar.OnRatingBarChangeListener mRatingBarListener = new RatingBar.OnRatingBarChangeListener() {

		public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
			if ( ratingBar.getId() == attackRatingBar.getId() ){
				attackRatingValue.setText(String.valueOf(rating));
			} else if (ratingBar.getId() == defenseRatingBar.getId() ) {
				defenseRatingValue.setText(String.valueOf(rating));
			} else if (ratingBar.getId() == teamworkRatingBar.getId() ) {
				teamworkRatingValue.setText(String.valueOf(rating));
			} else if (ratingBar.getId() == mentalRatingBar.getId() ) {
				mentalRatingValue.setText(String.valueOf(rating));
			} else if (ratingBar.getId() == powerRatingBar.getId() ) {
				powerRatingValue.setText(String.valueOf(rating));
			} else if (ratingBar.getId() == speedRatingBar.getId() ) {
				speedRatingValue.setText(String.valueOf(rating));
			} else if (ratingBar.getId() == staminaRatingBar.getId() ) {
				staminaRatingValue.setText(String.valueOf(rating));
			} else if (ratingBar.getId() == ballControlRatingBar.getId() ) {
				ballControlRatingValue.setText(String.valueOf(rating));
			} else if (ratingBar.getId() == passRatingBar.getId() ) {
				passRatingValue.setText(String.valueOf(rating));
			} else if (ratingBar.getId() == shotRatingBar.getId() ) {
				shotRatingValue.setText(String.valueOf(rating));
			} else if (ratingBar.getId() == headerRatingBar.getId() ) {
				headerRatingValue.setText(String.valueOf(rating));
			} else if (ratingBar.getId() == cuttingRatingBar.getId() ) {
				cuttingRatingValue.setText(String.valueOf(rating));
			} else if (ratingBar.getId() == temperRatingBar.getId() ) {
				temperRatingValue.setText(String.valueOf(rating));
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

}
