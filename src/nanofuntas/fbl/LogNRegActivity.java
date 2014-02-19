package nanofuntas.fbl;

import nanofuntas.fbl.settings.ProfileUpdateActivity;

import org.json.simple.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogNRegActivity extends Activity {
	private final boolean DEBUG = true;
	private final String TAG = "LogNRegActivity";
	
	private EditText mEmailLogin;
	private EditText mPwLogin;
	private Button mLoginButton;
	
	private EditText mEmailReg;
	private EditText mPwReg;
	private EditText mPwRegRetype;
	private Button mRegButton;
	
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
			//JSONObject result = ServerIface.login(email, pw);
			Intent i = new Intent(LogNRegActivity.this, SplashScreenActivity.class);
			startActivity(i);
			finish();
        }
        
        mLoginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (DEBUG) Log.d(TAG, "loginButton clicked!");
				
				String email = mEmailLogin.getText().toString();
				String pw = mPwLogin.getText().toString();
				
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
				
				//TODO: remove test Text
				Toast.makeText(getApplication(), 
						"UID:"+Long.toString(uid) + " TID:" + Long.toString(tid),  
						Toast.LENGTH_LONG).show();
				
				if ( uid == -1 ) return;
				
				Utils.setMyUid(uid);
				Utils.setMyTid(tid);
				Utils.setMyLoginID(email);
				Utils.setMyLoginPW(pw);
					
				SharedPreferences sp = getSharedPreferences(Config.FBL_SETTINGS, 0);
				boolean firstLaunch = sp.getBoolean(Config.KEY_FIRST_LAUNCH, false);
				if (firstLaunch == false) {
					SharedPreferences.Editor editor = sp.edit();
					editor.putBoolean(Config.KEY_FIRST_LAUNCH, true).commit();
					Intent i = new Intent(LogNRegActivity.this, ProfileUpdateActivity.class);
					startActivity(i);
					finish();
				} else {
					Intent i = new Intent(LogNRegActivity.this, TabViewActivity.class);
					startActivity(i);
					finish();
				}
			}     	 
        });
        
        mRegButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (DEBUG) Log.d(TAG, "onCreate()");
				
				String email = mEmailReg.getText().toString();
				String pw = mPwReg.getText().toString();
				String pwRetype = mPwRegRetype.getText().toString();
				
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
					Toast.makeText(getApplication(), 
							"register password not equal!",  
							Toast.LENGTH_LONG).show();
					
					return;
				}
				
				JSONObject result = ServerIface.register(email, pw);
				long uid = (Long) result.get(Config.KEY_UID);
				long tid = (Long) result.get(Config.KEY_TID);
				
				Toast.makeText(getApplication(), 
						"UID:"+Long.toString(uid) + " TID:" + Long.toString(tid),  
						Toast.LENGTH_LONG).show();
				
			}       	
        });
    }

    private void initViews() {
    	mEmailLogin = (EditText) findViewById(R.id.email_login);
    	mPwLogin = (EditText) findViewById(R.id.pw_login);
    	mLoginButton = (Button) findViewById(R.id.login);
    	
    	mEmailReg = (EditText) findViewById(R.id.email_reg);
    	mPwReg = (EditText) findViewById(R.id.pw_reg);
    	mPwRegRetype = (EditText) findViewById(R.id.pw_reg_retype);
    	mRegButton = (Button) findViewById(R.id.regiter);
    	
    }
    
}