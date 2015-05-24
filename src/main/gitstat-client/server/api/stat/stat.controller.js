'use strict';

var _ = require('lodash');

var fs = require('fs');

// Get list of stats
exports.index = function(req, res) {
  var fileContent = fs.readFileSync('server/data/stats.json').toString();
  res.json(JSON.parse(fileContent));
};