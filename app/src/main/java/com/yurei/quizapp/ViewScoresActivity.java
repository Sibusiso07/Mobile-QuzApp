// ViewScoresActivity.java
package com.yurei.quizapp;


import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewScoresActivity extends AppCompatActivity {

    private ListView lvScores;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_scores_activity);

        // Initialize the ListView and DatabaseHelper
        lvScores = findViewById(R.id.lvScores);
        dbHelper = new DatabaseHelper(this);

        // Load scores from the database
        loadScores();
    }

    // Method to load scores from the SQLite database and display them
    private void loadScores() {
        ArrayList<String> scoresList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Query to retrieve user scores from the database
        Cursor cursor = db.rawQuery("SELECT * FROM scores", null);

        if (cursor.getCount() == 0) {
            // If no scores are found, display a message
            Toast.makeText(this, "No scores available", Toast.LENGTH_SHORT).show();
        } else {
            // Iterate through the cursor to get all scores
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int userId = cursor.getInt(cursor.getColumnIndex("user_id"));
                @SuppressLint("Range") int score = cursor.getInt(cursor.getColumnIndex("score"));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));

                // Format the score as a string and add it to the list
                scoresList.add("User ID: " + userId + ", Score: " + score + ", Date: " + date);
            }
        }
        cursor.close();

        // Display the scores using an ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoresList);
        lvScores.setAdapter(adapter);
    }
}

