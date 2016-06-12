package com.example.createxml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	private List<Sms> smslist;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		smslist = new ArrayList<Sms>();
		for(int i=0;i<5;i++){
			Sms sms = new Sms();
			sms.setAdress("1008"+i);
			sms.setBody("hello"+i);
			sms.setDate("201"+i);
			smslist.add(sms);
		}
		
	}
	public void click(View v){
		try {
			
			XmlSerializer ser = Xml.newSerializer();
			File file = new File(Environment.getExternalStorageDirectory(),"sms_bac2.xml");
			FileOutputStream fos = new FileOutputStream(file);
			ser.setOutput(fos, "utf-8");
			
			ser.startDocument("utf-8", true);
			
			ser.startTag(null, "smss");
			
			for (Sms sms : smslist) {
				ser.startTag(null, "sms");
				
				ser.startTag(null, "adress");
				ser.text(sms.getAdress());
				ser.endTag(null, "adress");
				
				ser.startTag(null, "body");
				ser.text(sms.getBody());
				ser.endTag(null, "body");
				
				ser.startTag(null, "date");
				ser.text(sms.getDate());
				ser.endTag(null, "date");
				
				ser.endTag(null, "sms");
			}
			
			ser.endTag(null, "smss");
			
			ser.endDocument();
			fos.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
