package net.cekage.playstoreornot;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class YesOrNoActivity extends Activity {

    private static final String TAG = "YesOrNoActivity";
    private static final String DROPBOX_PKGNAME="com.dropbox.android";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yes_or_no_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkIfInstalledOrNotViaPlayStore(DROPBOX_PKGNAME)) {
            TextView tv = (TextView) findViewById(R.id.tvYoNDropBox);
            tv.setText(R.string.yes);
        }
        if (checkIfInstalledOrNotViaPlayStore("net.cekage.playstoreornot")) {
            TextView tv = (TextView) findViewById(R.id.tvYoNThisApp);
            tv.setText(R.string.yes);
        }
    }

    private boolean checkIfInstalledOrNotViaPlayStore(String packagename) {
        String outputedLastLine = runAsAppCmd("pm list packages -i "+packagename+"");
        return (outputedLastLine.matches(".*installer=com.android.vending$"));
    }


    private static BufferedReader getOutput(Process p) {
        return new BufferedReader(new InputStreamReader(p.getInputStream()));
    }

    /*
        runAsAppCmd exec cmdLine and return the last string
     */
    private String runAsAppCmd(String cmdLine) {
        Log.d(TAG,"running : "+cmdLine);

        String returnedLine="";
        try {
            Process p = Runtime.getRuntime().exec(cmdLine);
            BufferedReader output = getOutput(p);
            String lastLine;
            while (( lastLine = output.readLine()) != null) {
                returnedLine=lastLine;
            }

            int res = p.waitFor();
            Log.d(TAG, "exec returned: " + res);
            if (res != 0)
                throw new Exception("fail in runAsAppCmd(" + cmdLine + ")");
        } catch (Exception e) {
            Log.d(TAG, "exec(): " + e);
                Toast.makeText(YesOrNoActivity.this, "exec(): " + e, Toast.LENGTH_LONG).show();
        }
        return returnedLine;

    }

}
