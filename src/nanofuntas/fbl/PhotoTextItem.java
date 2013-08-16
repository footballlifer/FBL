package nanofuntas.fbl;

import android.graphics.drawable.Drawable;

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
