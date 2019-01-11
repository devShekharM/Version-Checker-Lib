package com.example.versioncheckerlib;

import android.os.AsyncTask;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class VersionChecker {

    private static String latestVersion;

    public static void check(String appUrl, String version) {
        new GetLatestVersionAsync(appUrl, version).execute();
    }

    static class GetLatestVersionAsync extends AsyncTask<String, String, JSONObject> {

        //private Context mContext;
        //private ProgressDialog progressDialog;
        private String currentVersion, url;


        GetLatestVersionAsync(String version, String url) {
            this.url = url;
            this.currentVersion = version;
        }

        /*
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
                Document doc = Jsoup.connect(url).get();
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
