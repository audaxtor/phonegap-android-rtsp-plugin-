var exec = require('cordova/exec');

exports.play = function (uri) {
	exec(function(message){
		//alert(message);
	}, function(err) {
		alert(err);
	}, "RTSP", "play", [uri]);
};

