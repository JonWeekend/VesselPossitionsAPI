# VesselPossitionsAPI
Simple REST API interview test for Trackwell VMS

Created with Spring Boot.

The API takes in a json object (VesselPossition) which contains a vessel object and a position object.
Example:
```
  {
    "vessel": {
      "name": vesselName (string),
      "country": vesselCountry (string)
    },
    "position": {
      "date": positionDate (date),
      “latitude”: latitudeDegrees (number),
      “longitude”: longitudeDegrees (number),
      “speed”: speedKnots (number)
    }
  }
```
The API can take POST and GET requests.
The POST and the first GET endpoints are mapped to /vessel-positions.

* Sending a POST request to /vessel-positions will cache and return an altered VesselPossition object with a new attribute; "receivedDate", which is the date when the endpoint received the object. As well as converting the latitude and longitude from degrees to radians and the speed from knots to meters per second.
Example:
```
  {
    "vessel": {
      “name“: vesselName (string)
    },
    "position": {
      “date”: positionDate (date),
      “receivedDate”: receivedDate (date),
      “latitude”: latitudeRadians (number),
      “longitude”: longitudeRadians (number),
      “speed”: speedMetersPerSeconds (number)
    }
  }
```
* Sending a GET request to /vessel-possitions will return a list of all VesselPossition objects that have been cached.

The last GET endpoint is mapped to /vessel-positions/{vesselName}
* Sending a GET request to /vessel-positions/{vesselName} will return a list of all VesselPosition objects that contain a vessel with the given vesselName.

NOTE: the "date" and "receivedDate" attributes are expected to be in the following formats: "dd-MM-yyyy'T'HH:mm:ss a z"
Example date: "21-04-2022T13:46:51 PM GMT"

