package de.koffeinsucht.faketag;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
git c
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    private  HashMap<String, String> cmdMap = new HashMap<String, String>();

    /*
     * TODO: Generate a file chooser here to start emulation
     * of xml based config
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: start service here?
        Intent emuIntent = new Intent(this, CardEmuService.class);
        //emuIntent.putExtra("foo", cmdMap);
        startService(emuIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
