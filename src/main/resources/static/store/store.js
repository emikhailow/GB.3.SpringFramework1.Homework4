angular.module('market-front').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/app/';

    $scope.loadProducts = function () {
        $http({
            url: contextPath + 'api/v15/products',
            method: 'GET',
            params: {
                p: $scope.activePageNumber,
                category_id: $scope.activeProductCategory,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.ProductsPage.totalPages);
        });
    };

    $scope.loadCategories = function () {
        $http.get(contextPath + 'api/v15/products/categories')
            .then(function (response) {
                console.log(response.data);
                $scope.productCategories = response.data;
            });
    };

    $scope.addToCart = function (productId) {
        $http.get(contextPath + 'api/v15/cart/' + $localStorage.springWebGuestCartId + '/add/' + productId)
            .then(function (response) {
            });
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.findByCategory = function (productcategory_id) {
        $scope.activeProductCategory = productcategory_id;
        $scope.loadProducts();
    }

    $scope.findByPageNumber = function (pageIndex) {
        $scope.activePageNumber = pageIndex;
        $scope.loadProducts();
    }

    $scope.loadProducts();
    $scope.loadCategories();
});