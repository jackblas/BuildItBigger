package com.jokes.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokerActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "com.jokes.android.JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joker);

        Intent intent = getIntent();
        String jokeStr = intent.getStringExtra(EXTRA_JOKE);
        TextView textView = (TextView) findViewById(R.id.jokeTextView);
        textView.setText(jokeStr);
    }
}
