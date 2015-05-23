'use strict';

var _ = require('lodash');

// Get list of commits
var fs = require('fs');

exports.index = function(req, res) {
  var fileContent = fs.readFileSync('server/data/commits.json').toString();
  res.json(JSON.parse(fileContent));
};

exports.show = function(req, res) {
  var fileContent = fs.readFileSync('server/data/commits/'+ req.params.sha +'.json').toString();
  return res.json(JSON.parse(fileContent));
};