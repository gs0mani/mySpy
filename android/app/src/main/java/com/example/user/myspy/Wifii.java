package com.example.user.myspy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Wifii extends AppCompatActivity {
    TextView mainText, connText;
    WifiManager mainWifi;
    WifiReceiver receiverWifi;
    List<ScanResult> wifiList;
    StringBuilder sb = new StringBuilder();

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wifii);
        mainText = (TextView) findViewById(R.id.mainText);
        connText = (TextView) findViewById(R.id.connText);

        mainText.setMovementMethod(new ScrollingMovementMethod());
        connText.setMovementMethod(new ScrollingMovementMethod());
        // Initiate wifi service manager
        mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        // Check for wifi is disabled
        if (mainWifi.isWifiEnabled() == false)
        {
            // If wifi disabled then enable it
            Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled",
                    Toast.LENGTH_LONG).show();

            mainWifi.setWifiEnabled(true);
        }


        wifiList = mainWifi.getScanResults();
        String others= "";
        for (ScanResult scanResult: wifiList)
        {
            others+="\nSSID:"+scanResult.SSID;
            others+="\nBSSID:"+scanResult.BSSID;
            others+="\nchannel:"+scanResult.channelWidth;
        }
        mainText.setText(others);


        // wifi scaned value broadcast receiver
        receiverWifi = new WifiReceiver();

        // Register broadcast receiver
        // Broacast receiver will automatically call when number of wifi connections changed
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        mainWifi.startScan();
        mainText.setText("Starting Scan...");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Refresh");
        return super.onCreateOptionsMenu(menu);
    }

//    public boolean onMenuItemSelected(int featureId, MenuItem item) {
//        mainWifi.startScan();
//        mainText.setText("Starting Scan");
//        return super.onMenuItemSelected(featureId, item);
//    }

    protected void onPause() {
        unregisterReceiver(receiverWifi);
        super.onPause();
    }

    protected void onResume() {
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    // Broadcast receiver class called its receive method
    // when number of wifi connections changed

    class WifiReceiver extends BroadcastReceiver {

        // This method call when number of wifi connections changed
        public void onReceive(Context c, Intent intent) {

            String info="Connected wifi:";
            WifiInfo wifiInfo = mainWifi.getConnectionInfo();
            if(String.valueOf(wifiInfo.getSupplicantState()).equals("COMPLETED"))
            {
                info+="\nSSID:"+wifiInfo.getSSID();
                info+="\nRSSI:"+wifiInfo.getRssi();
                info+="\nBSSID:"+wifiInfo.getBSSID();
                info+="\nFreq:"+wifiInfo.getFrequency();
                info+="\nSpeed:"+wifiInfo.getLinkSpeed();
                info+="\nIP:"+wifiInfo.getIpAddress();
                connText.setText(info);

            }
            else
            {
                Toast.makeText(c, "Please connect to a wifi ", Toast.LENGTH_SHORT).show();
            }

            sb = new StringBuilder();
            wifiList = mainWifi.getScanResults();
            sb.append("\n        Number Of Wifi connections :"+wifiList.size()+"\n\n");

            for(int i = 0; i < wifiList.size(); i++){

                sb.append(new Integer(i+1).toString() + ". ");
//                sb.append((wifiList.get(i)).toString());
                sb.append("\nSSID:"+wifiList.get(i).SSID);
                sb.append("\nBSSID:"+wifiList.get(i).BSSID);
                sb.append("\nSignal(dBm):"+wifiList.get(i).level);
                sb.append("\nFreq:"+wifiList.get(i).frequency);
                sb.append("\nChannel:"+wifiList.get(i).channelWidth);
                sb.append("\n\n");
            }

            mainText.setText(sb);
        }

    }


}

