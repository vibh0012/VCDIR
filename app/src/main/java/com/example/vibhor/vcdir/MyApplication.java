package com.example.vibhor.vcdir;

import android.app.Application;

public class MyApplication extends Application {


        private String value_spinner_region;
        private String value_spinner_site;

        public String get_region() {
            return value_spinner_region;
        }
    public String get_site() {
        return value_spinner_site;
    }


    public void set_region(String a) {
            value_spinner_region = a;
        }
    public void set_site(String b) {
        value_spinner_site = b;
    }
}
/**
 * final MyApplication globalVariable = (MyApplication) getApplicationContext();
 globalVariable.set_region(selection);
 final String value_spinner_region = globalVariable.get_region();
 */

/**    final MyApplication globalVariable = (MyApplication) getApplicationContext();
globalVariable.set_region("Android Example context variable");
        You can get those values like this:

final MyApplication globalVariable = (MyApplication) getApplicationContext();
final String value_spinner_region  = globalVariable.get_region();
*/
// set
//((MyApplication) this.getApplication()).setSomeVariable("foo");

// get
  //      String s = ((MyApplication) this.getApplication()).getSomeVariable();
