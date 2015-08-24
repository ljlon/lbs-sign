var mysql = require('mysql');

global.pool = null;

module.exports = {
    init: function(host, database, userName, password) {
        pool = mysql.createPool({
          connectionLimit : 10,
          host            : host,
          user            : userName,
          password        : password,
          database        : database
        });    
    },
    query: function(sql, callback) {
        pool.getConnection(function(err, connection) {
            connection.query(sql, function(err, rows) {
                connection.release();
                callback(err, rows);
            });
        });
    }
}
