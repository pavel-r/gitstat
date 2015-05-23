'use strict';

angular.module('gitstatClientApp')
  .service('Commit', function ($resource) {
    return $resource('/api/commits/:sha', {sha:'@sha'});
  });
