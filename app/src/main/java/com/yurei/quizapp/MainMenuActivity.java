// MainMenu.java
package com.yurei.quizapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    private Button btnStartQuiz, btnViewScores, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);

        // Initialize UI components
        btnStartQuiz = findViewById(R.id.btnStartQuiz);
        btnViewScores = findViewById(R.id.btnViewScores);
        btnLogout = findViewById(R.id.btnLogout);

        // Set up button click listeners
        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start quiz activity
                Intent intent = new Intent(MainMenuActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        btnViewScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // View scores activity
                Intent intent = new Intent(MainMenuActivity.this, ViewScoresActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logout and return to login screen
                Intent intent = new Intent(MainMenuActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

