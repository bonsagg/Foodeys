'use strict';

/* Directives */

angular.module('foodeys.directives', []).
  directive('appVersion', ['version', function(version) {
    return function(scope, elm, attrs) {
      elm.text(version);
    };
  }])
  
  
  
    .directive('fastClick', function() {
        return function(scope, elem, attr) {
           elem.fastClick(function (e) {
                scope.$apply(attr.fastClick);
           });
        };
    })
    
	.directive('taglist', function() {
		return function($scope, element, attrs) {
			console.log("init taglist ------------");
		
				element.tagsInput({
					'defaultText':'',
					'interactive':true,
				});
		};
		
	})
    
  .directive('tagautoComplete', function($timeout) {
    return function(scope, iElement, iAttrs) {
                
            iElement.autocomplete({
                source: scope.foodList,
                select: function() {
                    $timeout(function() {
                      //iElement.trigger('input');
					  
					  var tagID = $(".tagClass").attr("id");
					  console.log(tagID);
                      $("#"+tagID).addTag(iElement.val());
					
                       iElement.val("");
                    }, 0);
                }
            });
    };
})


  .directive('autoComplete', function($timeout) {
    return function(scope, iElement, iAttrs) {
    
            iElement.autocomplete({
                source: scope.foodList,
                select: function() {
                    $timeout(function() {
                      iElement.trigger('input');
                    }, 0);
                }
            });
    };
})


.directive('ngBlur', function() {
  return function( scope, elem, attrs ) {
    elem.bind('blur', function() {
      scope.$apply(attrs.ngBlur);
    });
  };
});