package nanofuntas.fbl;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class TeamFragment extends Fragment {
	private final boolean DEBUG = true; 
	private final String TAG = "TeamFragment";
	
	private HexView mTeamHexView = null;
	private final float HUNDRED = 100.0f;
	
	private TextView mTeamName = null;
	private TextView mTeamOverallRating = null;
	private TextView mTeamATKRating = null;
	private TextView mTeamDFSRating = null;
	private TextView mTeamTECRating = null;
	private TextView mTeamPHYRating = null;
	private TextView mTeamTWKRating = null;
	private TextView mTeamMTLRating = null;	
	
	ListView mKillerListView = null;
	ListView mAssisterListView = null;		
	
	public TeamFragment() {
    }

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
    	mTeamOverallRating = (TextView) getView().findViewById(R.id.team_overall_rating);
    	mTeamATKRating = (TextView) getView().findViewById(R.id.team_atk_rating);
    	mTeamDFSRating = (TextView) getView().findViewById(R.id.team_dfs_rating);
    	mTeamTECRating = (TextView) getView().findViewById(R.id.team_tec_rating);
    	mTeamPHYRating = (TextView) getView().findViewById(R.id.team_phy_rating);
    	mTeamTWKRating = (TextView) getView().findViewById(R.id.team_twk_rating);
    	mTeamMTLRating = (TextView) getView().findViewById(R.id.team_mtl_rating);
    	
    	mKillerListView = (ListView) getView().findViewById(R.id.killer_list_view);
    	mAssisterListView = (ListView) getView().findViewById(R.id.assister_list_view);    	
    	
    	ArrayList<RankingPhotoTextItem> mKillerItemList = getKillerListView();
    	RankingPhotoTextListAdapter killerAapter = new RankingPhotoTextListAdapter(getActivity(), mKillerItemList);
    	mKillerListView.setAdapter(killerAapter);
    	
    	ArrayList<RankingPhotoTextItem> mAssisterItemList = getAssisterListView();
    	RankingPhotoTextListAdapter assisterApter = new RankingPhotoTextListAdapter(getActivity(), mAssisterItemList);
    	mAssisterListView.setAdapter(assisterApter);
    }

    private void getAndSetTeamStatus() {
    	if (DEBUG) Log.d(TAG, "getAndSetTeamStatus()");
    	
    	JSONObject status = null;
    	SharedPreferences settings = getActivity().getSharedPreferences(Config.FBL_SETTINGS, 0);
    	long tid = settings.getLong(Config.KEY_TID, 0);
    	
    	status = ServerIface.getTeamStatus(tid);
    	Log.d(TAG, "team status:" + status);
    	
    	mTeamName.setText( (String)status.get(Config.KEY_TEAM_NAME));
    	
    	Long tmAtkR = (Long)status.get(Config.KEY_TEAM_ATK);
    	Long tmDfsR = (Long)status.get(Config.KEY_TEAM_DFS);
    	Long tmTecR = (Long)status.get(Config.KEY_TEAM_TEC);
    	Long tmPhyR = (Long)status.get(Config.KEY_TEAM_PHY);
    	Long tmTwkR = (Long)status.get(Config.KEY_TEAM_TWK);
    	Long tmMtlR = (Long)status.get(Config.KEY_TEAM_MTL);
    	Long tmOvrR = (Long)status.get(Config.KEY_TEAM_OVERALL);    	    	
    	    	
    	setTextAndColor(mTeamATKRating, tmAtkR);
    	setTextAndColor(mTeamDFSRating, tmDfsR);
    	setTextAndColor(mTeamTECRating, tmTecR);
    	setTextAndColor(mTeamPHYRating, tmPhyR);
    	setTextAndColor(mTeamTWKRating, tmTwkR);
    	setTextAndColor(mTeamMTLRating, tmMtlR);    	
    	setTextAndColor(mTeamOverallRating, tmOvrR);
    	
    	float tmAtkRatio = (float)tmAtkR / HUNDRED;
    	float tmDfsRatio = (float)tmDfsR / HUNDRED;
    	float tmTecRatio = (float)tmTecR / HUNDRED;
    	float tmPhyRatio = (float)tmPhyR / HUNDRED;
    	float tmTwkRatio = (float)tmTwkR / HUNDRED;
    	float tmMtlRatio = (float)tmMtlR / HUNDRED;
    	    	
    	Log.d(TAG, " "+tmAtkRatio+" "+tmDfsRatio+" "+tmTecRatio+" "
    			+tmPhyRatio+" "+tmTwkRatio+" "+tmMtlRatio+" ");
    	
    	mTeamHexView.setRatingAndDraw(tmAtkRatio, tmDfsRatio, 
    			tmTecRatio, tmPhyRatio, tmTwkRatio, tmMtlRatio);
    	//mTeamHexView.setRatingAndDraw(0.79f, 0.81f, 0.61f, 0.91f, 0.51f, 0.71f);
    }
       
    private void setTextAndColor(TextView tv, Long rating) {
    	if( 0<= rating && rating <= 79) {
    		tv.setTextColor(Color.WHITE);
    	} else if (80 <= rating && rating <= 89) {
    		tv.setTextColor(Color.YELLOW);
    	} else if (90 <= rating && rating <= 100) {
    		tv.setTextColor(Color.RED);
    	}

    	tv.setText( rating.toString());
    }
    
    private ArrayList<RankingPhotoTextItem> getKillerListView(){
    	//TODO: KillerListView
    	
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
    	//TODO: AssisterListView
    	
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
