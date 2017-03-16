angular.module('starter.controllers', [])


  .controller('mainController',function($scope,$http){
    //$scope.data = {};
    $scope.init=function () {


        $scope.contents = null;
      var r =$scope.search;
      $http.get('https://kgsearch.googleapis.com/v1/entities:search?query='+encodeURIComponent(r)+'&key=AIzaSyDsXkEfKo5KxCruUXsfV0XACCRAOMKJ8kI&limit=1&indent=True')
        .success(function (data) {


            //$scope.contents = data;



          $scope.contents="Lollipops are available in a number of colors and flavors, particularly fruit flavors. With numerous companies producing lollipops, the candy now comes in dozens of flavors and many different shapes. They range from small ones which can be bought by the hundred and are often given away for free at banks, barbershops, and other locations, to very large ones made out of candy canes twisted into a circle. ";
          var msg = new SpeechSynthesisUtterance("Lollipops are available in a number of colors and flavors, particularly fruit flavors. With numerous companies producing lollipops, the candy now comes in dozens of flavors and many different shapes. They range from small ones which can be bought by the hundred and are often given away for free at banks, barbershops, and other locations, to very large ones made out of candy canes twisted into a circle.");
          window.speechSynthesis.speak(msg);

         /* for(var i = 1; i < 5; i++) {
            var txt = $('#itemListElement').val($scope.data[i].itemListElement);
            var msg = new SpeechSynthesisUtterance("hello");
            window.speechSynthesis.speak(msg);
          }*/

        })
        .error(function (data, status, error, config) {
          $scope.contents = [{heading: "Error", description: "Could not load json   data"}];
          var msg = new SpeechSynthesisUtterance("wrong description");
          window.speechSynthesis.speak(msg);
        });
    }
    //$scope.contents = [{heading:"Content heading", description:"The actual content"}];
    //Just a placeholder. All web content will be in this format
    $scope.init2=function ()
    {
      window.speechSynthesis.cancel();
      window.location.reload();
    }
  })
  .controller('myCtrl', function($scope, $cordovaGeolocation){

    $scope.toggle = function(){

      var posOption = {timeout: 10000,enableHighAccuracy: true};
      $cordovaGeolocation
        .getCurrentPosition(posOption)
        .then(function (position)  {

            $scope.lat = position.coords.latitude;
            $scope.long = position.coords.longitude;


          }, function(err){
            //error
          }

        );

    }



  })

  .controller('LoginCtrl', function($scope, LoginService, $ionicPopup, $state) {
    $scope.data = {};

    $scope.login = function() {
      LoginService.loginUser($scope.data.username, $scope.data.password).success(function(data) {
        $state.go('test5');
      }).error(function(data) {
        var alertPopup = $ionicPopup.alert({
          title: 'Login failed!',
          template: 'Please enter valid credentials!'
        });
      });
    }
  });
