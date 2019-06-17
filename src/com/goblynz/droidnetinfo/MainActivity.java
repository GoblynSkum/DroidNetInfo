package com.goblynz.droidnetinfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.net.DhcpInfo;
import android.net.wifi.*;
import android.content.Context;

import com.goblynz.droidnetinfo.R;
import com.stericson.RootTools.RootTools;



public class MainActivity extends Activity {
private TextView content;
private WifiManager wifi;
	@Override
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		content = (TextView) findViewById(R.id.droid_ping_txt);
		CharSequence outText = new String();
		WifiManager wim= (WifiManager) getSystemService(WIFI_SERVICE);
		//List<WifiConfiguration> l =  wim.getConfiguredNetworks(); 
		//WifiConfiguration wc = l.get(0); 
		try {
			WifiInfo info = wifi.getConnectionInfo();
			DhcpInfo dinfo = wim.getDhcpInfo();
			outText = "<table><tt><tr><th><td><b><big>WiFi AP Info</big></b></td></th></tr><br>";
			outText = outText + "<tr><td><b>Connection:</b></td>............<td></td><td> " + info.getSSID() +"</td></tr><br>";
			outText = outText + "<tr><td><b>Speed:</b></td><td>.................</td><td> " + info.getLinkSpeed() +"</td></tr><br>";
			outText = outText + "<tr><td><b>Strength:</b></td><td>..............</td><td> " + info.getRssi() +"</td></tr><br>";
			outText = outText +"<tr><td><b>DHCP Server:</b></td><td>...........</td><td> "+Formatter.formatIpAddress(dinfo.serverAddress) +"</td></tr><br>";
			outText = outText +"<tr><td><b>Lease Time:</b></td><td>............</td><td> "+String.valueOf(dinfo.leaseDuration) +"</td></tr><br>";     
			outText = outText +"<tr><td><b>Net ID:</b></td><td>................</td><td> " + wim.getConnectionInfo().getNetworkId() +"</td></tr><br>";
			outText = outText + "</table><br><br>";
			
			outText = outText + "<table><tr><th><td><b><big>Wireless Info</big></b></td></th></tr><br>";
			outText = outText + "<tr><td><b>IP:</b></td><td>................ " + Formatter.formatIpAddress(info.getIpAddress()) +"</td></tr><br>";
			 outText = outText +"<tr><td><b>Subnet:</b></td><td>............ "+Formatter.formatIpAddress(dinfo.netmask) +"</td></tr><br>";    
			 outText = outText +"<tr><td><b>Gateway:</b></td><td>........... "+Formatter.formatIpAddress(dinfo.gateway) +"</td></tr><br>";    
			 outText = outText + "<tr><td><b>DNS 1:</b></td><td>............. "+Formatter.formatIpAddress(dinfo.dns1) +"</td></tr><br>";
			 outText = outText +"<tr><td><b>DNS 2:</b></td><td>............. "+Formatter.formatIpAddress(dinfo.dns2) +"</td></tr><br>";    
			outText = outText + "<tr><td><b>MAC:</b></td><td>............... "+ info.getMacAddress() +"</td></tr><br>";

			 outText = outText + "</tt></table><br><br>";
			 // cat etc resolv.conf
				if (!RootTools.isAccessGiven()) {
					outText = outText + "<br><i>Root access refused- unable to read suffix search order</i>";
		            
		        }
				else{
					StringBuilder text = new StringBuilder();
					String str = "";
					URL url = new URL("file:///etc/resolv.conf");
					//File file = new File(MyActivity.this.getFilesDir(), "invoice2.xml");
			     	BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			     	while ((str = in.readLine()) != null) {
			     		text.append(str + "<br>");
			    	}
			     in.close();
				outText = outText +"<br>root granted - <b>resolv.conf</b><br><pre> " +text;
				}
		}catch(Exception e){
				outText = outText +"<br><i>"+ e.getMessage()+"</i>";
		}
		content.setText(Html.fromHtml(outText.toString()+"</pre>"));
		
	
   // setContentView(R.layout.activity_hosts);
  
		
		//RetValue
		
		String s = "";
		content.append( s);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void  getInfo (View view){
		
	}

}
