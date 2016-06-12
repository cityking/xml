package com.example.createxml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
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
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<smss>");
		for (Sms sms : smslist) {
			sb.append("<sms>");
			
			sb.append("<adress>");
			sb.append(sms.getAdress());
			sb.append("</adress>");
			
			sb.append("<body>");
			sb.append(sms.getBody());
			sb.append("</body>");
			
			sb.append("<date>");
			sb.append(sms.getDate());
			sb.append("</date>");
			
			sb.append("</sms>");
		}
		sb.append("</smss>");
		
		try {
			//记住添加权限
			File file = new File(Environment.getExternalStorageDirectory().getPath(),"sms_bac.xml");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(sb.toString().getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


}
