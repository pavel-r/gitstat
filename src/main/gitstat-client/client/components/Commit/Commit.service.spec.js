'use strict';

describe('Service: Commit', function () {

  // load the service's module
  beforeEach(module('gitstatClientApp'));

  // instantiate service
  var Commit;
  beforeEach(inject(function (_Commit_) {
    Commit = _Commit_;
  }));

  it('should do something', function () {
    expect(!!Commit).toBe(true);
  });

});
