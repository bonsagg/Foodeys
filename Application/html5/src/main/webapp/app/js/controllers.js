'use strict';

/* Controllers */
angular.module('foodeys.controllers', []);
;
  

var cookToken = "";

function LoginController($scope, $location, $timeout,restClient){

	$scope.errorText = "";

	// DATA MOCKUP
	$scope.loginName = "derkuba@kuba.cu";
	$scope.pw = "kuba";

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

			cook.login(jsonData).then(function(data) {
				cookToken = data;
				$location.url('/main');
			});

		}
	}
}


function RegisterController($scope, $location, restClient){

	$scope.errorText = "";

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
			
			cook.register(jsonData).then(function(data) {
				cookToken = data;
				$location.url('/login');
			});	
		}
	}
}
  

function CookController($scope, $location, cook){
	
	function getAllCooks(){
	
		cook.getAll().then(function(data) {
			// TODO
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
		cook.addCook(jsonData).then(function(data) {
			// TODO
		});
		
	} // addCook
	
	function getCook(id){
		cook.get(id).then(function(data) {
			
		});
		
	} // getCook
}



function FoodController($scope, $location, food){

	cookToken = "asdasd";

	getAllFood();

	function getFood(id){
		
		food.get(id).then(function(data) {
			
		});
	
	} // getFood
	
	function getAllFood(){
		
		food.getAll().then(function(data) {
			$scope.foods = data;
		});
	
	} // getAllFood
	

	function addFood(name, email){
	
		var jsonData = {
				"name" : name,
			};
			
		jsonData = JSON.stringify(jsonData);
			
		food.addFood(jsonData).then(function(data) {
			
		});
		
	} // addFood
	

	
	
	function getAllRecipesForFood(id){
	
		food.getAllFoodForRecipe(id).then(function(data) {
			
		});

	} // getAllRecipesForFood
	
	
	function searchFood(query){
	
		food.search(query).then(function(data) {
			
		});
		
	} // searchFood
	
	$scope.deleteFood = function(food){
	
		// FEHLT deleteRecipe(recipe);
		var index=$scope.foods.indexOf(food);
		$scope.foods.splice(index,1); 
	}
	
	$scope.addFoodtoDetailList = function(food){
		console.log(food);
		if( food == undefined){
			$scope.errorTextFood = "Please enter Food and Amount";
			return;
		}else{
			console.log("else" + food.name);
		
			var jsonData = {
				"name" : food.name
			};
			
			jsonData = JSON.stringify(jsonData);
			
			var trHtml = "<tr><td>"+ food.name + "</td><td><a fast-click=removeRecipeListItem()>remove</a></td></tr>";
			$("tbody").append(trHtml);
			addFood(cookToken, jsonData );
			
			food.name = "";
		}
	};
}


function IngredientController($scope, $location, ingredient){

	function getIngredient(id){
	
		ingredient.get(id).then(function(data) {
	
		});
		
	} // getIngredient

	function updateIngredient( amount, unit){
	
		var jsonData = {
			"amount" : amount,
			"unit" : unit
		};
		
		jsonData = JSON.stringify(jsonData);
		
		ingredient.update(cookToken, jsonData).then(function(data) {
			// TODO
		});
		
	} // updateIngredient
	
	function deleteIngredient(id){
		ingredient.del(id, cookToken).then(function(data) {
			// TODO
		});
	} // deleteIngredient

}

function RecipeController($scope, $location, restClient, recipe, food, foodFactory){

	$scope.foodList = [];

    getAllFood();
    getRecipes();
    

	function getRecipes(){
	
		recipe.getAll().then(function(data) {
				console.log(data);
				$scope.recipes = data;
		});
		
	} // getIngredients

	
	function searchRecipes( foods ){
		
		recipe.search(foods).then(function(data) {
	
		});
		
	} // getAllIngredients
	

	function deleteRecipe(recipe){
		recipe.del(recipe).then(function(data) {
		});
	} // deleteIngredient

	
    function getAllFood(){
	
		$scope.foodList = [];
		
		food.getAll().then(function(foods) {
		
			for(var i=0;i<foods.length;i++){
				$scope.foodList.push(foods[i].name);
			}  
		});
		foodFactory.setList($scope.foodList);
	} // getAllFood
	
	$scope.deleteRecipe = function(recipe){
	
		console.log($scope.recipes);
		deleteRecipe(recipe);
		var index=$scope.recipes.indexOf(recipe);
		$scope.recipes.splice(index,1); 
	}
	
	$scope.queryRecipes = function(){
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
}

function RecipeDetailController($scope, $location, $routeParams, recipe, foodFactory){

	$scope.hasRight = false;

	$scope.title = "";
	
	$scope.foodList = foodFactory.getList();
	
	$scope.recipeID = $routeParams.recipeID;
	
	getRecipe($routeParams.recipeID);
	
	if(cookToken ==""){
		$scope.hasRight = true;
	}
		
	function getRecipe(id){
		
		$scope.getrecipe = recipe.get(id);

		 $scope.$watch('getrecipe', function(asyncData) {
			if(angular.isDefined(asyncData)) {
				$scope.recipeDetail  = asyncData;
			
				$scope.title = asyncData.name;
				getIngredientsByRecipe(id);
			}
		});
		
	} // getIngredient

	
	function getIngredientsByRecipe(id){
		
		$scope.getingredient =recipe.getIngredientsByRecipe(id);
		 $scope.$watch('getingredient', function(asyncData) {
			if(angular.isDefined(asyncData)) {
				$scope.ingredients  = asyncData;
			}
		});
	} // getIngredientsByRecipe
	
	$scope.openRecipeDetailsEdit = function(url){
		$location.path("/editRecipe/"+url);
	};
	
}

function EditRecipeDetailController($scope, $location,  $routeParams, recipe, food, foodFactory){
	
	$scope.foodList = foodFactory.getList();
	if($scope.foodList.length == 0){
		getAllFood();
	}
	
	$scope.recipeID = $routeParams.recipeID;
	
	getRecipe($routeParams.recipeID);
	
	$scope.editable = true;
	
	function getRecipe(id){
		
		
		recipe.get(id).then(function(data) {
				$scope.recipeDetail = data;
				$scope.title = data.name
		});

		getIngredientsByRecipe(id);
		
	} // getIngredient

	
	function getIngredientsByRecipe(id){
		
		recipe.getIngredientsByRecipe(id).then(function(data) {
				$scope.ingredients = data;
		});
		
	} // getIngredientsByRecipe
	
	function updateRecipe(recipeItem){
	
		var jsonData = {
				"name" : recipeItem.name,
				"instructions": recipeItem.instructions
			};
			
		jsonData = JSON.stringify(jsonData);
		recipe.updateRecipe(cookToken, jsonData, recipeItem.recipe_url).then(function(data) {
				$scope.recipes = data;
		});
		
	} // updateRecipe
	
	function addIngredientsToRecipe(recipeId, food, amount, unit){

		var jsonData = {
			"foodID" : food,
			"amount": amount,
			"unit": unit
		};
			
		jsonData = JSON.stringify(jsonData);
		recipe.addIngredientsToRecipe(recipeId, jsonData).then(function(data) {
				
		});
		
	}
	
	/* UI HELPER*/
	$scope.deleteIngredient = function(ingre){
		//ingre.ingredient_url
		 // TODO 
		var index=$scope.ingredients.indexOf(ingre);
		$scope.ingredients.splice(index,1); 
	};
		
	$scope.saveRecipe = function(){
		
		//edit
		if($routeParams != undefined ){
			updateRecipe($scope.recipeDetail)
		}else{
		// new
			addRecipe( $scope.recipeDetail.name);
		}
	}
		
	$scope.addFoodtoDetailList = function(ingr){
		
		if( ingr.recipeFood == undefined || ingr.recipeFoodAmount == undefined || ingr.recipeFoodUnit == undefined){
			$scope.errorTextFood = "Please enter Food and Amount";
		}else{
			console.log("else" + ingr.recipeFood  + " "+ ingr.recipeFoodAmount);
		
			var trHtml = "<tr><td>"+ ingr.recipeFood + "</td><td> " + ingr.recipeFoodAmount + "</td><td> " + ingr.recipeFoodUnit + "</td> <td><a fast-click=removeRecipeListItem()>remove</a></td></tr>";
			$("tbody").append(trHtml);
			addIngredientsToRecipe($scope.recipeID, ingr.recipeFoodAmount,  ingr.recipeFood,  ingr.recipeFoodUnit );
			
			ingr.recipeFood = "";
			ingr.recipeFoodAmount = "";
			ingr.recipeFoodUnit = "";
		}
	};
	
	function getAllFood(){
	
		$scope.foodList = [];
		
		food.getAll().then(function(foods) {
		
			for(var i=0;i<foods.length;i++){
				$scope.foodList.push(foods[i].name);
			}  
		});
		foodFactory.setList($scope.foodList);
	} // getAllFood
	
}

function NewRecipeController($scope, $location,  recipe, foodFactory){

	$scope.hasRight = false;
	var cookToken = "TEST";

	$scope.title = "New Recipe";
	$scope.foodList = foodFactory.getList();
	$scope.editable = false;
	
	
	$scope.saveRecipeName = function(){
		
		if($scope.editable == false){
			saveRecipe($scope.recipeDetail.name);
			$scope.editable = true;
		}else{
			updateRecipe($scope.recipeDetail);
		}
	}
	
	$scope.saveRecipeInstructions = function(){
		updateRecipe($scope.recipeDetail);
	}
	
	function updateRecipe(recipeItem){

		var jsonData = {
				"name" : recipeItem.name,
				"instructions": recipeItem.instructions
			};
			
		jsonData = JSON.stringify(jsonData);
		
		recipe.updateRecipe(cookToken, jsonData, recipeItem.recipe_url).then(function(data) {
				$scope.recipes = data;
		});
		
	} // updateRecipe
	
	function saveRecipe( name){
	
		var jsonData = {
				"name" : name,
			};
			
		jsonData = JSON.stringify(jsonData);
		recipe.addRecipe(cookToken, jsonData).then(function(data) {
				$scope.recipes = data;
		});
		
	} // addRecipe
	
}




