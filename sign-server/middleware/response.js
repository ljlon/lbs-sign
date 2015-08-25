
module.exports = function(req, res, next) {
    res.append('Content-Type', 'text/json; charset=utf-8');
    next();
}