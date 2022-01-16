angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v9';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
                $scope.ProductsList = response.data.content;
            });
    };

     $scope.removeItem = function (id) {
            $http.delete(contextPath + '/products/' + id)
                .then(function (response) {
                    $scope.loadProducts();
                });
     };

     $scope.addItem = function () {
       console.log($scope.newItem);
       $http.post(contextPath + '/products', $scope.newItem)
       .then(function (response) {
                   $scope.loadProducts();
                        });
        }

    $scope.loadProducts();
});