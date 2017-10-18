angular.module('myapp', [])
  .controller('allBandsCtrl', ['$scope', '$http',
    function($scope, $http) {

      $http({
        type: 'GET',
        url: '/ac/band/get-all-json'
      }).then(function(videos) {

        $scope.bands = videos.data;
        $scope.band = $scope.bands[0];

        console.dir($scope.bands);
      });
    }
  ]);
