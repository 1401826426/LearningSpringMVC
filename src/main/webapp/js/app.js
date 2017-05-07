
var app = angular.module("sprang", ["ngResource","ngFileUpload"]).
    config(function ($routeProvider) {
        $routeProvider
            .when('/showAll', {templateUrl: '/views/showAll.html' ,controller:'showAllController'})
            .when('/search', {templateUrl: '/views/search.html' , controller:'searchController'})
            .when('/craw', {templateUrl:'/views/craw.html' ,controller:'crawController'})
            .when('/plotFeature' ,{templateUrl:'/views/plotFeature.html',controller:'plotFeature'})
            .otherwise({redirectTo:'/showAll'}) ;
    });

app.controller("showAllController" , function($scope,$http){
    function showAllTypes(){
        $http.get("/image/types")
            .then(function success(response) {
                $scope.types = response.data.data ;
                console.log(response)
            },function error(response) {
                console.log(response) ;
            })
    }
    function refresh(type,page) {
        var url = "/image/type"
        url += "/" + type + "/page/" + page;
        $http({
            method: 'GET',
            url: url
        }).then(function success(response) {
            console.log(response.data.message)
            if(response.data.status == "SUCCESS") {
                $scope.items = response.data.data.data;
                $scope.curPage = response.data.data.curPage;
                $scope.totalPage = response.data.data.totalPage;
                $scope.type = type;
            }
            console.log(response);
        }, function fail(response) {
            console.log("fail")
            console.log(response);
        })
    }
    showAllTypes() ;
    refresh("all" , 1) ;
    $scope.refresh = refresh ;
    $scope.delete = function (id) {
        console.log("删除图像" + id) ;
        $http.delete("/image/id/" + id)
        refresh($scope.type , $scope.curPage) ;
    }


})

app.controller("crawController" , function ($scope , $http) {
    console.log($scope) ;
    $scope.craw = function () {
        var url = "/craw/v2";
        console.log(url) ;
        var data = angular.toJson({
            "url":$scope.crawUrl ,
            "type":$scope.type
        }) ;
        $http.post(url,data)
            .then(function success(response) {
                var data  = response.data.data ;
                if(data === "")
                    alert("爬虫失败") ;
                else
                    alert("爬虫成功") ;
                console.log(response)  ;
            },function error(response) {
            console.log(response);
        }) ;
    }
})

app.controller("plotFeature" , function ($scope , $http) {

    $scope.img1 = "/img/default.jpg" ;
    $scope.img2 = "/img/default.jpg" ;
    $scope.img3 = "/img/default.jpg" ;
    $scope.img4 = "/img/default.jpg" ;
    $scope.img5 = "/img/default.jpg" ;
    $scope.img6 = "/img/default.jpg" ;
    $scope.img7 = "/img/default.jpg" ;
    $scope.refresh1 = 1 ;
    $scope.refresh2 = 1 ;
    $scope.refresh3 = 1 ;

    function save(fd , url , callback) {
        // var fd = new FormData();
        // // var file = document.querySelector('input[type=file]').files[0];
        // fd.append('image', file);
        // fd.append("category" , "category") ;
        console.log("發出請求" + url)
        $http({
            method:'POST',
            url:url ,
            data: fd,
            headers: {'Content-Type':undefined},
            transformRequest: angular.identity
        }).success( function (response) {
            console.log(response) ;
            if(response.status === "FAILURE")
                alert("失敗")
            callback() ;
            return response ;
        }).error(function (response) {
            console.log(response) ;
            alert("失敗") ;
        });
    }

    $scope.plotFeature = function(){
        var fd = new FormData() ;
        var file = document.querySelector('input[id=img1]').files[0];
        console.log(file) ;
        fd.append("image" , file) ;
        save(fd , "/plot/feature" ,function () {
            $scope.refresh1 = 1 + $scope.refresh1 ;
            $scope.img1 = "/plot/image/img1/" + $scope.refresh1;
            $scope.img2 = "/plot/image/img2/" + $scope.refresh1;
        }) ;
    }

    $scope.plotHist = function(){
        var fd = new FormData() ;
        var file = document.querySelector('input[id=img2]').files[0];
        console.log(file) ;
        fd.append("image" , file) ;
        save(fd , "/plot/hist" ,function () {
            $scope.refresh3 = 1 + $scope.refresh3 ;
            $scope.img6 = "/plot/image/img6/" + $scope.refresh3;
            $scope.img7 = "/plot/image/img7/" + $scope.refresh3;
        }) ;
    }


    $scope.plotMatch = function(){
        var fd = new FormData() ;
        var image1 = document.querySelector('input[id=img3]').files[0];
        var image2 = document.querySelector('input[id=img4]').files[0];
        console.log(image1) ;
        console.log(image2) ;
        fd.append("image1" , image1)  ;
        fd.append("image2" , image2) ;
        save(fd ,"/plot/match" , function () {
            $scope.refresh2 = 1 + $scope.refresh2 ;
            $scope.img3 = "/plot/image/img3" + "/" + $scope.refresh2 ;
            $scope.img4 = "/plot/image/img4" + "/" + $scope.refresh2;
            $scope.img5 = "/plot/image/img5" + "/" + $scope.refresh2;
        })
    }

})

app.controller("searchController" , function ($scope , $http) {

    $scope.imgs = [] ;
    for(var i = 0; i < 8;i++){
        $scope.imgs.push("/img/default.jpg") ;
    }
    
    $scope.search = function () {
        var file = document.querySelector('input[type=file]').files[0] ;
        var fd = new FormData() ;
        fd.append("image" , file) ;
        $http({
            method:'POST',
            url:'/search/sift' ,
            data: fd,
            headers: {'Content-Type':undefined},
            transformRequest: angular.identity
        }).success( function (response) {
            console.log(response) ;
            if(response.status === "FAILURE")
                alert("失敗")
            var data = response.data;
            for(var i = 0;i < data.length;i++){
                $scope.imgs[i] = "/image/id/" + data[i] ;
            }
        }).error(function (response) {
            console.log(response) ;
            alert("失敗") ;
        });
    }

})

