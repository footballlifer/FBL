package nanofuntas.fbl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PhotoTextView extends LinearLayout {
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
