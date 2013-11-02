package nanofuntas.fbl;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ImageActivity extends Activity {
	ImageView mViewDownload;
	Button mUpload;
	Button mDownload;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		
		mViewDownload = (ImageView) findViewById(R.id.image_downloaded);
		mUpload = (Button) findViewById(R.id.button1);
		mDownload = (Button) findViewById(R.id.button2);
		
		mUpload.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.hex);
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
				byte[] imageByteArray = stream.toByteArray();
				
				String result = ServerIface.uploadImage(imageByteArray, 1);
				
			}
		});
		
		mDownload.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				byte[] bytesImage = ServerIface.downloadImage(1);
				Bitmap bitmap = BitmapFactory.decodeByteArray(bytesImage, 0, bytesImage.length);
				
				if (bitmap != null)
					mViewDownload.setImageBitmap(bitmap);
				else
					Log.d("kakpple", "mBitmap == null");
			}	
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image, menu);
		return true;
	}

}
