package nanofuntas.fbl;

import android.graphics.drawable.Drawable;

/**
 * This class defines data type of PhotoTextItem.
 */
public class PhotoTextItem {
	private Drawable mPhoto = null;
	private Drawable mCondition = null;
	private String mName = null;
	
	private boolean mSelectable = true;
	
	public PhotoTextItem(){
	}
	
	public PhotoTextItem(Drawable photo, Drawable condition, String name){
		this.mPhoto = photo;
		this.mCondition = condition;
		this.mName = name;
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

}
