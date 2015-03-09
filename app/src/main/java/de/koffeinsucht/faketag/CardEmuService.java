package de.koffeinsucht.faketag;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class CardEmuService extends HostApduService {

    private HashMap<String, String> cmdMap = null;
    private boolean initialized = false;

    @Override
    public byte[]  processCommandApdu(byte[] apdu, Bundle extras) {
        // is initialized?
        if (!initialized) {
            parseJSONFile();
            initialized = true;
        }

        // get apdu bytes from cmdMap
        byte[] bytes = cmdMap.get(apdu.toString()).getBytes();
        if (bytes != null) {
            return bytes;
        }
        else { // command not supported by json file
            return new byte[]{0x68,0x00}; // => "The request function is not supported by the card."
        }
    }

    @Override
    public void onDeactivated(int reason) {

    }

    public void parseJSONFile() {
        try
        {
            //Load File
            File jsonfile = new File(Environment.getExternalStorageDirectory(), "faketag.json");
            InputStream jsonReader = new FileInputStream(jsonfile);
            int size = jsonReader.available();

            byte[] buffer = new byte[size];
            jsonReader.read(buffer);
            jsonReader.close();
            String json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json); //jsonBuilder.toString());
            for (int index = 0; index < jsonArray.length(); index++) {
                //add values to map
                byte[] request = Base64.decode(jsonArray.getJSONObject(index).getString("request"), Base64.DEFAULT);
                byte[] response = Base64.decode(jsonArray.getJSONObject(index).getString("response"), Base64.DEFAULT);
                cmdMap.put(request.toString(), response.toString());
            }
        } catch (FileNotFoundException e) {
            Log.e("jsonFile", "file not found");
        } catch (IOException e) {
            Log.e("jsonFile", "ioerror");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
