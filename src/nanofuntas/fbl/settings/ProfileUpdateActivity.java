package nanofuntas.fbl.settings;

import java.io.ByteArrayOutputStream;

import nanofuntas.fbl.Config;
import nanofuntas.fbl.LogNRegActivity;
import nanofuntas.fbl.R;
import nanofuntas.fbl.ServerIface;
import nanofuntas.fbl.SplashScreenActivity;
import nanofuntas.fbl.TabViewActivity;
import nanofuntas.fbl.Utils;

import org.json.simple.JSONObject;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ProfileUpdateActivity extends Activity {
	private final boolean DEBUG = true;
	private final String TAG = "MyProfileUpdate";
	
	private ImageView mProfilePhoto;
	private AlertDialog mPhotoDialog;
	private Bitmap mBitmapPic;
	
	private EditText mNameUpdate;
	private EditText mPositionUpdate;
	private EditText mAgeUpdate;
	private EditText mHeightUpdate;
	private EditText mWeightUpdate;
	private EditText mFootUpdate;
	
	private final int CAMERA_CAPTURE = 1;
	private final int CAMERA_CAPTURE_CROP = 2;
	private final int PICK_FROM_GALLERY = 3;
		
	private long UID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_update);
	    
    	UID = Utils.getMyUid();
    	initViews();
		downloadImage(UID);
		
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

	private void initViews() {
		mProfilePhoto = (ImageView) findViewById(R.id.profile_photo);
		mNameUpdate = (EditText) findViewById(R.id.name_update);
		mPositionUpdate = (EditText) findViewById(R.id.pos_update);
		mAgeUpdate = (EditText) findViewById(R.id.age_update);
		mHeightUpdate = (EditText) findViewById(R.id.height_update);
		mWeightUpdate = (EditText) findViewById(R.id.weight_update);
		mFootUpdate = (EditText) findViewById(R.id.foot_update);
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
		intent.putExtra("outputX", 256);
		intent.putExtra("outputY", 256);
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

    @SuppressWarnings("unchecked")
	private boolean updateMyInfo() {
    	String name = mNameUpdate.getText().toString();
		String position = mPositionUpdate.getText().toString();
		String age = mAgeUpdate.getText().toString();
		String height = mHeightUpdate.getText().toString();
		String weight = mWeightUpdate.getText().toString();
		String foot = mFootUpdate.getText().toString();
		
		if (name.equals("")) { 
			Log.d(TAG, "Please fill in name");
			Toast.makeText(getApplication(), "Please fill in name", Toast.LENGTH_LONG).show();
			return false;
		}
		if (position.equals("")) { 
			Log.d(TAG, "Please fill in position");
			Toast.makeText(getApplication(), "Please fill in position", Toast.LENGTH_LONG).show();
			return false;
		}
		if (age.equals("")) { 
			Log.d(TAG, "Please fill in position");
			Toast.makeText(getApplication(), "Please fill in position", Toast.LENGTH_LONG).show();
			return false;
		}
		if (height.equals("")) { 
			Log.d(TAG, "Please fill in position");
			Toast.makeText(getApplication(), "Please fill in position", Toast.LENGTH_LONG).show();
			return false;
		}
		if (weight.equals("")) { 
			Log.d(TAG, "Please fill in position");
			Toast.makeText(getApplication(), "Please fill in position", Toast.LENGTH_LONG).show();
			return false;
		}
		if (foot.equals("")) { 
			Log.d(TAG, "Please fill in position");
			Toast.makeText(getApplication(), "Please fill in position", Toast.LENGTH_LONG).show();
			return false;
		}
		
		JSONObject myProfile = new JSONObject();
		myProfile.put(Config.KEY_NAME, name);
		myProfile.put(Config.KEY_POSITION, position);
		myProfile.put(Config.KEY_AGE, age);
		myProfile.put(Config.KEY_HEIGHT, height);
		myProfile.put(Config.KEY_WEIGHT, weight);
		myProfile.put(Config.KEY_FOOT, foot);
		
		String result = ServerIface.updateMyProfile(UID, myProfile);
		if (result.equals(Config.KEY_OK)) {
			Toast.makeText(getApplication(), "My Profile updated OK", Toast.LENGTH_SHORT).show();
		}
		
		return true;
    }
    
	private void uploadImage() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
		if (mBitmapPic != null)
			mBitmapPic.compress(Bitmap.CompressFormat.PNG, 100, stream);
		
		byte[] imageByteArray = stream.toByteArray();
		
		String result = ServerIface.uploadImage(imageByteArray, UID);
	} 

	private void downloadImage(long uid) {
		byte[] bytesImage = ServerIface.downloadImage(uid);
		if ( bytesImage == null ) {
			if (DEBUG) Log.d(TAG, "downloadImage(" + uid + "):" + "returned null");
			return; 
		}
		
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
        	if ( updateMyInfo() == false ) 
        		return false;
        	uploadImage();
        	Intent i = new Intent(ProfileUpdateActivity.this, SplashScreenActivity.class);
			startActivity(i);
			return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
