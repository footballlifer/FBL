package nanofuntas.fbl;

import java.util.concurrent.ExecutionException;
import org.json.simple.JSONObject;
import android.os.AsyncTask;
import android.util.Log;

/** 
 * This ServerIface class provides applications APIs to send/get JSON data to/from Server,
 * This class use underlying HttpUrlService to send/receive date to/from server 
 */
public class ServerIface {
	private static final boolean DEBUG = true;
	private static final String TAG = "ServerIface";
	
	/**
     * This HttpPostStrAsyncTask class sends/gets String data to/from server using HttpUrlService class
     * But not used by now
     */
    private static class HttpPostStrAsyncTask extends AsyncTask<String , Void, String>{
		@Override
		protected String doInBackground(String... params) {
    		if(DEBUG) Log.i(TAG, "HttpPostStrAsyncTask, doInBackground()");
			return HttpUrlService.execStrPost(params[0]);
		}	
    }

    /**
     * This HttpPostJsonAsyncTask class sends/gets JSON data to/from server using HttpUrlService class
     */
    private static class HttpPostJsonAsyncTask extends AsyncTask<JSONObject , Void, JSONObject>{
    	@Override
		protected JSONObject doInBackground(JSONObject... params) {
    		if(DEBUG) Log.i(TAG, "HttpPostJsonAsyncTask, doInBackground()");
    		return HttpUrlService.execJsonPost(params[0]);
		}	
    }
    
	private static JSONObject postNgetJson(JSONObject jsonReq) {
		JSONObject jsonRsp = null;	
		HttpPostJsonAsyncTask mHttpPostJsonAsyncTask = new HttpPostJsonAsyncTask();
		mHttpPostJsonAsyncTask.execute(jsonReq);			
		
		try {
			jsonRsp = mHttpPostJsonAsyncTask.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return jsonRsp;
	}
    
	@SuppressWarnings("unchecked")
	public static JSONObject login(String strEmailLogin, String strPwLogin) {
		if (DEBUG) Log.i(TAG, "login()");
		
		JSONObject jsonLogin = new JSONObject();
		jsonLogin.put(Config.KEY_REQ_TYPE, Config.KEY_REQ_TYPE_LOGIN);
		jsonLogin.put(Config.KEY_EMAIL, strEmailLogin);
		jsonLogin.put(Config.KEY_PASSWORD, strPwLogin);
		
		JSONObject jsonResult = postNgetJson(jsonLogin);
		JSONObject result = (JSONObject) jsonResult.get(Config.KEY_RESULT);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject register(String strEmailReg, String strPwReg) {
		if (DEBUG) Log.i(TAG, "register()");
		
		JSONObject jsonRegister = new JSONObject();
		jsonRegister.put(Config.KEY_REQ_TYPE, Config.KEY_REQ_TYPE_REGISTER);
		jsonRegister.put(Config.KEY_EMAIL, strEmailReg);
		jsonRegister.put(Config.KEY_PASSWORD, strPwReg);
		
		JSONObject jsonResult = postNgetJson(jsonRegister);
		JSONObject result = (JSONObject) jsonResult.get(Config.KEY_RESULT);
		return result;
		
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject getPlayerStatus(long uid) {
		if (DEBUG) Log.i(TAG, "getPlayerStatus()");

		JSONObject jsonStatus = new JSONObject();
		jsonStatus.put(Config.KEY_REQ_TYPE, Config.KEY_REQ_TYPE_PLAYER_STATUS);
		jsonStatus.put(Config.KEY_UID, uid);
		
		JSONObject jsonResult = postNgetJson(jsonStatus);		
		return jsonResult;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject getTeamStatus(long tid) {
		if (DEBUG) Log.i(TAG, "getTeamStatus()");

		JSONObject jsonStatus = new JSONObject();
		jsonStatus.put(Config.KEY_REQ_TYPE, Config.KEY_REQ_TYPE_TEAM_STATUS);
		jsonStatus.put(Config.KEY_TID, tid);
		
		JSONObject jsonResult = postNgetJson(jsonStatus);		
		return jsonResult;
	}
	
	@SuppressWarnings("unchecked")
	public static String ratePlayer(long uid, JSONObject jsonRating) {
		if (DEBUG) Log.i(TAG, "ratePlayer()");

		JSONObject jsonPlayerRating = new JSONObject();
		jsonPlayerRating.put(Config.KEY_REQ_TYPE, Config.KEY_REQ_TYPE_PLAYER_RATING);
		jsonPlayerRating.put(Config.KEY_UID, uid);
		jsonPlayerRating.put(Config.KEY_RATING, jsonRating);
		
		JSONObject jsonResult = postNgetJson(jsonPlayerRating);

		String result = (String) jsonResult.get(Config.KEY_RESULT);		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static long createTeam(long uid, String teamName) {
		if (DEBUG) Log.i(TAG, "createTeam()");

		JSONObject jsonCreateTeam = new JSONObject();
		jsonCreateTeam.put(Config.KEY_REQ_TYPE, Config.KEY_REQ_TYPE_CREATE_TEAM);
		jsonCreateTeam.put(Config.KEY_UID, uid);
		jsonCreateTeam.put(Config.KEY_TEAM_NAME, teamName);
		
		JSONObject jsonResult = postNgetJson(jsonCreateTeam);

		long result = (Long) jsonResult.get(Config.KEY_RESULT);		
		return result;
	}

	@SuppressWarnings("unchecked")
	public static long joinTeam(long uid, String teamName) {
		if (DEBUG) Log.i(TAG, "joinTeam()");
		
		JSONObject jsonJoinTeam = new JSONObject();
		jsonJoinTeam.put(Config.KEY_REQ_TYPE, Config.KEY_REQ_TYPE_JOIN_TEAM);
		jsonJoinTeam.put(Config.KEY_UID, uid);
		jsonJoinTeam.put(Config.KEY_TEAM_NAME, teamName);
		
		JSONObject jsonResult = postNgetJson(jsonJoinTeam);

		long result = (Long) jsonResult.get(Config.KEY_RESULT);		
		return result;
	}

	@SuppressWarnings("unchecked")
	public static long incruitPlayer(long tid, String playerName) {
		if (DEBUG) Log.i(TAG, "incruitPlayer()");
		
		JSONObject jsonIncruitPlayer = new JSONObject();
		jsonIncruitPlayer.put(Config.KEY_REQ_TYPE, Config.KEY_REQ_TYPE_INCRUIT_PLAYER);
		jsonIncruitPlayer.put(Config.KEY_TID, tid);
		jsonIncruitPlayer.put(Config.KEY_NAME, playerName);
		
		JSONObject jsonResult = postNgetJson(jsonIncruitPlayer);

		long result = (Long) jsonResult.get(Config.KEY_RESULT);		
		return result;
	}

	@SuppressWarnings("unchecked")
	public static String updateMyProfile(long uid, JSONObject myProfile) {
		if (DEBUG) Log.i(TAG, "updateMyProfile()");

		JSONObject jsonUpdateMyProfile = new JSONObject();
		jsonUpdateMyProfile.put(Config.KEY_REQ_TYPE, Config.KEY_REQ_TYPE_UPDATA_PLAYER_PROFILE);
		jsonUpdateMyProfile.put(Config.KEY_UID, uid);
		jsonUpdateMyProfile.put(Config.KEY_PLAYER_PROFILE, myProfile);
		
		JSONObject jsonResult = postNgetJson(jsonUpdateMyProfile);

		String result = (String) jsonResult.get(Config.KEY_RESULT);		
		return result;
	}

}
