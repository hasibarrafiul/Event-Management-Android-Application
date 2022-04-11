package com.techrz.moblieprogramminglab;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class MyAttendanceActivity extends Activity {

    private WebView webView;
    private String URL = "https://www.muthosoft.com/univ/attendance/report.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_attendance);

        webView = findViewById(R.id.webView);

        String[] keys = {"CSE489-Lab", "year", "semester", "course", "section", "sid"};
        String[] values = {"true", "2022", "1", "CSE489", "2", "2019160036"};
        httpRequest(keys, values);
    }
    @SuppressLint("StaticFieldLeak")
    private void httpRequest(final String keys[], final String values[]){
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... param){
                try{
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    for(int i=0; i<keys.length; i++){
                        params.add(new BasicNameValuePair(keys[i], values[i]));
                    }
                    String data = JSONParser.getInstance().makeHttpRequest(URL, "POST", params);
                    return data;
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(String data){

                if(data!=null){
                    try{
                        webView.loadDataWithBaseURL(null, data, "text/html", "UTF-8",  null);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        }.execute();
    }
}