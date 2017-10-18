angular.module('myapp', [])
    .controller('bandDetailCtrl', ['$scope', '$http', '$sce',
        function($scope, $http, $sce) {

            var bandId = decodeURIComponent(location.search.match(/bandId=(.*?)(&|$)/)[1]);
            
            $http({
                method: 'GET',
                url: '/ac/band/get-one-json?bandId=' + bandId
            }).then(function(band) {
                $scope.band = band.data;
                
                var hasLoved = false;
                
                $http({
                    method: 'GET',
                    url: '/ac/band/get-love-bands-by-login-user'
                }).then(function(bands) {

                    var array = bands.data;
                    console.log(array);
                    array.filter(function(item, index) {
                        if (item.id == band.data.id) {
                        	hasLoved = true;
                        }
                    });
                
                if(hasLoved){
                	$scope.loveBandButton = $sce.trustAsHtml(
                			'<a href="'+ ctx +'/band/delete-love-bands?bandId='+ bandId +'"'
                			+ ' class="btn btn-default btn-group pull-right">LOVED THIS BAND</a>');
                }else{
                	$scope.loveBandButton = $sce.trustAsHtml(
                			'<a href="'+ ctx +'/band/love?bandId='+ bandId +'"'
                            + ' class="btn btn-danger btn-group pull-right">LOVE THIS BAND</a>');
                }
                });
            });
            
            $http({
                method: 'GET',
                url: '/ac/video/get-band-videos-json?bandId=' + bandId
            }).then(function(videos) {

                $scope.results = [];

                angular.forEach(videos.data, function(value, index, array) {

                	var like;
                    var hasLiked = false;
                    $http({
                      method: 'GET',
                      url: '/ac/video/get-one-json-by-login-user'
                    }).then(function(videos) {

                      var arr = videos.data;
                      arr.filter(function(item, index) {
                        if (item.id == value.id) {
                          hasLiked = true;
                        }
                      });
                      if (hasLiked) {
                    	  like = $sce.trustAsHtml(
                          '<a href="' + ctx + '/video/delete-like-videos?videoId=' + value.id + '"' +
                          ' class="btn btn-default btn-xs" role="button" style="color:yellow;">★</a>');
                      } else {
                    	  like = $sce.trustAsHtml(
                          '<a href="' + ctx + '/video/addlikes?videoId=' + value.id + '"' +
                          ' class="btn btn-default btn-xs" role="button">☆</a>');
                      }
                    });
                	
                    var url = 'https://www.googleapis.com/youtube/v3/videos?' + ['id=' +
                        encodeURIComponent(value.youtubeId),
                        'key=AIzaSyCi73Clf3nXaxoocsBAp3d9ewlgGGjXuW4',
                        'part=snippet,statistics',
                    ].join('&');

                    $http({
                        method: 'GET',
                        url: url
                    }).then(function(data) {
                        $scope.trustSrc = function(src) {
                            return $sce.trustAsResourceUrl('http://www.youtube.com/embed/' + src);
                        }
                        $scope.results.push({
                            video: value, //動画情報
                            data: data.data.items[0],//YouTube情報
                            likeVideoButton: like
                            });
                    });
                });
            });
        }
    ]);

