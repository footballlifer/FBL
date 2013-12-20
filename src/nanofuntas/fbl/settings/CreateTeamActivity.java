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

public class CreateTeamActivity extends Activity {
	private final boolean DEBUG = true;
	private final String TAG = "CreateTeamActivity";
	
	private EditText mTeamName;
	
	private long UID;
	private long TID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_team);
		
		mTeamName = (EditText) findViewById(R.id.team_name_create);
		
    	UID = Utils.getMyUid();
    	TID = Utils.getMyTid();
	}

	private void createTeam() {
		if (DEBUG) Log.d(TAG, "Create Team clicked!");
		
		if (TID > 0) {
			if (DEBUG) Log.d(TAG, "You already in a team, cant make a team");
			Toast.makeText(getApplication(), 
					"You already in a team, cant make a team",  Toast.LENGTH_LONG).show();
			return;
		}
		
		String teamName = mTeamName.getText().toString();
		long tid = ServerIface.createTeam(UID, teamName);
		
		Utils.setMyTid(tid);
		
		Toast.makeText(getApplication(), 
				"Team Created, TID:" + Long.toString(tid), Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_team, menu);
		return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {        
        switch (item.getItemId()) {
        case R.id.menu_create:
        	createTeam();
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
