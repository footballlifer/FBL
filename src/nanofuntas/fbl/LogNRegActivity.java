package nanofuntas.fbl;

import org.json.simple.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogNRegActivity extends Activity {
	private final boolean DEBUG = true;
	private final String TAG = "LoginNRegister";
	
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

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_n_reg);
        
        initViews();
        
        loginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (DEBUG) Log.d(TAG, "loginButton clicked!");
				
				String strEmailLogin = emailLogin.getText().toString();
				String strPwLogin = pwLogin.getText().toString();
				
				JSONObject result = ServerIface.login(strEmailLogin, strPwLogin);	
				long uid = (Long) result.get(Config.KEY_UID);
				long tid = (Long) result.get(Config.KEY_TID);
				
				testText.setText(Long.toString(uid) + " " + Long.toString(tid));
				
				if ( uid != -1 ) {
					// save uid 
					SharedPreferences settings = getSharedPreferences(Config.FBL_SETTINGS, 0);
					SharedPreferences.Editor editor = settings.edit();
					editor.putLong(Config.KEY_UID, uid);
					editor.putLong(Config.KEY_TID, tid);
					editor.commit();
					
					Intent i = new Intent(LogNRegActivity.this, TabViewActivity.class);
					startActivity(i);
				}
			}     	 
        });
        
        regButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (DEBUG) Log.d(TAG, "onCreate()");
				
				String strEmailReg = emailReg.getText().toString();
				String strPwReg = pwReg.getText().toString();
				String strPwRegRetype = pwRegRetype.getText().toString();
				
				if ( !strPwReg.equals(strPwRegRetype) ) {
					Log.d(TAG, "register password not equal!");
					testText.setText("register password not equal!");
					return;
				}
				
				JSONObject result = ServerIface.register(strEmailReg, strPwReg);	
				long uid = (Long) result.get(Config.KEY_UID);
				long tid = (Long) result.get(Config.KEY_TID);
				
				testText.setText(Long.toString(uid) + " " + Long.toString(tid));			
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
