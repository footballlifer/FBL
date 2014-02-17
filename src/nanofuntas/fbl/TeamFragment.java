package nanofuntas.fbl;


import org.json.simple.JSONObject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TeamFragment extends Fragment {
	private final boolean DEBUG = true; 
	private final String TAG = "TeamFragment";
	
	private HexView mTeamHexView = null;
	private final float HUNDRED = 100.0f;
	
	private TextView mTeamName = null;
	private TextView mTeamOVERating = null;
	private TextView mTeamATKRating = null;
	private TextView mTeamDFSRating = null;
	private TextView mTeamTECRating = null;
	private TextView mTeamPHYRating = null;
	private TextView mTeamTWKRating = null;
	private TextView mTeamMTLRating = null;	
    
	private TextView mTeamAttackRating = null;
    private TextView mTeamDefenseRating = null;
    private TextView mTeamTeamworkRating = null;
    private TextView mTeamMentalRating = null;
    private TextView mTeamPowerRating = null;
    private TextView mTeamSpeedRating = null;
    private TextView mTeamStaminaRating = null;
    private TextView mTeamBallControlRating = null;
    private TextView mTeamPassRating = null;
    private TextView mTeamShotRating = null;
    private TextView mTeamHeaderRating = null;
    private TextView mTeamCuttingRating = null;
    private TextView mTeamOverallRating = null;
    
	public TeamFragment() {}

    public static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public void onStart(){
    	super.onStart();        	    	
    	initViews();
    	getAndSetTeamStatus();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {    	
    	View view = inflater.inflate(R.layout.fragment_team, container, false);
        return view;
    }    

    private void initViews() {
    	if (DEBUG) Log.d(TAG, "initViews()");
    	
    	mTeamHexView = (HexView) getView().findViewById(R.id.team_hex_view);
    	
    	mTeamName = (TextView) getView().findViewById(R.id.team_name);
    	mTeamOVERating = (TextView) getView().findViewById(R.id.team_ove_rating);
    	mTeamATKRating = (TextView) getView().findViewById(R.id.team_atk_rating);
    	mTeamDFSRating = (TextView) getView().findViewById(R.id.team_dfs_rating);
    	mTeamTECRating = (TextView) getView().findViewById(R.id.team_tec_rating);
    	mTeamPHYRating = (TextView) getView().findViewById(R.id.team_phy_rating);
    	mTeamTWKRating = (TextView) getView().findViewById(R.id.team_twk_rating);
    	mTeamMTLRating = (TextView) getView().findViewById(R.id.team_mtl_rating);
    	
    	mTeamAttackRating = (TextView) getView().findViewById(R.id.team_attack_rating);
    	mTeamDefenseRating = (TextView) getView().findViewById(R.id.team_defense_rating);
        mTeamTeamworkRating = (TextView) getView().findViewById(R.id.team_teamwork_rating);
        mTeamMentalRating = (TextView) getView().findViewById(R.id.team_mental_rating);
        mTeamPowerRating = (TextView) getView().findViewById(R.id.team_power_rating);
        mTeamSpeedRating = (TextView) getView().findViewById(R.id.team_speed_rating);
        mTeamStaminaRating = (TextView) getView().findViewById(R.id.team_stamina_rating);
        mTeamBallControlRating = (TextView) getView().findViewById(R.id.team_ball_control_rating);
        mTeamPassRating = (TextView) getView().findViewById(R.id.team_pass_rating);
        mTeamShotRating = (TextView) getView().findViewById(R.id.team_shot_rating);
        mTeamHeaderRating = (TextView) getView().findViewById(R.id.team_header_rating);
        mTeamCuttingRating = (TextView) getView().findViewById(R.id.team_cutting_rating);
        mTeamOverallRating = (TextView) getView().findViewById(R.id.team_overall_rating);
    	
    }

    private void getAndSetTeamStatus() {
    	if (DEBUG) Log.d(TAG, "getAndSetTeamStatus()");
    	
    	SharedPreferences settings = getActivity().getSharedPreferences(Config.FBL_SETTINGS, 0);
    	long tid = settings.getLong(Config.KEY_TID, 0);
    	
    	if (tid <=0 ) {
    		if (DEBUG) Log.d(TAG, "tid <= 0, return");
    		return;
    	}
    	
    	JSONObject status = ServerIface.getTeamStatus(tid);
    	Log.d(TAG, "team status:" + status);
    	
    	mTeamName.setText( (String)status.get(Config.KEY_TEAM_NAME) );
    	
    	long tmAtkR = (Long)status.get(Config.KEY_TEAM_ATK);
    	long tmDfsR = (Long)status.get(Config.KEY_TEAM_DFS);
    	long tmTecR = (Long)status.get(Config.KEY_TEAM_TEC);
    	long tmPhyR = (Long)status.get(Config.KEY_TEAM_PHY);
    	long tmTwkR = (Long)status.get(Config.KEY_TEAM_TWK);
    	long tmMtlR = (Long)status.get(Config.KEY_TEAM_MTL);
    	long tmOvrR = (Long)status.get(Config.KEY_TEAM_OVERALL);    	    	
    	    	
    	Utils.setTextAndColor(mTeamATKRating, (int)tmAtkR);
    	Utils.setTextAndColor(mTeamDFSRating, (int)tmDfsR);
    	Utils.setTextAndColor(mTeamTECRating, (int)tmTecR);
    	Utils.setTextAndColor(mTeamPHYRating, (int)tmPhyR);
    	Utils.setTextAndColor(mTeamTWKRating, (int)tmTwkR);
    	Utils.setTextAndColor(mTeamMTLRating, (int)tmMtlR);    	
    	Utils.setTextAndColor(mTeamOVERating, (int)tmOvrR);
    	
    	float tmAtkRatio = (float)tmAtkR / HUNDRED;
    	float tmDfsRatio = (float)tmDfsR / HUNDRED;
    	float tmTecRatio = (float)tmTecR / HUNDRED;
    	float tmPhyRatio = (float)tmPhyR / HUNDRED;
    	float tmTwkRatio = (float)tmTwkR / HUNDRED;
    	float tmMtlRatio = (float)tmMtlR / HUNDRED;
    	    	
    	Log.d(TAG, " "+tmAtkRatio+" "+tmDfsRatio+" "+tmTecRatio+" "
    			+tmPhyRatio+" "+tmTwkRatio+" "+tmMtlRatio+" ");
    	
    	mTeamHexView.setRatingAndDraw(tmAtkRatio, tmDfsRatio, 
    			tmTecRatio, tmPhyRatio, tmTwkRatio, tmMtlRatio);
    	
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
    	
    	Utils.setTextAndColor(mTeamAttackRating, (int)atkRating);
    	Utils.setTextAndColor(mTeamDefenseRating, (int)dfsRating);
    	Utils.setTextAndColor(mTeamTeamworkRating, (int)twkRating);
    	Utils.setTextAndColor(mTeamMentalRating, (int)mtlRating);
    	Utils.setTextAndColor(mTeamPowerRating, (int)powRating);
    	Utils.setTextAndColor(mTeamSpeedRating, (int)spdRating);
    	Utils.setTextAndColor(mTeamStaminaRating, (int)staRating);
    	Utils.setTextAndColor(mTeamBallControlRating, (int)blcRating);
    	Utils.setTextAndColor(mTeamPassRating, (int)pasRating);
    	Utils.setTextAndColor(mTeamShotRating, (int)shtRating);
    	Utils.setTextAndColor(mTeamHeaderRating, (int)hdrRating);
    	Utils.setTextAndColor(mTeamCuttingRating, (int)cutRating);
    	Utils.setTextAndColor(mTeamOverallRating, (int)ovrRating);
    	
    }
    
}
