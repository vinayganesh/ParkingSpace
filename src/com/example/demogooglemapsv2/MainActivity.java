package com.example.demogooglemapsv2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



import android.R.string;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private final LatLng LOCATION_SJSU_PARKING = new LatLng(37.335169,-121.88107);
	private final LatLng LOCATION_THIRDSTREE_PARKING = new LatLng(37.32808,-121.881694); 
	private final LatLng LOCATION_EX1 = new LatLng(37.31917,-122.04511);
	private final LatLng LOCATION_EX2 = new LatLng(37.35411,-121.95524);
	private final LatLng LOCATION_EX3 = new LatLng(37.77493,-122.41942);
	private LatLng LOCATION_CURRENT;
	private LatLng LOCATION_CURRENT1;
	private LatLng LOCATION_NEW;
	private Owner owner;
	ArrayList<Owner> olist;
	Button button;
	
	String name;
	String address;
	String phone;
	String price;

	private GoogleMap map;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();       
        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = service.getBestProvider(criteria, false);
        Location location = service.getLastKnownLocation(provider);
        LOCATION_CURRENT = new LatLng(location.getLatitude(),location.getLongitude());
        map.addMarker(new MarkerOptions().position(LOCATION_CURRENT).title("You Are Here ! "));
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    	CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_CURRENT, 14);
    	map.animateCamera(update);
    	getLocationData();  	
    }

    //Fetch the data from the database and display the data
    public void getLocationData()
    {
    	DatabaseHandler db = new DatabaseHandler(this);
    	
    	 List<Owner> olit =  db.getAllOwners();  
    	 for (Owner ow : olit) {
             name = ow.getName();
             address = ow.getAddress();
             phone = ow.getPhone();
             price = ow.getPrice();
             LatLng LOCATION_ADDRESS = convertAddress(address);
     		 map.addMarker(new MarkerOptions().position(LOCATION_ADDRESS).title(phone).snippet("call"+" "+ name+ " " + price));
    	 }
    	 
    }
    
    //convert the given address into LAT and LNG Values
    public LatLng convertAddress(String address)
    {
    	List<Address> foundGeocode = null;
    	try 
    	{
			foundGeocode = new Geocoder(this).getFromLocationName(address,1);
		} 
    	
    	catch (IOException e) 
    	{
			e.printStackTrace();
		}
    	foundGeocode.get(0).getLatitude(); //getting latitude
    	foundGeocode.get(0).getLongitude();//getting longitude
    	
    	LOCATION_NEW = new LatLng(foundGeocode.get(0).getLatitude(),foundGeocode.get(0).getLongitude());
    	
    	return LOCATION_NEW; 	
    }
   

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onClick_Burnaby(View v){
    	 map.addMarker(new MarkerOptions().position(LOCATION_SJSU_PARKING).title("Call 408-643-9438").snippet("$2/hr"));
         map.addMarker(new MarkerOptions().position(LOCATION_THIRDSTREE_PARKING).title("Call 510-666-7586").snippet("$5/hr"));
         map.addMarker(new MarkerOptions().position(LOCATION_EX1).title("Call 510-666-7586").snippet("$5/hr"));
         map.addMarker(new MarkerOptions().position(LOCATION_EX2).title("Call 493-928-2929").snippet("$4/hr"));
         map.addMarker(new MarkerOptions().position(LOCATION_EX3).title("Call 508-837-0292").snippet("$8/hr"));
         LOCATION_CURRENT1 = convertAddress("199 Julian Street San jose");
         map.addMarker(new MarkerOptions().position(LOCATION_CURRENT1).title("Call 5101-666-7586").snippet("$9/hr"));
    }
    
   
    public void onClick_OwnerMode(View v) 
    {
    	final Context context = this;
        Intent intent = new Intent(context, OwnerActivity.class);
        startActivity(intent);  
   }
	
}


