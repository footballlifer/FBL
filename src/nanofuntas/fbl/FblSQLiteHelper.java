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
		Config.KEY_FOOT,
	};
	
	private static final String TEAM_RATING_TABLE = "team_rating";
	private static final String CREATE_TEAM_RATING_TABLE 
		= "CREATE TABLE " + TEAM_RATING_TABLE + " ( " 
		//+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "  
		+ Config.KEY_TID + " INTEGER, "  			
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
	
	private static final String[] TEAM_RATING_COLUMNS = {
		Config.KEY_TID,
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
	
	private static final String TEAM_PROFILE_TABLE = "team_profile";
	private static final String CREATE_TEAM_PROFILE_TABLE 
		= "CREATE TABLE " + TEAM_PROFILE_TABLE + " ( " 
		+ Config.KEY_TID + " INTEGER, "  			
		+ Config.KEY_TEAM_NAME + " TEXT " + ")";
	
	private static final String[] TEAM_PROFILE_COLUMNS = {
		Config.KEY_TID,
		Config.KEY_TEAM_NAME,
	};
	
	private static final String TEAM_LEVEL_TABLE = "team_level";
	private static final String CREATE_TEAM_LEVEL_TABLE 
		= "CREATE TABLE " + TEAM_LEVEL_TABLE + " ( " 
		//+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "  
		+ Config.KEY_TID + " INTEGER, "  			
		+ Config.KEY_TEAM_ATK + " INTEGER, "
		+ Config.KEY_TEAM_DFS + " INTEGER, "
		+ Config.KEY_TEAM_TEC + " INTEGER, "
		+ Config.KEY_TEAM_PHY + " INTEGER, "
		+ Config.KEY_TEAM_TWK + " INTEGER, "
		+ Config.KEY_TEAM_MTL + " INTEGER, "
		+ Config.KEY_TEAM_OVERALL + " INTEGER )";
	
	private static final String[] TEAM_LEVEL_COLUMNS = {
		Config.KEY_TID,
		Config.KEY_TEAM_ATK,
		Config.KEY_TEAM_DFS,
		Config.KEY_TEAM_TEC,
		Config.KEY_TEAM_PHY,
		Config.KEY_TEAM_TWK,
		Config.KEY_TEAM_MTL,
		Config.KEY_TEAM_OVERALL,
	};
	
	public FblSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		if (DEBUG) Log.d(TAG, "onCreate()");
		db.execSQL(CREATE_PLAYER_RATING_TABLE);
		db.execSQL(CREATE_PLAYER_PROFILE_TABLE);
		db.execSQL(CREATE_TEAM_RATING_TABLE);
		db.execSQL(CREATE_TEAM_PROFILE_TABLE);
		db.execSQL(CREATE_TEAM_LEVEL_TABLE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
		if (DEBUG) Log.d(TAG, "onUpgrade()");
		db.execSQL("DROP TABLE IF EXISTS " + PLAYER_RATING_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + PLAYER_PROFILE_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + TEAM_RATING_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + TEAM_PROFILE_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + TEAM_LEVEL_TABLE);
		this.onCreate(db);
	}
	
	public void createAllTables() {
		if (DEBUG) Log.d(TAG, "createAllTables()");
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(CREATE_PLAYER_RATING_TABLE);
		db.execSQL(CREATE_PLAYER_PROFILE_TABLE);
		db.execSQL(CREATE_TEAM_RATING_TABLE);
		db.execSQL(CREATE_TEAM_PROFILE_TABLE);
		db.execSQL(CREATE_TEAM_LEVEL_TABLE);
		db.close();
	}
	
	public void dropAllTables() {
		if (DEBUG) Log.d(TAG, "dropAllTables()");
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + PLAYER_RATING_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + PLAYER_PROFILE_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + TEAM_RATING_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + TEAM_PROFILE_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + TEAM_LEVEL_TABLE);
		db.close();
	}
	
	public void addPlayerProfile(PlayerInfo.PlayerProfile pp) {
		if (DEBUG) Log.d(TAG, "addPlayerProfile()");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = makeValuesFromPlayerProfile(pp);
		db.insert(PLAYER_PROFILE_TABLE, null, values);
		db.close();
	}
	
	public PlayerInfo.PlayerProfile getPlayerProfile(long uid) {
		if (DEBUG) Log.d(TAG, "getPlayerProfile(), uid="+uid);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(PLAYER_PROFILE_TABLE, 
				PLAYER_PROFILE_COLUMNS,
				" " + Config.KEY_UID + " = ?", 
				new String[] {String.valueOf(uid)},
				null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		PlayerInfo.PlayerProfile pp = makePlayerProfileFromCursor(cursor);
		db.close();
		return pp;
	}
	
	public List<PlayerInfo.PlayerProfile> getAllPlayerProfile() {
		Log.d(TAG, "getAllPlayerProfile()");
		List<PlayerInfo.PlayerProfile> listPP = 
				new ArrayList<PlayerInfo.PlayerProfile>();
		String query = "SELECT * FROM " + PLAYER_PROFILE_TABLE;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				PlayerInfo.PlayerProfile pp = makePlayerProfileFromCursor(cursor);
				listPP.add(pp);
			} while(cursor.moveToNext());
		}
		db.close();
		return listPP;
	}
	
	public int updatePlayerProfile(PlayerInfo.PlayerProfile pp) {
		Log.d(TAG, "updatePlayerProfile()");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = makeValuesFromPlayerProfile(pp);
		
		int i = db.update(PLAYER_PROFILE_TABLE, values,
				Config.KEY_UID + " =?", 
				new String[] {String.valueOf(pp.getUid())});
		db.close();
		return i;
	}
	
	public void deletePlayerProfile(PlayerInfo.PlayerProfile pp) {
		Log.d(TAG, "deletePlayerProfile()");
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(PLAYER_PROFILE_TABLE, 
				Config.KEY_UID + " = ?", 
				new String[] {String.valueOf(pp.getUid())});
		db.close();
	}
	
	public void addTeamProfile(TeamInfo.TeamProfile tp) {
		if (DEBUG) Log.d(TAG, "addTeamProfile()");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = makeValuesFromTeamProfile(tp);
		db.insert(TEAM_PROFILE_TABLE, null, values);
		db.close();
	}
	
	private ContentValues makeValuesFromTeamProfile(TeamInfo.TeamProfile tp) {
		Log.d(TAG, "makeValuesFromTeamProfile()");
		ContentValues values = new ContentValues();
		values.put(Config.KEY_TID, tp.getTid());
		values.put(Config.KEY_TEAM_NAME, tp.getTeamName());
		
		return values;
	}

	public TeamInfo.TeamProfile getTeamProfile(long tid) {
		if (DEBUG) Log.d(TAG, "getTeamProfile(), tid="+tid);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TEAM_PROFILE_TABLE, 
				TEAM_PROFILE_COLUMNS,
				" " + Config.KEY_TID + " = ?", 
				new String[] {String.valueOf(tid)},
				null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		TeamInfo.TeamProfile tp = makeTeamProfileFromCursor(cursor);
		db.close();
		return tp;
	}
	
	private TeamInfo.TeamProfile makeTeamProfileFromCursor(Cursor cursor) {
		Log.d(TAG, "makeTeamProfileFromCursor()");
		TeamInfo.TeamProfile tp = new TeamInfo.TeamProfile();
		
		tp.setTid(cursor.getInt(0));
		tp.setTeamName(cursor.getString(1));
		return tp;
	}

	public int updateTeamProfile(TeamInfo.TeamProfile tp) {
		Log.d(TAG, "updateTeamProfile()");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = makeValuesFromTeamProfile(tp);
		
		int i = db.update(TEAM_PROFILE_TABLE, values,
				Config.KEY_TID + " =?", 
				new String[] {String.valueOf(tp.getTid())});
		db.close();
		return i;
	}
	
	public void deleteTeamProfile(TeamInfo.TeamProfile tp) {
		Log.d(TAG, "deleteTeamProfile()");
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TEAM_PROFILE_TABLE, 
				Config.KEY_TID + " = ?", 
				new String[] {String.valueOf(tp.getTid())});
		db.close();
	}
	
	private ContentValues makeValuesFromPlayerProfile(PlayerInfo.PlayerProfile pp) {
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
	
	private PlayerInfo.PlayerProfile makePlayerProfileFromCursor(Cursor cursor) {
		Log.d(TAG, "makePlayerProfileFromCursor()");
		PlayerInfo.PlayerProfile pp = new PlayerInfo.PlayerProfile();
		
		pp.setUid(Integer.parseInt(cursor.getString(0)));
		pp.setName(cursor.getString(1));
		pp.setPosition(cursor.getString(2));
		pp.setAge(cursor.getString(3));
		pp.setHeight(cursor.getString(4));
		pp.setWeight(cursor.getString(5));
		pp.setFoot(cursor.getString(6));
		
		return pp;
	}
	
	public void addPlayerRating(PlayerInfo.PlayerRating pr) {
		if (DEBUG) Log.d(TAG, "addPlayerRating()");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = makeValuesFromPlayerRating(pr);
		db.insert(PLAYER_RATING_TABLE, null, values);
		db.close();
	}
	
	public PlayerInfo.PlayerRating getPlayerRating(long uid) {
		if (DEBUG) Log.d(TAG, "getPlayerRating(), uid="+uid);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(PLAYER_RATING_TABLE, 
				PLAYER_RATING_COLUMNS,
				" " + Config.KEY_UID + " = ?", 
				new String[] {String.valueOf(uid)},
				null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		PlayerInfo.PlayerRating pr = makePlayerRatingFromCursor(cursor);
		db.close();
		return pr;
	}
	
	public List<PlayerInfo.PlayerRating> getAllPlayerRating() {
		if (DEBUG) Log.d(TAG, "getAllPlayerRating()");
		List<PlayerInfo.PlayerRating> listPR = 
				new ArrayList<PlayerInfo.PlayerRating>();
		String query = "SELECT * FROM " + PLAYER_RATING_TABLE;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				PlayerInfo.PlayerRating pr = makePlayerRatingFromCursor(cursor);
				listPR.add(pr);
			} while(cursor.moveToNext());
		}
		db.close();
		return listPR;
	}
	
	public int updatePlayerRating(PlayerInfo.PlayerRating pr) {
		if (DEBUG) Log.d(TAG, "updatePlayerRating()");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = makeValuesFromPlayerRating(pr);
		
		int i = db.update(PLAYER_RATING_TABLE, values,
				Config.KEY_UID + " =?", 
				new String[] {String.valueOf(pr.getUid())});
		db.close();
		return i;
	}
	
	public void deletePlayerRating(PlayerInfo.PlayerRating pr) {
		if (DEBUG) Log.d(TAG, "deletePlayerRating()");
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(PLAYER_RATING_TABLE, 
				Config.KEY_UID + " = ?", 
				new String[] {String.valueOf(pr.getUid())});
		db.close();
	}
	
	private ContentValues makeValuesFromPlayerRating(PlayerInfo.PlayerRating pr) {
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
	
	private PlayerInfo.PlayerRating makePlayerRatingFromCursor(Cursor cursor) {
		Log.d(TAG, "makePlayerRatingFromCursor()");
		PlayerInfo.PlayerRating pr = new PlayerInfo.PlayerRating();
		
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
	
	public void addTeamRating(TeamInfo.TeamRating tr) {
		if (DEBUG) Log.d(TAG, "addTeamRating()");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = makeValuesFromTeamRating(tr);
		db.insert(TEAM_RATING_TABLE, null, values);
		db.close();
	}
	
	public TeamInfo.TeamRating getTeamRating(long tid) {
		if (DEBUG) Log.d(TAG, "getTeamRating(), tid="+tid);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TEAM_RATING_TABLE, 
				TEAM_RATING_COLUMNS,
				" " + Config.KEY_TID + " = ?", 
				new String[] {String.valueOf(tid)},
				null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		TeamInfo.TeamRating tr = makeTeamRatingFromCursor(cursor);
		db.close();
		return tr;
	}
	
	public int updateTeamRating(TeamInfo.TeamRating tr) {
		if (DEBUG) Log.d(TAG, "updateTeamRating()");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = makeValuesFromTeamRating(tr);
		
		int i = db.update(TEAM_RATING_TABLE, values,
				Config.KEY_TID + " =?", 
				new String[] {String.valueOf(tr.getTid())});
		db.close();
		return i;
	}
	
	public void deleteTeamRating(TeamInfo.TeamRating tr) {
		if (DEBUG) Log.d(TAG, "deleteTeamRating()");
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TEAM_RATING_TABLE, 
				Config.KEY_TID + " = ?", 
				new String[] {String.valueOf(tr.getTid())});
		db.close();
	}
	
	private ContentValues makeValuesFromTeamRating(TeamInfo.TeamRating tr) {
		if (DEBUG) Log.d(TAG, "makeValuesFromTeamRating()");
		ContentValues values = new ContentValues();
		
		values.put(Config.KEY_TID, tr.getTid());
		values.put(Config.KEY_ATTACK, tr.getAttack());
		values.put(Config.KEY_DEFENSE, tr.getDefense());
		values.put(Config.KEY_TEAMWORK, tr.getTeamwork());
		values.put(Config.KEY_MENTAL, tr.getMental());
		values.put(Config.KEY_POWER, tr.getPower());
		values.put(Config.KEY_SPEED, tr.getSpeed());
		values.put(Config.KEY_STAMINA, tr.getStamina());
		values.put(Config.KEY_BALL_CONTROL, tr.getBallControl());
		values.put(Config.KEY_PASS, tr.getPass());
		values.put(Config.KEY_SHOT, tr.getShot());
		values.put(Config.KEY_HEADER, tr.getHeader());
		values.put(Config.KEY_CUTTING, tr.getCutting());
		values.put(Config.KEY_OVERALL, tr.getOverall());
		
		return values;
	}
	
	private TeamInfo.TeamRating makeTeamRatingFromCursor(Cursor cursor) {
		Log.d(TAG, "makeTeamRatingFromCursor()");
		TeamInfo.TeamRating tr = new TeamInfo.TeamRating();
		
		tr.setTid(Integer.parseInt(cursor.getString(0)));
		tr.setAttack(Integer.parseInt(cursor.getString(1)));
		tr.setDefense(Integer.parseInt(cursor.getString(2)));
		tr.setTeamwork(Integer.parseInt(cursor.getString(3)));
		tr.setMental(Integer.parseInt(cursor.getString(4)));
		tr.setPower(Integer.parseInt(cursor.getString(5)));
		tr.setSpeed(Integer.parseInt(cursor.getString(6)));
		tr.setStamina(Integer.parseInt(cursor.getString(7)));
		tr.setBallControl(Integer.parseInt(cursor.getString(8)));
		tr.setPass(Integer.parseInt(cursor.getString(9)));
		tr.setShot(Integer.parseInt(cursor.getString(10)));
		tr.setHeader(Integer.parseInt(cursor.getString(11)));
		tr.setCutting(Integer.parseInt(cursor.getString(12)));
		tr.setOverall(Integer.parseInt(cursor.getString(13)));
		
		return tr;
	}
	
	public void addTeamLevel(TeamInfo.TeamLevel tl) {
		if (DEBUG) Log.d(TAG, "addTeamLevel()");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = makeValuesFromTeamLevel(tl);
		db.insert(TEAM_LEVEL_TABLE, null, values);
		db.close();
	}
	
	public TeamInfo.TeamLevel getTeamLevel(long tid) {
		if (DEBUG) Log.d(TAG, "getTeamLevel(), tid="+tid);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TEAM_LEVEL_TABLE, 
				TEAM_LEVEL_COLUMNS,
				" " + Config.KEY_TID + " = ?", 
				new String[] {String.valueOf(tid)},
				null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		TeamInfo.TeamLevel tl = makeTeamLevelFromCursor(cursor);
		db.close();
		return tl;
	}
	
	public int updateTeamLevel(TeamInfo.TeamLevel tl) {
		if (DEBUG) Log.d(TAG, "updateTeamLevel()");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = makeValuesFromTeamLevel(tl);
		
		int i = db.update(TEAM_LEVEL_TABLE, values,
				Config.KEY_TID + " =?", 
				new String[] {String.valueOf(tl.getTid())});
		db.close();
		return i;
	}
	
	public void deleteTeamLevel(TeamInfo.TeamLevel tl) {
		if (DEBUG) Log.d(TAG, "deleteTeamLevel()");
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TEAM_LEVEL_TABLE, 
				Config.KEY_TID + " = ?", 
				new String[] {String.valueOf(tl.getTid())});
		db.close();
	}
	
	private ContentValues makeValuesFromTeamLevel(TeamInfo.TeamLevel tl) {
		if (DEBUG) Log.d(TAG, "makeValuesFromTeamLevel()");
		ContentValues values = new ContentValues();
		
		values.put(Config.KEY_TID, tl.getTid());
		values.put(Config.KEY_TEAM_ATK, tl.getATK());
		values.put(Config.KEY_TEAM_DFS, tl.getDFS());
		values.put(Config.KEY_TEAM_TEC, tl.getTEC());
		values.put(Config.KEY_TEAM_PHY, tl.getPHY());
		values.put(Config.KEY_TEAM_TWK, tl.getTWK());
		values.put(Config.KEY_TEAM_MTL, tl.getMTL());
		values.put(Config.KEY_TEAM_OVERALL, tl.getOVERALL());

		return values;
	}
	
	private TeamInfo.TeamLevel makeTeamLevelFromCursor(Cursor cursor) {
		Log.d(TAG, "makeTeamLevelFromCursor()");
		TeamInfo.TeamLevel tl = new TeamInfo.TeamLevel();
		
		tl.setTid(Integer.parseInt(cursor.getString(0)));
		tl.setATK(Integer.parseInt(cursor.getString(1)));
		tl.setDFS(Integer.parseInt(cursor.getString(2)));
		tl.setTEC(Integer.parseInt(cursor.getString(3)));
		tl.setPHY(Integer.parseInt(cursor.getString(4)));
		tl.setTWK(Integer.parseInt(cursor.getString(5)));
		tl.setMTL(Integer.parseInt(cursor.getString(6)));
		tl.setOVERALL(Integer.parseInt(cursor.getString(7)));
		
		return tl;
	}
	
}
