'use strict';

/* Services */
var apiary =  "http://foodeys.apiary.io/";
var host = "http://api.foodeys.io/";
  
var hostURL =	apiary;

// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('foodeys.services', []).
  value('version', '0.1')
 
	.factory('restClient', function($q, $rootScope) {
		return {
			query: function(url, method, data) {
				
				var deferred = $q.defer();
				var xhr = createCORSRequest(method, url);
				if (!xhr) {
					alert('CORS not supported');
					return;
				}
				
				xhr.setRequestHeader('Content-type', 'application/json');
				// Response handlers.
				xhr.onload = function() {
					console.log("status " + xhr.status  + " " + xhr.responseText);
			
					
					if(xhr.responseText.length>0){
						var data = JSON.parse(xhr.responseText);
					}else{
						var data = "";
					}
				
					var response = {
						"data":data,
						"status": xhr.status
					};
				
					deferred.resolve(response);
					$rootScope.$apply(deferred.resolve);
					
				};

				xhr.onerror = function() {
						console.log("errorstatus " + xhr.status );
						$rootScope.paymentStatusCode
						deferred.reject();
						$rootScope.$apply(deferred.reject);
				};
				
				
				xhr.send(data);
				
				return deferred.promise;
			}
		}
	})

	
	.factory('cook', function($rootScope, restClient) {
	
		var url = hostURL + 'cooks/'; 
		
		return {
	
			login: function(jsonData) {
	
				var method = 'POST';
				url += 'login/';
		
				var responseData = restClient.query(url, method, jsonData).then(function(response) {

					if(response.status == 200 ){
						return response.data.token;
					}
				});
				
				return responseData;
			},
			
			register: function(jsonData) {
	
				var method = 'POST';
		
				var responseData = restClient.query(url, method, jsonData).then(function(response) {

					if(response.status == 201 ){
						return true;
					}
				});
				
				return responseData;
			},
	
			get: function(id) {
			
				var method = 'GET';
				url += id;
				
				var responseData = restClient.query(url, method).then(function(response) {
					if(response.status == 200){
						return response.data.cooks;
					}
				});
				
				return responseData;
			},
	
	
			getAll: function() {
			
				var method = 'GET';
				
				var responseData = restClient.query(url, method).then(function(response) {
					if(response.status == 200){
						return response.data.cooks;
					}
				});
				
				return responseData;
			},
		
			
		}
	
	})
	
	
	.factory('recipe', function($rootScope, restClient) {
		
		var url = hostURL + 'recipes/'; 
		
		return {
	
			getAll: function() {
			
				var method = 'GET';
				
				var responseData = restClient.query(url, method).then(function(response) {
					if(response.status == 200){
						return response.data.recipes;
					}
					if(response.status == 400){
						//$scope.errorText  = response.data.errorResponse.message;
					}
				});
				
				return responseData;
			},
			
			get: function(id) {
						
				var method = 'GET';
				var _url = url  + id;
				
				var responseData = restClient.query(_url, method).then(function(response) {
					if(response.status == 200){
					
						return response.data;
							
					}
					if(response.status == 400){
						//$scope.errorText  = response.data.errorResponse.message;
					}
				});
				
				return responseData;
			},
			
			search: function(foods) {
				
				var method = 'GET';
				
				var _url =  url + 'search/?'+foods;

		
				var responseData = restClient.query(url, method).then(function(response) {
					if(response.status == 200){
						return response.data.data;
						
					}
					if(response.status == 400){
						$scope.errorText  = response.data.errorResponse.message;
					}
				});
				return responseData;
			},
			
			addRecipe: function(cookToken, jsonData) {

				var method = 'POST';
				var _url = url + '?cookToken=' + cookToken; 
				
				var responseData = restClient.query(_url, method, jsonData).then(function(response) {

					if(response.status == 201 ){
						console.log(response);
						return response.data.data;
					
					}
		
				});
					return responseData;
			},
			
						
			updateRecipe: function(cookToken, jsonData, id) {

				var method = 'PUT';
				var _url = url + id +'/cookToken='+cookToken; 

				var responseData = restClient.query(_url, method, jsonData).then(function(response) {

					if(response.status == 200 ){
						console.log(response);
						return response.data.data;
						
					}
		
				});
				
				return responseData;
			},
			
			del: function(recipeItem) {
				var method = 'DELETE';
				var _url = url +  recipeItem.recipe_url; 
				
				var responseData = restClient.query(_url, method).then(function(response) {

					if(response.status == 204 ){
						console.log(response);
						return  response.data.data;
				
					}
		
				});
				return responseData;
			},
			
			getIngredientsByRecipe: function(id) {
			
				var method = 'GET';
				var _url = url + id+'/ingredients/'; 
				
				var responseData = restClient.query(_url, method).then(function(response) {
					if(response.status == 200){
					
						return response.data.ingredients;
						
					}
				});
			
				return responseData;
			},
			
			addIngredientsToRecipe: function(id, jsonData) {
						
				var method = 'POST';
				var _url = url + id+'/ingredients/'; 
				
				var responseData = restClient.query(_url, method, jsonData).then(function(response) {
					if(response.status == 200){
						
						return response.data.ingredients;
						
					}
				});
				
				return responseData;
			},
			
			
		}
		
	})
	
	.factory('food', function( $rootScope, restClient) {
	
		var url = hostURL + 'foods/'; 
		
		return {
	
			get: function(id) {
			
				var method = 'GET';
				url += id;
				
				var responseData = restClient.query(url, method).then(function(response) {
					if(response.status == 200){
						return response.data;
					}
				});
				
				return responseData;
			},
	
			getAll: function() {
			
				var method = 'GET';
				
				var responseData = restClient.query(url, method).then(function(response) {
					if(response.status == 200){
						return response.data.foods;
					}
				});
				
				return responseData;
			},
			
			getAllFoodForRecipe: function( id ) {
			
				var method = 'GET';
				url += id + '/recipes'
				
				var responseData = restClient.query(url, method).then(function(response) {
					if(response.status == 200){
						return response;
					}
				});
				
				return responseData;
			},
			
			search: function(query) {
			
				var method = 'GET';
				url = 'autocomplete/' + query ; 
		
				var responseData = restClient.query(url, method).then(function(response) {
					if(response.status == 200){
						return response;
					}
				});
				
				return responseData;
			},
			
			addFood: function(cookToken, jsonData) {
			
				var method = 'POST';
				url += '?cookToken=' + cookToken; 
				
				var responseData = restClient.query(url, method, jsonData).then(function(response) {
					if(response.status == 200){
						return response;
					}
				});
				
				return responseData;
			},
			
			
		}
	
	})
	
	.factory('ingredient', function( $rootScope, restClient) {
	
		var url = hostURL + 'ingredients/'; 
		
		var deferred = $q.defer();
		
		return {
	
			get: function(id) {
			
				var method = 'GET';
				url += id;
				
				var responseData = restClient.query(url, method).then(function(response) {
					if(response.status == 200){
						return response.data.foods;
					}
				});
				
				return responseData;
			},
			
			update: function(cookToken, jsonData) {
				var method = 'PUT';
				url +=  id + "/?cookToken="+ cookToken; 
				
				var responseData = restClient.query(url, method, jsonData).then(function(response) {

					if(response.status == 200 ){
					 	return response;
					}
					
					if(response.status == 401 ){
						$scope.errorText = response.data.errorResponse.message;
					}
				});
				return responseData;
			},
			del: function(id, cookToken) {
			
				var method = 'DELETE';
				url +=  id + "/?cookToken="+ cookToken; 
				
				var responseData = restClient.query(url, method).then(function(response) {
					if(response.status == 200){
						return response.data.foods;
					}
				});
				
				return responseData;
			},
		}
	})
	
	.factory('foodFactory', function() {
		var foodList = [];
		var foodService = {};
    
		foodService.setList = function(list) {
			foodList = list;
		};
		foodService.getList = function() {
			return foodList;
		};
    
		return foodService;
	})
	
	.factory('cookTokenFactory', function() {
		var cookToken = "";
		var cookTokenService = {};
    
		cookTokenService.setToken = function(token) {
			cookToken = token;
		};
		cookTokenService.getToken = function() {
			return cookToken;
		};
    
		return cookTokenService;
	})
	
	.factory('recipeFactory', function() {
		var recipeList = [];
		var recipeService = {};
    
		recipeService.setList = function(recipe) {
			recipeList = recipe;
		};
		recipeService.getList = function() {
			return recipeList;
		};
    
		return recipeService;
	});
	
		
function createCORSRequest(method, url) {
	var xhr = new XMLHttpRequest();
	if ("withCredentials" in xhr) {
		// XHR for Chrome/Firefox/Opera/Safari.
		xhr.open(method, url, true);
	} else if (typeof XDomainRequest != "undefined") {
		// XDomainRequest for IE.
		xhr = new XDomainRequest();
		xhr.open(method, url);
	} else {
		// CORS not supported.
		xhr = null;
	}
	return xhr;
}
	

	