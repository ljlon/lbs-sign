
module.exports = {
    verify: function(userName, password, callback) {
        if (userName == "admin" && password == "password") {
            var userInfo = {"user_name":userName};
            callback(null, userInfo);
            return;
        }

        callback(2001);
    }
}