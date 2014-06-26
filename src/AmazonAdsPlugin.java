import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.LinearLayout;

import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdRegistration;


public class AmazonAdsPlugin extends CordovaPlugin{
	public AdLayout adView;
	public static final String ACTION_DISPLAY_AMAZON_ADS = "displayAmazonAds";
	
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		try {
		    if (ACTION_DISPLAY_AMAZON_ADS.equals(action)) {
		    	
		    	JSONObject arg_object = args.getJSONObject(0);
		    	displayAmazonAds(arg_object);
		    	callbackContext.success();
		    	return true;
		    }
		    callbackContext.error("Invalid action");
		    return false;
		} catch(Exception e) {
		    System.err.println("Exception: " + e.getMessage());
		    callbackContext.error(e.getMessage());
		    return false;
		} 
	}
	
	private void displayAmazonAds(JSONObject args_object) throws JSONException{
		adView = (AdLayout) new AdLayout(this.cordova.getActivity());
		boolean debug = args_object.getBoolean("debug");
		boolean isOnTop = args_object.getBoolean("isOnTop");
		String adKey = args_object.getString("adKey");
		
		// For debugging purposes enable logging, but disable for production builds.
        AdRegistration.enableLogging(debug);
        // For debugging purposes flag all ad requests as tests, but set to false for production builds.
        AdRegistration.enableTesting(debug);
        // Register Application Key
        AdRegistration.setAppKey(adKey);
        
        LinearLayout layout = new LinearLayout(this.cordova.getActivity());
        
        if(isOnTop)
        	layout.addView(adView);
        else
        	layout.addView(adView, layout.getChildCount());
        
        adView.loadAd();
		
		
		
	}
}
