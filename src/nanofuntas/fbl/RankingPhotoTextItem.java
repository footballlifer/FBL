package nanofuntas.fbl;

import android.graphics.drawable.Drawable;

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
