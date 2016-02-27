var cliente = angular.module('Aton', ['ngRoute', 'ngCookies']);
cliente.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'login.html',
        controller: 'ContLogin'
    });
    $routeProvider.when('/listar', {
        templateUrl: 'equipos.html',
        controller: 'ContEquipos'
    });
}]);

cliente.service('Usuario', function($http) {
    this.validar = function(usuario, password) {
        return $http({
            method: 'GET',
            url: 'ws/usuarios',
            params: {
                usuario: usuario,
                password: password
            }
        });
    };
});

cliente.controller('ContLogin', function($scope, Usuario, $location) {
    $scope.validar = function() {
        Usuario.validar($scope.nombreUsuario, $scope.password).success(
            function(data) {
                if (data !== "") {
                    alert(data);
                } else {
                    $location.url('/listar');
                }
            });
    };

});

cliente.service('Equipo', function($http) {
    this.obtener = function() {
        return $http({
            method: 'GET',
            url: 'ws/equipos'
        });
    };
});

cliente.controller('ContEquipos', function($scope, Equipo) {
    Equipo.obtener().success(function(data) {
        console.log(data);
        $scope.listaSalas = data;
    });
});

cliente.service('Cliente', function($http) {
    this.obtener = function() {
        return $http({
            method: 'GET',
            url: 'ws/ClienteServlet'
        });
    };
});
