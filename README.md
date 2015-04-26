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

```
document.addEventListener("deviceready", onDeviceReadyAmazonAds, false);
function onDeviceReadyAmazonAds() {
	var appKey = "sample-app-v1_pub-2";       //The Application Key for the Amazon App
	var isOnTop = false;                      //Is ad displayed at the top of the screen? (true = top of screen; false = bottom of screen)
	var isDebug = true;                       //if true, enables debugging options and test ads
	var success = null;                       //success callback function
	var failure = null;                       //failure callback function
	amazonAdsPlugin.displayAds(appKey, isOnTop, isDebug, success, failure);
}
```
