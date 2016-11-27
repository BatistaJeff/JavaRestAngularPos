
app.controller("pedidoController", function($scope, $http, URL_BASE){
		$scope.acao = "l"; // l-listar / a-alterar / i-inserir		
		$scope.pedido = {};
		$scope.pedidos = [];
		$scope.textoAcao;

		$scope.listar = function(){
			$scope.acao = 'l';
			$scope.reset();
			var req = {
				method: 'GET',
				url: URL_BASE + "pedido/all"
			}
			$http(req).then(function(response){
				$scope.pedidos = response.data;
			}, function(err){
				$scope.msgError = 
				"Erro ao listar os pedidos " + err.statusText;
			});
		}		

		$scope.salvar = function(){
			if ($scope.acao == 'e'){

				var req = {
					method: 'DELETE',
					url: URL_BASE+"pedido",
					headers: {
						'Content-Type': 'application/json'
					},
					data: $scope.pedido
				}

				$http(req).then(function(response){
					alert(response.data.msg);
					$scope.listar();
				}, 
				function(err){
					$scope.msgError = err.statusText;
				});

			}


			if ($scope.acao == 'a'){
				var req = {
					method: 'PUT',
					url: URL_BASE+"pedido",
					headers: {
						'Content-Type': 'application/json'
					},
					data: $scope.pedido
				}

				$http(req).then(function(response){
					$scope.pedido = response.data;
					$scope.listar();
				}, 
				function(err){
					$scope.msgError = err.statusText;
				});		

			}

			if ($scope.acao == 'i'){

				var req = {
					method: 'POST',
					url: URL_BASE+"pedido",
					headers: {
						'Content-Type': 'application/json'
					},
					data: $scope.pedido
				}

				$http(req).then(function(response){
					$scope.pedido.codigo = response.data + "ok";					
					alert(response.data);
					$scope.listar();					
					$scope.reset();
				}, 
				function(err){
					$scope.msgError = err.statusText;
				});
			}

		} 

		$scope.alterar = function(pedido){
			$scope.acao = "a";
			$scope.textoAcao = "Alterar";
			$scope.pedido = pedido;				
		}
		$scope.incluir = function(){
			$scope.acao = "i";
			$scope.textoAcao = "Incluir";
			$scope.reset();
		}

		$scope.reset = function(){
			$scope.pedido = {
				numeroPedido: 0, 
				dataPedido: 0000/00/00,
			},$scope.cliente = {codigo : 0
				
			};
		}

		$scope.excluir = function(pedido){
			$scope.acao = "e";
			$scope.textoAcao = "Excluir";
			$scope.pedido = pedido;			
		}

		$scope.init = function(){
			$scope.listar();
		}

		$scope.init(); // chamando metodo de inicializacao

	});