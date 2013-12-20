package nanofuntas.fbl.settings;

import nanofuntas.fbl.R;
import nanofuntas.fbl.ServerIface;
import nanofuntas.fbl.Utils;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class JoinTeamActivity extends Activity {
	private final boolean DEBUG = true;
	private final String TAG = "JoinTeamActivity";
	
	private EditText mTeamNameJoin;
		
	private long UID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_team);
		
		mTeamNameJoin = (EditText) findViewById(R.id.team_name_join);
		
		UID = Utils.getMyUid();
	}

	private void joinTeam() {
		if (DEBUG) Log.d(TAG, "Join Team clicked!");
		
		String teamName = mTeamNameJoin.getText().toString();
		long tid = ServerIface.joinTeam(UID, teamName);
		
		Utils.setMyTid(tid);
		
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
