package nanofuntas.fbl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
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

	private ListView mListView = null;		
	private Map<Long, PlayerProfile> map = new HashMap<Long, PlayerProfile>();
	
	public MemberFragment() {
    }

    public static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public void onStart(){
    	super.onStart();    	

    	mListView = (ListView) getView().findViewById(R.id.listView1);
    	ArrayList<PhotoTextItem> mItemList = getListView();
    	PhotoTextListAdapter mPhotoTextListAdapter = new PhotoTextListAdapter(getActivity(), mItemList);
        mListView.setAdapter(mPhotoTextListAdapter);
        
        mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				PlayerProfile pp = map.get((long)position);
				
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
    
    private ArrayList<PhotoTextItem> getListView(){
    	if (DEBUG) Log.d(TAG, "getListView()");
    	
    	Drawable photo = getResources().getDrawable(R.drawable.cr3);
    	Drawable condition = getResources().getDrawable(R.drawable.condition);
    	
    	ArrayList<PhotoTextItem> itemList = new ArrayList<PhotoTextItem>();
    	PhotoTextItem item = new PhotoTextItem();
    	JSONObject status = null;
    	PlayerProfile pp = null;
    	
    	SharedPreferences settings = getActivity().getSharedPreferences(Config.FBL_SETTINGS, 0);
    	long tid = settings.getLong(Config.KEY_TID, 0);
    	
    	JSONObject jsonMembersStatus = ServerIface.getMembersStatus(tid);
    	long count = (Long) jsonMembersStatus.get(Config.KEY_MEMBERS_COUNT);    	
    	
    	if (tid == -1) {
    		Log.d(TAG, "tid == -1, return");
    		return itemList;
    	}
    	
    	long atkRating = 0;
    	long dfsRating = 0;
    	long twkRating = 0;
    	long mtlRating = 0;
    	long powRating = 0;
    	long spdRating = 0;
    	long staRating = 0;
    	long blcRating = 0;
    	long pasRating = 0;
    	long shtRating = 0;
    	long hdrRating = 0;
    	long cutRating = 0;
    	long ovrRating = 0;    	    	    	
    	
    	for (long i = 1; i <= count; i++) {
    		status = (JSONObject) jsonMembersStatus.get(Long.toString(i));
    		
        	atkRating = (Long)status.get(Config.KEY_ATTACK);
        	dfsRating = (Long)status.get(Config.KEY_DEFENSE);
        	twkRating = (Long)status.get(Config.KEY_TEAMWORK);
        	mtlRating = (Long)status.get(Config.KEY_MENTAL);
        	powRating = (Long)status.get(Config.KEY_POWER);
        	spdRating = (Long)status.get(Config.KEY_SPEED);
        	staRating = (Long)status.get(Config.KEY_STAMINA);
        	blcRating = (Long)status.get(Config.KEY_BALL_CONTROL);
        	pasRating = (Long)status.get(Config.KEY_PASS);
        	shtRating = (Long)status.get(Config.KEY_SHOT);
        	hdrRating = (Long)status.get(Config.KEY_HEADER);
        	cutRating = (Long)status.get(Config.KEY_CUTTING);
        	ovrRating = (Long)status.get(Config.KEY_OVERALL);    	    	
        	    	
        	float rATK = (float)atkRating / HUNDRED;
        	float rDFS = (float)(dfsRating + cutRating) / (2*HUNDRED);
        	float rTWK = (float)twkRating / HUNDRED;
        	float rMTL = (float)mtlRating / HUNDRED;
        	float rPHY = (float)(powRating + spdRating + staRating) / (3*HUNDRED);
        	float rTEC = (float)(blcRating + pasRating + shtRating + hdrRating) / (4*HUNDRED);
        	
        	Log.d(TAG, " "+rATK+" "+rTEC+" "+rTWK+" "+rDFS+" "+rMTL+" "+rPHY+" ");    		    		
    		
    		item = new PhotoTextItem();
        	item.setPhoto(photo);
        	item.setCondition(condition);
        	item.setName( (String)status.get(Config.KEY_NAME) );
        	item.setHexRating(rATK, rTEC, rTWK, rDFS, rMTL, rPHY);        	
        	itemList.add(item);
        	
        	pp = new PlayerProfile();
    		pp.setUid( (Long)status.get(Config.KEY_UID) );
    		pp.setName( (String)status.get(Config.KEY_NAME) );
    		pp.setPosition( (String)status.get(Config.KEY_POSITION) );
    		map.put(i, pp);
        	
    	}

    	
    	return itemList;
    }

}
