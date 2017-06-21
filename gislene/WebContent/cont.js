var map;
var eventHandler = null;
var infowindow = null;
var figures = [];
var buttonPressed = null;
var request = new XMLHttpRequest();

function initMap() {
  //Enabling new cartography and themes
   google.maps.visualRefresh = true;
                    //Setting starting options of map
   var mapOptions = {
       center: new google.maps.LatLng(39.9078, 32.8252),
       zoom: 10,
       mapTypeId: google.maps.MapTypeId.ROADMAP
    };
 //Getting map DOM element
  var mapElement = document.getElementById('mapDiv');
  //Creating a map with DOM element which is just obtained
       map = new google.maps.Map(mapElement, mapOptions);  
}



function processRequest(){
	if (request.readyState==4&&request.status==200){
		document.getElementById('topicName').value = request.responseText;
	}
}

function sendRequest(){
	 request.open("Get","/Gislene/InterfaceIntegration?topicName="+document.getElementById("topicName").value);
	 request.onreadystatechange = processRequest;
	 request.send(null);
}

function habilitaFigura(fig,id){


if(buttonPressed==null) {
  buttonPressed = id;
  $(buttonPressed).addClass('button2')
}
else if(buttonPressed==id){
  $(buttonPressed).removeClass('button2')
  return;
}
else{
  $(buttonPressed).removeClass('button2')
  $(id).addClass('button2')
  buttonPressed = id;
}


google.maps.event.clearInstanceListeners(map);
if (fig=="deactivate") return




google.maps.event.addListener(map, 'click', function(e) {
 if (infowindow != null)
  infowindow.close();

  infowindow = new google.maps.InfoWindow({
  content: '<b>Mouse Coordinates : </b><br><b>Latitude : </b>' + e.latLng.lat() + '<br><b>Longitude: </b>' + e.latLng.lng(),
  position: e.latLng
});
 infowindow.open(map);
 if(fig=="circle"){

  addCircle(e);
}
 if(fig=="rect") {
  addRectangle(e);
}
 figures.push(
 {
   lat:e.latLng.lat(),
   lng:e.latLng.lng(),
   tipo:fig
 })

});


}

function addRectangle(e) {

  var bound1 = new google.maps.LatLng(e.latLng.lat()-0.5,e.latLng.lng()-0.5);
  var bound2 = new google.maps.LatLng(e.latLng.lat()+0.5,e.latLng.lng()+0.5);
  var bounds = new google.maps.LatLngBounds(bound1,bound2);
  var rectOptions = {
  fillColor: '#A19E98',
  fillOpacity: 0.45,
  strokeColor: '#FF0000',
  strokeOpacity: 0.0,
  strokeWeight: 1,
  map: map,
  bounds: bounds
};
  var rectangle = new google.maps.Rectangle(rectOptions);
} 

  



