package com.example.vibhor.vcdir.databs;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class vc_contract {
    private vc_contract() {
    }

    public static final String CONTENT_AUTHORITY = "com.example.vibhor.vcdir";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MAIN = "main_table";
    public static final String PATH_ADMIN = "admin";

    public static final class MainEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_MAIN);
        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MAIN;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MAIN;
        public final static String TABLE_NAME = "main_table";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_REGION = "region";
        public final static String COLUMN_SITE = "site";
        public final static String COLUMN_SITE_NAME = "site_name";
        public final static String COLUMN_VCP_IP = "vcp_ip";
        public final static String COLUMN_IT_COORDINATOR_EMP_NO = "it_coordinator_emp_no";
        public final static String COLUMN_IT_COORDINATOR_EMP_NAME = "it_coordinator_emp_name";
        public final static String COLUMN_IT_COORDINATOR_CELL_NO = "it_coordinator_cell_no";
        public final static String COLUMN_IT_COORDINATOR_EMAIL = "it_coordinator_email";
    }

    public static final class ADMIN implements BaseColumns {
        public static final Uri CONTENT_URI_2 = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ADMIN);

        public static final String CONTENT_LIST_TYPE_2 =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ADMIN;

        public static final String CONTENT_ITEM_TYPE_2 =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ADMIN;
        public final static String TABLE_NAME_2 = "admin";
        public final static String COLUMN_ID_2 = BaseColumns._ID;
        public final static String COLUMN_LOGIN = "login";
        public final static String COLUMN_PASSWORD = "password";


    }
}