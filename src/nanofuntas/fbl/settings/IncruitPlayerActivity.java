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

public class IncruitPlayerActivity extends Activity {
	private final boolean DEBUG = true;
	private final String TAG = "IncruitPlayerActivity";
	
	private EditText mPlayerNameIncruit;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_incruit_player);
		
		mPlayerNameIncruit = (EditText) findViewById(R.id.player_name_incruit);
	}

	private void incruitPlayer() {
		if (DEBUG) Log.d(TAG, "Incruit Player clicked!");
		long tid = Utils.getMyTid();
		
		if (tid <= 0) {
			Toast.makeText(getApplication(), 
					"Please Join or Create Team First!", Toast.LENGTH_SHORT).show();
			return;
		} 
		
		String playerName = mPlayerNameIncruit.getText().toString();
		long urid = ServerIface.incruitPlayer(tid, playerName);

		if (urid > 0) {
			Toast.makeText(getApplication(), 
					"Player Incruited, UID:" + urid, Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getApplication(), 
					"No such Player, UID:" + urid, Toast.LENGTH_SHORT).show();
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
