'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('foodies.services', []).
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
	

	