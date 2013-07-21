package nanofuntas.fbl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PhotoTextView extends LinearLayout {
	private ImageView mPhoto;
	private ImageView mCondition;
	private TextView mName;	
	
	public PhotoTextView(Context context, PhotoTextItem mItem) {
		super(context);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.item_view, this, true);
		
		mPhoto = (ImageView) findViewById(R.id.photo);
		mCondition = (ImageView) findViewById(R.id.condition);
		mName = (TextView) findViewById(R.id.name);
				
		mPhoto.setImageDrawable(mItem.getPhoto());
		mCondition.setImageDrawable(mItem.getCondition());
		mName.setText(mItem.getName());
		
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
	
}
