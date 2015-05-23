'use strict';

describe('Controller: MainHomeCtrl', function () {

  // load the controller's module
  beforeEach(module('gitstatClientApp'));

  var MainHomeCtrl, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MainHomeCtrl = $controller('MainHomeCtrl', {
      $scope: scope
    });
  }));

  it('should ...', function () {
    expect(1).toEqual(1);
  });
});
