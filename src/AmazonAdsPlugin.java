package com.quantumrand.amazonAdsPlugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.ViewGroup;

import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdRegistration;


public class AmazonAdsPlugin extends CordovaPlugin{
	public AdLayout adView;
	public static final String ACTION_DISPLAY_AMAZON_ADS = "displayAmazonAds";
	
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		
		if (ACTION_DISPLAY_AMAZON_ADS.equals(action)) {
			JSONObject arg_object;
			arg_object = args.getJSONObject(0);
			displayAmazonAds(arg_object, callbackContext);
			return true;
		}
		else {
			callbackContext.error("Invalid action");
		    return false;
		}
		
	}
	
	private void displayAmazonAds(JSONObject args_object, final CallbackContext callbackContext){
		
		
		adView = (AdLayout) new AdLayout(this.cordova.getActivity());
		final boolean debug;
		final boolean isOnTop;
		final String appKey;
		try {
			debug = args_object.getBoolean("debug");
			isOnTop = args_object.getBoolean("isOnTop");
			appKey = args_object.getString("appKey");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			callbackContext.error(e.getMessage());
			e.printStackTrace();
			return;
		}
		cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
            	
            
				// For debugging purposes enable logging, but disable for production builds.
		        AdRegistration.enableLogging(debug);
		        // For debugging purposes flag all ad requests as tests, but set to false for production builds.
		        AdRegistration.enableTesting(debug);
		        // Register Application Key
		        AdRegistration.setAppKey(appKey);
		        
		        if (adView.getParent() != null) {							//check for existing ad
		            ((ViewGroup)adView.getParent()).removeView(adView); 
		        }
		        ViewGroup parentView = (ViewGroup) webView.getParent();
		        if (isOnTop) {
		            parentView.addView(adView, 0);
		        } else {
		            parentView.addView(adView);
		        }
		        
		        adView.loadAd();
		        callbackContext.success(); // Thread-safe.
		        
            }
		
		});
	}
}
