# Gmap-Marker
Sample project for Gmap marker with SQLiteDatabase

1) click add button from actionbar to add the marker into your current location.

2) you can chage the location of the marker bt dragging it.

3) Click on marker to add the name for marker and press save bottum from dialog to save the marker into SQLiteDatabse

4) To view your marker select the My Marker oprion from navigation drawer, this will navigate you to MtMarker Activity

5) In MyMarkerActivity you can see the list of marer that you saved into the SQLiteDatabse.

6) select delete to delate the marker from database.

7) use simple OnItemClickListener for MarkerADapter to gat the selected Marker Details, then you can use this marker for fyrther user by passing th marker bean between activities.


## Reference : 
1) https://developers.google.com/android/reference/com/google/android/gms/maps/model/Marker : this is an official link from google,to customize marker in map.
