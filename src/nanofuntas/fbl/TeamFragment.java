package nanofuntas.fbl;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class TeamFragment extends Fragment {

	public TeamFragment() {
    }

    public static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public void onStart(){
    	super.onStart();    
    	ListView mKillerListView = (ListView) getView().findViewById(R.id.killer_list_view);
    	ListView mAssisterListView = (ListView) getView().findViewById(R.id.assister_list_view);
    	
    	ArrayList<RankingPhotoTextItem> mKillerItemList = getKillerListView();
    	RankingPhotoTextListAdapter killerAapter = new RankingPhotoTextListAdapter(getActivity(), mKillerItemList);
    	mKillerListView.setAdapter(killerAapter);
    	
    	ArrayList<RankingPhotoTextItem> mAssisterItemList = getAssisterListView();
    	RankingPhotoTextListAdapter assisterApter = new RankingPhotoTextListAdapter(getActivity(), mAssisterItemList);
    	mAssisterListView.setAdapter(assisterApter);
    	
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {    	
    	View view = inflater.inflate(R.layout.fragment_team, container, false);
        return view;
    } 
    
    
    private ArrayList<RankingPhotoTextItem> getKillerListView(){
    	ArrayList<RankingPhotoTextItem> itemList = new ArrayList<RankingPhotoTextItem>();
    	RankingPhotoTextItem item = null;
    	Drawable photo = getResources().getDrawable(R.drawable.cr2);
    	
    	item = new RankingPhotoTextItem();
    	item.setRanking("1");
    	item.setPhoto(photo);
    	item.setName("WU YONGSHOU");
    	item.setGoals("18");
    	itemList.add(item);
    	
    	item = new RankingPhotoTextItem();
    	item.setRanking("2");
    	item.setPhoto(photo);
    	item.setName("SUN JUNJIE");
    	item.setGoals("16");
    	itemList.add(item);
    	
    	item = new RankingPhotoTextItem();
    	item.setRanking("3");
    	item.setPhoto(photo);
    	item.setName("CUI DONGXU");
    	item.setGoals("12");
    	itemList.add(item);    	
    	
    	return itemList;
    	
    }
    
    private ArrayList<RankingPhotoTextItem> getAssisterListView(){
    	ArrayList<RankingPhotoTextItem> itemList = new ArrayList<RankingPhotoTextItem>();
    	RankingPhotoTextItem item = null;
    	Drawable photo = getResources().getDrawable(R.drawable.cr2);
    	
    	item = new RankingPhotoTextItem();
    	item.setRanking("1");
    	item.setPhoto(photo);
    	item.setName("WU YONGSHOU");
    	item.setGoals("18");
    	itemList.add(item);
    	
    	item = new RankingPhotoTextItem();
    	item.setRanking("2");
    	item.setPhoto(photo);
    	item.setName("SUN JUNJIE");
    	item.setGoals("16");
    	itemList.add(item);
    	
    	item = new RankingPhotoTextItem();
    	item.setRanking("3");
    	item.setPhoto(photo);
    	item.setName("CUI DONGXU");
    	item.setGoals("12");
    	itemList.add(item);    	
    	
    	return itemList;
    	
    } 

}
