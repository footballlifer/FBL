package nanofuntas.fbl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RankingPhotoTextView extends LinearLayout {
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
