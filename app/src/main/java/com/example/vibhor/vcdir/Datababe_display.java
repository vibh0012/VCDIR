package com.example.vibhor.vcdir;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vibhor.vcdir.databs.vcDbHelper;
import com.example.vibhor.vcdir.databs.vc_contract;

public class Datababe_display extends AppCompatActivity {

    vcDbHelper new_helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datababe_display);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new_helper=new vcDbHelper(this);
        displaycursor();
    }
    void displaycursor()
    {
        String projection[]=
                {
                        vc_contract.MainEntry._ID,
                        vc_contract.MainEntry.COLUMN_REGION,
                        vc_contract.MainEntry.COLUMN_SITE,
                        vc_contract.MainEntry.COLUMN_SITE_NAME,
                        vc_contract.MainEntry.COLUMN_VCP_IP,
                        vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NO,
                        vc_contract.MainEntry.COLUMN_IT_COORDINATOR_CELL_NO,
                        vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NAME,
                        vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMAIL
                };
        SQLiteDatabase dbs=new_helper.getReadableDatabase();
        MyApplication gv = (MyApplication) getApplicationContext();
        String where;
        String selec_args[];
        if(!gv.get_site().matches("ALL")) {
             where = vc_contract.MainEntry.COLUMN_REGION + " = ? AND " + vc_contract.MainEntry.COLUMN_SITE + "=?";
            selec_args = new String[]{gv.get_region(), gv.get_site()};
        }
        else
        {
            where= vc_contract.MainEntry.COLUMN_REGION+" =? ";
            selec_args=new String[]{gv.get_region()};
        }
        //String[] args = {gv.get_region()};
        Log.v("Datababe_display","we have"+gv.get_region()+","+gv.get_site());
        //String where = "TAG1=? OR TAG2=? OR TAG3=? OR TAG4=? OR TAG5=?";
        //String[] args = {"tagname", "tagname", "tagname", "tagname", "tagname"};
        Cursor cursr=dbs.query(vc_contract.MainEntry.TABLE_NAME,projection,where,selec_args,null,null,null);
        ListView view=(ListView)findViewById(R.id.list);
        DatabaseCursorAdapter adap=new DatabaseCursorAdapter(this,cursr);
        view.setAdapter(adap);
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor list_cursor = (Cursor)adapterView.getItemAtPosition(i);
                Intent list_intent=new Intent(Datababe_display.this,final_screen.class);
                list_intent.putExtra("phone",list_cursor.getString(list_cursor.getColumnIndexOrThrow(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_CELL_NO)));
                list_intent.putExtra("email",list_cursor.getString(list_cursor.getColumnIndexOrThrow(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMAIL)));
                startActivity(list_intent);
            }
        });
    }
}
