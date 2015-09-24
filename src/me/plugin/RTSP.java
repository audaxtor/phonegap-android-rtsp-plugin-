package me.plugin;

import android.content.Intent;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.net.URI;
import java.net.URISyntaxException;

import me.activity.PlayerActivity;

/**
 * Created by imivan on 15-9-21.
 */
public class RTSP extends CordovaPlugin {
    public final static String ACTION_PLAY = "play";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action==null||args==null) return false;
        if (action.equals(ACTION_PLAY)) {
            return play(args,callbackContext);
        } else return false;
    }

    private boolean play(JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (args.length()==1) {
            String uri_str = args.getString(0);
            URI uri;
            try {
                uri = new URI(uri_str);
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return false;
            }
            if (uri.getScheme()!=null&&uri.getScheme().equals("rtsp")) {
                Intent intent = new Intent(webView.getContext(),PlayerActivity.class);
                intent.putExtra("uri",uri_str);
                webView.getContext().startActivity(intent);
                callbackContext.success();
                return true;
            } else {
                callbackContext.error(uri_str+"should begin with rtsp://");
                return false;
            }
        } else return false;
    }
}


