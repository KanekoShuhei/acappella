var myapp = angular.module("myapp", []);
myapp.controller('mainCtrl', [
  '$scope',
  '$http',
  '$sce',
  function($scope, $http, $sce) {

    $http({method: 'GET', url: '/ac/video/get-all-json'}).then(function(videos) {
      showVideos(videos);
    });

    function showVideos(videos) {

      $scope.results = [];

      angular.forEach(videos.data, function(value, index, array) {

        var like = $sce.trustAsHtml('<a href="' + ctx + '/video/addlikes?videoId=' + value.id + '"' + ' class="btn btn-default btn-xs" role="button">★</a>');
        var hasLiked = false;

        var checkLiked = function() {
          return new Promise(function(resolve, reject) {

            $http({method: 'GET', url: '/ac/video/get-one-json-by-login-user'}).then(function(loginUserLikeVideos) {

              var arr = loginUserLikeVideos.data;
              arr.filter(function(item, index) {
                if (item.id == value.id) {
                  hasLiked = true;
                }
              });
              if (hasLiked) {
                like = $sce.trustAsHtml('<a href="' + ctx + '/video/delete-like-videos?videoId=' + value.id + '"' + ' class="btn btn-default btn-xs" role="button" style="color:yellow;">★</a>');
              }
              
              resolve();
              
            },function errorCallback(response) {
            	console.log("ログインしていません");
            	resolve();
              });

          });
        };

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
              video: value, //動画情報
              data: data.data.items[0], //YouTube情報
              likeVideoButton: like //いいねボタン
            });
          });

        });

      });
      console.dir($scope.results);
    }
  }
]);
