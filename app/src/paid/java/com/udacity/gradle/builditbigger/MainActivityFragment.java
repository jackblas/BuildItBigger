package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


/**
 * MainActivityFragment - paid version without an interstitial ad.
 */
public class MainActivityFragment extends Fragment {

    ProgressBar spinner;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        spinner=(ProgressBar)root.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);

        Button button = (Button) root.findViewById(R.id.tell_joke_button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Step 1 - Make the button display a toast showing a joke
                // retrieved from your Java joke telling library.

                //Joker joker = new Joker();
                //String jokeStr = joker.getJoke();

                //Toast.makeText(this, jokeStr, Toast.LENGTH_SHORT).show();

                //Step 2 - Start Activity that will display a joke passed to it as an intent extra
                //Intent intent = new Intent(getActivity(), JokerActivity.class);
                //intent.putExtra(JokerActivity.EXTRA_JOKE,jokeStr);
                //startActivity(intent);

                //Step 3 - Pull jokes from a GCE development server
                getJokeFromServer();

            }
        });

        return root;
    }

    private void getJokeFromServer(){


        new JokerAsyncTask(this).execute(getContext());
    }
}
