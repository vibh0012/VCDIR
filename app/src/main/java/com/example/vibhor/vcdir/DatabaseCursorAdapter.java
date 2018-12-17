package com.example.vibhor.vcdir;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.vibhor.vcdir.databs.vc_contract;

/**
 * {@link DatabaseCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of pet data as its data source. This adapter knows
 * how to create list items for each row of pet data in the {@link Cursor}.
 */
public class DatabaseCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link DatabaseCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public DatabaseCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.data_entry,parent,false);
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     *
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView sitename_listview=(TextView)view.findViewById(R.id.d_site_name);
        TextView vcpip_listview=(TextView)view.findViewById(R.id.d_vcpip);

        //TextView empno_listview=(TextView)view.findViewById(R.id.d_emp_no);
        //TextView cellno_listview=(TextView)view.findViewById(R.id.d_cell_no);
        //TextView empname_listview=(TextView)view.findViewById(R.id.d_emp_name);
        //TextView empemail_listview=(TextView)view.findViewById(R.id.d_emp_email);

        String sitename_lv=cursor.getString(cursor.getColumnIndexOrThrow(vc_contract.MainEntry.COLUMN_SITE_NAME));
        String vcpip_lv=cursor.getString(cursor.getColumnIndexOrThrow(vc_contract.MainEntry.COLUMN_VCP_IP));
        Integer empno_lv=cursor.getInt(cursor.getColumnIndexOrThrow(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NO));
        String empno_lv_string=empno_lv.toString();
        Integer cellno_lv=cursor.getInt(cursor.getColumnIndexOrThrow(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_CELL_NO));
        String cellno_lv_string=cellno_lv.toString();
        String empname_lv=cursor.getString(cursor.getColumnIndexOrThrow(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMP_NAME));
        String empemail_lv=cursor.getString(cursor.getColumnIndexOrThrow(vc_contract.MainEntry.COLUMN_IT_COORDINATOR_EMAIL));
        sitename_listview.setText("Sitename : "+sitename_lv);
        vcpip_listview.setText("VCP_IP : "+vcpip_lv+"\n");
        vcpip_listview.append("IT COORDINATOR EMP NO. : "+empno_lv_string+"\n");
        vcpip_listview.append("IT COORDINATOR CELL NO : "+cellno_lv_string+"\n");
        vcpip_listview.append("IT COORDINATOR EMP NAME : "+empname_lv+"\n");
        vcpip_listview.append("IT COORDINATOR EMAIL : "+empemail_lv+"\n");
    }
}
