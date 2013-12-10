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

public class IncruitPlayerActivity extends Activity {
	private final boolean DEBUG = true;
	private final String TAG = "IncruitPlayerActivity";
	
	private EditText mPlayerNameIncruit;
	
	private SharedPreferences settings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_incruit_player);
		
		mPlayerNameIncruit = (EditText) findViewById(R.id.player_name_incruit);
		settings = getSharedPreferences(Config.FBL_SETTINGS, 0);
	}

	private void incruitPlayer() {
		if (DEBUG) Log.d(TAG, "Incruit Player clicked!");
		long tid = settings.getLong(Config.KEY_TID, 0);
		
		if (tid <= 0) {
			Toast.makeText(getApplication(), 
					"Please Join or Create Team First!", Toast.LENGTH_SHORT).show();
			return;
		} 
		
		String playerName = mPlayerNameIncruit.getText().toString();
		long uid = ServerIface.incruitPlayer(tid, playerName);

		if (uid > 0) {
			Toast.makeText(getApplication(), 
					"Player Incruited, UID:" + uid, Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getApplication(), 
					"No such Player, UID:" + uid, Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.incruit_player, menu);
		return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {        
        switch (item.getItemId()) {
        case R.id.menu_incruit:
        	incruitPlayer();
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
}
