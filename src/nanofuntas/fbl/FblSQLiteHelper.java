package nanofuntas.fbl;

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
	
	private static final String[] COLUMNS = {
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
	
	public FblSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PLAYER_RATING_TABLE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
		db.execSQL("DROP TABLE IF EXISTS " + PLAYER_RATING_TABLE);
		this.onCreate(db);
	}
	
	public void addPlayerRating(PlayerRating pr) {
		if (DEBUG) Log.d(TAG, "addPlayerRating()");
		SQLiteDatabase db = this.getWritableDatabase();
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
		
		db.insert(PLAYER_RATING_TABLE, null, values);
	}
	
	public PlayerRating getPlayerRating(int uid) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(PLAYER_RATING_TABLE, 
				COLUMNS,
				" " + Config.KEY_UID + " = ?", 
				new String[] {String.valueOf(uid)},
				null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		if (cursor == null) {
			Log.d(TAG, "cursor == null");
			return null;
		}
		
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
