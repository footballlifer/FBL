package nanofuntas.fbl;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class MemberFragment extends Fragment {
	private boolean DEBUG = true;
	private String TAG = "MemberFragment";
	
	private ListView mListView = null;
	private Button mDetails = null;
	
	public MemberFragment() {
    }

    public static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public void onStart(){
    	super.onStart();    	
    	
    	initViews();
    	
    	mDetails.setOnClickListener(new OnClickListener(){			
    		@Override
			public void onClick(View arg0) {
    			if (DEBUG) Log.d(TAG, "details Clicked!");
    			
				Intent i = new Intent(getActivity(), ProfileActivity.class);
				startActivity(i);
			}    	
    	});
    	
    	ArrayList<PhotoTextItem> mItemList = getListView();
    	PhotoTextListAdapter mPhotoTextListAdapter = new PhotoTextListAdapter(getActivity(), mItemList);
        mListView.setAdapter(mPhotoTextListAdapter);
    }
    
    private void initViews() {
    	if (DEBUG) Log.d(TAG, "initViews()");
    	
    	mListView = (ListView) getView().findViewById(R.id.listView1);
    	mDetails = (Button) getView().findViewById(R.id.details);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {    	
    	View view = inflater.inflate(R.layout.fragment_member, container, false);
        return view;
    }   
    
    private ArrayList<PhotoTextItem> getListView(){
    	ArrayList<PhotoTextItem> itemList = new ArrayList<PhotoTextItem>();
    	PhotoTextItem item = null;
    	Drawable photo = getResources().getDrawable(R.drawable.cr2);
    	Drawable condition = getResources().getDrawable(R.drawable.condition);
    	
    	item = new PhotoTextItem();
    	item.setPhoto(photo);
    	item.setCondition(condition);
    	item.setName("name1");
    	itemList.add(item);
    	
    	item = new PhotoTextItem();
    	item.setPhoto(photo);
    	item.setCondition(condition);
    	item.setName("name1");
    	itemList.add(item);
    	
    	item = new PhotoTextItem();
    	item.setPhoto(photo);
    	item.setCondition(condition);
    	item.setName("name1");
    	itemList.add(item);    	

    	item = new PhotoTextItem();
    	item.setPhoto(photo);
    	item.setCondition(condition);
    	item.setName("name1");
    	itemList.add(item);

    	item = new PhotoTextItem();
    	item.setPhoto(photo);
    	item.setCondition(condition);
    	item.setName("name1");
    	itemList.add(item);

    	item = new PhotoTextItem();
    	item.setPhoto(photo);
    	item.setCondition(condition);
    	item.setName("name1");
    	itemList.add(item);

    	item = new PhotoTextItem();
    	item.setPhoto(photo);
    	item.setCondition(condition);
    	item.setName("name1");
    	itemList.add(item);

    	item = new PhotoTextItem();
    	item.setPhoto(photo);
    	item.setCondition(condition);
    	item.setName("name1");
    	itemList.add(item);

    	item = new PhotoTextItem();
    	item.setPhoto(photo);
    	item.setCondition(condition);
    	item.setName("name1");
    	itemList.add(item);

    	item = new PhotoTextItem();
    	item.setPhoto(photo);
    	item.setCondition(condition);
    	item.setName("name1");
    	itemList.add(item);

    	item = new PhotoTextItem();
    	item.setPhoto(photo);
    	item.setCondition(condition);
    	item.setName("name1");
    	itemList.add(item);

    	item = new PhotoTextItem();
    	item.setPhoto(photo);
    	item.setCondition(condition);
    	item.setName("name1");
    	itemList.add(item);

    	item = new PhotoTextItem();
    	item.setPhoto(photo);
    	item.setCondition(condition);
    	item.setName("name1");
    	itemList.add(item);     
    	
    	return itemList;
    	
    }

}
