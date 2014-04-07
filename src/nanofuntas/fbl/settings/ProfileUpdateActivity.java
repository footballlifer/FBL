package nanofuntas.fbl.settings;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import nanofuntas.fbl.Config;
import nanofuntas.fbl.R;
import nanofuntas.fbl.ServerIface;
import nanofuntas.fbl.SplashScreenActivity;
import nanofuntas.fbl.Utils;

import org.json.simple.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

public class ProfileUpdateActivity extends Activity {
	private final boolean DEBUG = true;
	private final String TAG = "MyProfileUpdate";
	
	private ImageView mProfilePhoto;
	private AlertDialog mPhotoDialog;
	private AlertDialog mPositonDialog;
	private AlertDialog mAgeDialog;
	private AlertDialog mHeightDialog;
	private AlertDialog mWeightDialog;
	private AlertDialog mFootDialog;
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
		//TODO: do not download from server, this takes a long time
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
	    
	    final CharSequence[] positionItems = {"CF", "AMF", "LW", 
	    		"RW", "DMF", "CB", "LWB", "RWB", "GK"};

	    AlertDialog.Builder positionBuilder = new AlertDialog.Builder(this);
	    positionBuilder.setTitle("Make your selection");
	    positionBuilder.setItems(positionItems, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int item) {
	        	mPositionUpdate.setText(positionItems[item]);
	        }
	    });
	    mPositonDialog = positionBuilder.create();
	    
	    mPositionUpdate.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mPositonDialog.show();
				return false;
			}
	    });
	    
	    LayoutInflater inflater = getLayoutInflater();
	    View ageView = inflater.inflate(R.layout.dialog_number_picker, null);
	    final NumberPicker npAge = (NumberPicker) ageView.findViewById(R.id.numberPicker1);
	    npAge.setMinValue(1950);
	    npAge.setMaxValue(2013);
	    AlertDialog.Builder ageBuilder = new AlertDialog.Builder(this)
	    	.setTitle("Make your selection")
	    	.setView(ageView)
	    	.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	    		@Override
	    		public void onClick(DialogInterface dialog, int which) {
	    			mPositonDialog.dismiss();
	    		}
	    	})
	    	.setPositiveButton("Set", new DialogInterface.OnClickListener() {
	    		@Override
	    		public void onClick(DialogInterface dialog, int which) {
	    			mAgeUpdate.setText(npAge.getValue()+"");
	    		}
	    	});
	    mAgeDialog = ageBuilder.create();
	    mAgeUpdate.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mAgeDialog.show();
				return false;
			}
	    });
	    
	    View heightView = inflater.inflate(R.layout.dialog_number_picker, null);
	    final NumberPicker npHeight = (NumberPicker) heightView.findViewById(R.id.numberPicker1);
	    npHeight.setMinValue(150);
	    npHeight.setMaxValue(200);
	    AlertDialog.Builder heightBuilder = new AlertDialog.Builder(this)
	    	.setTitle("Make your selection")
	    	.setView(heightView)
	    	.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	    		@Override
	    		public void onClick(DialogInterface dialog, int which) {
	    			mHeightDialog.dismiss();
	    		}
	    	})
	    	.setPositiveButton("Set", new DialogInterface.OnClickListener() {
	    		@Override
	    		public void onClick(DialogInterface dialog, int which) {
	    			mHeightUpdate.setText(npHeight.getValue()+"");
	    		}
	    	});
	    mHeightDialog = heightBuilder.create();
	    mHeightUpdate.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mHeightDialog.show();
				return false;
			}
	    });
	    
	    View weightView = inflater.inflate(R.layout.dialog_number_picker, null);
	    final NumberPicker npWeight = (NumberPicker) weightView.findViewById(R.id.numberPicker1);
	    npWeight.setMinValue(40);
	    npWeight.setMaxValue(150);
	    AlertDialog.Builder weightBuilder = new AlertDialog.Builder(this)
	    	.setTitle("Make your selection")
	    	.setView(weightView)
	    	.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	    		@Override
	    		public void onClick(DialogInterface dialog, int which) {
	    			mWeightDialog.dismiss();
	    		}
	    	})
	    	.setPositiveButton("Set", new DialogInterface.OnClickListener() {
	    		@Override
	    		public void onClick(DialogInterface dialog, int which) {
	    			mWeightUpdate.setText(npWeight.getValue()+"");
	    		}
	    	});
	    mWeightDialog = weightBuilder.create();
	    mWeightUpdate.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mWeightDialog.show();
				return false;
			}
	    });
	    
	    final CharSequence[] footItems = {"Right", "Left", "Both"};

	    AlertDialog.Builder footBuilder = new AlertDialog.Builder(this);
	    footBuilder.setTitle("Make your selection");
	    footBuilder.setItems(footItems, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int item) {
	        	mFootUpdate.setText(footItems[item]);
	        }
	    });
	    mFootDialog = footBuilder.create();
	    
	    mFootUpdate.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mFootDialog.show();
				return false;
			}
	    });
	    
	}
	
	public static class DatePickerFragment extends DialogFragment
    implements OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			// Do something with the date chosen by the user
		}
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
