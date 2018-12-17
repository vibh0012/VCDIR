package com.example.vibhor.vcdir.databs;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.InputStream;

public class vcDbHelper extends SQLiteOpenHelper {

    public final static int DATABASE_VERSION=1;
    public static final String LOG_TAG = vcDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "vc_directory.db";

    public vcDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_MAIN_TABLE =  "CREATE TABLE " + vc_contract.MainEntry.TABLE_NAME + " ("
                + vc_contract.MainEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + vc_contract.MainEntry.COLUMN_REGION+" TEXT NOT NULL, "
                + vc_contract.MainEntry.COLUMN_SITE+" TEXT NOT NULL, "
                + vc_contract.MainEntry.COLUMN_SITE_NAME + " TEXT , "
                + vc_contract.MainEntry.COLUMN_VCP_IP + " TEXT , "
                + vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NO + " INTEGER , "
                + vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NAME+" TEXT , "
                + vc_contract.MainEntry.COLUMN_IT_COORDINATOR_CELL_NO+" INTEGER , "
                + vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMAIL + " TEXT  );";
        String SQL_CREATE_ADMINS_TABLE="CREATE TABLE "+vc_contract.ADMIN.TABLE_NAME_2+" ("
                +vc_contract.ADMIN.COLUMN_ID_2+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +vc_contract.ADMIN.COLUMN_LOGIN+" TEXT NOT NULL, "
                +vc_contract.ADMIN.COLUMN_PASSWORD+" TEXT NOT NULL );";

        // Execute the SQL statement
        //USE THEM MODAF SPACES
        db.execSQL(SQL_CREATE_MAIN_TABLE);
        db.execSQL(SQL_CREATE_ADMINS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /**
         // The database is still at version 1, so there's nothing to do be done here.
         String SQL_DELETE_PETS_TABLE=
         "DROP TABLE IF EXISTS "+ petContract.PetEntry.TABLE_NAME;
         db.execSQL(SQL_DELETE_PETS_TABLE);
         onCreate(db);*/
    }
}

