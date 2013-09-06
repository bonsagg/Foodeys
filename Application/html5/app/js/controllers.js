'use strict';

/* Controllers */
angular.module('foodies.controllers', []);
;
  
var hostURL = "http://foodeys.apiary.io/";
var cookToken = "";

var foodList=[];
  
function LoginController($scope, $location, $timeout,restClient){

	$scope.errorText = "";

	// DATA MOCKUP
	$scope.loginName = "derkuba@kuba.cu";
	$scope.pw = "kuba";
	
	var method = 'POST';
	var url = hostURL +  'cooks/login/'; 
	
	$scope.submitted = false;
	
	$scope.login = function(login){
		$scope.submitted = true;
        
		if(login.$valid){
			var name = $scope.loginName;
			var pw = $scope.pw;
			
			var jsonData = {
					"name" : pw,
					"email" : name
			};
		
			jsonData = JSON.stringify(jsonData);
			
			console.log(name + " " + pw);
		
			restClient.query(url, method, jsonData).then(function(response) {
					
				if(response.status == 200 ){
					cookToken = response.data.token;
					
					$location.url('/main');
				}
					
				if(response.status == 401 ){
					$scope.errorText = response.data.errorResponse.message;
					$scope.submitted = false;
				}
			});
		} 
	}
}


function RegisterController($scope, $location, restClient){

	$scope.errorText = "";

	var method = 'POST';
	var url = hostURL + 'cooks/'; 
	
	$scope.register = function(regForm){
	
		$scope.submit = true;
	
		var name = $scope.name;
		var email = $scope.email;
		
		if(regForm.$valid){
			var jsonData = {
				"name" : name,
				"email" : email
			};
		
			jsonData = JSON.stringify(jsonData);
			
			restClient.query(url, method, jsonData).then(function(response) {
					console.log("pre " + response.status);
				if(response.status == 201 ){
					
					console.log("post " + response.status);
					
					$location.url('/login');
					
					// SUCCESSMESSAGE
				}
					
				if(response.status == 401 ){
					$scope.errorText = response.data.errorResponse.message;
				}
			});
		}
	}
}
  



function CookController($scope, $location, restClient){
	
	function getAllCooks(){
	
		var method = 'GET';
		var url = hostURL + '/cooks/'; 
		
		restClient.query(url, method).then(function(response) {
			if(response.status == 200){
				var list = response.data.cooks;
				
				// list.url
				// list. name
				
					
			}
			if(response.status == 400){
				$scope.errorText  = response.data.errorResponse.message;
			}
		});
	} // getAllCooks

	function addCook(name, email){
	
		var method = 'POST';
		var url = hostURL + '/cooks/'; 
		
		var jsonData = {
				"name" : name,
				"email" : email
			};
			
		jsonData = JSON.stringify(jsonData);
		
		restClient.query(url, method, jsonData).then(function(response) {
			if(response.status == 201){
				
				// OK
					
			}
			if(response.status == 303){
				$scope.errorText  = response.data.errorResponse.message;
			}
		});
	} // addCook
	
	function getCook(id){
	
		var method = 'GET';
		var url = hostURL + '/cooks/'+id; 
		restClient.query(url, method).then(function(response) {
			if(response.status == 200){
				
					var cook = response.data.name;
					//
			}
			if(response.status == 400){
				$scope.errorText  = response.data.errorResponse.message;
			}
		});
	} // getCook
}



function FoodController($scope, $location, restClient){

	

	function addFood(name, email){
	
		var method = 'POST';
		var url = hostURL + '/cooks/?cookToken=' + cookToken; 
		
		var jsonData = {
				"name" : name,
			};
			
		jsonData = JSON.stringify(jsonData);
		
		restClient.query(url, method, jsonData).then(function(response) {
			if(response.status == 201){
				
				// OK
					
			}
			if(response.status == 303 || response.status == 401 ){
				$scope.errorText  = response.data.errorResponse.message;
			}
		});
	} // addCook
	
	function getFood(id){
	
		var method = 'GET';
		var url = hostURL + '/foods/'+id; 
		restClient.query(url, method).then(function(response) {
			if(response.status == 200){
				
					var cook = response.data.name;
					//
			}
			if(response.status == 400){
				$scope.errorText  = response.data.errorResponse.message;
			}
		});
	} // getFood
	
	
	function getAllRecipesForFood(id){
	
		var method = 'GET';
		var url = hostURL + '/foods/' + id + '/recipes' ; 
		
		restClient.query(url, method).then(function(response) {
			if(response.status == 200){
			
			}
			if(response.status == 400){
				$scope.errorText  = response.data.errorResponse.message;
			}
		});
	} // getAllRecipes
	
	
	function searchFood(query){
	
		var method = 'GET';
		var url = hostURL + '/foods/autocomplete/' + query ; 
		
		restClient.query(url, method).then(function(response) {
			if(response.status == 200){
				var list = response.data;
				
			}
			if(response.status == 400){
				$scope.errorText  = response.data.errorResponse.message;
			}
		});
	} // getAllFood
}




function IngredientController($scope, $location, restClient){


	function getIngredient(id){
	
		var method = 'GET';
		var url = hostURL + '/ingredients/' + id; 
		
		restClient.query(url, method).then(function(response) {
			if(response.status == 200){
				
				var ingredient = response.data;
				
				// ingredient.food
				// ingredient.amount
				// ingredient.unit
				// ingredient.recipe_url
				
			}
			if(response.status == 400){
				$scope.errorText  = response.data.errorResponse.message;
			}
		});
	} // getIngredient

	function updateIngredient( amount, unit){
	
		var method = 'PUT';
		var url = hostURL + '/ingredients/' + id + "/?cookToken="+ cookToken; 
	
		var jsonData = {
					"amount" : amount,
					"unit" : unit
		};
		
		jsonData = JSON.stringify(jsonData);
			
		restClient.query(url, method, jsonData).then(function(response) {

			if(response.status == 200 ){
				
					 // TODO 
					 
					 
					//$location.url('/main');
			}
					
			if(response.status == 401 ){
				$scope.errorText = response.data.errorResponse.message;
			}
		});
	} // updateIngredient
	
	function deleteIngredient(){
	
		var method = 'DELETE';
		var url = hostURL + '/ingredients/' + id + "/?cookToken="+ cookToken; 
		restClient.query(url, method, jsonData).then(function(response) {

			if(response.status == 200 ){
				
					 // TODO 
					 
					 
					//$location.url('/main');
			}
					
			if(response.status == 401 ){
				$scope.errorText = response.data.errorResponse.message;
			}
		});
	} // deleteIngredient
}

var recipeDetailString = "";

function RecipeController($scope, $location, restClient, foodFactory, recipeFactory){

	$scope.foodList = [];

    getAllFood();
    getRecipes();
    
	$scope.queryRecipies = function(){
		var foodList =  $("#taglist").val();
		if(foodList.length > 0 ){
			var splittedList = foodList.split(",");
			var foodQueryString = "";
			
			for(var i=0;i<splittedList.length;i++){
				foodQueryString += "&food="+splittedList[i];
			}
			
			searchRecipes(foodQueryString);
		} else{
			getRecipes();
		}
	};
	
	
	function getRecipes(){
	
		var method = 'GET';
		var url = hostURL + 'recipes/'; 
		
		restClient.query(url, method).then(function(response) {
			if(response.status == 200){
				$scope.recipies = response.data.recipes;
				recipeFactory.setList($scope.recipies);
			}
			if(response.status == 400){
				$scope.errorText  = response.data.errorResponse.message;
			}
		});
	} // getIngredients

	
	function searchRecipes( foods ){
	
		var method = 'GET';
		var url = hostURL + 'recipes/search/?'+foods; 
		
		restClient.query(url, method).then(function(response) {
			if(response.status == 200){
				
				$scope.ingredients = response.data;
				
			}
			if(response.status == 400){
				$scope.errorText  = response.data.errorResponse.message;
			}
		});
	} // getAllIngredients
	
	
	$scope.deleteRecipie = function(query){
	
		var method = 'DELETE';
		var url = hostURL + 'recipes/' + query; 
		restClient.query(url, method).then(function(response) {

			if(response.status == 200 ){

				console.log(response);
			}
					
			if(response.status != 200){
				$scope.errorText = response.data.errorResponse.message;
			}
		});
	} // deleteIngredient

	function getIngredientsByRecipe(){
		var method = 'GET';
		var url = hostURL + 'recipes/'+ id+'/ingredients/'; 
		
		restClient.query(url, method).then(function(response) {
			if(response.status == 200){
				
				var ingredient = response.data;
			
			}
			if(response.status == 400){
				$scope.errorText  = response.data.errorResponse.message;
			}
		});
	} // getIngredientsByRecipe
	
	function addIngredientsToRecipe( foodID, amount, unit){
		var method = 'POST';
		var url = hostURL + 'recipes/'+ id+'/ingredients/'; 
		
		var jsonData = {
			"foodID" : foodID,
			"amount": amount,
			"unit": unit
		};
			
		jsonData = JSON.stringify(jsonData);
		
		restClient.query(url, method, jsonData).then(function(response) {
			if(response.status == 200){
				
				var ingredient = response.data;
			
			}
			if(response.status == 400){
				$scope.errorText  = response.data.errorResponse.message;
			}
		});
	
	}
    

    function getAllFood(){
	
		var method = 'GET';
		var url = hostURL + '/foods/'; 
		
		restClient.query(url, method).then(function(response) {
			if(response.status == 200){
				var foods = response.data.foods;
                for(var i=0;i<foods.length;i++){
                     $scope.foodList.push(foods[i].name);
					 foodList =  $scope.foodList;
                }  
				
				foodFactory.setList(foodList);
			}
			if(response.status == 400){
				$scope.errorText  = response.data.errorResponse.message;
			}
		});
	} // getAllFood
	
	
	
	$scope.openRecipieDetails = function(url){
		console.log(url);
		recipeDetailString = url;
		$location.path("/recipie");
	};
	
}


function RecipeDetailController($scope, $location, restClient, foodFactory){

	$scope.editable = false;

	$scope.title = "";
	
	$scope.foodList = foodFactory.getList();

	if(recipeDetailString.length>0){
		getRecipe(recipeDetailString);
		$scope.editable = true;
	}
	
	function getRecipe(id){
	
		var method = 'GET';
		var url = hostURL + 'recipes/' + id; 
		
		restClient.query(url, method).then(function(response) {
			if(response.status == 200){
				$scope.recipieDetail = response.data;
				$scope.title = 	$scope.recipieDetail.name;
			}
			if(response.status == 400){
				$scope.errorText  = response.data.errorResponse.message;
			}
		});
	} // getIngredient

	
	function addRecipe( name){
	
		var method = 'POST';
		var url = hostURL + 'recipes/?cookToken=' + cookToken; 
		
		var jsonData = {
				"name" : name,
			};
			
		jsonData = JSON.stringify(jsonData);
		
		restClient.query(url, method, jsonData).then(function(response) {
			if(response.status == 201){
				
				// OK
					
			}
			if(response.status == 303 || response.status == 401 ){
				$scope.errorText  = response.data.errorResponse.message;
			}
		});
	} // addRecipe
	
	
	function updateRecipe(recipe){
		var method = 'PUT';
		var url = hostURL + 'recipes/'+ recipe.recipe_url +'/=cookToken='+cookToken; 
		
		var jsonData = {
				"name" : recipe.name,
				"instructions": recipe.instructions
			};
			
		jsonData = JSON.stringify(jsonData);
		
		restClient.query(url, method, jsonData).then(function(response) {
			if(response.status == 200){
				
				console.log(response.data);
			
			}
			if(response.status == 400){
				$scope.errorText  = response.data.errorResponse.message;
			}
		});
	} // updateRecipe
	
	$scope.saveRecipe = function(){
		
		//edit
		if(recipeDetailString.length>0){
			updateRecipe($scope.recipieDetail)
		}else{
		// new
			addRecipe( $scope.recipieDetail.name);
		}
	}
	
	/* UI HELPER*/
	$scope.addFoodtoDetailList = function(){

		if( $scope.recipeFood == undefined || $scope.recipeFoodAmount == undefined){
			$scope.errorTextFood = "Please enter Food and Amount";
				console.log("ja");
		}else{
			console.log("else" + $scope.recipeFood  + " "+ $scope.recipeFoodAmount);
		
			var trHtml = "<tr><td>"+ $scope.recipeFood + "</td><td> " + $scope.recipeFoodAmount + "</td> <td><a fast-click=removeRecipeListItem()>remove</a></td></tr>";
			$("tbody").append(trHtml);
			
		}
	};
}






