package nanofuntas.fbl;

import nanofuntas.fbl.settings.MyProfileUpdate;

import org.json.simple.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogNRegActivity extends Activity {
	private final boolean DEBUG = true;
	private final String TAG = "LogNRegActivity";
	
	private EditText emailLogin;
	private EditText pwLogin;
	private Button loginButton;
	
	private EditText emailReg;
	private EditText pwReg;
	private EditText pwRegRetype;
	private Button regButton;
	
	private TextView testText;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (DEBUG) Log.d(TAG, "onCreate()");

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_n_reg);
        
        initViews();
        // initialize SharedPreference in Utils for the first time and only once
        Utils.initSharedPreference(getApplicationContext());
        
        String email = Utils.getMyLoginID();
        String pw = Utils.getMyLoginPW();
        
        // if not loged in ever, LoginID and LoginPW return "NULL" string default
        if (!email.equals("NULL") && !pw.equals("NULL")) {
			JSONObject result = ServerIface.login(email, pw);
			Intent i = new Intent(LogNRegActivity.this, TabViewActivity.class);
			startActivity(i);
			finish();
        }
        
        loginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (DEBUG) Log.d(TAG, "loginButton clicked!");
				
				String email = emailLogin.getText().toString();
				String pw = pwLogin.getText().toString();
				
				if (email.equals("")) { 
					Log.d(TAG, "Please fill in email");
					Toast.makeText(getApplication(), "Please fill in email", Toast.LENGTH_LONG).show();
					return;
				}
				
				if (pw.equals("")) { 
					Log.d(TAG, "Please fill in password");
					Toast.makeText(getApplication(), "Please fill in password", Toast.LENGTH_LONG).show();
					return;
				}
				
				JSONObject result = ServerIface.login(email, pw);
				long uid = (Long) result.get(Config.KEY_UID);
				long tid = (Long) result.get(Config.KEY_TID);
				
				testText.setText("UID:"+Long.toString(uid) + " TID:" + Long.toString(tid));
				
				if ( uid != -1 ) {
					Utils.setMyUid(uid);
					Utils.setMyTid(tid);
					Utils.setMyLoginID(email);
					Utils.setMyLoginPW(pw);
					
					Intent i = new Intent(LogNRegActivity.this, MyProfileUpdate.class);
					startActivity(i);
					finish();
				}
			}     	 
        });
        
        regButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (DEBUG) Log.d(TAG, "onCreate()");
				
				String email = emailReg.getText().toString();
				String pw = pwReg.getText().toString();
				String pwRetype = pwRegRetype.getText().toString();
				
				if (email.equals("")) { 
					Log.d(TAG, "Please fill in email");
					Toast.makeText(getApplication(), "Please fill in email", Toast.LENGTH_LONG).show();
					return;
				}
				
				if (pw.equals("")) { 
					Log.d(TAG, "Please fill in password");
					Toast.makeText(getApplication(), "Please fill in password", Toast.LENGTH_LONG).show();
					return;
				}
				
				if (pwRetype.equals("")) { 
					Log.d(TAG, "Please fill in password again");
					Toast.makeText(getApplication(), "Please fill in password again", Toast.LENGTH_LONG).show();
					return;
				}
				
				if ( !pw.equals(pwRetype) ) {
					Log.d(TAG, "register password not equal!");
					testText.setText("register password not equal!");
					return;
				}
				
				JSONObject result = ServerIface.register(email, pw);
				long uid = (Long) result.get(Config.KEY_UID);
				long tid = (Long) result.get(Config.KEY_TID);
				
				testText.setText("UID:"+Long.toString(uid) + " TID:" + Long.toString(tid));
			}       	
        });
    }

    private void initViews() {
    	emailLogin = (EditText) findViewById(R.id.email_login);
    	pwLogin = (EditText) findViewById(R.id.pw_login);
    	loginButton = (Button) findViewById(R.id.login);
    	
    	emailReg = (EditText) findViewById(R.id.email_reg);
    	pwReg = (EditText) findViewById(R.id.pw_reg);
    	pwRegRetype = (EditText) findViewById(R.id.pw_reg_retype);
    	regButton = (Button) findViewById(R.id.regiter);
    	
    	testText = (TextView) findViewById(R.id.test_text);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_log_n_reg, menu);
        return true;
    }
}