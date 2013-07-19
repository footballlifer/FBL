package nanofuntas.fbl;

import org.json.simple.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
	private final boolean DEBUG = true;
	private final String TAG = "PlayerProfile";
	
	private final float HUNDRED = 100.0f;
	
    private TextView mPlayerName = null;
    private TextView mPosition = null;
    private Button mRateMeButton = null;
    
    private TextView mAttackRating = null;
    private TextView mDefenseRating = null;
    private TextView mTeamworkRating = null;
    private TextView mMentalRating = null;
    private TextView mPowerRating = null;
    private TextView mSpeedRating = null;
    private TextView mStaminaRating = null;
    private TextView mBallControlRating = null;
    private TextView mPassRating = null;
    private TextView mShotRating = null;
    private TextView mHeaderRating = null;
    private TextView mCuttingRating = null;
    private TextView mOverallRating = null;
    private HexView mHexView = null;
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {   	         	
    	View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
    	if (DEBUG) Log.d(TAG, "onActivityCreated()");
    	
    	super.onActivityCreated(savedInstanceState);
    	
    	initView();
    	getAndSetPlayerStatus();    	
    	
    	mRateMeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getView().getContext(), PlayerRatingActivity.class);
				startActivity(i);
			}
		});
    }
    
    private void initView() {
    	mPlayerName = (TextView) getView().findViewById(R.id.player_name);
    	mPosition = (TextView) getView().findViewById(R.id.position_value);
    	mRateMeButton = (Button) getView().findViewById(R.id.rate_me);
    	
    	mAttackRating = (TextView) getView().findViewById(R.id.attack_rating);
    	mDefenseRating = (TextView) getView().findViewById(R.id.defense_rating);
    	mTeamworkRating = (TextView) getView().findViewById(R.id.teamwork_rating);
    	mMentalRating = (TextView) getView().findViewById(R.id.mental_rating);
    	mPowerRating = (TextView) getView().findViewById(R.id.power_rating);
    	mSpeedRating = (TextView) getView().findViewById(R.id.speed_rating);
    	mStaminaRating = (TextView) getView().findViewById(R.id.stamina_rating);
    	mBallControlRating = (TextView) getView().findViewById(R.id.ball_control_rating);
    	mShotRating = (TextView) getView().findViewById(R.id.shot_rating);
    	mPassRating = (TextView) getView().findViewById(R.id.pass_rating);
    	mHeaderRating = (TextView) getView().findViewById(R.id.header_rating);
    	mCuttingRating = (TextView) getView().findViewById(R.id.cutting_rating);
    	mOverallRating = (TextView) getView().findViewById(R.id.overall_rating);
    	
    	mHexView = (HexView) getActivity().findViewById(R.id.hexView);
    }
    
    private void getAndSetPlayerStatus() {
    	JSONObject status = null;
    	SharedPreferences settings = getActivity().getSharedPreferences(Config.FBL_SETTINGS, 0);
    	long uid = settings.getLong(Config.KEY_UID, 0);
    	
    	status = ServerIface.getPlayerStatus(uid);
    	   	
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
    	
    	Log.d(TAG, " "+rATK+" "+rTEC+" "+rTWK+" "+rDFS+" "+rMTL+" "+rPHY+" ");
    	
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
}
