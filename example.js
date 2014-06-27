document.addEventListener("deviceready", onDeviceReady, false);
function onDeviceReady() {
  var appKey = "sample-app-v1_pub-2";       //The Application Key for the Amazon App
  var isOnTop = true;                       //Is ad displaye at the top of the screen? (true = top of screen; false = bottom of screen)
  var isDebug = true;                       //if true, enables debugging options and test ads
  var success = null;                       //success callback function
  var failure = null;                       //failure callback function
  amazonAdsPlugin.displayAds(appKey, isOnTop, isDebug, success, failure);
}
