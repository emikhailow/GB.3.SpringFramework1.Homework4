angular.module('market-front').controller('ordersController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/app/';

    $scope.loadOrders = function () {
        $http.get(contextPath + 'api/v15/orders')
            .then(function (response) {
                $scope.MyOrders = response.data;
            });
    }

    $scope.loadOrders();

});