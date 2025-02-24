<%-- 
    Document   : map
    Created on : Jan 4, 2015, 11:36:28 PM
    Author     : kiwi
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:indexpage>
    <script type="text/javascript" src="${Constants.URL}js/powerhour.printmaps.js"></script>
	<style>
                #googleMap .gm-style .gmnoprint .gmnoprint div img {
                    content:url("${Constants.URL}img/biggerSmallerMap.png") !important;
                    width: 20px !important;
                    height: 40px !important;
                    left: 0 !important;
                    top: 0 !important;
                }
                #googleMap .gm-style .gmnoprint .gmnoprint{
                    left: 50px !important;
                    top: 45px !important;
                }
                .addToRouteButton{
                    margin: 0 auto;
                    text-align: right;
                    margin-top: 25px;
                    position: absolute;
                    bottom: 25px;
                    right: 20px;
                    cursor: pointer;
                }
                #sliderDiv ,  #mapRouteButton , #mapControls, #pushRightConrainer {
                    position:absolute;
                    z-index:5;
                }
	</style>
    <script>
        var center;
        /*
        var icons = ['church','hotels','info','militaries',
            'museums','restaurants','ruins','swimming','tasting_hall',
            'yacht'];
        */
        var markers = [];
        var map;
        var markerCluster;
	var count = 1;
        var mapProp;
        var zoomMap;
        var waypts = [];
        var routeMarkers = [];
        var mapStyles = [{"featureType":"landscape","elementType":"labels","stylers":[{"visibility":"off"}]},
            {"featureType":"transit","elementType":"labels","stylers":[{"visibility":"off"}]},
            {"featureType":"poi","elementType":"labels","stylers":[{"visibility":"off"}]},
            {"featureType":"water","elementType":"labels","stylers":[{"visibility":"off"}]},
            {"featureType":"road","elementType":"labels.icon","stylers":[{"visibility":"off"}]},{"stylers":[{"hue":"#00aaff"},{"saturation":-100},{"gamma":2.15},{"lightness":12}]},
            {"featureType":"road","elementType":"labels.text.fill","stylers":[{"visibility":"on"},{"lightness":24}]},
            {"featureType":"road","elementType":"geometry","stylers":[{"lightness":57}]}];
        var standartStyle = [];
        var styleTrigger = true;
        var summary = '';
        
	function initialize() {
            var styledMap = new google.maps.StyledMapType(mapStyles,
                {name: "Styled Map"});
                
            mapProp = {
                center: center,
                zoom: zoomMap,
                mapTypeControl: false,
                panControl: false,
                zoomControl: false,
                zoomControlOptions: {
                    style: google.maps.ZoomControlStyle.SMALL
                },
                streetViewControl: false,
                mapTypeControlOptions: {
                    mapTypeIds: [google.maps.MapTypeId.ROADMAP, 'map_style']
                }
            };
            map=new google.maps.Map(document.getElementById('googleMap'),
                mapProp);
            map.mapTypes.set('map_style', styledMap);
            map.setMapTypeId('map_style');
		
            var infowindow =  new google.maps.InfoWindow({
		content: ""
            });
            var filterNames = [];
            <c:forEach items="${markers}" var="marker">
                var markerIcons = "${marker.filters}".split(",");
                //KIT NE PASHE ROZKOMENTUTU
                //if(icons.indexOf(markerIcons[0])==-1){
                //markerIcons.splice(0,0,'info');
                //}
                var pinIcon = new google.maps.MarkerImage(
                    "${Constants.URL}img/markers/"+markerIcons[0]+".png",
                    null, 
                    null, 
                    null, 
                    new google.maps.Size(42, 58)
                ); 
                var photos = "${marker.image}".split(",");
                var images = "";
                for(var n = 0; n < photos.length; n++){
                    images = images + '<div class="markerImage"><img src="${Constants.URL}'+photos[n]+'" style="width: 100%;"></div>';
                    if(n==0){break;}
                };
                var x = "${marker.x}".substring(0, 7);
                var y = "${marker.y}".substring(0, 7);
		var myLatlng = new google.maps.LatLng(${marker.x}, ${marker.y});
		var marker = new google.maps.Marker({
                    id: "${marker.id}",
                    position: myLatlng,
                    map: map,
                    icon: pinIcon,
                    props: markerIcons,
                    country: "${marker.public_country}",
                    title: "${marker.title}"
		});
                var info = '<div id="descriptionHideButton"><img src="${Constants.URL}img/hideDescrButton.png" onclick="hideDescr()"></div>' +
                    images +
                    '<div class="descrImageUnderline"></div>' +
                    '<div class="markerName">${marker.title}</div>' +
                    '<div class="infoBlock"><div class="markerDescr">'+'${marker.textEN}'.substr(0,300)+'...</div>'+
                    '<div onclick="addMarkerToRoute(\'${marker.title}\',\'${marker.x}\',\'${marker.y}\')" class="addToRouteButton"><a class="btn btn-theme detailsButton">ADD TO ROUTE</a></div>'+
                    '<div class="detailsButtonContainer"><a class="btn btn-theme detailsButton" href="${Constants.URL}${lan}/map/markers/${marker.id}">DETAILS</a></div></div>';	
		markers.push(marker);
                filterNames.push(markerIcons[0]);
                bindDescription(marker,map, info);
                //bindInfoWindow(marker, map, infowindow, info);
            </c:forEach>
            var clusterStyles = [
                { 
                    anchor:[10,0],
                    textColor: "white",
                    textSize: 15,
                    height: 68, 
                    width: 42, 
                    url: '${Constants.URL}img/group_marker.png' 
                }
            ];
            var mcOptions = {
                styles: clusterStyles
            };
            filterNames = filterNames.unique();
            var filterHtml = "";
            for(var i = 0; i< filterNames.length; i++){
                filterHtml = filterHtml+'<div id="filter"><label><input type="checkbox" name="'+filterNames[i]+'" id="'+filterNames[i]+'" onclick="Markers(\''+filterNames[i]+'\')" checked="checked" />'+filterNames[i]+'</div>' ;
            }
            //var filterDiv = document.getElementById('filterDiv');
            //var div = document.createElement('div');
            //div.innerHTML = filterHtml;
            //div.innerHTML = '<div id="filter" style="margin-top:35px;">'+<c:forEach items="${filters}" var="filter">'<label><input type="checkbox" name="${filter}" id="${filter}" onclick="Markers(\'${filter}\')" checked="checked" />${filter}<br />' +</c:forEach> '</div>' 
            //filterDiv.appendChild(div);
            //$("#legend").html(filterHtml);
          
            var markerDescription = document.getElementById('markerDescription');
            var div = document.createElement('div');
            markerDescription.appendChild(div);
            map.controls[google.maps.ControlPosition.LEFT_BOTTOM].push(
		document.getElementById('markerDescription'));
		
            markerCluster = new MarkerClusterer(map, markers,mcOptions);
            map.controls[google.maps.ControlPosition.RIGHT_TOP].push(
                document.getElementById('sliderDiv'));
            map.controls[google.maps.ControlPosition.RIGHT_TOP].push(
                document.getElementById('mapControls'));
            map.controls[google.maps.ControlPosition.RIGHT_TOP].push(
                document.getElementById('mapRouteButton'));
            map.controls[google.maps.ControlPosition.RIGHT_TOP].push(
                document.getElementById('pushRightConrainer'));
            
                var hideButton = document.getElementById('filterHide');
                /*google.maps.event.addDomListener(hideButton, 'click', function() {
                    $("#legend").animate({width: 'toggle'});
                    $("#filter").animate({width: 'toggle'});
                    if (count == 0){
                        $("#filterHide").html('<img src="${Constants.URL}img/rightArrow.png" style="width: 100%;">');
                        count = 1;
                        getElementById('#googleMap').setAttribute("style","width:0%");
                        googleMap.style.width = "0%";
                        $('#sliderDiv').css('height', 0);
                    }
                    else{
                        $("#filterHide").html('<img src="${Constants.URL}img/leftArrow.png" style="width: 100%;">');
                        count = 0;
                    }
                });*/
            
            if('${country}'!=''){
                for (var i=0;i<markers.length;i++) {
                    if((markers[i].country!='${country}')&&('${country}'!='allMap')){
                        markers[i].setMap(null);
                        markerCluster.removeMarker(markers[i]);
                    }
                }
            }
            
            if('${id}'!=''){
                for (var i=0;i<markers.length;i++){
                    if('${id}'==markers[i].id){
                        map.setCenter(markers[i].getPosition());
                        map.setZoom(12);
                    }else{
                        markers[i].setMap(null);
                        markerCluster.removeMarker(markers[i]);
                    }
                }
            }
	}
	google.maps.event.addDomListener(window, 'load', initialize);
        
        
	function bindInfoWindow(marker, map, infowindow, strDescription) {
		google.maps.event.addListener(marker, 'click', function() {
			infowindow.setContent(strDescription);
			infowindow.open(map, marker);
		});
	}
        
        function bindDescription(marker, map, info) {
		google.maps.event.addListener(marker, 'click', function() {
                    document.getElementById("markerDescription").innerHTML = info;
                    $("#markerDescription").show("slow");
		});
	}
        
        function hideFilterSection(id){
            $( "#"+id ).slideToggle( "slow", function() {
            });
            if ($( "#"+id.substring(0,id.length-6)+"Hide").attr('src')=='${Constants.URL}img/hideMinus.png') {
                $( "#"+id.substring(0,id.length-6)+"Hide").attr('src', '${Constants.URL}img/showPlus.png');
            } else {
                $( "#"+id.substring(0,id.length-6)+"Hide").attr('src', '${Constants.URL}img/hideMinus.png');
            }
        }
        function hideDescr(){
            $('#markerDescription').hide("slow");
        }
	function Markers(type){
            console.log(type);
            if(type.length == 1){
		if (document.getElementById(type[0]).checked==false) {
                    for (var i=0;i<markers.length;i++) {
                        if(markers[i].props.indexOf(type[0])!=-1){
                            var show = false;
                            for(var j = 0; j<markers[i].props.length; j++){
                                if(document.getElementById(markers[i].props[j]).checked==true){
                                    show = true;
                                    var pinIcon = new google.maps.MarkerImage(
                                        "${Constants.URL}img/markers/"+markers[i].props[j]+".png",
                                        null, 
                                        null, 
                                        null, 
                                        new google.maps.Size(42,58)
                                    );
                                    markers[i].icon=pinIcon;
                                    markerCluster.removeMarker(markers[i]);
                                    markerCluster.addMarker(markers[i]);
                                    markers[i].setMap(null);
                                    markers[i].setMap(map);
                                    break;
                                }
                            }
                            if(!show){
                                markers[i].setMap(null);
                                markerCluster.removeMarker(markers[i]);
                            }
                        }
                    }
		} else {
                    for (var i=0;i<markers.length;i++) {
                        if((markers[i].props.indexOf(type[0])!=-1)&&(markers[i].map==null)){
                            markers[i].setMap(null);
                            markers[i].setMap(map);
                            markerCluster.removeMarker(markers[i]);
                            markerCluster.addMarker(markers[i]);
                            var pinIcon = new google.maps.MarkerImage(
                                "${Constants.URL}img/markers/"+type[0]+".png",
                                null, 
                                null, 
                                null, 
                                new google.maps.Size(42,58)
                            ); 
                            markers[i].icon=pinIcon;
                        }
                    }
		}
            } else {
                for(var k=1;k<type.length;k++){
                    if(type[0]=='markAll'){
                        if(document.getElementById(type[0]).checked == true){
                            document.getElementById(type[k]).checked = true;
                        }else{
                            document.getElementById(type[k]).checked = false;
                        }
                    }
                    else{
                        if(document.getElementById(type[0]).checked == true){
                            document.getElementById(type[k]).checked = false;
                        }else{
                            document.getElementById(type[k]).checked = true;
                        }
                    }
                }
                for(var k=1;k<type.length;k++){
                    Markers([type[k]]);
                }
            }
	}
        
        Array.prototype.contains = function(v) {
            for(var i = 0; i < this.length; i++) {
                if(this[i] === v) return true;
            }
            return false;
        };
        Array.prototype.unique = function() {
            var arr = [];
            for(var i = 0; i < this.length; i++) {
                if(!arr.contains(this[i])) {
                    arr.push(this[i]);
                }
            }
            return arr; 
        }
    function borderButton(button){
        for (var i=0;i<markers.length;i++) {
            if((markers[i].country==button.substr(1,button.length))||(button=="#allMap")){
                markers[i].setMap(null);
                markerCluster.removeMarker(markers[i]);
                markers[i].setMap(map);
                markerCluster.addMarker(markers[i]);
            }
            else{
                markers[i].setMap(null);
                markerCluster.removeMarker(markers[i]);
            }
        }
        $('#allMap').removeClass( "buttonBorderClassMapPage" );
        $('#Poland').removeClass( "buttonBorderClassMapPage" );
        $('#Hungary').removeClass( "buttonBorderClassMapPage" );
        $('#Romania').removeClass( "buttonBorderClassMapPage" );
        $('#Slovakia').removeClass( "buttonBorderClassMapPage" );
        $('#Ukraine').removeClass( "buttonBorderClassMapPage" );
        $(button).addClass( "buttonBorderClassMapPage" );
    }
        var counter = 0;
        var mapSlider;
        var countries = ['all','poland','hungary','romania','slovakia','ukraine'];
        jQuery(document).ready(function ($) {
            $('#hungaryBorder').hide();
            $('#polandBorder').hide();
            $('#romaniaBorder').hide();
            $('#slovakiaBorder').hide();
            $('#ukraineBorder').hide();
            $("#allMap").addClass( "buttonBorderClass" );
            $(".mapType").hide();
            if('${id}'!=''){
                $('#markAll').click();
            }else{
                $('#tourism_infrastructure_units').click();
            }
        });
        var rightMapContainerCounter = false;
        var filtersContainerCounter = false;
        var mapRouteContainerCounter = false;
        
        function hideMap(){
            if(filtersContainerCounter){hideFilters()}
            if(mapRouteContainerCounter){hideRoute()}
            if(showTypes){chooseMapType()}
            if(rightMapContainerCounter){
                rightMapContainerCounter = false;
                $('#pushRightConrainer').removeClass('pushRightConrainerBig');
                $('#mapControls').removeClass('mapControlsBig');
                $('.indexMapContainer').fadeOut(100);
                $('.indexMapMenu').fadeOut(100);
                $('#mapControlsImage').attr("src","${Constants.URL}img/marker"+countryChooser+".png");
            }
            else{
                rightMapContainerCounter = true;
                $('#pushRightConrainer').addClass('pushRightConrainerBig');
                $('#mapControls').addClass('mapControlsBig');
                $('.indexMapContainer').fadeIn(300);
                $('.indexMapMenu').fadeIn(300);
                $('#mapControlsImage').attr("src","${Constants.URL}img/marker_active_map.png");
                $('#sliderDiv').addClass('sliderDiv');
                $('#sliderDiv').removeClass('sliderDivScroll');
                $('#mapControls').addClass('mapControls');
                $('#mapControls').removeClass('mapControlsScroll');
                $('#pushRightConrainer').addClass('pushRightConrainer');
                $('#pushRightConrainer').removeClass('pushRightConrainerScroll');
            }
        }
        
        function hideRoute(){
            if(filtersContainerCounter){hideFilters()}
            if(rightMapContainerCounter){hideMap()}
            if(showTypes){chooseMapType()}
            if(mapRouteContainerCounter){
                mapRouteContainerCounter = false;
                $('#mapRouteButton').removeClass('pushRightConrainerRoute');
                $('.routeMapContainer').fadeOut(100);
                $('#mapRouteImage').attr("src","${Constants.URL}img/route_icon.png");
            }
            else{
                mapRouteContainerCounter = true;
                $('#mapRouteButton').addClass('pushRightConrainerRoute');
                $('.routeMapContainer').fadeIn(100);
                $('#mapRouteImage').attr("src","${Constants.URL}img/icon_route_active.png");
            }
        }
        
	$( document ).ready(function() {
            $("#markerDescription").animate({width: 'hide'});
            var descriptionDiv = document.getElementById('descriptionHideButton');
            /*google.maps.event.addDomListener(descriptionDiv, 'click', function() {
                $("#markerDescription").animate({width: 'hide'});
            });*/
            $('.indexMapContainer').fadeOut(1);
            $('.routeMapContainer').fadeOut(1);
            $('.indexMapMenu').fadeOut(1);
            $('#filtersContainer').fadeOut(1);
            
            switch('${country}'){
                case "allMap":
                    mapPageMenu();
                    zoomMap = 7;
                    center = new google.maps.LatLng(48.6193605,22.2095977);
                    $('#mapControlsImage').attr('src','${Constants.URL}img/markerallMap.png');
                    break;
                case "Romania":
                    mapPageMenu();
                    zoomMap = 8;
                    center = new google.maps.LatLng(46.07323062540838,24.708251953125);
                    $('#mapControlsImage').attr('src','${Constants.URL}img/markerRomania.png');
                    break;
                case "Poland":
                    mapPageMenu();
                    zoomMap = 8;
                    center = new google.maps.LatLng(50.00067775723633,21.4068603515625);
                    $('#mapControlsImage').attr('src','${Constants.URL}img/markerPoland.png');
                    break;
                case "Ukraine":
                    mapPageMenu();
                    zoomMap = 9;
                    center = new google.maps.LatLng(48.705462895790596,23.895263671875);
                    $('#mapControlsImage').attr('src','${Constants.URL}img/markerUkraine.png');
                    break;
                case "Slovakia":
                    mapPageMenu();
                    zoomMap = 8;
                    center = new google.maps.LatLng(48.828565527993234,19.9346923828125);
                    $('#mapControlsImage').attr('src','${Constants.URL}img/markerSlovakia.png');
                    break;
                case "Hungary":
                    mapPageMenu();
                    zoomMap = 8;
                    center = new google.maps.LatLng(47.21210577562242,19.5611572265625);
                    $('#mapControlsImage').attr('src','${Constants.URL}img/markerHungary.png');
                    break;
                case "":
                    mapPageMenu();
                    center = new google.maps.LatLng(48.6193605,22.2095977);
                    $('#mapControlsImage').attr('src','${Constants.URL}img/markerallMap.png');
                    countryChooser = "allMap";
                    zoomMap = 7;
                    break;
            }
        });
        function hideFilters(){
            if(rightMapContainerCounter){hideMap()}
            if(mapRouteContainerCounter){hideRoute()}
            if(showTypes){chooseMapType()}
            if(filtersContainerCounter){
                filtersContainerCounter = false;
                $('#pushRightConrainer').removeClass('pushRightConrainerFilters');
                $('#filtersContainer').fadeOut(100);
                $('#mainImageRightContaineMap').attr("src","${Constants.URL}img/mapRightContainer.png");
            }  
            else{
                filtersContainerCounter = true;
                $('#filtersContainer').fadeIn(100);
                $('#pushRightConrainer').addClass('pushRightConrainerFilters');
                if(window.innerHeight<830){
                    $('.pushRightConrainerFilters').css('height', window.innerHeight-372);
                }
                $('#mainImageRightContaineMap').attr("src","${Constants.URL}img/filtersImageActive.png");
                $('#sliderDiv').addClass('sliderDiv');
                $('#sliderDiv').removeClass('sliderDivScroll');
                $('#mapControls').addClass('mapControls');
                $('#mapControls').removeClass('mapControlsScroll');
                $('#pushRightConrainer').addClass('pushRightConrainer');
                $('#pushRightConrainer').removeClass('pushRightConrainerScroll');
            }
        }
        
        ( function( $ ) {
        $( document ).ready(function() {
            $('#filtersContainer > ul > li > a').click(function() {
              var checkElement = $(this).next();
                $('#filtersContainer li').removeClass('activeFilter');
                $(this).closest('li').addClass('activeFilter');
              if((checkElement.is('ul')) && (checkElement.is(':visible'))) {
                checkElement.slideUp('normal');
              }
              if((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
                $('#filtersContainer ul ul:visible').slideUp('normal');
                checkElement.slideDown('normal');
              }
              if($(this).closest('li').find('ul').children().length == 0) {
                return true;
              } else {
                return false;	
              }		
            });
            });
        } )( jQuery );
        
        var styleCounter = 0;
        function changeStyle(){
            styleCounter++;
            if(styleCounter==2){
                if(styleTrigger){
                    styleTrigger = false;
                    map.setMapTypeId(google.maps.MapTypeId.TERRAIN);
                }
                else{
                    var styledMap = new google.maps.StyledMapType(mapStyles,
                        {name: "Styled Map"});
                    map.mapTypes.set('map_style', styledMap);
                    map.setMapTypeId('map_style');
                    styleTrigger = true;
                }
                styleCounter=0;
            }
        }
        
        function rotateCaret(number){
            for(var i = 1; i<8; i++){
                if(i!=number){
                    $('#filtersCaret'+i).removeClass('rotatedCaret');
                }
            }
            if($('#filtersCaret'+number).hasClass('rotatedCaret')){
                $('#filtersCaret'+number).removeClass('rotatedCaret');
            }
            else{
                $('#filtersCaret'+number).addClass('rotatedCaret');
            }
        }
        
        function addMarkerToRoute(name, x, y){
            mapRouteContainerCounter = false;
            var latlng = new google.maps.LatLng(x, y);
            var markersList = '<div class="mapRouteText">';
            var add = true;
            var routeMarker = {title: name, coords: latlng,x:x,y:y};
            for(var n = 0; n < routeMarkers.length; n++){
                if(routeMarker.title==routeMarkers[n].title){
                    add = false;
                }
            }
            if(add){
                routeMarkers.push(routeMarker);
            }
            for(var n = 0; n < routeMarkers.length; n++){
                markersList = markersList + ' ' + 
                '<div class="deleteFromRouteImage" onclick="removeFromRoute(\''+routeMarkers[n].title+'\')">'+
                '<img src="${Constants.URL}img/hideDescrButtonRed.png"></div>'+  
                routeMarkers[n].title + '<br>';
            }
            markersList = markersList + '</div><div class="buildRouteButton">'+  
                '<a onclick="buildRouteMap()" class="btn btn-theme printRoute">BUILD ROUTE</a>'+
                '<a target="_blank" id="printRoute" href="#" onclick="printRoute()" class="btn btn-theme printRoute">PRINT</a></div>';
            $('.routeMapContainer').html(markersList);
            $('#routePointsNumber').html(routeMarkers.length);
        }
        
        function buildRouteMap(){
            mapRouteContainerCounter = false;
            if(routeMarkers.length>2){
                for(var n = 1; n < routeMarkers.length-1; n++){
                    waypts.push({location:routeMarkers[n].coords, stopover: true});
                }
            }
            var directionsDisplay = new google.maps.DirectionsRenderer();
            var directionsService = new google.maps.DirectionsService();
            directionsDisplay.setMap(map);
            var request = {
                origin: routeMarkers[0].coords,
                destination: routeMarkers[routeMarkers.length-1].coords,
                waypoints: waypts,
                optimizeWaypoints: true,
                travelMode: google.maps.TravelMode.DRIVING
            };
            summary = '';
            directionsService.route(request, function(response, status) {
            if (status == google.maps.DirectionsStatus.OK) {
                directionsDisplay.setDirections(response);
                var route = response.routes[0];
                // For each route, display summary information.
                for (var i = 0; i < route.legs.length; i++) {
                  var routeSegment = i + 1;
                  summary += '<b>Route Segment: ' + routeSegment + '</b><br>';
                  summary += route.legs[i].start_address + ' to ';
                  summary += route.legs[i].end_address + '<br>';
                  summary += route.legs[i].distance.text + '<br><br>';
                }
              }
            });
        }
        
        function removeFromRoute(marker){
            mapRouteContainerCounter = false;
            for(var n = 0; n < routeMarkers.length; n++){
                if(routeMarkers[n].title==marker){
                    routeMarkers.splice(n, 1);
                }
            }
            buildRouteFromArray();
        }
        
        function buildRouteFromArray(){
            mapRouteContainerCounter = false;
            var markersList = '<div class="mapRouteText">';
            for(var n = 0; n < routeMarkers.length; n++){
                markersList = markersList + ' ' + 
                '<div class="deleteFromRouteImage" onclick="removeFromRoute(\''+routeMarkers[n].title+'\')"><img src="${Constants.URL}img/hideDescrButtonRed.png"></div>'+  
                routeMarkers[n].title + '<br>';
            }
            if(routeMarkers.length!=0){
                markersList = markersList + '</div><div class="buildRouteButton" onclick="buildRouteMap()">'+ 
                '<a onclick="buildRouteMap()" class="btn btn-theme printRoute">BUILD ROUTE</a>'+
                '<a target="_blank" href="#" id="printRoute" onclick="printRoute()" class="btn btn-theme printRoute">PRINT</a></div>';
                $('.routeMapContainer').html(markersList);
                }
            else{
                $('.routeMapContainer').html("No points in your route");
            }
            $('#routePointsNumber').html(routeMarkers.length);
        }
        function printRoute(){
            var printCoords = [];
            for(var n = 0; n < routeMarkers.length; n++){
                printCoords.push(routeMarkers[n].x+','+routeMarkers[n].y);
            }
            $('#printRoute').attr('href', PowerHour.getPrintUrl(printCoords));
        }
        
        var showTypes = false;
        function chooseMapType(){
            if(filtersContainerCounter){hideFilters()}
            if(mapRouteContainerCounter){hideRoute()}
            if(rightMapContainerCounter){hideMap()}
            if(!showTypes){
                $('.sliderDiv').addClass('sliderDivBig');
                $('.mapType').fadeIn('slow');
                showTypes = true;
            }else{
                $('.sliderDiv').removeClass('sliderDivBig');
                $('.mapType').fadeOut('slow');
                showTypes = false;
            }
        }
        
        function changeMapType(lett){
            $('#chosenType').html(lett);
            switch(lett) {
                case 'S':
                    map.setMapTypeId(google.maps.MapTypeId.SATELLITE);
                    break;
                case 'D':
                    var styledMap = new google.maps.StyledMapType(mapStyles,
                        {name: "Styled Map"});
                    map.mapTypes.set('map_style', styledMap);
                    map.setMapTypeId('map_style');
                    break;
                case 'R':
                    map.setMapTypeId(google.maps.MapTypeId.ROADMAP);
                    break;
                case 'T':
                    map.setMapTypeId(google.maps.MapTypeId.TERRAIN);
                    break;
            }
        }
        
</script>
    <map name="mainMap">
        <area href="${Constants.URL}map/Hungary" shape="poly" onmouseover="showMapHover('#hungary')"
              coords="456,127,468,136,488,138,503,151,525,147,550,166,521,181,504,207,497,231,485,255,469,282,426,295,392,290,368,308,329,318,304,317,283,294,259,272,238,248,253,210,277,181,300,191,326,196,351,195,374,183,370,173,404,163,422,165,438,156"/>
        <area href="${Constants.URL}map/Romania" shape="poly"  onmouseover="showMapHover('#romania')"
              coords="577,170,558,184,531,200,512,231,498,261,488,290,479,306,447,312,459,330,471,351,494,363,512,406,846,408,852,378,813,357,814,299,820,282,817,259,806,248,808,232,789,218,778,199,776,185,761,153,744,149,733,162,722,173,690,178,682,188,664,190,650,187,637,183,624,177"/>
        <area href="${Constants.URL}map/Ukraine" shape="poly"  onmouseover="showMapHover('#ukraine')"
              coords="546,95,537,132,561,152,580,150,598,159,614,163,633,158,658,164,682,159,717,151,738,129,747,118,796,111,840,129,869,145,881,169,883,194,906,214,909,239,929,266,979,262,979,3,597,1,559,42,560,70,574,86,566,99"/>
        <area href="${Constants.URL}map/Slovakia" shape="poly"  onmouseover="showMapHover('#slovakia')"
              coords="533,89,527,108,515,118,512,132,498,123,484,119,468,119,443,118,436,122,426,137,400,143,388,144,378,150,360,156,350,161,348,174,306,177,285,153,271,139,273,117,276,108,313,105,333,79,350,61,370,71,389,66,408,84,439,77,469,80,479,69,506,73,513,87"/>
        <area href="${Constants.URL}map/Poland" shape="poly"  onmouseover="showMapHover('#poland')"
              coords="580,0,321,0,310,12,332,14,353,21,364,39,371,53,388,38,405,41,413,60,453,54,481,50,508,56,526,70,551,86,543,70,538,56,542,40"/>
    </map>
<div style="width:100%;margin-top: -30px; margin-bottom:-45px;">
    <div id="sliderDiv" class="sliderDiv" onclick="chooseMapType()">
        <div id="chosenType">D</div>
        <div class="mapType">
            <ul id="nav" class="mapTypeLi">
                <li id="defaultMap">
                    <div class='mapButton' id='allMap' onmouseover="showMapHover('#all')"
                                                                onclick="changeMapType('D')">DEFAULT</div>
                </li>
                <li id="satelliteMap" class="mapMenuLeftMArgin">
                    <div class='mapButton' id='ukraineMap' onmouseover="showMapHover('#ukraine')"
                                                                   onclick="changeMapType('S')">SATELLITE</div>
                </li>
                <li id="roadMap" class="mapMenuLeftMArgin">
                    <div class='mapButton' id='slovakiaMap' onmouseover="showMapHover('#slovakia')"
                                                                   onclick="changeMapType('R')">ROADMAP</div>
                </li>
                <li id="roadMap" class="mapMenuLeftMArgin">
                    <div class='mapButton' id='hungaryMap' onmouseover="showMapHover('#hungary')"
                                                                   onclick="changeMapType('T')">TERRAIN</div>
                </li>
            </ul>
        </div>
    </div>
    <div id="mapRouteButton" onclick="hideRoute()" class="mapRoute">
        <div id="routePointsNumber">0</div>
        <img id="mapRouteImage" src="${Constants.URL}img/route_icon.png"
             onmouseover="if(!mapRouteContainerCounter){this.src='${Constants.URL}img/icon_route_hover.png';}"
             onmouseout="if(!mapRouteContainerCounter){this.src='${Constants.URL}img/route_icon.png';}">
        <div class="routeMapContainer" id="printable">No points in your route</div>
    </div>
    <div id="mapControls" class="mapControls">
        <img id="mapControlsImage" onclick="hideMap()" src="${Constants.URL}img/mapControlsImage.png"
             onmouseover="if(!rightMapContainerCounter){this.src='${Constants.URL}img/marker_hover.png';}"
             onmouseout="if(!rightMapContainerCounter){this.src='${Constants.URL}img/marker'+countryChooser+'.png';}">
            <div class="indexMapMenu">
                <ul id="nav">
                    <li id="allMenu">
                        <a href="${Constants.URL}map/allMap"><div class='mapButton' id='allMap'
                                                                  onmouseover="showMapHover('#all')">ALL COUNTRIES</div></a>
                    </li>
                    <li id="ukraineMenu" class="mapMenuLeftMArgin">
                        <a href="${Constants.URL}map/Ukraine"><div class='mapButton' id='ukraineMap'
                                                                   onmouseover="showMapHover('#ukraine')">UKRAINE</div></a>
                    </li>
                    <li id="slovakiaMenu" class="mapMenuLeftMArgin">
                        <a href="${Constants.URL}map/Slovakia"><div class='mapButton' id='slovakiaMap'
                                                                    onmouseover="showMapHover('#slovakia')">SLOVAKIA</div></a>
                    </li>
                    <li id="hungaryMenu" class="mapMenuLeftMArgin">
                        <a href="${Constants.URL}map/Hungary"><div class='mapButton' id='hungaryMap'
                                                                   onmouseover="showMapHover('#hungary')">HUNGARY</div></a>
                    </li>
                    <li id="romaniaMenu" class="mapMenuLeftMArgin">
                        <a href="${Constants.URL}map/Romania"><div class='mapButton' id='romaniaMap'
                                                                   onmouseover="showMapHover('#romania')">ROMANIA</div></a>
                    </li>
                    <li id="polandMenu" class="mapMenuLeftMArgin">
                        <a href="${Constants.URL}map/Poland"><div class='mapButton' id='polandMap'
                                                                  onmouseover="showMapHover('#poland')">POLAND</div></a>
                    </li>
                </ul>
            </div>
    </div>
    <div id="pushRightConrainer" class="pushRightConrainer">
        <img onclick="hideFilters()" id="mainImageRightContaineMap" src="${Constants.URL}img/mapRightContainer.png"
             onmouseover="if(!filtersContainerCounter){this.src='${Constants.URL}img/mapRightContainerHover.png';}"
             onmouseout="if(!filtersContainerCounter){this.src='${Constants.URL}img/mapRightContainer.png';}">
            <div class="indexMapContainer">
                <img src="${Constants.URL}img/main_map.png">
                <img id="allBorder" src="${Constants.URL}img/map_all.png">
                <img id="hungaryBorder" src="${Constants.URL}img/map_hungary.png">
                <img id="polandBorder" src="${Constants.URL}img/map_poland.png">
                <img id="romaniaBorder" src="${Constants.URL}img/map_romania.png">
                <img id="slovakiaBorder" src="${Constants.URL}img/map_slovakia.png">
                <img id="ukraineBorder" src="${Constants.URL}img/map_ukraine.png">
                <img src="${Constants.URL}img/mapHoverImage.png" usemap="#mainMap">
            </div>
            <div id='filtersContainer'>
                <div class="filtersName">
                    <input type="checkbox" id="markAll" name="markAll"
                        onclick="Markers(['markAll'${htmlOnClick}])" 
                        class="css-checkbox" checked="checked" />
                    <label for="markAll" class="css-label">Filters</label>
                </div>
                ${htmlFilters}
            </div>
    </div>
    <div id="googleMap" style="overflow: visible;width: 100%;"></div>
    <div style="overflow: visible;" id="markerDescription"></div>
</div>    
 </t:indexpage>