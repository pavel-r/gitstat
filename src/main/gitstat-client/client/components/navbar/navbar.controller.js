'use strict';

angular.module('gitstatClientApp')
  .controller('NavbarCtrl', function ($scope, $location) {
    $scope.menu = [{
      'title': 'Home',
      'link': 'main.home'
    },{
      'title': 'Commits',
      'link': 'main.commits'
    }];

    $scope.isCollapsed = true;

    $scope.isActive = function (route) {
      return route === $location.path();
    };
  });
