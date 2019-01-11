package com.example.shekharmangrule.appupgradedemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.versioncheckerlib.VersionChecker;

public class MainActivity extends AppCompatActivity {

    String currentVersion, latestVersion;
    String APP_URL = "https://play.google.com/store/apps/details?id=info.mta.mymta&hl=en_IN";
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCurrentVersion();
    }

    private void getCurrentVersion(){
        PackageManager pm = this.getPackageManager();
        PackageInfo pInfo = null;

        try {
            pInfo =  pm.getPackageInfo(this.getPackageName(),0);

        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }
        currentVersion = pInfo.versionName;

        VersionChecker.check(APP_URL, currentVersion);
        //new GetLatestVersion().execute();
    }

    private void showUpdateDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String title = "MYMTA new update " + latestVersion + " is available. Current version is " + currentVersion;
        builder.setTitle(title);
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse
                        ("market://details?id=" + APP_URL)));
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //background.start();
            }
        });

        builder.setCancelable(false);
        dialog = builder.show();
    }

}
