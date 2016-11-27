// criacao da aplicação
var app = angular.module("app", ["ngRoute"]);

app.value("URL_BASE", "http://localhost:8082/studyWeb/rest/");

app.config(function($routeProvider) {
    $routeProvider
    .when("/produto", {
        templateUrl : "views/produto.html",
        controller : "produtoController"
    })
    .when("/cliente", {
        templateUrl : "views/cliente.html",
        controller : "clienteController"
    })
    .when("/pedido", {
        templateUrl : "views/pedido.html",
        controller : "pedidoController"
    })
    .when("/itemPedido", {
        templateUrl : "views/itemPedido.html",
        controller : "itemPedidoController"
    })
    .otherwise({
       redirectTo: "/itemPedido"
    });

});