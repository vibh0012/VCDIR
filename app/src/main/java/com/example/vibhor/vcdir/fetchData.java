package com.example.vibhor.vcdir;

import android.content.res.AssetManager;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.vibhor.vcdir.databs.vcDbHelper;
import com.example.vibhor.vcdir.databs.vc_contract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
public class fetchData extends AsyncTask<SQLiteDatabase,Void,Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    @Override
    protected Void doInBackground(SQLiteDatabase...p) {
        try {
            SQLiteDatabase db=p[0];
            //SQLiteDatabase db=json_helper.getWritableDatabase();
            URL url = new URL("https://api.myjson.com/bins/g8anu");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper(db, vc_contract.MainEntry.TABLE_NAME);
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
                    JSONArray JA = new JSONArray(data);
                    for (int i = 0; i < JA.length(); i++) {
                        JSONObject JO = (JSONObject) JA.get(i);
                        ih.prepareForInsert();
                        ih.bind(region_Col, JO.get("Region").toString());
                        ih.bind(site_col,JO.get("Site").toString());
                        ih.bind(sitename_col,JO.get("Site Name").toString());
                        ih.bind(vcp_ip_col,JO.get("VCP IP").toString());
                        ih.bind(emp_no_col,JO.get("IT COORDINATOR EMP NO").toString());
                        ih.bind(emp_name_col,JO.get("IT COORDINATOR EMP NAME").toString());
                        ih.bind(cell_no_col,JO.get("IT COORDINATOR CELL NO").toString());
                        ih.bind(email_col,JO.get("IT COORDINATOR EMAIL").toString());
                        ih.execute();
                    }
                }
                catch (Exception e)
                { }
                finally {
                    ih.close();  // See comment below from Stefan Anca
                }
            }
         catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
        catch (JSONException e) {
            e.printStackTrace();
        }*/
        return null;
    }
}