package nanofuntas.fbl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
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
	private Map<Long, PlayerProfile> map = new HashMap<Long, PlayerProfile>();
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
    	
    	mListView = (ListView) getView().findViewById(R.id.listView1);
    	ArrayList<PhotoTextItem> mItemList = getListView();
    	PhotoTextListAdapter mPhotoTextListAdapter = new PhotoTextListAdapter(getActivity(), mItemList);
        mListView.setAdapter(mPhotoTextListAdapter);
        
        mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				if (DEBUG) Log.d(TAG, "onItemClick()");
				// map index starts from 1
				PlayerProfile pp = map.get((long)position+1);
				
				Intent i = new Intent(getActivity(), ProfileActivity.class);
				i.putExtra(Config.KEY_UID, pp.getUid());
				i.putExtra(Config.KEY_NAME, pp.getName());
				i.putExtra(Config.KEY_POSITION, pp.getPosition());
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
    	JSONObject status = null;
    	mPhoto = getResources().getDrawable(R.drawable.cr3);
    	
    	long tid = Utils.getMyTid();
    	
    	JSONObject jsonMembersStatus = ServerIface.getMembersStatus(tid);
    	long count = (Long) jsonMembersStatus.get(Config.KEY_MEMBERS_COUNT);    	
    	
    	for (long i = 1; i <= count; i++) {
    		status = (JSONObject) jsonMembersStatus.get(Long.toString(i));
    		
    		
    		
    		setItemAndMap(status, itemList, map, i);
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
    private void setItemAndMap(JSONObject status, 
    ArrayList<PhotoTextItem> itemList, Map<Long, PlayerProfile> map, long indexToMap) {
    	if (DEBUG) Log.d(TAG, "setItemAndMap()");
    	
    	PhotoTextItem item = null; 
    	PlayerProfile pp = null;
    	
    	long atkRating = (Long)status.get(Config.KEY_ATTACK);
    	long dfsRating = (Long)status.get(Config.KEY_DEFENSE);
    	long twkRating = (Long)status.get(Config.KEY_TEAMWORK);
    	long mtlRating = (Long)status.get(Config.KEY_MENTAL);
    	long powRating = (Long)status.get(Config.KEY_POWER);
    	long spdRating = (Long)status.get(Config.KEY_SPEED);
    	long staRating = (Long)status.get(Config.KEY_STAMINA);
    	long blcRating = (Long)status.get(Config.KEY_BALL_CONTROL);
    	long pasRating = (Long)status.get(Config.KEY_PASS);
    	long shtRating = (Long)status.get(Config.KEY_SHOT);
    	long hdrRating = (Long)status.get(Config.KEY_HEADER);
    	long cutRating = (Long)status.get(Config.KEY_CUTTING);
    	long ovrRating = (Long)status.get(Config.KEY_OVERALL);    	    	
    	    	
    	float rATK = (float)atkRating / HUNDRED;
    	float rDFS = (float)(dfsRating + cutRating) / (2*HUNDRED);
    	float rTWK = (float)twkRating / HUNDRED;
    	float rMTL = (float)mtlRating / HUNDRED;
    	float rPHY = (float)(powRating + spdRating + staRating) / (3*HUNDRED);
    	float rTEC = (float)(blcRating + pasRating + shtRating + hdrRating) / (4*HUNDRED);
    	
    	Log.d(TAG, " "+rATK+" "+rTEC+" "+rTWK+" "+rDFS+" "+rMTL+" "+rPHY+" ");    		    		
		
    	//TODO test image
    	long uid = (Long)status.get(Config.KEY_UID);
    	byte[] b = ServerIface.downloadImage(uid);
    	if (b != null)
    		mPhoto =  new BitmapDrawable(BitmapFactory.decodeByteArray(b, 0, b.length));
    	else 
        	mPhoto = getResources().getDrawable(R.drawable.cr3);

    	item = new PhotoTextItem();
		
    	if (mPhoto != null)
    		item.setPhoto(mPhoto);
    	
    	//item.setCondition(mArrowRed);
    	setRandomCondition(item);
    	item.setName( (String)status.get(Config.KEY_NAME) );
    	item.setPosition( (String)status.get(Config.KEY_POSITION) );
    	item.setHexRating(rATK, rTEC, rTWK, rDFS, rMTL, rPHY);        	
    	itemList.add(item);
    	
    	pp = new PlayerProfile();
		pp.setUid( (Long)status.get(Config.KEY_UID) );
		pp.setName( (String)status.get(Config.KEY_NAME) );
		pp.setPosition( (String)status.get(Config.KEY_POSITION) );
		// this map must match listView position
		map.put(indexToMap, pp);
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