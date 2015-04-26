AmazonAdsPlugin
===============

A PhoneGap Plugin for the Amazon Ads SDK

Requires the amazon-ads library from the Android Amazon SDK be added to your PhoneGap project.

Installation
------------
```
cordova plugin add https://github.com/QuantumRand/AmazonAdsPlugin.git
```

Usage
=====

Amazon Ads Plugin currently supports basic banner ads as well as interstitial ads.

Displaying a Banner Ad
----------------------
```
amazonAdsPlugin.displayAds(appKey, isOnTop, isDebug, success, failure);
```
appKey: a string containing your Amazon app's "Application Key."
isOnTop: a boolean. True to play banner at the top of the screen, False for the bottom.
isDebug: a boolean. True to enable debuggable SDK calls and test ads.
success: function called on a successful ad request.
failure: function called on a failed ad request.

Displaying an Interstitial Ad
-----------------------------
```
amazonAdsPlugin.displayInterstitial(appKey, isDebug, success, failure);
```
appKey: a string containing your Amazon app's "Application Key."
isDebug: a boolean. True to enable debuggable SDK calls and test ads.
success: function called on a successful ad request.
failure: function called on a failed ad request.

Full Example
------------

```
document.addEventListener("deviceready", onDeviceReadyAmazonAds, false);
function onDeviceReadyAmazonAds() {
	var appKey = "sample-app-v1_pub-2";       //The Application Key for the Amazon App
	var isOnTop = false;                      //Is ad displayed at the top of the screen? (true = top of screen; false = bottom of screen)
	var isDebug = true;                       //if true, enables debugging options and test ads
	var success = null;                       //success callback function
	var failure = null;                       //failure callback function
	amazonAdsPlugin.displayAds(appKey, isOnTop, isDebug, success, failure);		//make a banner request
	amazonAdsPlugin.displayInterstitial(appKey, isDebug, success, failure);		//make an interstitial request
}
```
