package nanofuntas.fbl;

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

public class SettingsFragment2 extends Fragment {
	private final boolean DEBUG = true; 
	private final String TAG = "SettingFragment2";
	
	private TextView createTeam;
	private TextView joinTeam;
	private TextView incruitPlayer;
	private TextView updateMyProfile;
	
	public SettingsFragment2() {}

    public static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public void onStart(){
    	super.onStart();        	    	
    	initViews();
    	
    	createTeam.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getView().getContext(), CreateTeamActivity.class);
				startActivity(i);
			}    		
    	});
    	
    	joinTeam.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getView().getContext(), JoinTeamActivity.class);
				startActivity(i);
			}    		
    	});
    	
    	incruitPlayer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getView().getContext(), IncruitPlayerActivity.class);
				startActivity(i);
			}    		
    	});
    	
    	updateMyProfile.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getView().getContext(), MyProfileUpdate.class);
				startActivity(i);
			}    		
    	});
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {    	
    	View view = inflater.inflate(R.layout.fragment_settings2, container, false);
        return view;
    }

    private void initViews() {
    	if (DEBUG) Log.d(TAG, "initViews()");  
    	createTeam = (TextView) getView().findViewById(R.id.create_team);
    	joinTeam = (TextView) getView().findViewById(R.id.join_team);
    	incruitPlayer = (TextView) getView().findViewById(R.id.incruit_player);
    	updateMyProfile = (TextView) getView().findViewById(R.id.update_my_profile);    
    }
    
}
