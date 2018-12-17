package com.example.vibhor.vcdir;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.vibhor.vcdir.databs.vcDbHelper;
import com.example.vibhor.vcdir.databs.vc_contract;

import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


public class Spinner_screen extends AppCompatActivity {

    vcDbHelper helper;
    private Spinner spinner_region;
    public Spinner spinner_site;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_screen);
        Button a=(Button)findViewById(R.id.search_button);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(Spinner_screen.this,Datababe_display.class);
                startActivity(k);
            }
        });
        helper=new vcDbHelper(this);
        /**
        ContentValues v=new ContentValues();
        v.put(vc_contract.MainEntry.COLUMN_REGION,"NR1");
        v.put(vc_contract.MainEntry.COLUMN_SITE,"SR1");
        v.put(vc_contract.MainEntry.COLUMN_SITE_NAME,"delhi");
        v.put(vc_contract.MainEntry.COLUMN_VCP_IP,"1.23.45");
        v.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NO,3412);
        v.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NAME,"saurabh");
        v.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_CELL_NO,987645643);
        v.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMAIL,"bu@pwgd.com");
        Uri URI=getContentResolver().insert(vc_contract.MainEntry.CONTENT_URI,v);
        ContentValues vs=new ContentValues();
        vs.put(vc_contract.MainEntry.COLUMN_REGION,"NR1");
        vs.put(vc_contract.MainEntry.COLUMN_SITE,"SR2");
        vs.put(vc_contract.MainEntry.COLUMN_SITE_NAME,"haryana");
        vs.put(vc_contract.MainEntry.COLUMN_VCP_IP,"1.23.45");
        vs.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NO,3412);
        vs.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NAME,"nikhil");
        vs.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_CELL_NO,987645643);
        vs.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMAIL,"nk@pwgd.com");
        Uri URI_vs=getContentResolver().insert(vc_contract.MainEntry.CONTENT_URI,vs);
        ContentValues vas=new ContentValues();
        vas.put(vc_contract.MainEntry.COLUMN_REGION,"NR2");
        vas.put(vc_contract.MainEntry.COLUMN_SITE,"SR1");
        vas.put(vc_contract.MainEntry.COLUMN_SITE_NAME,"gandhinagar");
        vas.put(vc_contract.MainEntry.COLUMN_VCP_IP,"1.23.46");
        vas.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NO,566412);
        vas.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NAME,"ria");
        vas.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_CELL_NO,987645643);
        vas.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMAIL,"ria@pwgd.com");
        Uri URIa_vas=getContentResolver().insert(vc_contract.MainEntry.CONTENT_URI,vas);
        ContentValues va=new ContentValues();
        va.put(vc_contract.MainEntry.COLUMN_REGION,"NR2");
        va.put(vc_contract.MainEntry.COLUMN_SITE,"SR2");
        va.put(vc_contract.MainEntry.COLUMN_SITE_NAME,"jaipur");
        va.put(vc_contract.MainEntry.COLUMN_VCP_IP,"1.23.46");
        va.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NO,566412);
        va.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NAME,"surbhi");
        va.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_CELL_NO,987645643);
        va.put(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMAIL,"surbhi@pwgd.com");
        Uri URIa=getContentResolver().insert(vc_contract.MainEntry.CONTENT_URI,va);
        */
        setupSpinner();
    }

    /**
    public void insert_bulk(SQLiteDatabase db) {
        DatabaseUtils.InsertHelper ih=new DatabaseUtils.InsertHelper(db, vc_contract.MainEntry.TABLE_NAME);
        // Get the numeric indexes for each of the columns that we're updating
        final int region_Col = ih.getColumnIndex(vc_contract.MainEntry.COLUMN_REGION);
        final int site_col = ih.getColumnIndex(vc_contract.MainEntry.COLUMN_SITE);
        final int sitename_col = ih.getColumnIndex(vc_contract.MainEntry.COLUMN_SITE_NAME);
        final int vcp_ip_col = ih.getColumnIndex(vc_contract.MainEntry.COLUMN_VCP_IP);
        final int emp_no_col = ih.getColumnIndex(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NO);
        final int emp_name_col = ih.getColumnIndex(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NAME);
        final int cell_no_col = ih.getColumnIndex(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_CELL_NO);
        final int email_col = ih.getColumnIndex(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMAIL);
        try {
            AssetManager am=getAssets();// If this line gives you ERROR then try AssetManager am=getActivity().getAssets();
            InputStream is=am.open("dummy_dat_pwgd.xls");
            Workbook wb =Workbook.getWorkbook(is);
            Sheet s=wb.getSheet(0);
            int row =s.getRows();
            for(int i=1;i<row;i++) {
                ih.prepareForInsert();
                Cell z = s.getCell(0, i);
                ih.bind(region_Col,z.getContents());
                Cell l = s.getCell(1, i);
                ih.bind(site_col,l.getContents());
                Cell m = s.getCell(2, i);
                ih.bind(sitename_col,m.getContents());
                Cell n = s.getCell(3, i);
                ih.bind(vcp_ip_col,n.getContents());
                Cell o = s.getCell(4, i);
                ih.bind(emp_no_col,o.getContents());
                Cell p = s.getCell(5, i);
                ih.bind(emp_name_col,p.getContents());
                Cell q = s.getCell(6, i);
                ih.bind(cell_no_col,q.getContents());
                Cell r = s.getCell(7, i);
                ih.bind(email_col,r.getContents());
                ih.execute();
            }
        }

        catch (Exception e)
        {

        }
        finally {
            ih.close();  // See comment below from Stefan Anca
        }

    }*/
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout

        Spinner spinner_region = (Spinner)findViewById(R.id.s_region);

        SQLiteDatabase dbew = helper.getReadableDatabase();
        String proj[]=
                {
                        vc_contract.MainEntry._ID,
                        vc_contract.MainEntry.COLUMN_REGION
                };
        final Cursor c_region=dbew.query(true, vc_contract.MainEntry.TABLE_NAME,proj,null,null, vc_contract.MainEntry.COLUMN_REGION,null,null,null);
        String[] fromColumns_region = {
                vc_contract.MainEntry.COLUMN_REGION
        };
// View IDs to map the columns (fetched above) into
        int[] toViews_region = {
                android.R.id.text1
        };

        SimpleCursorAdapter adapter_region = new SimpleCursorAdapter(
                this, // context
                android.R.layout.simple_spinner_item, // layout file
                c_region , // DB cursor
                fromColumns_region, // data to bind to the UI
                toViews_region, // views that'll represent the data from `fromColumns`
                0
        );
        adapter_region.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_region.setAdapter(adapter_region);
        // Set the integer mSelected to the constant values
        spinner_region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cursor select_cursor_region = (Cursor)parent.getItemAtPosition(position);
                final MyApplication globalVariable = (MyApplication) getApplicationContext();
                globalVariable.set_region(select_cursor_region.getString(select_cursor_region.getColumnIndexOrThrow(vc_contract.MainEntry.COLUMN_REGION)));
                Spinner spinner_site=(Spinner)findViewById(R.id.s_site);
                // Find the ListView which will be populated with the pet data
                SQLiteDatabase db = helper.getReadableDatabase();
                String projection[]=
                        {
                                vc_contract.MainEntry._ID,
                                vc_contract.MainEntry.COLUMN_SITE
                        };
                Log.v("Spinner_screen","no problem till here 1");
                String sss= vc_contract.MainEntry.COLUMN_REGION+"=?";
                String sele[]={globalVariable.get_region()};

                //Cursor cursor2 = db.rawQuery("select DISTINCT " + vc_contract.MainEntry._ID+","+vc_contract.MainEntry.COLUMN_SITE + " from " +
                       // vc_contract.MainEntry.TABLE_NAME+" where "+vc_contract.ADMIN.COLUMN_ID_2 + "=?",
                        //sele);
                 final Cursor c=db.query(true, vc_contract.MainEntry.TABLE_NAME,projection,sss,sele, vc_contract.MainEntry.COLUMN_SITE,null,null,null);
                // Setup an Adapter to create a list item for each row of pet data in the Cursor.
                // Columns from DB to map into the view file
                Log.v("Spinner_screen","no problem till here 2");
                String[] fromColumns = {
                        vc_contract.MainEntry.COLUMN_SITE
                };
// View IDs to map the columns (fetched above) into
                int[] toViews = {
                        android.R.id.text1
                };
                MatrixCursor extras=new MatrixCursor(new String[]{vc_contract.MainEntry._ID, vc_contract.MainEntry.COLUMN_SITE});
                extras.addRow(new String[]{"-1","ALL"});
                Cursor[] cursors={extras,c};
                Cursor extendedCursor=new MergeCursor(cursors);

                SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                        getBaseContext(), // context
                        android.R.layout.simple_spinner_item, // layout file
                        extendedCursor , // DB cursor
                        fromColumns, // data to bind to the UI
                        toViews, // views that'll represent the data from `fromColumns`
                        0
                );
                Log.v("Spinner_screen","no problem till here 3");
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_site.setAdapter(adapter);
                spinner_site.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Cursor select_cursor = (Cursor)adapterView.getItemAtPosition(i);
                        final MyApplication globalVar = (MyApplication) getApplicationContext();
                        globalVar.set_site(select_cursor.getString(select_cursor.getColumnIndexOrThrow(vc_contract.MainEntry.COLUMN_SITE)));
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        final MyApplication globalVar = (MyApplication) getApplicationContext();
                        globalVar.set_site("SR1");
                         }
                });
// Create the list view and bind the adapter
                //countryView.setAdapter(adapter);
                // SpinnerCursorAdapter adapter = new SpinnerCursorAdapter(getApplicationContext(), cursor2);
                //spinner_site.setAdapter(adapter);
                // Apply the adapter to the spinner
                // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Set the integer mSelected to the constant values
                //spinner_site.setOnItemSelectedListener();
            }


            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                final MyApplication globalVariable = (MyApplication) getApplicationContext();
                globalVariable.set_region("NR2");
            }
        });

    }

}
