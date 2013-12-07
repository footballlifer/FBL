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

/**
 * This class defines data type of PhotoTextItem.
 */
public class PhotoTextItem {
	private Drawable mPhoto = null;
	private Drawable mCondition = null;
	private String mName = null;
	private String mPosition = null;
	private HexView mHexView = null;
	
	private float rATK = 0.0f;
	private float rTEC = 0.0f; 
	private float rTWK = 0.0f;
	private float rDFS = 0.0f;
	private float rMTL = 0.0f;
	private float rPHY = 0.0f;
	
	private boolean mSelectable = true;
	
	public PhotoTextItem(){
	}
	
	public PhotoTextItem(Drawable photo, Drawable condition, 
	String name, String position, HexView hexView){
		this.mPhoto = photo;
		this.mCondition = condition;
		this.mName = name;
		this.mPosition = position;
		this.mHexView = hexView;
	}
	
	public void setHexRating(float rATK, float rTEC, 
	float rTWK, float rDFS, float rMTL, float rPHY) {
		this.rATK = rATK;
		this.rTEC = rTEC;
		this.rTWK = rTWK;
		this.rDFS = rDFS;
		this.rMTL = rMTL;
		this.rPHY = rPHY;
	}	
	
	public boolean isSelectable(){
		return mSelectable;
	}
	public void setSelectable(boolean selectable){
		this.mSelectable = selectable;
	}
	
	public void setPhoto(Drawable photo){
		this.mPhoto = photo;
	}
	public Drawable getPhoto(){
		return mPhoto;
	}
	
	public void setCondition(Drawable condition){
		this.mCondition = condition;
	}
	public Drawable getCondition(){
		return mCondition;
	}
	
	public void setName(String name){
		this.mName = name;
	}
	public String getName(){
		return mName;
	}

	public void setPosition(String position){
		this.mPosition = position;
	}
	public String getPosition(){
		return mPosition;
	}
	
	public void setHexView(HexView hexView) {
		this.mHexView = hexView;
	}
	public HexView getHexView() {
		return mHexView;
	}
	
	public float getrATK() {
		return rATK;
	}
	public float getrTEC() {
		return rTEC;
	}
	public float getrTWK() {
		return rTWK;
	}
	public float getrDFS() {
		return rDFS;
	}
	public float getrMTL() {
		return rMTL;
	}
	public float getrPHY() {
		return rPHY;
	}
}

class PhotoTextView extends LinearLayout {
	private boolean DEBUG = true;
	private String TAG = "PhotoTextView";
	
	private ImageView mPhoto;
	private ImageView mCondition;
	private TextView mName;	
	private TextView mPosition;
	private HexView mHexView;
	
	private float rATK = 0.0f;
	private float rTEC = 0.0f; 
	private float rTWK = 0.0f;
	private float rDFS = 0.0f;
	private float rMTL = 0.0f;
	private float rPHY = 0.0f;
	
	public PhotoTextView(Context context, PhotoTextItem mItem) {
		super(context);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.item_view, this, true);
		
		mPhoto = (ImageView) findViewById(R.id.photo);
		mCondition = (ImageView) findViewById(R.id.condition);
		mName = (TextView) findViewById(R.id.name);
		mPosition = (TextView) findViewById(R.id.position_item);
		mHexView = (HexView) findViewById(R.id.hexview_item);
		
		mPhoto.setImageDrawable(mItem.getPhoto());
		mCondition.setImageDrawable(mItem.getCondition());
		mName.setText(mItem.getName());
		mPosition.setText(mItem.getPosition());
		
		this.rATK = mItem.getrATK();
		this.rTEC = mItem.getrTEC();
		this.rTWK = mItem.getrTWK();
		this.rDFS = mItem.getrDFS();
		this.rMTL = mItem.getrMTL();
		this.rPHY = mItem.getrPHY();

		mHexView.setRatingAndDraw(rATK, rTEC, rTWK, rDFS, rMTL, rPHY);
		mItem.setHexView(mHexView);
	}
	
	public void setViewPhoto(Drawable photo){
		mPhoto.setImageDrawable(photo);
	}
	
	public void setViewCondition(Drawable condition) {
		mCondition.setImageDrawable(condition);
	}
	
	public void setViewName(String name){
		mName.setText(name);
	}
	
	public void setViewPosition(String position) {
		mPosition.setText(position);
	}
	
	public void setViewHexView(HexView hexView) {
		this.mHexView = hexView;
	}
	
	public void setViewHexRating(float rATK, float rTEC, 
	float rTWK, float rDFS, float rMTL, float rPHY) {
		mHexView.setRatingAndDraw(rATK, rTEC, rTWK, rDFS, rMTL, rPHY);
	}
}

class PhotoTextListAdapter extends BaseAdapter {
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

