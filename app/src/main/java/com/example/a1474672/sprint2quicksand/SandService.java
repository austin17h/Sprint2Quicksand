package com.example.a1474672.sprint2quicksand;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class SandService extends IntentService {
    final static String ACTION_KEY = "action_key";
    public SandService ()
    {
        super("SandService");
    }
    @Override
    protected void onHandleIntent(Intent intent_from_activity) {

        // THIS METHOD IS CALLED WHEN onClick in the activity triggers the start of the service
        Log.i("tag", "GOT MESSAGE!");
        respondToActivity();
    }

    public int operationQuicksand() {
        int x = 0;
        int y = 0;
        int z = 0;

        while (x<50000) {
            x++;
            y = 0;
            while (y<x) {
                y++;
                z += y;
            }
        }
        return z;
    }
    private void respondToActivity() {

        // create a new intent to send data back to the activity
        Intent intent_from_service = new Intent();

        // define a key that will be paired to the intent
        intent_from_service.setAction(ACTION_KEY);

        // optionally attach data to the intent.
        // the key implementation here is weak.
        intent_from_service.putExtra("super_sand", operationQuicksand());

        // send the local broadcast
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent_from_service);
    }
}
