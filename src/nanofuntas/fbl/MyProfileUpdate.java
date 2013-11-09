package nanofuntas.fbl;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MyProfileUpdate extends Activity {
	private ImageView mProfilePhoto;
	private AlertDialog mPhotoDialog;
	private Bitmap mBitmapPic;
	
	private final int CAMERA_CAPTURE = 1;
	private final int CAMERA_CAPTURE_CROP = 2;
	private final int PICK_FROM_GALLERY = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_profile_update);
	    
		mProfilePhoto = (ImageView) findViewById(R.id.profile_photo);

		downloadImage();
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setItems(R.array.photo_array,  new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	switch (which) {
            	case 0:
            		takePhoto();
            		break;
            	case 1:
            		selectPhotoFromGallery();
    				break;
            	}
            }
	    });    
	    mPhotoDialog = builder.create();	
		
	    mProfilePhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mPhotoDialog.show();
			}
		});
	}

	private void takePhoto() {
		try {
        	Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(captureIntent, CAMERA_CAPTURE);
    	}
		catch(ActivityNotFoundException e){
			e.printStackTrace();
		}
	}
	
	private void selectPhotoFromGallery() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 200);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);				

		try {
			startActivityForResult(Intent.createChooser(intent,
					"Complete action using"), PICK_FROM_GALLERY);
		} catch (ActivityNotFoundException e) {
			e.printStackTrace();
		}
	}
	
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode == RESULT_OK) {
    		if(requestCode == CAMERA_CAPTURE){
    			Uri picUri = data.getData();
    			performCrop(picUri);
    		}
    		else if(requestCode == CAMERA_CAPTURE_CROP){
    			Bundle extras = data.getExtras();
    			mBitmapPic = extras.getParcelable("data");
    			mProfilePhoto.setImageBitmap(mBitmapPic);
    		} else if (requestCode == PICK_FROM_GALLERY) {
    			Bundle extras2 = data.getExtras();
    			mBitmapPic = extras2.getParcelable("data");
    			mProfilePhoto.setImageBitmap(mBitmapPic);
    		}
    	}
    }
    
    private void performCrop(Uri picUri){
    	Intent cropIntent = new Intent("com.android.camera.action.CROP"); 
    	cropIntent.setDataAndType(picUri, "image/*");
    	cropIntent.putExtra("crop", "true");
    	cropIntent.putExtra("aspectX", 1);
    	cropIntent.putExtra("aspectY", 1);
    	cropIntent.putExtra("outputX", 256);
    	cropIntent.putExtra("outputY", 256);
    	cropIntent.putExtra("return-data", true);
        
    	try {
	    	startActivityForResult(cropIntent, CAMERA_CAPTURE_CROP);
    	}
    	catch(ActivityNotFoundException e){
    		e.printStackTrace();
    	}
    }

	private void uploadImage() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
		if (mBitmapPic != null)
			mBitmapPic.compress(Bitmap.CompressFormat.PNG, 100, stream);
		
		byte[] imageByteArray = stream.toByteArray();
		String result = ServerIface.uploadImage(imageByteArray, 1);
	} 

	private void downloadImage() {
		byte[] bytesImage = ServerIface.downloadImage(1);
		Bitmap bitmap = BitmapFactory.decodeByteArray(bytesImage, 0, bytesImage.length);
		
		if (bitmap != null)
			mProfilePhoto.setImageBitmap(bitmap);
		else
			Log.d("kakpple", "mBitmap == null");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_profile_update, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {        
        switch (item.getItemId()) {
        case R.id.menu_save:
        	uploadImage();
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}