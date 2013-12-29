package nanofuntas.fbl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MemberFragment extends Fragment {
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
    	ArrayList<PhotoTextItem> mItemList = getListView();
    	PhotoTextListAdapter mPhotoTextListAdapter = new PhotoTextListAdapter(getActivity(), mItemList);
        mListView.setAdapter(mPhotoTextListAdapter);
        
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
}