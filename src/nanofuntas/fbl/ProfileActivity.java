package nanofuntas.fbl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends Activity {
	private final boolean DEBUG = true;
	private final String TAG = "ProfileActivity";
	
	private final float HUNDRED = 100.0f;
	
    private TextView mPlayerName;
    private TextView mPosition;
    private TextView mHeightWeight;
    private TextView mFoot;
    private TextView mAge;
    
    private ImageView mImageView;
    
    private TextView mAttackRating;
    private TextView mDefenseRating;
    private TextView mTeamworkRating;
    private TextView mMentalRating;
    private TextView mPowerRating;
    private TextView mSpeedRating;
    private TextView mStaminaRating;
    private TextView mBallControlRating;
    private TextView mPassRating;
    private TextView mShotRating;
    private TextView mHeaderRating;
    private TextView mCuttingRating;
    private TextView mOverallRating;
    private HexView mHexView;
	
    private long mUid = -1;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		initView();
		
		mUid = getIntent().getExtras().getLong(Config.KEY_UID);		
		
		FblSQLiteHelper db = new FblSQLiteHelper(this);
		PlayerInfo.PlayerProfile pp = db.getPlayerProfile(mUid);    	
    	mPlayerName.setText(pp.getName());
    	mPosition.setText(pp.getPosition());
    	
    	setImageView(mUid);
    	getAndSetPlayerStatus(mUid);
	}

    private void setImageView(long uid) {
    	Drawable mPhoto = null;
    	Bitmap bitmap = Utils.getProfileImage(uid);
    	
    	if (bitmap != null)
    		mPhoto =  new BitmapDrawable(bitmap);
    	else 
        	mPhoto = getResources().getDrawable(R.drawable.cr3);

    	mImageView.setImageDrawable(mPhoto);
    }
    
    private void initView() {
    	if (DEBUG) Log.d(TAG, "initView()");
    	
    	mPlayerName = (TextView) findViewById(R.id.player_name);
    	mPosition = (TextView) findViewById(R.id.position);
    	mHeightWeight = (TextView) findViewById(R.id.height_weight);
    	mFoot = (TextView) findViewById(R.id.foot);
    	mAge = (TextView) findViewById(R.id.age);
    	
    	mImageView = (ImageView) findViewById(R.id.imageView1);
    	
    	mAttackRating = (TextView) findViewById(R.id.attack_rating);
    	mDefenseRating = (TextView) findViewById(R.id.defense_rating);
    	mTeamworkRating = (TextView) findViewById(R.id.teamwork_rating);
    	mMentalRating = (TextView) findViewById(R.id.mental_rating);
    	mPowerRating = (TextView) findViewById(R.id.power_rating);
    	mSpeedRating = (TextView) findViewById(R.id.speed_rating);
    	mStaminaRating = (TextView) findViewById(R.id.stamina_rating);
    	mBallControlRating = (TextView) findViewById(R.id.ball_control_rating);
    	mShotRating = (TextView) findViewById(R.id.shot_rating);
    	mPassRating = (TextView) findViewById(R.id.pass_rating);
    	mHeaderRating = (TextView) findViewById(R.id.header_rating);
    	mCuttingRating = (TextView) findViewById(R.id.cutting_rating);
    	mOverallRating = (TextView) findViewById(R.id.overall_rating);
    	
    	mHexView = (HexView) findViewById(R.id.hex_view);
    }
    
    private void startRateActivity() {
    	if (DEBUG) Log.d(TAG, "RateMeButton onClick()");
		
		Intent i = new Intent(ProfileActivity.this, PlayerRatingActivity.class);
		i.putExtra(Config.KEY_UID, mUid);
		startActivity(i);
    }
    
    private void getAndSetPlayerStatus(long uid) {
    	if (DEBUG) Log.d(TAG, "getAndSetPlayerStatus()");
    	
    	FblSQLiteHelper db = new FblSQLiteHelper(this);
    	PlayerInfo.PlayerProfile pp = db.getPlayerProfile((int)uid);
    	mPlayerName.setText(pp.getName());
    	mPosition.setText(pp.getPosition());
    	mHeightWeight.setText(pp.getHeight() + "cm/"+pp.getWeight()+"Kg");
    	mFoot.setText(pp.getFoot());
    	mAge.setText(pp.getAge());
    	
    	PlayerInfo.PlayerRating pr = db.getPlayerRating((int)uid);
    	
		int atkRating = (int) pr.getAttack();
    	int dfsRating = (int) pr.getDefense();
    	int twkRating = (int) pr.getTeamwork();
    	int mtlRating = (int) pr.getMental();
    	int powRating = (int) pr.getPower();
    	int spdRating = (int) pr.getSpeed();
    	int staRating = (int) pr.getStamina();
    	int blcRating = (int) pr.getBallControl();
    	int pasRating = (int) pr.getPass();
    	int shtRating = (int) pr.getShot();
    	int hdrRating = (int) pr.getHeader();
    	int cutRating = (int) pr.getCutting();
    	int ovrRating = (int) pr.getOverall(); 	    	
    	
    	Utils.setTextAndColor(mAttackRating, atkRating);
    	Utils.setTextAndColor(mDefenseRating, dfsRating);
    	Utils.setTextAndColor(mTeamworkRating, twkRating);
    	Utils.setTextAndColor(mMentalRating, mtlRating);
    	Utils.setTextAndColor(mPowerRating, powRating);
    	Utils.setTextAndColor(mSpeedRating, spdRating);
    	Utils.setTextAndColor(mStaminaRating, staRating);
    	Utils.setTextAndColor(mBallControlRating, blcRating);
    	Utils.setTextAndColor(mShotRating, shtRating);
    	Utils.setTextAndColor(mPassRating, pasRating);
    	Utils.setTextAndColor(mHeaderRating, hdrRating);
    	Utils.setTextAndColor(mCuttingRating, cutRating);
    	Utils.setTextAndColor(mOverallRating, ovrRating);
    	    	
    	float rATK = (float)atkRating / HUNDRED;
    	float rDFS = (float)(dfsRating + cutRating) / (2*HUNDRED);
    	float rTWK = (float)twkRating / HUNDRED;
    	float rMTL = (float)mtlRating / HUNDRED;
    	float rPHY = (float)(powRating + spdRating + staRating) / (3*HUNDRED);
    	float rTEC = (float)(blcRating + pasRating + shtRating + hdrRating) / (4*HUNDRED);
    	
    	//Log.d(TAG, " "+rATK+" "+rTEC+" "+rTWK+" "+rDFS+" "+rMTL+" "+rPHY+" ");
    	
    	mHexView.setRatingAndDraw(rATK, rTEC, rTWK, rDFS, rMTL, rPHY);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_profile, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {        
        switch (item.getItemId()) {
        case R.id.menu_rate:
        	startRateActivity();
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

}
