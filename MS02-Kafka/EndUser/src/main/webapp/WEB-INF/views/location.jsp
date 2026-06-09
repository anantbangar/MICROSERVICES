<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
<title>Live Location</title>

<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />

<script
	src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<style>

#map {
	height: 400px;
	width: 60%;
	margin: 0 auto;
}
</style>
</head>

<body>

	<h2>Vehicle Tracking</h2>

	<p>
		Latitude : <span id="latText">${location.latitude}</span>
	</p>

	<p>
		Longitude : <span id="lngText">${location.longitude}</span>
	</p>

	<div id="map"></div>

	<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>

	<script>
		var lat = parseFloat("${location.latitude}");
		var lng = parseFloat("${location.longitude}");

		var map = L.map('map').setView([ lat, lng ], 15);

		L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
			maxZoom : 19
		}).addTo(map);

		var marker = L.marker([ lat, lng ]).addTo(map)
				.bindPopup("Current Location");

		// AJAX call every 5 seconds
		setInterval(function() {
			
			console.log("Calling API...");

			$.ajax({
				url : "/getLocation",
				type : "GET",
				dataType : "json",
				success : function(data) {

					var newLat = parseFloat(data.latitude);
					var newLng = parseFloat(data.longitude);

					$("#latText").text(newLat);
					$("#lngText").text(newLng);

					// Move marker
					marker.setLatLng([ newLat, newLng ]);

					// Move map center
					map.setView([ newLat, newLng ], 15);

					console.log("Updated : " + newLat + ", " + newLng);
				},
				error : function() {
					console.log("Failed to fetch location");
				}
			});

		}, 5000);
	</script>

</body>
</html>