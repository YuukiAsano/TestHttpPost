package com.example.testfileupload;

import java.io.File;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class UploadAsyncTask extends AsyncTask < String, Integer, Integer> {
	
	ProgressDialog dialog;
	Context context;
	
	public UploadAsyncTask(Context context){
		this.context = context;
	}

	@Override
	protected Integer doInBackground(String... params) {
		try {
			
		      String fileName = "/mnt/sdcard/test.mp4";
		      
		      HttpClient httpClient = new DefaultHttpClient();
		      //HttpPost httpPost = new HttpPost("http://219.94.251.92/index.php/upload");
		      HttpPost httpPost = new HttpPost("http://219.94.251.92/index.php/upload");
		      ResponseHandler<String> responseHandler =
		        new BasicResponseHandler();
		      MultipartEntity multipartEntity =
		        new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
		      
		      File file = new File(fileName);
		      FileBody fileBody = new FileBody(file, "video/mp4");
		      StringBody event = new StringBody("test1");
		      StringBody volume = new StringBody("1.1");
		      StringBody pressure = new StringBody("1.1");
		      StringBody accx = new StringBody("2.2");
		      StringBody accy = new StringBody("3.3");
		      StringBody accz = new StringBody("4.4");
		      
		      multipartEntity.addPart("movie", fileBody);
		      multipartEntity.addPart("event", event);
		      multipartEntity.addPart("volume", volume);
		      multipartEntity.addPart("pressure", pressure);
		      multipartEntity.addPart("accx", accx);
		      multipartEntity.addPart("accy", accy);
		      multipartEntity.addPart("accz", accz);
		      
		      httpPost.setEntity(multipartEntity);
		      httpClient.execute(httpPost, responseHandler);
		    } catch (ClientProtocolException e) {
		    	Log.e("UploadAsyncTask",e.toString());
		      e.printStackTrace();
		    } catch (IOException e) {
		    	Log.e("UploadAsyncTask",e.toString());
		      e.printStackTrace();
		    }
		    
	    return 0;
	}
	 @Override
	  protected void onPostExecute(Integer result) {
	    if(dialog != null){
	      dialog.dismiss();
	    }
	  }

	  @Override
	  protected void onPreExecute() {
		  /*
	    dialog = new ProgressDialog(context);
	    dialog.setTitle("Please wait");
	    dialog.setMessage("Uploading...");
	    dialog.show();
	    */
	  }  
 }