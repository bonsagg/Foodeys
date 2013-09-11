'use strict';


// Declare app level module which depends on filters, and services
angular.module('foodeys', ['foodeys.filters', 'foodeys.services', 'foodeys.directives', 'foodeys.controllers']).
  config(['$routeProvider', function($routeProvider) {

		$routeProvider.when('/', {templateUrl: 'app/partials/recipes.html', controller: 'RecipeController'});
		$routeProvider.when('/login', {templateUrl: 'app/partials/login.html', controller: 'LoginController'});
		$routeProvider.when('/register', {templateUrl: 'app/partials/register.html', controller: 'RegisterController'});
		
		$routeProvider.when('/recipe/:recipeID', {templateUrl: 'app/partials/recipeDetail.html', controller: 'RecipeDetailController'});
		$routeProvider.when('/editRecipe/:recipeID', {templateUrl: 'app/partials/recipeDetailInput.html', controller: 'EditRecipeDetailController'});
		$routeProvider.when('/newRecipe', {templateUrl: 'app/partials/recipeDetailInput.html', controller: 'NewRecipeController'});
		
		$routeProvider.when('/ingredient', {templateUrl: 'app/partials/ingredientsDetail.html', controller: 'RecipeController'});
		$routeProvider.when('/food', {templateUrl: 'app/partials/foodDetail.html', controller: 'FoodController'});
		
    }])
  .config(['$httpProvider', function($httpProvider) {
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
  }]);
  
  
 