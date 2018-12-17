package com.example.vibhor.vcdir;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.DatabaseUtils.InsertHelper;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vibhor.vcdir.databs.vcDbHelper;
import com.example.vibhor.vcdir.databs.vc_contract;

import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class MainActivity extends AppCompatActivity {
    vcDbHelper main_helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_helper=new vcDbHelper(this);
        /**
        String proj_main[]={
                vc_contract.MainEntry._ID,
                vc_contract.MainEntry.COLUMN_REGION
        };
        Cursor c_main=getContentResolver().query(vc_contract.MainEntry.CONTENT_URI,proj_main,null,null,null);
        c_main.moveToNext();
        int region_exist=c_main.getColumnIndexOrThrow(vc_contract.MainEntry.COLUMN_REGION);
        String region_exist_string=c_main.getString(region_exist);
        if(!region_exist_string.isEmpty()) {}*/
            int rows_main_del = getContentResolver().delete(vc_contract.MainEntry.CONTENT_URI, null, null);
            int rows_admin_del = getContentResolver().delete(vc_contract.MainEntry.CONTENT_URI, null, null);
        SQLiteDatabase db_js=main_helper.getWritableDatabase();
        SQLiteDatabase[] abc={db_js};
        fetchData process = new fetchData();
        process.execute(abc);
        ContentValues va=new ContentValues();
        va.put(vc_contract.MainEntry.COLUMN_REGION,"NR4");
        va.put(vc_contract.MainEntry.COLUMN_SITE,"SR2");
        va.put(vc_contract.MainEntry.COLUMN_SITE_NAME,"jaipur");
        va.put(vc_contract.MainEntry.COLUMN_VCP_IP,"1.23.46");
        va.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NO,566412);
        va.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NAME,"surbhi");
        va.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_CELL_NO,987645643);
        va.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMAIL,"surbhi@pwgd.com");
        Uri URIa=getContentResolver().insert(vc_contract.MainEntry.CONTENT_URI,va);
        ContentValues values=new ContentValues();
        values.put(vc_contract.ADMIN.COLUMN_LOGIN,"vibhor");
        values.put(vc_contract.ADMIN.COLUMN_PASSWORD,"password");
        Uri URI=getContentResolver().insert(vc_contract.ADMIN.CONTENT_URI_2,values);

        ContentValues val=new ContentValues();
        val.put(vc_contract.ADMIN.COLUMN_LOGIN,"log");
        val.put(vc_contract.ADMIN.COLUMN_PASSWORD,"pass");
        Uri UR=getContentResolver().insert(vc_contract.ADMIN.CONTENT_URI_2,val);
        Button l=(Button)findViewById(R.id.log_button);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });
    }
    void check()
    {
        String projection[]={
                vc_contract.ADMIN.COLUMN_LOGIN,
                vc_contract.ADMIN.COLUMN_PASSWORD
        };
        Cursor cursor=getContentResolver().query(vc_contract.ADMIN.CONTENT_URI_2,projection,
                null,null,null);
        Boolean a=false;
        EditText e_login=(EditText)findViewById(R.id.login);
        String login_e = e_login.getText().toString().trim();
        EditText e_password=(EditText)findViewById(R.id.pasword);
        String password_e=e_password.getText().toString().trim();
        while(cursor.moveToNext())
        {
            String login_s=cursor.getString(cursor.getColumnIndexOrThrow(vc_contract.ADMIN.COLUMN_LOGIN));
            String password_s=cursor.getString(cursor.getColumnIndexOrThrow(vc_contract.ADMIN.COLUMN_PASSWORD));
            if(login_e.equals(login_s)&&password_e.equals(password_s))
            {
                a=true;
                break;
            }
        }
        if(a)
        {
            Intent i=new Intent(MainActivity.this,Spinner_screen.class);
            startActivity(i);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"INVALID LOGIN OR PASSWORD",Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }
}
