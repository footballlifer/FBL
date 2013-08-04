package nanofuntas.fbl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends Fragment {
	private final boolean DEBUG = true; 
	private final String TAG = "SettingFragment";
	
	public SettingsFragment() {
    }

    public static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public void onStart(){
    	super.onStart();        	    	
    	initViews();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {    	
    	View view = inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }    

    private void initViews() {
    	if (DEBUG) Log.d(TAG, "initViews()");
    }
    
}
