package com.example.ratingbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RatingBar mRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRatingBar = findViewById(R.id.ratingBar);
        String rating = String.valueOf(mRatingBar.getRating());
        Toast.makeText(this, rating, Toast.LENGTH_SHORT).show();
    }
}
