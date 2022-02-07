angular.module('market-front').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/app/';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + 'api/v14/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
        });
    };

    $scope.addToCart = function (productId) {
        $http.get(contextPath + 'api/v14/cart/add/' + productId)
            .then(function (response) {
            });
    }

    $scope.loadProducts();
});