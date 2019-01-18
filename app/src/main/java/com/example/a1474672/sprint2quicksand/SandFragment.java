package com.example.a1474672.sprint2quicksand;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SandFragment extends Fragment implements View.OnClickListener{

        View mRootView;
        Button mButton;
        TextView mTextView;
        SandServiceReceiver mSandReceiver;
        public SandFragment() {
            // Required empty public constructor

        }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mRootView =  inflater.inflate(R.layout.sand_fragment, container, false);
        mButton = mRootView.findViewById(R.id.b_start_service);
        mButton.setOnClickListener(this);
        mTextView = mRootView.findViewById(R.id.textView);
        mSandReceiver = new SandServiceReceiver();
        return mRootView;
    }
    @Override
    public void onClick(View v) {

        Log.i("tag","STARTING SERVICE");

        // the intent that will start the service
        Intent intent_from_activity_to_service =  new Intent();

        // define the context and the target (the service that will be started)
        intent_from_activity_to_service.setClass(getActivity(), SandService.class);

        // go ahead and start the service
        getActivity().startService(intent_from_activity_to_service);
    }

    @Override
    public void onResume() {
        super.onResume();

        // BROADCAST RECEIVERS ARE REGISTERED IN onResume

        // local broadcasts are used when your app is the sole listener
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getActivity());

        // create a filter for the manager
        //    => i.e. listen to messages with this a specific key from our service class
        IntentFilter filter = new IntentFilter(SandService.ACTION_KEY);

        // register your receiver class with the local broadcast receiver
        manager.registerReceiver(
                mSandReceiver,
                filter);
    }


    @Override
    public void onPause() {
        super.onPause();

        // BROADCAST RECEIVERS ARE UN-REGISTERED IN onPause - lest bad things happen

        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getActivity());
        manager.unregisterReceiver(mSandReceiver);
    }


        class SandServiceReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent_from_service) {

            // get any information embedded in the intent
            int secretNumber = intent_from_service.getIntExtra("super_sand",-1);

            mTextView.setText("Service Value is: "+secretNumber);
            Log.i("Tag", "DATA_IN: "+secretNumber);
        }
    }

}
