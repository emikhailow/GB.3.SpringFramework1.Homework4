angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {
        const contextPath = 'http://localhost:8189/app/api/v13';

        if ($localStorage.springWebUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
        }

        $rootScope.isUserLoggedIn = function () {
            if ($localStorage.springWebUser) {
                return true;
            } else {
                return false;
            }
        };

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
        };

        $scope.loadCart = function () {
            $http.get(contextPath + '/carts')
                .then(function successCallback(response) {
                    $scope.cart = response.data;
                });
        };

        $scope.addToCart = function (productId) {
            if (!$rootScope.isUserLoggedIn()) {
                alert('Authorize before adding to cart');
                return;
            }
            $http.get(contextPath + '/carts/add/' + productId)
                .then(function successCallback(response) {
                    $scope.loadCart();
                });
        };

        $scope.clearCart = function () {
            $http.get(contextPath + '/carts/clear')
                .then(function successCallback(response) {
                    $scope.loadCart();
                });
        };

        $scope.saveOrder = function () {
            $http.post(contextPath + '/carts/save')
                .then(function successCallback(response) {
                        alert('You order has been saved with id ' + response.data.id);
                        $scope.clearCart();
                    },
                    function errorCallback(response) {
                        alert(response.data.message);
                    });
        };


        $scope.tryToAuth = function () {
            $http.post('http://localhost:8189/app/auth', $scope.user)
                .then(function successCallback(response) {
                    if (response.data.token) {
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                        $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                        $scope.user.username = null;
                        $scope.user.password = null;
                    }
                }, function errorCallback(response) {
                });
        };

        $scope.tryToSignUp = function () {
            $http.post('http://localhost:8189/app/signup', $scope.newUser)
                .then(function successCallback(response) {
                    alert('User has been created');
                    $scope.newUser.username = null;
                    $scope.newUser.password = null;
                    $scope.newUser.email = null;
                }, function errorCallback(response) {
                    alert(response.data.message);
                });
        };

        $scope.tryToLogout = function () {
            $scope.clearUser();
            if ($scope.user.username) {
                $scope.user.username = null;
            }
            if ($scope.user.password) {
                $scope.user.password = null;
            }
        };

        $scope.clearUser = function () {
            delete $localStorage.springWebUser;
            $http.defaults.headers.common.Authorization = '';
        };

        $scope.loadProducts();
        $scope.loadCart();

    }
)
;