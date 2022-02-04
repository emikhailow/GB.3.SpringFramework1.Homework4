angular.module('market-front').controller('signupController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/app/';

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

});