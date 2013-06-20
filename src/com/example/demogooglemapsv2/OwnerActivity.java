package com.example.demogooglemapsv2;
import java.util.List;

import com.example.demogooglemapsv2.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class OwnerActivity extends Activity {
	
	Button button;
	EditText txtname;
	EditText txtaddress;
	EditText txtphone;
	EditText txtprice;
	String name;
	String address;
	String phone;
	String price;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);
	}
	
	public void onClick_OpenMap(View v) 
    {
		txtname   = (EditText)findViewById(R.id.txtName);
		txtaddress   = (EditText)findViewById(R.id.txtAddress);
		txtphone   = (EditText)findViewById(R.id.txtPhone);
		txtprice   = (EditText)findViewById(R.id.txtPrice);
		
		name = txtname.getText().toString();
		address = txtaddress.getText().toString();
		phone = txtphone.getText().toString();
		price = txtprice.getText().toString();
		
		//insert the entered data
		insertData();
		
		
		final Context context = this;
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);  
   }
	
	
	public void insertData()
	{
		DatabaseHandler db = new DatabaseHandler(this);
    	// Inserting Contacts
        db.addOwner(new Owner(name,address,phone,price));  
	}        
       
}
	

