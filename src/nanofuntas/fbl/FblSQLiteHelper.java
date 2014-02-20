package nanofuntas.fbl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FblSQLiteHelper extends SQLiteOpenHelper {
	private final boolean DEBUG = true;
	private final String TAG = "FblSQLiteHelper";
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "fbl.db";
	
	private static final String PLAYER_RATING_TABLE = "player_rating";
	private static final String CREATE_PLAYER_RATING_TABLE 
		= "CREATE TABLE " + PLAYER_RATING_TABLE + " ( " 
		//+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "  
		+ Config.KEY_UID + " INTEGER, "  			
		+ Config.KEY_ATTACK + " INTEGER, "
		+ Config.KEY_DEFENSE + " INTEGER, "
		+ Config.KEY_TEAMWORK + " INTEGER, "
		+ Config.KEY_MENTAL + " INTEGER, "
		+ Config.KEY_POWER + " INTEGER, "
		+ Config.KEY_SPEED + " INTEGER, "
		+ Config.KEY_STAMINA + " INTEGER, "
		+ Config.KEY_BALL_CONTROL + " INTEGER, "
		+ Config.KEY_PASS + " INTEGER, "
		+ Config.KEY_SHOT + " INTEGER, "
		+ Config.KEY_HEADER + " INTEGER, "
		+ Config.KEY_CUTTING + " INTEGER, "
		+ Config.KEY_OVERALL + " INTEGER )";
	
	private static final String[] PLAYER_RATING_COLUMNS = {
		Config.KEY_UID,
		Config.KEY_ATTACK,
		Config.KEY_DEFENSE,
		Config.KEY_TEAMWORK,
		Config.KEY_MENTAL,
		Config.KEY_POWER,
		Config.KEY_SPEED,
		Config.KEY_STAMINA,
		Config.KEY_BALL_CONTROL,
		Config.KEY_PASS,
		Config.KEY_SHOT,
		Config.KEY_HEADER,
		Config.KEY_CUTTING,
		Config.KEY_OVERALL,
	};
	
	private static final String PLAYER_PROFILE_TABLE = "player_profile";
	private static final String CREATE_PLAYER_PROFILE_TABLE 
		= "CREATE TABLE " + PLAYER_PROFILE_TABLE + " ( " 
		+ Config.KEY_UID + " INTEGER, "  			
		+ Config.KEY_NAME + " TEXT, "
		+ Config.KEY_POSITION + " TEXT, " 
		+ Config.KEY_AGE + " TEXT, "
		+ Config.KEY_HEIGHT + " TEXT, "
		+ Config.KEY_WEIGHT + " TEXT, "
		+ Config.KEY_FOOT + " TEXT " + ")";
	
	private static final String[] PLAYER_PROFILE_COLUMNS = {
		Config.KEY_UID,
		Config.KEY_NAME,
		Config.KEY_POSITION,
		Config.KEY_AGE,
		Config.KEY_HEIGHT,
		Config.KEY_WEIGHT,
		Config.KEY_FOOT,};
	
	public FblSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		if (DEBUG) Log.d(TAG, "onCreate()");
		db.execSQL(CREATE_PLAYER_RATING_TABLE);
		db.execSQL(CREATE_PLAYER_PROFILE_TABLE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
		if (DEBUG) Log.d(TAG, "onUpgrade()");
		db.execSQL("DROP TABLE IF EXISTS " + PLAYER_RATING_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + PLAYER_PROFILE_TABLE);
		this.onCreate(db);
	}
	
	public void createAllTables() {
		if (DEBUG) Log.d(TAG, "createAllTables()");
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(CREATE_PLAYER_RATING_TABLE);
		db.execSQL(CREATE_PLAYER_PROFILE_TABLE);
		db.close();
	}
	
	public void dropAllTables() {
		if (DEBUG) Log.d(TAG, "dropAllTables()");
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + PLAYER_RATING_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + PLAYER_PROFILE_TABLE);
		db.close();
	}
	
	public void addPlayerProfile(PlayerProfile pp) {
		if (DEBUG) Log.d(TAG, "addPlayerProfile()");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = makeValuesFromPlayerProfile(pp);
		db.insert(PLAYER_PROFILE_TABLE, null, values);
		db.close();
	}
	
	public PlayerProfile getPlayerProfile(long uid) {
		if (DEBUG) Log.d(TAG, "getPlayerProfile(), uid="+uid);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(PLAYER_PROFILE_TABLE, 
				PLAYER_PROFILE_COLUMNS,
				" " + Config.KEY_UID + " = ?", 
				new String[] {String.valueOf(uid)},
				null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		PlayerProfile pp = makePlayerProfileFromCursor(cursor);
		db.close();
		return pp;
	}
	
	public List<PlayerProfile> getAllPlayerProfile() {
		Log.d(TAG, "getAllPlayerProfile()");
		List<PlayerProfile> listPP = new ArrayList<PlayerProfile>();
		String query = "SELECT * FROM " + PLAYER_PROFILE_TABLE;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				PlayerProfile pp = makePlayerProfileFromCursor(cursor);
				listPP.add(pp);
			} while(cursor.moveToNext());
		}
		db.close();
		return listPP;
	}
	
	public int updatePlayerProfile(PlayerProfile pp) {
		Log.d(TAG, "updatePlayerProfile()");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = makeValuesFromPlayerProfile(pp);
		
		int i = db.update(PLAYER_PROFILE_TABLE, values,
				Config.KEY_UID + " =?", 
				new String[] {String.valueOf(pp.getUid())});
		db.close();
		return i;
	}
	
	public void deletePlayerProfile(PlayerProfile pp) {
		Log.d(TAG, "deletePlayerProfile()");
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(PLAYER_PROFILE_TABLE, 
				Config.KEY_UID + " = ?", 
				new String[] {String.valueOf(pp.getUid())});
		db.close();
	}
	
	private ContentValues makeValuesFromPlayerProfile(PlayerProfile pp) {
		Log.d(TAG, "makeValuesFromPlayerProfile()");
		ContentValues values = new ContentValues();
		
		values.put(Config.KEY_UID, pp.getUid());
		values.put(Config.KEY_NAME, pp.getName());
		values.put(Config.KEY_POSITION, pp.getPosition());
		values.put(Config.KEY_AGE, pp.getAge());
		values.put(Config.KEY_HEIGHT, pp.getHeight());
		values.put(Config.KEY_WEIGHT, pp.getWeight());
		values.put(Config.KEY_FOOT, pp.getFoot());
		
		return values;
	}
	
	private PlayerProfile makePlayerProfileFromCursor(Cursor cursor) {
		Log.d(TAG, "makePlayerProfileFromCursor()");
		PlayerProfile pp = new PlayerProfile();
		
		pp.setUid(Integer.parseInt(cursor.getString(0)));
		pp.setName(cursor.getString(1));
		pp.setPosition(cursor.getString(2));
		pp.setAge(cursor.getString(3));
		pp.setHeight(cursor.getString(4));
		pp.setWeight(cursor.getString(5));
		pp.setFoot(cursor.getString(6));
		
		return pp;
	}
	
	public void addPlayerRating(PlayerRating pr) {
		if (DEBUG) Log.d(TAG, "addPlayerRating()");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = makeValuesFromPlayerRating(pr);
		db.insert(PLAYER_RATING_TABLE, null, values);
		db.close();
	}
	
	public PlayerRating getPlayerRating(long uid) {
		if (DEBUG) Log.d(TAG, "getPlayerRating(), uid="+uid);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(PLAYER_RATING_TABLE, 
				PLAYER_RATING_COLUMNS,
				" " + Config.KEY_UID + " = ?", 
				new String[] {String.valueOf(uid)},
				null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		PlayerRating pr = makePlayerRatingFromCursor(cursor);
		db.close();
		return pr;
	}
	
	public List<PlayerRating> getAllPlayerRating() {
		if (DEBUG) Log.d(TAG, "getAllPlayerRating()");
		List<PlayerRating> listPR = new ArrayList<PlayerRating>();
		String query = "SELECT * FROM " + PLAYER_RATING_TABLE;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				PlayerRating pr = makePlayerRatingFromCursor(cursor);
				listPR.add(pr);
			} while(cursor.moveToNext());
		}
		db.close();
		return listPR;
	}
	
	public int updatePlayerRating(PlayerRating pr) {
		if (DEBUG) Log.d(TAG, "updatePlayerRating()");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = makeValuesFromPlayerRating(pr);
		
		int i = db.update(PLAYER_RATING_TABLE, values,
				Config.KEY_UID + " =?", 
				new String[] {String.valueOf(pr.getUid())});
		db.close();
		return i;
	}
	
	public void deletePlayerRating(PlayerRating pr) {
		if (DEBUG) Log.d(TAG, "deletePlayerRating()");
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(PLAYER_RATING_TABLE, 
				Config.KEY_UID + " = ?", 
				new String[] {String.valueOf(pr.getUid())});
		db.close();
	}
	
	private ContentValues makeValuesFromPlayerRating(PlayerRating pr) {
		if (DEBUG) Log.d(TAG, "makeValuesFromPlayerRating()");
		ContentValues values = new ContentValues();
		
		values.put(Config.KEY_UID, pr.getUid());
		values.put(Config.KEY_ATTACK, pr.getAttack());
		values.put(Config.KEY_DEFENSE, pr.getDefense());
		values.put(Config.KEY_TEAMWORK, pr.getTeamwork());
		values.put(Config.KEY_MENTAL, pr.getMental());
		values.put(Config.KEY_POWER, pr.getPower());
		values.put(Config.KEY_SPEED, pr.getSpeed());
		values.put(Config.KEY_STAMINA, pr.getStamina());
		values.put(Config.KEY_BALL_CONTROL, pr.getBallControl());
		values.put(Config.KEY_PASS, pr.getPass());
		values.put(Config.KEY_SHOT, pr.getShot());
		values.put(Config.KEY_HEADER, pr.getHeader());
		values.put(Config.KEY_CUTTING, pr.getCutting());
		values.put(Config.KEY_OVERALL, pr.getOverall());
		
		return values;
	}
	
	private PlayerRating makePlayerRatingFromCursor(Cursor cursor) {
		Log.d(TAG, "makePlayerRatingFromCursor()");
		PlayerRating pr = new PlayerRating();
		
		pr.setUid(Integer.parseInt(cursor.getString(0)));
		pr.setAttack(Integer.parseInt(cursor.getString(1)));
		pr.setDefense(Integer.parseInt(cursor.getString(2)));
		pr.setTeamwork(Integer.parseInt(cursor.getString(3)));
		pr.setMental(Integer.parseInt(cursor.getString(4)));
		pr.setPower(Integer.parseInt(cursor.getString(5)));
		pr.setSpeed(Integer.parseInt(cursor.getString(6)));
		pr.setStamina(Integer.parseInt(cursor.getString(7)));
		pr.setBallControl(Integer.parseInt(cursor.getString(8)));
		pr.setPass(Integer.parseInt(cursor.getString(9)));
		pr.setShot(Integer.parseInt(cursor.getString(10)));
		pr.setHeader(Integer.parseInt(cursor.getString(11)));
		pr.setCutting(Integer.parseInt(cursor.getString(12)));
		pr.setOverall(Integer.parseInt(cursor.getString(13)));
		
		return pr;
	}
}
