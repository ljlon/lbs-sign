var response = require('../library/response');
var express = require('express');
var router = express.Router();
var user  = require('../models/user');

/*  login */
router.post('/login', function(req, res, next) {
    var userName = req.body.user_name;
    var password = req.body.password;

    if (!userName || !password) {
        response.error(res, 1001);
        return;
    }

    user.verify(userName, password, function(errCode, result) {
        if (errCode) {
            response.error(res, errCode);
        }
        else {
            response.success(res, result); 
        }
    });
});

module.exports = router;
