
app.controller("produtoController", function($scope, $http, URL_BASE){
		$scope.acao = "l"; // l-listar / a-alterar / i-inserir		
		$scope.produto = {};
		$scope.produtos = [];
		$scope.textoAcao;

		$scope.listar = function(){
			$scope.acao = 'l';
			$scope.reset();
			var req = {
				method: 'GET',
				url: URL_BASE + "produto/all"
			}
			$http(req).then(function(response){
				$scope.produtos = response.data;
			}, function(err){
				$scope.msgError = 
				"Erro ao listar os produtos " + err.statusText;
			});
		}		

		$scope.salvar = function(){
			if ($scope.acao == 'e'){

				var req = {
					method: 'DELETE',
					url: URL_BASE+"produto",
					headers: {
						'Content-Type': 'application/json'
					},
					data: $scope.produto
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
					url: URL_BASE+"produto",
					headers: {
						'Content-Type': 'application/json'
					},
					data: $scope.produto
				}

				$http(req).then(function(response){
					$scope.produto = response.data;
					$scope.listar();
					
				}, 
				function(err){
					$scope.msgError = err.statusText;
				});		

			}

			if ($scope.acao == 'i'){

				var req = {
					method: 'POST',
					url: URL_BASE+"produto",
					headers: {
						'Content-Type': 'application/json'
					},
					data: $scope.produto
				}

				$http(req).then(function(response){
					$scope.produto.codigo = response.data + "ok";
					alert(response.data);
					$scope.listar();
					$scope.reset();
				}, 
				function(err){
					$scope.msgError = err.statusText;
				});
			}

		} 

		$scope.alterar = function(produto){
			$scope.acao = "a";
			$scope.textoAcao = "Alterar";
			$scope.produto = produto;				
		}
		$scope.incluir = function(){
			$scope.acao = "i";
			$scope.textoAcao = "Incluir";
			$scope.reset();
		}

		$scope.reset = function(){
			$scope.produto = {codigo: null, nome: null};
		}

		$scope.excluir = function(produto){
			$scope.acao = "e";
			$scope.textoAcao = "Excluir";
			$scope.produto = produto;			
		}

		$scope.init = function(){
			$scope.listar();
		}

		$scope.init(); // chamando metodo de inicializacao

	});