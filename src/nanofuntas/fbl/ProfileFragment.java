package nanofuntas.fbl;

import org.json.simple.JSONObject;

import android.content.SharedPreferences;
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
	
	//private FrameLayout mFrameLayout = null;
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
    private TextView mTemperRating = null;
    private TextView mOverallRating = null;
    
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
    	
//      mFrameLayout = (FrameLayout)findViewById(R.id.frameLayout);              
//      HexStatus mHexStatus = new HexStatus(this);
//      mFrameLayout.addView(mHexStatus);
    	initView();
    	getPlayerStatus();
    	
    	mRateMeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Intent i = new Intent(getView().getContext(), PlayerRating.class);
				//startActivity(i);
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
    	mTemperRating = (TextView) getView().findViewById(R.id.temper_rating);
    	mOverallRating = (TextView) getView().findViewById(R.id.overall_rating);
    	
    }
    
    private void getPlayerStatus() {
    	JSONObject status = null;
    	SharedPreferences settings = getActivity().getSharedPreferences(Config.FBL_SETTINGS, 0);
    	long uid = settings.getLong(Config.KEY_UID, 0);
    	
    	status = ServerIface.getPlayerStatus(uid);
    	   	
    	mPlayerName.setText( (String)status.get(Config.KEY_NAME));
    	mPosition.setText( (String)status.get(Config.KEY_POSITION));
    	
    	mAttackRating.setText( ((Long)status.get(Config.KEY_ATTACK)).toString());
    	mDefenseRating.setText( ((Long)status.get(Config.KEY_DEFENSE)).toString());
    	mTeamworkRating.setText( ((Long)status.get(Config.KEY_TEAMWORK)).toString());
    	mMentalRating.setText( ((Long)status.get(Config.KEY_MENTAL)).toString());
    	mPowerRating.setText( ((Long)status.get(Config.KEY_POWER)).toString());
    	mSpeedRating.setText( ((Long)status.get(Config.KEY_SPEED)).toString());
    	mStaminaRating.setText( ((Long)status.get(Config.KEY_STAMINA)).toString());
    	mBallControlRating.setText( ((Long)status.get(Config.KEY_BALL_CONTROL)).toString());
    	mShotRating.setText( ((Long)status.get(Config.KEY_PASS)).toString());
    	mPassRating.setText( ((Long)status.get(Config.KEY_SHOT)).toString());
    	mHeaderRating.setText( ((Long)status.get(Config.KEY_HEADER)).toString());
    	mCuttingRating.setText( ((Long)status.get(Config.KEY_CUTTING)).toString());
    	mTemperRating.setText( ((Long)status.get(Config.KEY_TEMPER)).toString());
    	mOverallRating.setText( ((Long)status.get(Config.KEY_OVERALL)).toString());
    	
    }
}
