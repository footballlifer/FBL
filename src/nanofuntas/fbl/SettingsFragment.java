package nanofuntas.fbl;

import nanofuntas.fbl.settings.CreateTeamActivity;
import nanofuntas.fbl.settings.IncruitPlayerActivity;
import nanofuntas.fbl.settings.JoinTeamActivity;
import nanofuntas.fbl.settings.MyProfileUpdate;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsFragment extends Fragment {
	private final boolean DEBUG = true; 
	private final String TAG = "SettingFragment2";
	
	private TextView mCreateTeam;
	private TextView mJoinTeam;
	private TextView mIncruitPlayer;
	private TextView mUpdateMyProfile;
	private TextView mLogOut;
	
	public SettingsFragment() {}

    public static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public void onStart(){
    	super.onStart();        	    	
    	initViews();
    	
    	mCreateTeam.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getView().getContext(), CreateTeamActivity.class);
				startActivity(i);
			}    		
    	});
    	
    	mJoinTeam.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getView().getContext(), JoinTeamActivity.class);
				startActivity(i);
			}    		
    	});
    	
    	mIncruitPlayer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getView().getContext(), IncruitPlayerActivity.class);
				startActivity(i);
			}    		
    	});
    	
    	mUpdateMyProfile.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getView().getContext(), MyProfileUpdate.class);
				startActivity(i);
			}    		
    	});
    	
    	mLogOut.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Utils.removeLoginIdPw();
				Intent i = new Intent(getView().getContext(), LogNRegActivity.class);
				startActivity(i);
			}
    	});
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {    	
    	View view = inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }

    private void initViews() {
    	if (DEBUG) Log.d(TAG, "initViews()");  
    	mCreateTeam = (TextView) getView().findViewById(R.id.create_team);
    	mJoinTeam = (TextView) getView().findViewById(R.id.join_team);
    	mIncruitPlayer = (TextView) getView().findViewById(R.id.incruit_player);
    	mUpdateMyProfile = (TextView) getView().findViewById(R.id.update_my_profile);
    	mLogOut = (TextView) getView().findViewById(R.id.log_out);
    }
}
