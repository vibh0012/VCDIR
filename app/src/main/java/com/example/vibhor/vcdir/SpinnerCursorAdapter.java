package com.example.vibhor.vcdir;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.vibhor.vcdir.databs.vc_contract;

public class SpinnerCursorAdapter extends CursorAdapter {

    public SpinnerCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
     return LayoutInflater.from(context).inflate(R.layout.simple_item,null,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView site_listview=(TextView)view.findViewById(R.id.data_site);
        String lv_site=cursor.getString(cursor.getColumnIndexOrThrow(vc_contract.MainEntry.COLUMN_SITE));
        site_listview.setText(lv_site);
    }
}
