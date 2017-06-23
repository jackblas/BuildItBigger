package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


/**
 * MainActivityFragment - free version with an Interstitial ad.
 */
public class MainActivityFragment extends Fragment {

    ProgressBar spinner;
    InterstitialAd mInterstitialAd;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        spinner=(ProgressBar)root.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);

        Button button = (Button) root.findViewById(R.id.tell_joke_button);

        // Create an InterstitialAd object
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(getString(R.string.your_physical_device_id)).build());

        // Set an AdListener to reload the ad if closed by the user
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {

                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });


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

        //Optional Task 1 - Interstitial Ad
        if (mInterstitialAd.isLoaded()) {
            Log.d("TAG", "The interstitial was loaded.");
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }

        new JokerAsyncTask(this).execute(getContext());
    }
}
