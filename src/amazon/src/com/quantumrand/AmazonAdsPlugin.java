package com.quantumrand.amazonAdsPlugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.ViewGroup;

import com.amazon.device.ads.*;


public class AmazonAdsPlugin extends CordovaPlugin{
	public AdLayout adView;
	public InterstitialAd interstitialAd;
	public static final String ACTION_DISPLAY_AMAZON_ADS = "displayAmazonAds";
	public static final String ACTION_DISPLAY_AMAZON_INTERSTITIAL = "displayAmazonInterstitial";
	
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		
		if (ACTION_DISPLAY_AMAZON_ADS.equals(action)) {
			JSONObject arg_object;
			arg_object = args.getJSONObject(0);
			displayAmazonAds(arg_object, callbackContext);
			return true;
		}
		else if (ACTION_DISPLAY_AMAZON_INTERSTITIAL.equals(action)){
			JSONObject arg_object;
			arg_object = args.getJSONObject(0);
			displayAmazonInterstitial(arg_object, callbackContext);
			return true;
		}
		else {
			callbackContext.error("Invalid action");
		    return false;
		}
		
	}
	
	private void displayAmazonInterstitial(JSONObject args_object, CallbackContext callbackContext) {
		
		interstitialAd = new InterstitialAd(this.cordova.getActivity());
		final boolean debug;
		final String appKey;
		try {
			debug = args_object.getBoolean("debug");
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
		        
		        
		        interstitialAd.setListener(new SimpleAdListener());
		        interstitialAd.loadAd();
            }
		        
        });
		
	}
	
	class SimpleAdListener extends DefaultAdListener
    {
        /**
         * This event is called once an ad loads successfully.
         */
        @Override
        public void onAdLoaded(final Ad ad, final AdProperties adProperties) {
    
            // Once an interstitial ad has been loaded, it can then be shown.
            interstitialAd.showAd();
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
		        ViewGroup parentView = ((ViewGroup) webView).getParent();
		        if (isOnTop) {
		        	if (parentView.getChildCount() >= 2)
		        		parentView.removeViewAt(0);
		        	
		            parentView.addView(adView, 0);
		        } else {
		        	if (parentView.getChildCount() >= 2)
		        		parentView.removeViewAt(1);
		            parentView.addView(adView);
		        }
		        
		        adView.loadAd();
		        callbackContext.success(); // Thread-safe.
		        
            }
		
		});
	}
}
