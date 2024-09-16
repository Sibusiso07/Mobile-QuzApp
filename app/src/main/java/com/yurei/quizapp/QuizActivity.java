// QuizActivity.java
package com.yurei.quizapp;

import static com.yurei.quizapp.DatabaseHelper.COLUMN_ANSWER;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuestion;
    private RadioGroup rgOptions;
    private RadioButton rbOptionA, rbOptionB, rbOptionC, rbOptionD;
    private Button btnSubmit;
    private DatabaseHelper dbHelper;
    private Cursor cursor;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        // Initialize UI components
        tvQuestion = findViewById(R.id.tvQuestion);
        rgOptions = findViewById(R.id.rgOptions);
        rbOptionA = findViewById(R.id.rbOptionA);
        rbOptionB = findViewById(R.id.rbOptionB);
        rbOptionC = findViewById(R.id.rbOptionC);
        rbOptionD = findViewById(R.id.rbOptionD);
        btnSubmit = findViewById(R.id.btnSubmit);

        dbHelper = new DatabaseHelper(this);

        // Fetch questions from the database
        cursor = dbHelper.getAllQuestions();
        if (cursor.moveToFirst()) {
            loadQuestion();
        }

        // Set up submit button click listener
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check the selected answer and update score
                checkAnswer();

                // Load the next question or finish the quiz
                if (cursor.moveToNext()) {
                    loadQuestion();
                } else {
                    // Quiz finished
                    saveScore();
                    Toast.makeText(QuizActivity.this, "Quiz Finished! Your Score: " + score, Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    // Load a question from the database
    @SuppressLint("Range")
    private void loadQuestion() {
        tvQuestion.setText(cursor.getString(cursor.getColumnIndex("question")));
        rbOptionA.setText(cursor.getString(cursor.getColumnIndex("option_a")));
        rbOptionB.setText(cursor.getString(cursor.getColumnIndex("option_b")));
        rbOptionC.setText(cursor.getString(cursor.getColumnIndex("option_c")));
        rbOptionD.setText(cursor.getString(cursor.getColumnIndex("option_d")));
        rgOptions.clearCheck();
    }

    // Check the selected answer
    private void checkAnswer() {
        int selectedId = rgOptions.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);

        if (selectedRadioButton != null) {
            String selectedAnswer = selectedRadioButton.getText().toString().trim(); // Trim any extra spaces
            @SuppressLint("Range") String correctAnswer = cursor.getString(cursor.getColumnIndex(COLUMN_ANSWER)).trim(); // Trim any extra spaces

            if (selectedAnswer.equalsIgnoreCase(correctAnswer)) {
                score++;
            }
        }
    }

    // Save the user's score to the database
    private void saveScore() {
        // For this example, we're using a hardcoded user ID and date. You can replace this with dynamic values.
        dbHelper.saveUserScore(1, score, "2024-09-13"); // Placeholder for user ID and current date
    }
}
