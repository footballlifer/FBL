package nanofuntas.fbl;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinTeamActivity extends Activity {
	private final boolean DEBUG = true;
	private final String TAG = "JoinTeamActivity";
	
	private EditText mTeamNameJoin;
	private Button mJoinTeam;
	
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	
	private long UID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_team);
		
		mTeamNameJoin = (EditText) findViewById(R.id.team_name_join);
		mJoinTeam = (Button) findViewById(R.id.join_team);
		
		settings = getSharedPreferences(Config.FBL_SETTINGS, 0);
    	editor = settings.edit();
    	
    	UID = settings.getLong(Config.KEY_UID, 0);
	}

	private void joinTeam() {
		if (DEBUG) Log.d(TAG, "Join Team clicked!");
		
		String teamName = mTeamNameJoin.getText().toString();
		long tid = ServerIface.joinTeam(UID, teamName);
		
		editor.putLong(Config.KEY_TID, tid);
		editor.commit();	
		
		Toast.makeText(getApplication(), 
				"You Joined, TID:" + Long.toString(tid), Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.join_team, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {        
        switch (item.getItemId()) {
        case R.id.menu_join:
        	joinTeam();
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
}
