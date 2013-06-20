package com.example.demogooglemapsv2;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "Parking";
 
    // Contacts table name
    private static final String TABLE_OWNER = "OwnerInfo";
 
    // Contacts Table Columns names
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PRICE = "price";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_OWNER_TABLE = "CREATE TABLE " + TABLE_OWNER + "("+ KEY_NAME + " INTEGER PRIMARY KEY," + KEY_ADDRESS + " TEXT,"+ KEY_PHONE + " TEXT"  + KEY_PRICE + " TEXT" + ")";
        db.execSQL(CREATE_OWNER_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_OWNER);
		 
        // Create tables again
        onCreate(db);
	}
	
	//insert
	public void addOwner(Owner owner) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(KEY_NAME, owner.getName()); // Owner Name
	    values.put(KEY_PHONE, owner.getPhone()); // Owner Phone Number
	    values.put(KEY_ADDRESS,owner.getAddress());// Owner Address
	    values.put(KEY_PRICE,owner.getPrice());//Owner Price
	 
	    // Inserting Row
	    db.insert(TABLE_OWNER, null, values);
	    db.close(); // Closing database connection
	}
	
	
	
    // Getting All Owners
 public List<Owner> getAllOwners() {
    List<Owner> ownerList = new ArrayList<Owner>();
    // Select All Query
    String selectQuery = "SELECT  * FROM " + TABLE_OWNER;
 
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);
 
    // looping through all rows and adding to list
    if (cursor.moveToFirst()) {
        do {
        	Owner contact = new Owner();
            contact.setName(cursor.getString(0));
            contact.setAddress(cursor.getString(1));
            contact.setPhone(cursor.getString(2));
            contact.setPrice(cursor.getString(3));
            // Adding contact to list
            ownerList.add(contact);
        } while (cursor.moveToNext());
    }
 
    // return contact list
    return ownerList;
}

}
