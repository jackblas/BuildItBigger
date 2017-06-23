package com.udacity.gradle.builditbigger;

/**
 * Created by jackb on 6/10/2017.
 * This class was created so the AsyncTask can be tested without existence of
 * UI elements.
 */

public class JokerAsyncTaskTest extends JokerAsyncTask {

    @Override
    protected void onPreExecute() {
        //super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        //super.onPostExecute(s);
    }
}
