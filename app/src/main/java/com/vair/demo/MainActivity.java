package com.vair.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vair.myapplication.DownloadApk;

public class MainActivity extends AppCompatActivity {
      Button checkUpdateBtn;
    private static final int MY_PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 1001;

  /*  public class BG extends AsyncTask<String, Void,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("BG Tasks","onPreExecute");
        }
        @Override
        protected String doInBackground(String... strings) {
            Log.e("BG Tasks","doInBackground");
            String background="going";
            return  background;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("BG Tasks","onPostExecute");
            Log.e("BG Tasks","onPostExecute-------------"+s);
        }

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  BG bg=new BG();
       // bg.execute();

        checkUpdateBtn=findViewById(R.id.checkUpdateBtn);
        checkUpdateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

               checkWriteExternalStoragePermission();
            }
        });
    }

    private void checkUpdate() {
        DownloadApk downloadApk = new DownloadApk(MainActivity.this);

        // For starting download call the method startDownloadingApk() by passing the URL and the optional filename
        downloadApk.startDownloadingApk("https://github.com/Piashsarker/AndroidAppUpdateLibrary/raw/master/app-debug.apk", "Update 2.0");
    }



    private void checkWriteExternalStoragePermission() {

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // If we have permission than we can Start the Download the task
            checkUpdate();
        } else {
            //  If we don't have permission than requesting  the permission
            requestWriteExternalStoragePermission();
        }
    }

    private void requestWriteExternalStoragePermission() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,  new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE);
        } else{
            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==MY_PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE && grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            checkUpdate();
        } else {
            Toast.makeText(MainActivity.this, "Permission Not Granted.", Toast.LENGTH_SHORT).show();
        }
    }

}