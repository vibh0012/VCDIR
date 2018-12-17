package com.example.vibhor.vcdir.databs;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import java.security.Provider;


public class VcProvider extends ContentProvider {

    /** Tag for the log messages */
    public static final String LOG_TAG = VcProvider.class.getSimpleName();
    public static final int MAIN_DATA=100;
    public static final int MAIN_DATA_ID=101;
    public static final int ADM=102;
    public static final int ADM_ID=103;
    private static final UriMatcher sUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
    static
    {
        sUriMatcher.addURI(vc_contract.CONTENT_AUTHORITY,vc_contract.PATH_MAIN,MAIN_DATA);
        sUriMatcher.addURI(vc_contract.CONTENT_AUTHORITY,vc_contract.PATH_MAIN+"/#",MAIN_DATA_ID);
        sUriMatcher.addURI(vc_contract.CONTENT_AUTHORITY,vc_contract.PATH_ADMIN,ADM);
        sUriMatcher.addURI(vc_contract.CONTENT_AUTHORITY,vc_contract.PATH_ADMIN+"/#",ADM_ID);
    }
    /**
     * Initialize the provider and the database helper object.
     */
    private vcDbHelper mDbhelper;
    @Override
    public boolean onCreate() {
        // TODO: Create and initialize a PetDbHelper object to gain access to the pets database.
        // Make sure the variable is a global variable, so it can be referenced from other
        // ContentProvider methods.
        mDbhelper=new vcDbHelper(getContext());
        return true;
    }

    /**
     * Perform the query for the given URI. Use the given projection, selection, selection arguments, and sort order.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase database=mDbhelper.getReadableDatabase();
        Cursor cursor;
        int match=sUriMatcher.match(uri);
        switch(match)
        {
            case MAIN_DATA:
            {
                cursor=database.query(vc_contract.MainEntry.TABLE_NAME,projection,null,
                        null,null,null,null);
                break;
            }
            case MAIN_DATA_ID:
            {
                selection= vc_contract.MainEntry._ID+"=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor=database.query(vc_contract.MainEntry.TABLE_NAME,projection,selection,
                        selectionArgs,null,null,sortOrder);
                break;
            }
            case ADM:
            {
                cursor=database.query(vc_contract.ADMIN.TABLE_NAME_2,projection,null,
                        null,null,null,null);
                break;
            }
            case ADM_ID:
            {
                selection= vc_contract.ADMIN.COLUMN_ID_2+"=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor=database.query(vc_contract.ADMIN.TABLE_NAME_2,projection,selection,
                        selectionArgs,null,null,sortOrder);
                break;
            }
            default: throw new IllegalArgumentException("uri not valid!"+ uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    /**
     * Insert new data into the provider with the given ContentValues.
     */
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {


        int match = sUriMatcher.match(uri);
        switch (match) {
            case MAIN_DATA: {

                return insert_main_data(uri,contentValues);

            }
            case ADM: {

                Log.v(LOG_TAG, "ACCEPTED INTO INSERT PETPROVIDER\n");
                return insertAdmin(uri,contentValues);


            }
            default:

                throw new IllegalArgumentException("uri not valid!" + uri);

        }

    }
    private Uri insertAdmin(Uri uri,ContentValues values)
    {
        String log=values.getAsString(vc_contract.ADMIN.COLUMN_LOGIN);
        if(log.trim().isEmpty())
        {
            throw new IllegalArgumentException("login_id cannot be null");
        }
        String pas=values.getAsString(vc_contract.ADMIN.COLUMN_PASSWORD);

        if(pas.trim().isEmpty())
        {
            throw new IllegalArgumentException("password cannot be null");
        }
        SQLiteDatabase database = mDbhelper.getWritableDatabase();

        // Insert the new pet with the given values
        long id = database.insert(vc_contract.ADMIN.TABLE_NAME_2, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri,null);

        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }
    private Uri insert_main_data(Uri uri, ContentValues values) {
        // Check that the name is not null
        String regions = values.getAsString(vc_contract.MainEntry.COLUMN_REGION);
        if (regions.trim().isEmpty()) {

            throw new IllegalArgumentException("REGIONS CANNOT BE EMPTY");
        }
        // Get writeable database
        SQLiteDatabase database = mDbhelper.getWritableDatabase();

        // Insert the new pet with the given values
        long id = database.insert(vc_contract.MainEntry.TABLE_NAME, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri,null);

        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }
    /**
     * Updates the data at the given selection and selection arguments, with the new ContentValues.
     */
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MAIN_DATA:
                return update_main_data(uri, contentValues, selection, selectionArgs);
            case MAIN_DATA_ID: {   // For the PET_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = vc_contract.MainEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return update_main_data(uri, contentValues, selection, selectionArgs);
            }
            case ADM:
                return updateAdmin(uri, contentValues, selection, selectionArgs);
            case ADM_ID: {// For the PET_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = vc_contract.ADMIN.COLUMN_ID_2 + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateAdmin(uri, contentValues, selection, selectionArgs);
            }
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);}

    }
    private int updateAdmin(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        if (values.containsKey(vc_contract.ADMIN.COLUMN_LOGIN)) {
            String log = values.getAsString(vc_contract.ADMIN.COLUMN_LOGIN);
            if (log.trim().isEmpty()) {
                throw new IllegalArgumentException("login_id cannot be null");
            }
        }
        if (values.containsKey(vc_contract.ADMIN.COLUMN_PASSWORD)) {
            String pas = values.getAsString(vc_contract.ADMIN.COLUMN_PASSWORD);

            if (pas.trim().isEmpty()) {
                throw new IllegalArgumentException("password cannot be null");
            }
        }
        if (values.size() == 0) {
            return 0;
        }

        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = mDbhelper.getWritableDatabase();
        int rowsupdated=database.update(vc_contract.ADMIN.TABLE_NAME_2,values,selection,selectionArgs);
        if(rowsupdated!=0)
            getContext().getContentResolver().notifyChange(uri,null);
        return rowsupdated;
    }
    private int update_main_data(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // If the {@link PetEntry#COLUMN_PET_NAME} key is present,
        // check that the name value is not null.
        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }

        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = mDbhelper.getWritableDatabase();
        int rowsupdated=database.update(vc_contract.MainEntry.TABLE_NAME,values,selection,selectionArgs);
        if(rowsupdated!=0)
            getContext().getContentResolver().notifyChange(uri,null);
        return rowsupdated;
    }
    /**
     * Delete the data at the given selection and selection arguments.
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Get writeable database
        SQLiteDatabase database = mDbhelper.getWritableDatabase();
        int rowsdeleted;
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MAIN_DATA:
                // Delete all rows that match the selection and selection args
                rowsdeleted = database.delete(vc_contract.MainEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case MAIN_DATA_ID:
                // Delete a single row given by the ID in the URI
                selection = vc_contract.MainEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                rowsdeleted = database.delete(vc_contract.MainEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case ADM:
                // Delete all rows that match the selection and selection args
                rowsdeleted = database.delete(vc_contract.ADMIN.TABLE_NAME_2, selection, selectionArgs);
                break;

            case ADM_ID:
                // Delete a single row given by the ID in the URI
                selection = vc_contract.ADMIN.COLUMN_ID_2 + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                rowsdeleted = database.delete(vc_contract.ADMIN.TABLE_NAME_2, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        if (rowsdeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);

        }
        return rowsdeleted;
    }

    /**
     * Returns the MIME type of data for the content URI.
     */
    @Override
    public String getType(Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case MAIN_DATA:
                return vc_contract.MainEntry.CONTENT_LIST_TYPE;
            case MAIN_DATA_ID:
                return vc_contract.MainEntry.CONTENT_ITEM_TYPE;
            case ADM:
                return vc_contract.ADMIN.CONTENT_LIST_TYPE_2;
            case ADM_ID:
                return vc_contract.ADMIN.CONTENT_ITEM_TYPE_2;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
}