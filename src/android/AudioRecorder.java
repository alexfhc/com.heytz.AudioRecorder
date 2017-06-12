package com.heytz.AudioRecorder;


import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * This class starts transmit to activation
 */
public class AudioRecorder extends CordovaPlugin {

    private static String TAG = "=====AudioRecorder.class====";
    private CallbackContext socketCallbackContext;
    private Context context;
    private String FileName = "test1";
    private MediaPlayer mPlayer = null;
    private MediaRecorder mRecorder = null;


    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        context = cordova.getActivity().getApplicationContext();
        FileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        FileName += "/test1.wmv";
    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        socketCallbackContext = callbackContext;
        if (action.equals("startRecord")) {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
            mRecorder.setOutputFile(FileName);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            try {
                mRecorder.prepare();
            } catch (IOException e) {

            }
            mRecorder.start();

            return true;
        }
        if (action.equals("stopRecord")) {
            mRecorder.stop();
            mRecorder.release();
            return true;
        }
        if (action.equals("startPlay")) {
            mPlayer = new MediaPlayer();
            try {
                mPlayer.setDataSource(FileName);
                mPlayer.prepare();
                mPlayer.start();
            } catch (IOException e) {

            }
            return true;
        }
        if (action.equals("stopPlay")) {
//            mPlayer = new MediaPlayer();
//                                       try{
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
//                                       }catch(IOException e){
//
//                                       }
            return true;
        }
        return false;
    }

//      public static void HideKeyboard(View v)
//      {
//          InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
//        if ( imm.isActive( ) ) {
//            imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );
//
//        }
//      }
}
