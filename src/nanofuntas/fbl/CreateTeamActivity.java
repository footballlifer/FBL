package nanofuntas.fbl;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateTeamActivity extends Activity {
	private final boolean DEBUG = true;
	private final String TAG = "CreateTeamActivity";
	
	private EditText mTeamName;
	private Button mCreateTeam;
	
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_team);
		
		mTeamName = (EditText) findViewById(R.id.team_name_create);
		mCreateTeam = (Button) findViewById(R.id.create_team);
		
		settings = getSharedPreferences(Config.FBL_SETTINGS, 0);
    	editor = settings.edit();
    	final long UID = settings.getLong(Config.KEY_UID, 0);

    	mCreateTeam.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (DEBUG) Log.d(TAG, "Create Team clicked!");
				
				String teamName = mTeamName.getText().toString();
				long tid = ServerIface.createTeam(UID, teamName);
				
				editor.putLong(Config.KEY_TID, tid);
				editor.commit();
				
				Toast.makeText(getApplication(), 
						"Team Created, TID:" + Long.toString(tid), Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_team, menu);
		return true;
	}

}
