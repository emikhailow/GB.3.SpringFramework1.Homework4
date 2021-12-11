angular.module('homework7', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/homework7';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
            .then(function (response) {
                $scope.ProductsList = response.data;
            });
    };

     $scope.removeItem = function (id) {
            $http.get(contextPath + '/products/remove/' + id)
                .then(function (response) {
                    $scope.loadProducts();
                });
     };

     $scope.addItem = function (title) {
                 newItemInputField = document.getElementById("newItemInputField");
                 if(newItemInputField.value === undefined){
                    return;
                 }
                 $http.get(contextPath + '/products/add/' + newItemInputField.value)
                     .then(function (response) {
                         $scope.loadProducts();
                     });
                     newItemInputField.value = ""

          };

    $scope.loadProducts();
});