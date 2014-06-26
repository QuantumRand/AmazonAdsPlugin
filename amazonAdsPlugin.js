var amazonAdsPlugin = {
    displayAds: function(appKey, isOnTop, debug, successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'AmazonAdsPlugin', // mapped to our native Java class called "AmazonAdsPlugin"
            'displayAmazonAds', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "appKey": appKey,
                "isOnTop": isOnTop,
                "debug": debug
            }]
        ); 
     }
}
module.exports = amazonAdsPlugin;
