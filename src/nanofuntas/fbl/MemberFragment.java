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
    	JSONObject profile = null;
    	PlayerProfile pp = null;
    	
    	SharedPreferences settings = getActivity().getSharedPreferences(Config.FBL_SETTINGS, 0);
    	long uid = settings.getLong(Config.KEY_UID, 0);
    	long tid = settings.getLong(Config.KEY_TID, 0);
    	
    	item.setPhoto(photo);
    	item.setCondition(condition);
    	item.setName("Me");
    	itemList.add(item);
    	
    	pp = new PlayerProfile(uid, "Me", "FW");
    	map.put( (long)0, pp);
    	
    	if (tid == -1) {
    		Log.d(TAG, "tid == -1, return");
    		return itemList;
    	}
    	
    	JSONObject jsonMembersProfile = ServerIface.getMembersProfile(tid);
    	long count = (Long) jsonMembersProfile.get(Config.KEY_MEMBERS_COUNT);    	
    	
    	for (long i = 1; i <= count; i++) {
    		profile = (JSONObject) jsonMembersProfile.get(Long.toString(i));
    		
    		pp = new PlayerProfile();
    		pp.setUid( (Long)profile.get(Config.KEY_UID) );
    		pp.setName( (String)profile.get(Config.KEY_NAME) );
    		pp.setPosition( (String)profile.get(Config.KEY_POSITION) );

    		map.put(i, pp);
    		
    		item = new PhotoTextItem();
        	item.setPhoto(photo);
        	item.setCondition(condition);
        	item.setName( (String)profile.get(Config.KEY_NAME) );
        	itemList.add(item);
    	}
    	
    	return itemList;
    }

}
