package nanofuntas.fbl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PhotoTextListAdapter extends BaseAdapter {
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
