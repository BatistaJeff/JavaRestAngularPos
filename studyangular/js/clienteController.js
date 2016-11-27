
app.controller("clienteController", function($scope, $http, URL_BASE){
		$scope.acao = "l"; // l-listar / a-alterar / i-inserir		
		$scope.cliente = {};
		$scope.clientes = [];
		$scope.textoAcao;

		$scope.listar = function(){
			$scope.acao = "l";
			$scope.reset();
			var req = {
				method: 'GET',
				url: URL_BASE + "cliente/all"
			}
			$http(req).then(function(response){
				$scope.clientes = response.data;
			}, function(err){
				$scope.msgError = 
				"Erro ao listar os clientes " + err.statusText;
			});
		}		

		$scope.salvar = function(){
			if ($scope.acao == 'e'){

				var req = {
					method: 'DELETE',
					url: URL_BASE+"cliente",
					headers: {
						'Content-Type': 'application/json'
					},					
					data:$scope.cliente
				}

				$http(req).then(function(response){
					alert(response.data);					
					$scope.listar();
					$scope.reset();
					
				}, 
				function(err){
					$scope.msgError = err.statusText;
				});

			}


			if ($scope.acao == 'a'){
				var req = {
					method: 'PUT',
					url: URL_BASE+"cliente",
					headers: {
						'Content-Type': 'application/json'
					},
					data: $scope.cliente
				}

				$http(req).then(function(response){
					$scope.cliente = response.data;
					$scope.listar();
				}, 
				function(err){
					$scope.msgError = err.statusText;
				});		

			}

			if ($scope.acao == 'i'){

				var req = {
					method: 'POST',
					url: URL_BASE+"cliente",
					headers: {
						'Content-Type': 'application/json'
					},
					data: $scope.cliente
				}

				$http(req).then(function(response){
					$scope.cliente.codigo = response.data + "ok";								
					alert(response.data);
					$scope.listar();
					$scope.reset();

					
					
				}, 
				function(err){
					$scope.msgError = err.statusText;
				});
			}

		} 

		$scope.alterar = function(cliente){
			$scope.acao = "a";
			$scope.textoAcao = "Alterar";
			$scope.cliente = cliente;				
		}
		$scope.incluir = function(){			
			$scope.acao = "i";
			$scope.textoAcao = "Incluir";
			$scope.reset();
			
		}

		$scope.reset = function(){
			$scope.cliente = {codigo: 0, 
				nome: null, 
				inscricaoFederal: null};
			}

			$scope.excluir = function(cliente){
				$scope.acao = "e";
				$scope.textoAcao = "Excluir";
				$scope.cliente = cliente;			
			}

			$scope.init = function(){
				$scope.listar();
			}

		$scope.init(); // chamando metodo de inicializacao

	});