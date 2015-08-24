var error = require('./error');

module.exports = {
    success : function(res, msg) {
        var resMsg = {"error":0,
                    "result":msg};
        res.send(JSON.stringify(resMsg));
    },
    error : function(res, errorCode) {
        error.getErrMsg(errorCode, function(errMsg) {
            res.send(JSON.stringify(errMsg));
        });
    }
};

