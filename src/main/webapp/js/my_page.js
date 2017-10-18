angular.module('myapp', [])
.controller('myPageCtrl', ['$scope', '$http',
	function ($scope, $http) {

	$http({
		  type: 'GET',
		  url: '/ac/my-page/like-videos'
	}).then(function (videos) {
	    	  
  		$scope.results = [];
  		angular.forEach(videos.data, function(value, index, array) {
	      $scope.videos = value;
          var url = 'https://www.googleapis.com/youtube/v3/videos?' 
        	  + ['id=' + encodeURIComponent(value.youtubeId), 
        		 'key=AIzaSyCi73Clf3nXaxoocsBAp3d9ewlgGGjXuW4', 
        		 'part=snippet,statistics', ].join('&');
          $http({
            method: 'GET',
            url: url
          }).then(function (data) {
            $scope.trustSrc = function (src) {
              return $sce.trustAsResourceUrl('https://www.youtube.com/embed/' + src);
            }
            $scope.results.push({video:value,data:data.data.items[0]});
          });
  		});
	});
	
	$http({
		  type: 'GET',
		  url: '/ac/my-page/love-bands'
	}).then(function (videos) {
	
		$scope.bands = videos.data;
		$scope.band = $scope.bands[0];
		
		console.dir($scope.bands);
	});
}]);
