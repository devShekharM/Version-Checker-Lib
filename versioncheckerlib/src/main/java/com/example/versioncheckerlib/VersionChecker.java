package com.example.versioncheckerlib;

import android.os.AsyncTask;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class VersionChecker {

    private static String appUrl, currentVersion, latestVersion;

    public static void check() {
        new GetLatestVersionAsync().execute();
    }

    static class GetLatestVersionAsync extends AsyncTask<String, String, JSONObject> {

        //private Context mContext;
        //private ProgressDialog progressDialog;

        /*
        GetLatestVersionAsync(Context context, String version, String url) {
            this.mContext = context;
            this.currentVersion = version;
            this.url = url;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage("Checking for updates...");
            progressDialog.show();
        }
        */

        @Override
        protected JSONObject doInBackground(String... strings) {
            try {
                //It retrieves the latest version by scraping the content of current version from play store at runtime
                Document doc = Jsoup.connect(appUrl).get();
                latestVersion = doc.getElementsByClass("htlgb").get(6).text();

            } catch (Exception e) {
                e.printStackTrace();

            }
            return new JSONObject();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            /*if (progressDialog.isShowing())
                progressDialog.dismiss();*/

            if (latestVersion != null) {
                if (!currentVersion.equalsIgnoreCase(latestVersion)) {
                    // New update available here
                }
            }
            /*else
                background.start();*/
            super.onPostExecute(jsonObject);
        }
    }
}
