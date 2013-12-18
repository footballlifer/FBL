package nanofuntas.fbl;

import org.json.simple.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfileActivity extends Activity {
	private final boolean DEBUG = true;
	private final String TAG = "ProfileActivity";
	
	private final float HUNDRED = 100.0f;
	
    private TextView mPlayerName;
    private TextView mPosition;
    
    private TextView mAttackRating;
    private TextView mDefenseRating;
    private TextView mTeamworkRating;
    private TextView mMentalRating;
    private TextView mPowerRating;
    private TextView mSpeedRating;
    private TextView mStaminaRating;
    private TextView mBallControlRating;
    private TextView mPassRating;
    private TextView mShotRating;
    private TextView mHeaderRating;
    private TextView mCuttingRating;
    private TextView mOverallRating;
    private HexView mHexView;
	
    private long mUid = -1;
    private String mNameStr;
    private String mPositionStr;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		initView();
		
		mUid = getIntent().getExtras().getLong(Config.KEY_UID);		
		mNameStr = getIntent().getExtras().getString(Config.KEY_NAME);
    	mPositionStr = getIntent().getExtras().getString(Config.KEY_POSITION);
    	mPlayerName.setText(mNameStr);
    	mPosition.setText(mPositionStr);
    	
    	getAndSetPlayerStatus(mUid);
	}

    private void initView() {
    	if (DEBUG) Log.d(TAG, "initView()");
    	
    	mPlayerName = (TextView) findViewById(R.id.player_name);
    	mPosition = (TextView) findViewById(R.id.position_value);
    	
    	mAttackRating = (TextView) findViewById(R.id.attack_rating);
    	mDefenseRating = (TextView) findViewById(R.id.defense_rating);
    	mTeamworkRating = (TextView) findViewById(R.id.teamwork_rating);
    	mMentalRating = (TextView) findViewById(R.id.mental_rating);
    	mPowerRating = (TextView) findViewById(R.id.power_rating);
    	mSpeedRating = (TextView) findViewById(R.id.speed_rating);
    	mStaminaRating = (TextView) findViewById(R.id.stamina_rating);
    	mBallControlRating = (TextView) findViewById(R.id.ball_control_rating);
    	mShotRating = (TextView) findViewById(R.id.shot_rating);
    	mPassRating = (TextView) findViewById(R.id.pass_rating);
    	mHeaderRating = (TextView) findViewById(R.id.header_rating);
    	mCuttingRating = (TextView) findViewById(R.id.cutting_rating);
    	mOverallRating = (TextView) findViewById(R.id.overall_rating);
    	
    	mHexView = (HexView) findViewById(R.id.hex_view);
    }
    
    private void startRateActivity() {
    	if (DEBUG) Log.d(TAG, "RateMeButton onClick()");
		
		Intent i = new Intent(ProfileActivity.this, PlayerRatingActivity.class);
		i.putExtra(Config.KEY_UID, mUid);
		i.putExtra(Config.KEY_NAME, mNameStr);
		startActivity(i);
    }
    
    private void getAndSetPlayerStatus(long uid) {
    	if (DEBUG) Log.d(TAG, "getAndSetPlayerStatus()");
    	
    	JSONObject status = ServerIface.getPlayerStatus(uid);
    	   	
    	mPlayerName.setText( (String)status.get(Config.KEY_NAME));
    	mPosition.setText( (String)status.get(Config.KEY_POSITION));
    	
    	Long atkRating = (Long)status.get(Config.KEY_ATTACK);
    	Long dfsRating = (Long)status.get(Config.KEY_DEFENSE);
    	Long twkRating = (Long)status.get(Config.KEY_TEAMWORK);
    	Long mtlRating = (Long)status.get(Config.KEY_MENTAL);
    	Long powRating = (Long)status.get(Config.KEY_POWER);
    	Long spdRating = (Long)status.get(Config.KEY_SPEED);
    	Long staRating = (Long)status.get(Config.KEY_STAMINA);
    	Long blcRating = (Long)status.get(Config.KEY_BALL_CONTROL);
    	Long pasRating = (Long)status.get(Config.KEY_PASS);
    	Long shtRating = (Long)status.get(Config.KEY_SHOT);
    	Long hdrRating = (Long)status.get(Config.KEY_HEADER);
    	Long cutRating = (Long)status.get(Config.KEY_CUTTING);
    	Long ovrRating = (Long)status.get(Config.KEY_OVERALL);    	    	
    	
    	setTextAndColor(mAttackRating, atkRating);
    	setTextAndColor(mDefenseRating, dfsRating);
    	setTextAndColor(mTeamworkRating, twkRating);
    	setTextAndColor(mMentalRating, mtlRating);
    	setTextAndColor(mPowerRating, powRating);
    	setTextAndColor(mSpeedRating, spdRating);
    	setTextAndColor(mStaminaRating, staRating);
    	setTextAndColor(mBallControlRating, blcRating);
    	setTextAndColor(mShotRating, shtRating);
    	setTextAndColor(mPassRating, pasRating);
    	setTextAndColor(mHeaderRating, hdrRating);
    	setTextAndColor(mCuttingRating, cutRating);
    	setTextAndColor(mOverallRating, ovrRating);
    	    	
    	float rATK = (float)atkRating / HUNDRED;
    	float rDFS = (float)(dfsRating + cutRating) / (2*HUNDRED);
    	float rTWK = (float)twkRating / HUNDRED;
    	float rMTL = (float)mtlRating / HUNDRED;
    	float rPHY = (float)(powRating + spdRating + staRating) / (3*HUNDRED);
    	float rTEC = (float)(blcRating + pasRating + shtRating + hdrRating) / (4*HUNDRED);
    	
    	//Log.d(TAG, " "+rATK+" "+rTEC+" "+rTWK+" "+rDFS+" "+rMTL+" "+rPHY+" ");
    	
    	mHexView.setRatingAndDraw(rATK, rTEC, rTWK, rDFS, rMTL, rPHY);
    }
    
    private void setTextAndColor(TextView tv, Long rating) {
    	if( 0<= rating && rating <= 79) {
    		tv.setTextColor(Color.WHITE);
    	} else if (80 <= rating && rating <= 89) {
    		tv.setTextColor(Color.YELLOW);
    	} else if (90 <= rating && rating <= 100) {
    		tv.setTextColor(Color.RED);
    	}

    	tv.setText( rating.toString());
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_profile, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {        
        switch (item.getItemId()) {
        case R.id.menu_rate:
        	startRateActivity();
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

}
