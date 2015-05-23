'use strict';

describe('Controller: MainStatsCtrl', function () {

  // load the controller's module
  beforeEach(module('gitstatClientApp'));

  var MainStatsCtrl, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MainStatsCtrl = $controller('MainStatsCtrl', {
      $scope: scope
    });
  }));

  it('should ...', function () {
    expect(1).toEqual(1);
  });
});
