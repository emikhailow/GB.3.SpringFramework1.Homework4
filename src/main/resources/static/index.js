angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/app/api/v10';

    if ($localStorage.springWebUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    }

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

    $scope.loadCart = function (pageIndex = 1) {
        $http({
            url: contextPath + '/carts',
            method: 'GET'
        }).then(function (response) {
            console.log(response.data.productDtoList)
            $scope.CartList = response.data.productDtoList;
        });
    };

    $scope.addToCart = function (id) {
        console.log(id);
        $http.post(contextPath + '/carts/' + id)
            .then(function (response) {
                $scope.loadCart();
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

	$rootScope.isUserLoggedIn = function () {
		if ($localStorage.springWebUser) {
			return true;
		} else {
			return false;
		}
	};

    $scope.loadProducts();
    $scope.loadCart();

});