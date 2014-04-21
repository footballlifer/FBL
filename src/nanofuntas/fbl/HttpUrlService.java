/**
 * All copy rights reserved by nanoFuntas studio 2012.
 */
package nanofuntas.fbl;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import android.util.Log;

/**
 * HttpUrlService class serves for client to receive or send String, 
 * JSON or byte array data between client and server.
 */
public class HttpUrlService {
	private final static boolean DEBUG = true;
	private final static String TAG = "HttpUrlService";

	private final static int HTTP_CONNECT_TIMEOUT = 30 * 1000; //30 seconds
	private final static int HTTP_READ_TIMEOUT = 30 * 1000; //30 seconds

	//private final static String URL_FBLS = "http://111.207.243.179:9998/FBLS/FBLServlet";
	//private final static String URL_FBLS = "http://192.168.219.104:8080/FBLS/FBLServlet";
	private final static String URL_FBLS = "http://192.168.2.3:8080/FBLS/FBLServlet";

	/**
	 * Function execStrPost sends String data received from client to server,
	 * and fetch String data from server and return it to client.
	 * 
	 * @param strParam String data received from client to be sent to server
	 * @return String data received from server
	 */
	public static String execStrPost(String strParam) {		
		if(DEBUG) Log.i(TAG, "execStrPost");
		
		String strResult = null;
		//URL mURL = null;
		HttpURLConnection conn = null;
		OutputStream outStrm = null;
		ObjectOutputStream objOutStrm = null;
		InputStream inStrm = null;
		ObjectInputStream objInStrm = null;
		
		try{
			String url = URL_FBLS;
			URL mURL = new URL(url);
			conn = (HttpURLConnection) mURL.openConnection();
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);		
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(HTTP_CONNECT_TIMEOUT);
			conn.setReadTimeout(HTTP_READ_TIMEOUT);
	
			conn.connect();
			
			// write output data to be sent
			outStrm = conn.getOutputStream();
			objOutStrm = new ObjectOutputStream(outStrm);
			objOutStrm.writeObject(strParam);
			objOutStrm.flush();
					
			// Log HTTP status code for debugging
			int httpStatCode = conn.getResponseCode();
			if(DEBUG) Log.i( TAG, "HTTP status code: " + Integer.toString(httpStatCode) );
			
			// get data from server and parse it to string
			inStrm = conn.getInputStream();
			objInStrm = new ObjectInputStream(inStrm);	
			strResult = (String) objInStrm.readObject();
			
		} catch(IOException e){
			e.printStackTrace();
			
		} catch(ClassNotFoundException e){
			e.printStackTrace();
			
		} finally{
			try{
				// release resources
				if(conn != null) conn.disconnect();
				if(outStrm != null) outStrm.close();
				if(objOutStrm != null) objOutStrm.close();
				if(inStrm != null) inStrm.close();
				if(objInStrm != null) objInStrm.close();
				
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		return strResult;
	}
	
	/** 
	 * Function execJsonPost sends JSON data received from client to server,
	 * and fetch JSON data from server and return it to client.
	 * Function execJsonPost wraps function execStrPost 
	 * 
	 * @jsonParam JSONObject data received from client to be sent to server
	 * @return JSONObject data received from server
	 */
	public static JSONObject execJsonPost(JSONObject jsonParam) {
		String strIn = jsonParam.toString();
		String strOut = execStrPost(strIn);			
		JSONObject jsonResult = (JSONObject) JSONValue.parse(strOut);
		
		return jsonResult;
	}
}
