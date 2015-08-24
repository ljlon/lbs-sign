var md5 = require('md5');
var commonConfig = require('../config/common');
var database = require('../library/database');

module.exports = {
    verify: function(userName, password, callback) {
        if (!userName || !password) {
            callback(1001);
            return;
        }

        database.query('SELECT * FROM user WHERE CH_USER_NAME = "' + userName + '"', function(err, rows) {
            if (err || !rows || !rows.length) {
                console.log(err);
                callback(2001);
                return;
            }

            if (rows[0].CH_PASSWORD != md5(md5(password)+commonConfig.passwordSalt)) {
                callback(2001);
                return;
            }
            var userInfo = {'user_name':userName};
            callback(null, userInfo);
        });
    },
    register: function(userName, password, callback) {
        if (!userName || !password) {
            callback(1001);
            return;
        }

        var encodePwd = md5(md5(password)+commonConfig.passwordSalt);
        database.query('INSERT INTO user (CH_USER_NAME, CH_PASSWORD) VALUES ("' + userName + '","' + encodePwd + '")',
            function(err, rows) {
                if (err || !rows) {
                    console.log(err);
                    callback(2002);
                    return;
                }

                var userInfo = {'user_name':userName};
                callback(null, userInfo);
            }
        );
    }
}