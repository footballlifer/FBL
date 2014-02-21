package nanofuntas.fbl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONObject;

import nanofuntas.fbl.settings.CreateTeamActivity;
import nanofuntas.fbl.settings.IncruitPlayerActivity;
import nanofuntas.fbl.settings.JoinTeamActivity;
import nanofuntas.fbl.settings.ProfileUpdateActivity;
import nanofuntas.fbl.tb.TacticBoard;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TabViewActivity extends FragmentActivity {
	private final boolean DEBUG = true;
	private final String TAG = "TabViewActivity";
    
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (DEBUG) Log.d(TAG, "onCreate()");
    	    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabview);
        
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //actionBar.setDisplayShowTitleEnabled(false);
        //actionBar.setDisplayShowHomeEnabled(false);

        actionBar.addTab(actionBar.newTab().setText("Team")
        		.setTabListener(new TabListener<TeamFragment>(this, 
        				"TeamFragment", TeamFragment.class) ));
        actionBar.addTab(actionBar.newTab().setText("Member")
        		.setTabListener(new TabListener<MemberFragment>(this, 
        				"MemberFragment", MemberFragment.class) ));
        actionBar.addTab(actionBar.newTab().setText("Settings")
        		.setTabListener(new TabListener<SettingsFragment>(this, 
        				"SettingsFragment", SettingsFragment.class) ));
    }

    public class TabListener<T extends Fragment> implements ActionBar.TabListener {
    	private Fragment mFragment;
    	private final Activity mActivity;
    	private final String mTag;
    	private final Class<T> mClass;
    	
    	public TabListener(Activity activity, String tag, Class<T> cls){
    		mActivity = activity;
    		mTag = tag;
    		mClass = cls;
    	}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			if (DEBUG) Log.d(TAG, "onTabSelected()");
			mFragment = Fragment.instantiate(mActivity, mClass.getName());
			getSupportFragmentManager().beginTransaction().replace(R.id.container, mFragment).commit();
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {}
    	
    }
    
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM,
                getActionBar().getSelectedNavigationIndex());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.activity_tabview, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {        
        switch (item.getItemId()) {
        case R.id.menu_settings:
        	Intent i = new Intent(TabViewActivity.this, TacticBoard.class);
			startActivity(i);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    
    public static class TeamFragment extends Fragment {
    	private final boolean DEBUG = true; 
    	private final String TAG = "TeamFragment";
    	
    	private HexView mTeamHexView;
    	private final float HUNDRED = 100.0f;
    	
    	private TextView mTeamName;
    	private TextView mTeamOVERating;
    	private TextView mTeamATKRating;
    	private TextView mTeamDFSRating;
    	private TextView mTeamTECRating;
    	private TextView mTeamPHYRating;
    	private TextView mTeamTWKRating;
    	private TextView mTeamMTLRating;	
        
    	private TextView mTeamAttackRating;
        private TextView mTeamDefenseRating;
        private TextView mTeamTeamworkRating;
        private TextView mTeamMentalRating;
        private TextView mTeamPowerRating;
        private TextView mTeamSpeedRating;
        private TextView mTeamStaminaRating;
        private TextView mTeamBallControlRating;
        private TextView mTeamPassRating;
        private TextView mTeamShotRating;
        private TextView mTeamHeaderRating;
        private TextView mTeamCuttingRating;
        private TextView mTeamOverallRating;
        
    	public TeamFragment() {}

        public static final String ARG_SECTION_NUMBER = "section_number";

        @Override
        public void onStart(){
        	super.onStart();        	    	
        	initViews();
        	getAndSetTeamStatus();
        }
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {    	
        	View view = inflater.inflate(R.layout.fragment_team, container, false);
            return view;
        }    

        private void initViews() {
        	if (DEBUG) Log.d(TAG, "initViews()");
        	
        	mTeamHexView = (HexView) getView().findViewById(R.id.team_hex_view);
        	
        	mTeamName = (TextView) getView().findViewById(R.id.team_name);
        	mTeamOVERating = (TextView) getView().findViewById(R.id.team_ove_rating);
        	mTeamATKRating = (TextView) getView().findViewById(R.id.team_atk_rating);
        	mTeamDFSRating = (TextView) getView().findViewById(R.id.team_dfs_rating);
        	mTeamTECRating = (TextView) getView().findViewById(R.id.team_tec_rating);
        	mTeamPHYRating = (TextView) getView().findViewById(R.id.team_phy_rating);
        	mTeamTWKRating = (TextView) getView().findViewById(R.id.team_twk_rating);
        	mTeamMTLRating = (TextView) getView().findViewById(R.id.team_mtl_rating);
        	
        	mTeamAttackRating = (TextView) getView().findViewById(R.id.team_attack_rating);
        	mTeamDefenseRating = (TextView) getView().findViewById(R.id.team_defense_rating);
            mTeamTeamworkRating = (TextView) getView().findViewById(R.id.team_teamwork_rating);
            mTeamMentalRating = (TextView) getView().findViewById(R.id.team_mental_rating);
            mTeamPowerRating = (TextView) getView().findViewById(R.id.team_power_rating);
            mTeamSpeedRating = (TextView) getView().findViewById(R.id.team_speed_rating);
            mTeamStaminaRating = (TextView) getView().findViewById(R.id.team_stamina_rating);
            mTeamBallControlRating = (TextView) getView().findViewById(R.id.team_ball_control_rating);
            mTeamPassRating = (TextView) getView().findViewById(R.id.team_pass_rating);
            mTeamShotRating = (TextView) getView().findViewById(R.id.team_shot_rating);
            mTeamHeaderRating = (TextView) getView().findViewById(R.id.team_header_rating);
            mTeamCuttingRating = (TextView) getView().findViewById(R.id.team_cutting_rating);
            mTeamOverallRating = (TextView) getView().findViewById(R.id.team_overall_rating);
        	
        }

        private void getAndSetTeamStatus() {
        	if (DEBUG) Log.d(TAG, "getAndSetTeamStatus()");
        	
        	SharedPreferences settings = getActivity().getSharedPreferences(Config.FBL_SETTINGS, 0);
        	long tid = settings.getLong(Config.KEY_TID, 0);
        	
        	if (tid <=0 ) {
        		if (DEBUG) Log.d(TAG, "tid <= 0, return");
        		return;
        	}
        	
        	JSONObject status = ServerIface.getTeamStatus(tid);
        	Log.d(TAG, "team status:" + status);
        	
        	FblSQLiteHelper db = new FblSQLiteHelper(getActivity());
        	TeamProfile tp = db.getTeamProfile(tid);
        	TeamRating tr = db.getTeamRating(tid);
        	TeamLevel tl = db.getTeamLevel(tid);
        	
        	mTeamName.setText( tp.getTeamName() );
        	
        	long tmAtkR = tl.getATK();
        	long tmDfsR = tl.getDFS();
        	long tmTecR = tl.getTEC();
        	long tmPhyR = tl.getPHY();
        	long tmTwkR = tl.getTWK();
        	long tmMtlR = tl.getMTL();
        	long tmOvrR = tl.getOVERALL();
        	
        	Utils.setTextAndColor(mTeamATKRating, (int)tmAtkR);
        	Utils.setTextAndColor(mTeamDFSRating, (int)tmDfsR);
        	Utils.setTextAndColor(mTeamTECRating, (int)tmTecR);
        	Utils.setTextAndColor(mTeamPHYRating, (int)tmPhyR);
        	Utils.setTextAndColor(mTeamTWKRating, (int)tmTwkR);
        	Utils.setTextAndColor(mTeamMTLRating, (int)tmMtlR);    	
        	Utils.setTextAndColor(mTeamOVERating, (int)tmOvrR);
        	
        	long atkRating = tr.getAttack();
        	long dfsRating = tr.getDefense();
        	long twkRating = tr.getTeamwork();
        	long mtlRating = tr.getMental();
        	long powRating = tr.getPower();
        	long spdRating = tr.getSpeed();
        	long staRating = tr.getStamina();
        	long blcRating = tr.getBallControl();
        	long pasRating = tr.getPass();
        	long shtRating = tr.getShot();
        	long hdrRating = tr.getHeader();
        	long cutRating = tr.getCutting();
        	long ovrRating = tr.getOverall();
        	
        	Utils.setTextAndColor(mTeamAttackRating, (int)atkRating);
        	Utils.setTextAndColor(mTeamDefenseRating, (int)dfsRating);
        	Utils.setTextAndColor(mTeamTeamworkRating, (int)twkRating);
        	Utils.setTextAndColor(mTeamMentalRating, (int)mtlRating);
        	Utils.setTextAndColor(mTeamPowerRating, (int)powRating);
        	Utils.setTextAndColor(mTeamSpeedRating, (int)spdRating);
        	Utils.setTextAndColor(mTeamStaminaRating, (int)staRating);
        	Utils.setTextAndColor(mTeamBallControlRating, (int)blcRating);
        	Utils.setTextAndColor(mTeamPassRating, (int)pasRating);
        	Utils.setTextAndColor(mTeamShotRating, (int)shtRating);
        	Utils.setTextAndColor(mTeamHeaderRating, (int)hdrRating);
        	Utils.setTextAndColor(mTeamCuttingRating, (int)cutRating);
        	Utils.setTextAndColor(mTeamOverallRating, (int)ovrRating);
        	
        	float rATK = (float)(atkRating) / (HUNDRED);
        	float rDFS = (float)(dfsRating + cutRating) / (2*HUNDRED);
        	float rTWK = (float)(twkRating) / (HUNDRED);
        	float rMTL = (float)(mtlRating) / (HUNDRED);
        	float rPHY = (float)(powRating + spdRating + staRating) / (3*HUNDRED);
        	float rTEC = (float)(blcRating + pasRating + shtRating + hdrRating) / (4*HUNDRED);
        	
        	mTeamHexView.setRatingAndDraw(rATK, rTEC, rTWK, rDFS, rMTL, rPHY);
        }
        
    }
    
    
    public static class SettingsFragment extends Fragment {
    	private final boolean DEBUG = true; 
    	private final String TAG = "SettingFragment2";
    	
    	private TextView mCreateTeam;
    	private TextView mJoinTeam;
    	private TextView mIncruitPlayer;
    	private TextView mUpdateMyProfile;
    	private TextView mLogOut;
    	
    	public SettingsFragment() {}

        public static final String ARG_SECTION_NUMBER = "section_number";

        @Override
        public void onStart(){
        	super.onStart();        	    	
        	initViews();
        	
        	mCreateTeam.setOnClickListener(new OnClickListener() {
    			@Override
    			public void onClick(View arg0) {
    				Intent i = new Intent(getView().getContext(), CreateTeamActivity.class);
    				startActivity(i);
    			}    		
        	});
        	
        	mJoinTeam.setOnClickListener(new OnClickListener() {
    			@Override
    			public void onClick(View arg0) {
    				Intent i = new Intent(getView().getContext(), JoinTeamActivity.class);
    				startActivity(i);
    			}    		
        	});
        	
        	mIncruitPlayer.setOnClickListener(new OnClickListener() {
    			@Override
    			public void onClick(View arg0) {
    				Intent i = new Intent(getView().getContext(), IncruitPlayerActivity.class);
    				startActivity(i);
    			}    		
        	});
        	
        	mUpdateMyProfile.setOnClickListener(new OnClickListener() {
    			@Override
    			public void onClick(View arg0) {
    				Intent i = new Intent(getView().getContext(), ProfileUpdateActivity.class);
    				startActivity(i);
    			}    		
        	});
        	
        	mLogOut.setOnClickListener(new OnClickListener() {
    			@Override
    			public void onClick(View arg0) {
    				Utils.removeLoginIdPw();
    				
    				FblSQLiteHelper db = new FblSQLiteHelper(getActivity());
    				db.dropAllTables();
    				db.createAllTables();
    				
    				Intent i = new Intent(getView().getContext(), LogNRegActivity.class);
    				startActivity(i);
    			}
        	});
        }
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {    	
        	View view = inflater.inflate(R.layout.fragment_settings, container, false);
            return view;
        }

        private void initViews() {
        	if (DEBUG) Log.d(TAG, "initViews()");  
        	mCreateTeam = (TextView) getView().findViewById(R.id.create_team);
        	mJoinTeam = (TextView) getView().findViewById(R.id.join_team);
        	mIncruitPlayer = (TextView) getView().findViewById(R.id.incruit_player);
        	mUpdateMyProfile = (TextView) getView().findViewById(R.id.update_my_profile);
        	mLogOut = (TextView) getView().findViewById(R.id.log_out);
        }
    }
    
    public static class MemberFragment extends Fragment {
    	private boolean DEBUG = true;
    	private String TAG = "MemberFragment";
    	private static final float HUNDRED = 100.0f;

    	private ListView mListView;		
    	// this map is index - player profile map, which is used in onItemClick
    	private Map<Long, Long> map = new HashMap<Long, Long>();
    	
    	private Drawable mPhoto;
    	private Drawable mArrowGreen;
    	private Drawable mArrowYellow;
    	private Drawable mArrowRed;
    	
    	public MemberFragment() {}

        public static final String ARG_SECTION_NUMBER = "section_number";

        @Override
        public void onStart(){
        	super.onStart();    	

        	mArrowGreen = getResources().getDrawable(R.drawable.arrow_green);
        	mArrowYellow = getResources().getDrawable(R.drawable.arrow_yellow);
        	mArrowRed = getResources().getDrawable(R.drawable.arrow_red);
        	mPhoto = getResources().getDrawable(R.drawable.cr3);

        	mListView = (ListView) getView().findViewById(R.id.listView1);
        	ArrayList<PhotoTextItem> list = getListView();
        	PhotoTextListAdapter adapter = new PhotoTextListAdapter(getActivity(), list);
            mListView.setAdapter(adapter);
            
            mListView.setOnItemClickListener(new OnItemClickListener() {
    			@Override
    			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
    				if (DEBUG) Log.d(TAG, "onItemClick()");
    				// map index starts from 0
    				long uid = map.get((long)position);
    				
    				Intent i = new Intent(getActivity(), ProfileActivity.class);
    				i.putExtra(Config.KEY_UID, uid);
    				startActivity(i);
    				
    				Toast.makeText(getActivity(), "Position:" + position, Toast.LENGTH_SHORT).show();
    			}
            });
        }
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {    	
        	View view = inflater.inflate(R.layout.fragment_member, container, false);
            return view;
        }
        
        private ArrayList<PhotoTextItem> getListView() {
        	if (DEBUG) Log.d(TAG, "getListView()");
        	
        	ArrayList<PhotoTextItem> itemList = new ArrayList<PhotoTextItem>();
        	
    		FblSQLiteHelper db = new FblSQLiteHelper(getActivity());
    		List<PlayerRating> listPR = db.getAllPlayerRating();
        	List<PlayerProfile> listPP = db.getAllPlayerProfile();
        	
        	for (long i = 0; i < listPR.size(); i++) {
        		PlayerRating pr = listPR.get((int)i);
        		PlayerProfile pp = listPP.get((int)i);
        		setItem(pr, pp, itemList);
        		// this map must match listView position
        		map.put(i, pr.getUid());
        	}
        	
        	return itemList;
        }
        
        /**
         * This function setItemAndMap gets player status from status JSONObject
         * and sets item(PhotoTextItem) parameter and playerProfile parameter
         * 
         * @param status player status including player profile and rating
         * @param itemList itemList which shows member list 
         * @param map index to player profile mapping
         * @param indexToMap index of player profile to be added to map
         */
        private void setItem(PlayerRating pr, PlayerProfile pp, 
        ArrayList<PhotoTextItem> itemList) {
        	if (DEBUG) Log.d(TAG, "setItem()");
        	
        	PhotoTextItem item = new PhotoTextItem();
        	//set condition
        	setRandomCondition(item);

        	// set player profile
        	String name = pp.getName();
        	String position = pp.getPosition();
        	item.setName(name);
        	item.setPosition(position);
        	
        	// set image of item
        	long uid = pr.getUid();
        	Bitmap bitmap = Utils.getProfileImage(uid);
        	
        	if (bitmap != null)
        		mPhoto =  new BitmapDrawable(bitmap);
        	else 
            	mPhoto = getResources().getDrawable(R.drawable.cr3);
        	
        	if (mPhoto != null)
        		item.setPhoto(mPhoto);
        	
        	// set hex of item
        	long atkRating = pr.getAttack();
        	long dfsRating = pr.getDefense();
        	long twkRating = pr.getTeamwork();
        	long mtlRating = pr.getMental();
        	long powRating = pr.getPower();
        	long spdRating = pr.getSpeed();
        	long staRating = pr.getStamina();
        	long blcRating = pr.getBallControl();
        	long pasRating = pr.getPass();
        	long shtRating = pr.getShot();
        	long hdrRating = pr.getHeader();
        	long cutRating = pr.getCutting();
        	long ovrRating = pr.getOverall();	    	
        	
        	float rATK = (float)atkRating / HUNDRED;
        	float rDFS = (float)(dfsRating + cutRating) / (2*HUNDRED);
        	float rTWK = (float)twkRating / HUNDRED;
        	float rMTL = (float)mtlRating / HUNDRED;
        	float rPHY = (float)(powRating + spdRating + staRating) / (3*HUNDRED);
        	float rTEC = (float)(blcRating + pasRating + shtRating + hdrRating) / (4*HUNDRED);
        	
        	Log.d(TAG, " "+rATK+" "+rTEC+" "+rTWK+" "+rDFS+" "+rMTL+" "+rPHY+" ");
        	item.setHexRating(rATK, rTEC, rTWK, rDFS, rMTL, rPHY);        	
        	
        	itemList.add(item);
        }
        
        private void setRandomCondition(PhotoTextItem item) {
        	Random rand = new Random(System.currentTimeMillis());
        	switch (rand.nextInt(3)) {
        	case 0:
            	item.setCondition(mArrowGreen);
            	break;
        	case 1: 
            	item.setCondition(mArrowYellow);
            	break;
        	case 2:
            	item.setCondition(mArrowRed);
            	break;
            default:
            	Log.d(TAG, "setRandomArrow(), default case");
        	}
        }
        

        class PhotoTextItem {
        	private Drawable mPhoto;
        	private Drawable mCondition;
        	private String mName;
        	private String mPosition;
        	private HexView mHexView;
        	
        	private float rATK = 0.0f;
        	private float rTEC = 0.0f; 
        	private float rTWK = 0.0f;
        	private float rDFS = 0.0f;
        	private float rMTL = 0.0f;
        	private float rPHY = 0.0f;
        	
        	private boolean mSelectable = true;
        	
        	public PhotoTextItem(){}
        	
        	public PhotoTextItem(Drawable photo, Drawable condition, 
        	String name, String position, HexView hexView){
        		this.mPhoto = photo;
        		this.mCondition = condition;
        		this.mName = name;
        		this.mPosition = position;
        		this.mHexView = hexView;
        	}
        	
        	public void setHexRating(float rATK, float rTEC, 
        	float rTWK, float rDFS, float rMTL, float rPHY) {
        		this.rATK = rATK;
        		this.rTEC = rTEC;
        		this.rTWK = rTWK;
        		this.rDFS = rDFS;
        		this.rMTL = rMTL;
        		this.rPHY = rPHY;
        	}	
        	
        	public boolean isSelectable(){
        		return mSelectable;
        	}
        	public void setSelectable(boolean selectable){
        		this.mSelectable = selectable;
        	}
        	
        	public void setPhoto(Drawable photo){
        		this.mPhoto = photo;
        	}
        	public Drawable getPhoto(){
        		return mPhoto;
        	}
        	
        	public void setCondition(Drawable condition){
        		this.mCondition = condition;
        	}
        	public Drawable getCondition(){
        		return mCondition;
        	}
        	
        	public void setName(String name){
        		this.mName = name;
        	}
        	public String getName(){
        		return mName;
        	}

        	public void setPosition(String position){
        		this.mPosition = position;
        	}
        	public String getPosition(){
        		return mPosition;
        	}
        	
        	public void setHexView(HexView hexView) {
        		this.mHexView = hexView;
        	}
        	public HexView getHexView() {
        		return mHexView;
        	}
        	
        	public float getrATK() {
        		return rATK;
        	}
        	public float getrTEC() {
        		return rTEC;
        	}
        	public float getrTWK() {
        		return rTWK;
        	}
        	public float getrDFS() {
        		return rDFS;
        	}
        	public float getrMTL() {
        		return rMTL;
        	}
        	public float getrPHY() {
        		return rPHY;
        	}
        }

        class PhotoTextView extends LinearLayout {
        	private boolean DEBUG = true;
        	private String TAG = "PhotoTextView";
        	
        	private ImageView mPhoto;
        	private ImageView mCondition;
        	private TextView mName;	
        	private TextView mPosition;
        	private HexView mHexView;
        	
        	private float rATK = 0.0f;
        	private float rTEC = 0.0f; 
        	private float rTWK = 0.0f;
        	private float rDFS = 0.0f;
        	private float rMTL = 0.0f;
        	private float rPHY = 0.0f;
        	
        	public PhotoTextView(Context context, PhotoTextItem mItem) {
        		super(context);
        		
        		LayoutInflater inflater = 
        				(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        		inflater.inflate(R.layout.item_view, this, true);
        		
        		mPhoto = (ImageView) findViewById(R.id.photo);
        		mCondition = (ImageView) findViewById(R.id.condition);
        		mName = (TextView) findViewById(R.id.name);
        		mPosition = (TextView) findViewById(R.id.position_item);
        		mHexView = (HexView) findViewById(R.id.hexview_item);
        		
        		mPhoto.setImageDrawable(mItem.getPhoto());
        		mCondition.setImageDrawable(mItem.getCondition());
        		mName.setText(mItem.getName());
        		mPosition.setText(mItem.getPosition());
        		
        		this.rATK = mItem.getrATK();
        		this.rTEC = mItem.getrTEC();
        		this.rTWK = mItem.getrTWK();
        		this.rDFS = mItem.getrDFS();
        		this.rMTL = mItem.getrMTL();
        		this.rPHY = mItem.getrPHY();

        		mHexView.setRatingAndDraw(rATK, rTEC, rTWK, rDFS, rMTL, rPHY);
        		mItem.setHexView(mHexView);
        	}
        	
        	public void setViewPhoto(Drawable photo){
        		mPhoto.setImageDrawable(photo);
        	}
        	public void setViewCondition(Drawable condition) {
        		mCondition.setImageDrawable(condition);
        	}	
        	public void setViewName(String name){
        		mName.setText(name);
        	}	
        	public void setViewPosition(String position) {
        		mPosition.setText(position);
        	}	
        	public void setViewHexView(HexView hexView) {
        		this.mHexView = hexView;
        	}	
        	public void setViewHexRating(float rATK, float rTEC, 
        	float rTWK, float rDFS, float rMTL, float rPHY) {
        		mHexView.setRatingAndDraw(rATK, rTEC, rTWK, rDFS, rMTL, rPHY);
        	}
        }

        class PhotoTextListAdapter extends BaseAdapter {
        	private boolean DEBUG = true;
        	private String TAG = "PhotoTextListAdapter";
        	
        	private Context mContext = null;
        	private ArrayList<PhotoTextItem> mItemList = new ArrayList<PhotoTextItem>();
        	
        	public PhotoTextListAdapter(Context context, ArrayList<PhotoTextItem> itemList){
        		this.mContext = context;
        		this.mItemList = itemList;
        	}
        	public void setListItems(ArrayList<PhotoTextItem> itemList){
        		this.mItemList = itemList;
        	}
        	
        	@Override
        	public int getCount() {
        		return mItemList.size();
        	}

        	@Override
        	public Object getItem(int position) {
        		return mItemList.get(position);
        	}

        	@Override
        	public long getItemId(int position) {
        		return position;
        	}

        	@Override
        	public View getView(int position, View convertView, ViewGroup parent) {
        		PhotoTextView itemView = null;
        		if(convertView == null){
        			itemView = new PhotoTextView(mContext, mItemList.get(position));
        		}else{
        			itemView = (PhotoTextView) convertView;
        			
        			itemView.setViewPhoto(mItemList.get(position).getPhoto());
        			itemView.setViewCondition(mItemList.get(position).getCondition());
        			itemView.setViewName(mItemList.get(position).getName());
        			itemView.setViewPosition(mItemList.get(position).getPosition());
        			//itemView.setHexView(mItemList.get(position).getHexView());
        			
        			PhotoTextItem item = mItemList.get(position);
        			itemView.setViewHexRating(item.getrATK(), item.getrTEC(), 
        			item.getrTWK(), item.getrDFS(), item.getrMTL(), item.getrPHY());
        		}
        		
        		return itemView;
        	}

        }

    }
    
    
}
