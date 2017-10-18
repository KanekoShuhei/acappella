angular.module('myapp', []).controller('videoDetailCtrl', ['$scope', '$http', '$sce', function($scope, $http, $sce) {
	
    var youtubeId = decodeURIComponent(location.search.match(/youtubeId=(.*?)(&|$)/)[1]);
	
	$http({
	    method: 'GET',
        url: '/ac/video/get-one-json-by-youtubeId?youtubeId=' + youtubeId
	}).then(function(videos) {
		showVideos(videos);
	});
	
	function showVideos(video) {

        $scope.result = [];

        	var like;
            var hasLiked = false;
            $http({
              method: 'GET',
              url: '/ac/video/get-one-json-by-login-user'
            }).then(function(videos) {

              var arr = videos.data;
              arr.filter(function(item, index) {
                if (item.id == video.data.id) {
                  hasLiked = true;
                }
              });
              if (hasLiked) {
            	  like = $sce.trustAsHtml(
                  '<a href="' + ctx + '/video/delete-like-videos?videoId=' + video.data.id + '"' +
                  ' class="btn btn-default btn-xs" role="button" style="color:yellow;">★</a>');
              } else {
            	  like = $sce.trustAsHtml(
                  '<a href="' + ctx + '/video/addlikes?videoId=' + value.id + '"' +
                  ' class="btn btn-default btn-xs" role="button">☆</a>');
              }
            });
        	
            var url = 'https://www.googleapis.com/youtube/v3/videos?' + ['id=' +
                encodeURIComponent(youtubeId),
                'key=AIzaSyCi73Clf3nXaxoocsBAp3d9ewlgGGjXuW4',
                'part=snippet,statistics',
            ].join('&');

            $http({
                method: 'GET',
                url: url
            }).then(function(data) {
                $scope.trustSrc = function(src) {
                    return $sce.trustAsResourceUrl('https://www.youtube.com/embed/' + src);
                }
                $scope.result = {
                    video: video.data, //動画情報
                    data: data.data.items[0],//YouTube情報
                    likeVideoButton: like
                    };
            });
	}
}]);