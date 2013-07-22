package nanofuntas.fbl;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class RankingPhotoTextListAdapter extends BaseAdapter {

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
