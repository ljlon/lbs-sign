
var errorMsg = {
    1001 : "参数错误",

    2001 : "用户名或密码错误",
    2002 : "用户注册失败",
};

module.exports = {
    getErrMsg : function(errorCode, callback) {
        var resMsg = {"error":errorCode,
                    "errorMsg": errorMsg[errorCode]};
        callback(resMsg);
    }
};

