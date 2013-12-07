package nanofuntas.fbl;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RankingPhotoTextItem {

	private String mRanking = null;
	private Drawable mPhoto = null;
	private String mName = null;
	private String mGoals = null;
	
	private boolean mSelectable = true;
	
	public RankingPhotoTextItem(){
	}
	
	public RankingPhotoTextItem(String ranking, Drawable photo, String name, String goals){
		this.mRanking = ranking;
		this.mPhoto = photo;
		this.mName = name;
		this.mGoals = goals;
	}
	// selectable
	public boolean isSelectable(){
		return mSelectable;
	}
	public void setSelectable(boolean selectable){
		this.mSelectable = selectable;
	}	
	//ranking
	public void setRanking(String ranking){
		this.mRanking = ranking;
	}	
	public String getRanking(){
		return mRanking;
	}
	//photo
	public void setPhoto(Drawable photo){
		this.mPhoto = photo;
	}
	public Drawable getPhoto(){
		return mPhoto;
	}
	//name
	public void setName(String name){
		this.mName = name;
	}
	public String getName(){
		return mName;
	}
	//goals
	public void setGoals(String goals){
		this.mGoals = goals;
	}	
	public String getGoals(){
		return mGoals;
	}
	
}

class RankingPhotoTextView extends LinearLayout {
	private TextView mRanking;
	private ImageView mPhoto;
	private TextView mName;	
	private TextView mGoals;
	
	public RankingPhotoTextView(Context context, RankingPhotoTextItem mItem) {
		super(context);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.ranking_item_view, this, true);
		
		mRanking = (TextView) findViewById(R.id.ranking);
		mPhoto = (ImageView) findViewById(R.id.photo_ranking);
		mName = (TextView) findViewById(R.id.name_ranking);
		mGoals = (TextView) findViewById(R.id.goals_ranking);		
		
		mRanking.setText(mItem.getRanking());
		mPhoto.setImageDrawable(mItem.getPhoto());
		mName.setText(mItem.getName());
		mGoals.setText(mItem.getGoals());
	}

	public void setViewRanking(String ranking){
		mName.setText(ranking);
	}	
	
	public void setViewPhoto(Drawable photo){
		mPhoto.setImageDrawable(photo);
	}	
	
	public void setViewName(String name){
		mName.setText(name);
	}
	
	public void setViewGoals(String goals){
		mName.setText(goals);
	}
	
}

class RankingPhotoTextListAdapter extends BaseAdapter {

	private Context mContext = null;
	private ArrayList<RankingPhotoTextItem> mItemList = new ArrayList<RankingPhotoTextItem>();
	
	public RankingPhotoTextListAdapter(Context context, ArrayList<RankingPhotoTextItem> itemList){
		this.mContext = context;
		this.mItemList = itemList;
	}
	public void setListItems(ArrayList<RankingPhotoTextItem> itemList){
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
		RankingPhotoTextView itemView = null;
		if(convertView == null){
			itemView = new RankingPhotoTextView(mContext, mItemList.get(position));
		}else{
			itemView = (RankingPhotoTextView) convertView;
			
			itemView.setViewRanking(mItemList.get(position).getRanking());
			itemView.setViewPhoto(mItemList.get(position).getPhoto());
			itemView.setViewName(mItemList.get(position).getName());
			itemView.setViewGoals(mItemList.get(position).getGoals());
		}
		
		return itemView;
	}

}
