package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import com.example.jackb.builditbigger.jokeserver.jokesApi.JokesApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.jokes.android.JokerActivity;

import java.io.IOException;

/**
 * Created by jackb on 5/26/2017.
 * Async task to retrieve jokes from the GCE server
 */

public class JokerAsyncTask extends AsyncTask <Context, Void, String> {
    private JokesApi jokesApi = null;
    private Context context;
    private MainActivityFragment activityFragment;

    public JokerAsyncTask(){}

    public JokerAsyncTask(MainActivityFragment activityFragment){
        this.activityFragment=activityFragment;

    }

    @Override
    protected String doInBackground(Context... params) {

        if (jokesApi == null) {
            // Build a new instance of GoogleClient
            JokesApi.Builder builder = new JokesApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    // For testing on a physical device
                    // Change the endpoint root url to point to your computer's ip address
                    //.setRootUrl("http://192.168.1.107:8080/_ah/api/")
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            jokesApi = builder.build();
        }
        context = params[0];

        // Execute service
        try {
            return jokesApi.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    protected void onPreExecute() {
        //Before it starts, touch UI here
        activityFragment.spinner.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(String s) {
        activityFragment.spinner.setVisibility(View.GONE);
        //Start Joker Activity
        Intent intent = new Intent(context, JokerActivity.class);
        intent.putExtra(JokerActivity.EXTRA_JOKE,s);
        context.startActivity(intent);
    }
}
