package com.giga_appz.newmatrimony;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NANDHU on 20-12-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "mangallyam";

    // Contacts table name
    private static final String TABLE_PROFILE = "profiledetails";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_AGE = "age";
    private static final String KEY_EDU = "education";
    private static final String KEY_PHOTO = "photo";
    private static final String KEY_LOCATION = "location";
    SQLiteDatabase db = this.getWritableDatabase();
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROFILE_TABLE = "CREATE TABLE " + TABLE_PROFILE + "("
                + KEY_ID + " TEXT," + KEY_AGE + " TEXT"+ KEY_EDU+ " TEXT,"+ KEY_PHOTO + " BLOB,"+ KEY_LOCATION + " TEXT)";
        db.execSQL(CREATE_PROFILE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);

        // Create tables again
        onCreate(db);
    }

    // Getting single contact
    public Profilesoffline getProfile(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PROFILE, new String[] { KEY_ID,
                        KEY_AGE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Profilesoffline profile = new Profilesoffline(cursor.getString(0),
                cursor.getString(1));
        // return contact
        return profile;
    }

    // Adding new contact
    /*public void addProfile(Profilesoffline profile) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AGE, profile.getName());
        values.put(KEY_ID, profile.getId());

        // Inserting Row
        db.insert(TABLE_PROFILE, null, values);

        db.close();
    }*/

   /* public void addProfile(String id,String age,String education,byte[] photo,String location) throws SQLiteException {
        ContentValues cv = new  ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        cv.put(KEY_AGE,    age);
        cv.put(KEY_PHOTO,   photo);
        cv.put(KEY_ID,   id);
        cv.put(KEY_LOCATION,   location);
        cv.put(KEY_EDU,   education);
        db.insert( TABLE_PROFILE, null, cv );
    }*/
    public void addProfile(Profilesoffline profile) {
        ContentValues cv = new ContentValues();

        cv.put(KEY_AGE,    profile.age);
        cv.put(KEY_PHOTO, Utility.getBytes(profile.getPhoto()));
        cv.put(KEY_ID,   profile.getId());
        cv.put(KEY_LOCATION,   profile.getLocation());
        cv.put(KEY_EDU,   profile.getEducation());
        db.insert( TABLE_PROFILE, null, cv );
    }
    public Profilesoffline retriveDetails() throws SQLException {
        Cursor cur = db.query(true, TABLE_PROFILE, new String[] { KEY_ID,
                KEY_AGE, KEY_EDU,KEY_PHOTO,KEY_LOCATION }, null, null, null, null, null, null);
        if (cur.moveToFirst()) {
            byte[] blob = cur.getBlob(cur.getColumnIndex(KEY_PHOTO));
            String age = cur.getString(cur.getColumnIndex(KEY_AGE));
            String id = cur.getString(cur.getColumnIndex(KEY_ID));
            String edu = cur.getString(cur.getColumnIndex(KEY_EDU));
            String loc = cur.getString(cur.getColumnIndex(KEY_LOCATION));
            cur.close();
            return new Profilesoffline(id,age,edu,Utility.getPhoto(blob),loc);
        }
        cur.close();
        return null;
    }

/*

    // Getting All Contacts
    public List<Profilesoffline> getAllContacts() {
        List<Profilesoffline> contactList = new ArrayList<Profilesoffline>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PROFILE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Profilesoffline contact = new Profilesoffline();
                contact.setId(cursor.getString(0));
                contact.setAge(cursor.getString(1));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
*/

}
