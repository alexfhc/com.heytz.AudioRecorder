var exec = require('cordova/exec');

exports.startRecord = function (
                            success, error) {
  exec(success, error, "AudioRecorder", "startRecord",
    [

    ]);
};
exports.stopRecord = function () {
  exec(null, null, "AudioRecorder", "stopRecord",
    []);
};
exports.startPlay = function () {
  exec(null, null, "AudioRecorder", "startPlay",
    []);
};
exports.stopPlay = function () {
  exec(null, null, "AudioRecorder", "stopPlay",
    []);
};