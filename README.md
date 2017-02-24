# Gmap-Marker
Sample project for Gmap marker with SQLiteDatabase

1) click add button from actionbar to add the new marker into your current location.

2) you can change the location of the marker by dragging it.

3) Click on marker to add the name for marker and press save botton from dialog to save the marker into SQLiteDatabse

4) To view your marker select the My Marker oprion from navigation drawer, this will navigate you to MyMarker Activity

5) In MyMarkerActivity you can see the list of marker that you saved into the SQLiteDatabse.

6) select delete button from list to delete the marker from database.

7) use simple OnItemClickListener pattern for MarkerAdapter to get the selected Marker Details, then you can use this marker for further uses by passing th marker bean between activities.

### Note :
Label table is not implemented, this for grouping and filtering the marker by providing the label for each marker.


## Reference : 
1) https://developers.google.com/android/reference/com/google/android/gms/maps/model/Marker : this is an official document from google,to customize marker in Gmap.
