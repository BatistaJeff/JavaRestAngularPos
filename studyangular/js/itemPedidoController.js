
app.controller("itemPedidoController", function($scope, $http, URL_BASE){
		$scope.acao = "l"; // l-listar / a-alterar / i-inserir		
		$scope.itemPedido = {};
		$scope.itensPedido = [];
		$scope.textoAcao;

		$scope.listar = function(){
			$scope.acao = 'l';
			$scope.reset();
			var req = {
				method: 'GET',
				url: URL_BASE + "itemPedido/all"
			}
			$http(req).then(function(response){
				$scope.itensPedido = response.data;
			}, function(err){
				$scope.msgError = 
				"Erro ao listar os Itens pedido " + err.statusText;
			});
		}		

		$scope.salvar = function(){
			if ($scope.acao == 'e'){

				var req = {
					method: 'DELETE',
					url: URL_BASE+"itemPedido",
					headers: {
						'Content-Type': 'application/json'
					},
					data: $scope.itemPedido
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
					url: URL_BASE+"itemPedido",
					headers: {
						'Content-Type': 'application/json'
					},
					data: $scope.itemPedido
				}

				$http(req).then(function(response){
					$scope.itemPedido = response.data;
					$scope.listar();
					
				}, 
				function(err){
					$scope.msgError = err.statusText;
				});		

			}

			if ($scope.acao == 'i'){

				var req = {
					method: 'POST',
					url: URL_BASE+"itemPedido",
					headers: {
						'Content-Type': 'application/json'
					},
					data: $scope.itemPedido
				}

				$http(req).then(function(response){
					$scope.itemPedido.codigo = response.data + "ok";
					alert(response.data);
					$scope.listar();
					$scope.reset();
				}, 
				function(err){
					$scope.msgError = err.statusText;
				});
			}

		} 

		$scope.alterar = function(itemPedido){
			$scope.acao = "a";
			$scope.textoAcao = "Alterar";
			$scope.itemPedido = itemPedido;				
		}
		$scope.incluir = function(){
			$scope.acao = "i";
			$scope.textoAcao = "Incluir";
			$scope.reset();
		}

		$scope.reset = function(){
			$scope.itemPedido = {
				quantidade: 0.00, 
				valorUnitario: 0.00,
				valorTotal: 0.00
			};
		}

		$scope.excluir = function(itemPedido){
			$scope.acao = "e";
			$scope.textoAcao = "Excluir";
			$scope.itemPedido = itemPedido;			
		}

		$scope.init = function(){
			$scope.listar();
		}

		$scope.init(); // chamando metodo de inicializacao

	});