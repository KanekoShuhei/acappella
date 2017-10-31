var myapp = angular.module("myapp", []);
myapp.controller('mainCtrl', [
  '$scope',
  '$http',
  '$sce',
  function($scope, $http, $sce) {

	// 動画情報をデータベースから取得
    $http({method: 'GET', url: '/ac/video/get-all-json'}).then(function(videos) {
        // Youtube情報といいねの有無を調べ、加えて、resultにpush
    	showVideos(videos);
    });
    
    var arr = [];
    $http({method: 'GET', url: '/ac/video/get-one-json-by-login-user'}).then(function(loginUserLikeVideos) {
    	   arr = loginUserLikeVideos.data;
    });
    
    function showVideos(videos) {

      $scope.results = [];

      angular.forEach(videos.data, function(value, index, array) {

        var like = $sce.trustAsHtml('<a href="' + ctx + '/video/addlikes?videoId=' + value.id + '"' + ' class="btn btn-default btn-xs" role="button">★</a>');

        var checkLiked = function() {
          return new Promise(function(resolve, reject) {

        	// ここにログインしてなかったらresolve()を書きたい
        	  
          arr.filter(function(item, index) {
                if (item.id == value.id) {
                    like = $sce.trustAsHtml('<a href="' + ctx + '/video/delete-like-videos?videoId=' + value.id + '"' + ' class="btn btn-default btn-xs" role="button" style="color:yellow;">★</a>');
                }
          resolve();}
          ,function errorCallback(response) {
        	console.log("ログインしていません");
          })
          resolve();
          })};

        checkLiked().then(function() {

          var youtubeUrl = 'https://www.googleapis.com/youtube/v3/videos?' + [
            'id=' + encodeURIComponent(value.youtubeId),
            'key=AIzaSyCi73Clf3nXaxoocsBAp3d9ewlgGGjXuW4',
            'part=snippet,statistics'
          ].join('&');

          $http({method: 'GET', url: youtubeUrl}).then(function(data) {
            $scope.trustSrc = function(src) {
              return $sce.trustAsResourceUrl('https://www.youtube.com/embed/' + src);
            }
            $scope.results.push({
              video: value, // 動画情報
              data: data.data.items[0], // YouTube情報
              likeVideoButton: like // いいねボタン
            });
          });

        });

      });
      console.dir($scope.results);
    }
  }
]);
