// DatabaseHelper.java
package com.yurei.quizapp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database name and version
    private static final String DATABASE_NAME = "QuizApp.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_QUESTIONS = "questions";
    private static final String TABLE_SCORES = "scores";
    private static final String TABLE_USERS = "users";

    // Questions table columns
    private static final String COLUMN_QUESTION_ID = "id";
    private static final String COLUMN_QUESTION_TEXT = "question";
    private static final String COLUMN_OPTION_A = "option_a";
    private static final String COLUMN_OPTION_B = "option_b";
    private static final String COLUMN_OPTION_C = "option_c";
    private static final String COLUMN_OPTION_D = "option_d";
    static final String COLUMN_ANSWER = "answer";

    // Scores table columns
    private static final String COLUMN_SCORE_ID = "score_id";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_SCORE = "score";
    private static final String COLUMN_DATE = "date";

    // Users table columns
    private static final String COLUMN_USER_ID_COL = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    // Create table queries
    private static final String CREATE_TABLE_QUESTIONS = "CREATE TABLE " + TABLE_QUESTIONS + "("
            + COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_QUESTION_TEXT + " TEXT,"
            + COLUMN_OPTION_A + " TEXT,"
            + COLUMN_OPTION_B + " TEXT,"
            + COLUMN_OPTION_C + " TEXT,"
            + COLUMN_OPTION_D + " TEXT,"
            + COLUMN_ANSWER + " TEXT" + ")";

    private static final String CREATE_TABLE_SCORES = "CREATE TABLE " + TABLE_SCORES + "("
            + COLUMN_SCORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_ID + " INTEGER,"
            + COLUMN_SCORE + " INTEGER,"
            + COLUMN_DATE + " TEXT" + ")";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_USER_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_PASSWORD + " TEXT" + ")";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables in the database
        db.execSQL(CREATE_TABLE_QUESTIONS);
        db.execSQL(CREATE_TABLE_SCORES);
        db.execSQL(CREATE_TABLE_USERS);

        // Populate the database with some sample questions
        insertQuestions(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade database schema, if needed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Method to insert questions into the database
    private void insertQuestions(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        // Question 1
        values.put(COLUMN_QUESTION_TEXT, "What is the capital of France?");
        values.put(COLUMN_OPTION_A, "Paris");
        values.put(COLUMN_OPTION_B, "London");
        values.put(COLUMN_OPTION_C, "Rome");
        values.put(COLUMN_OPTION_D, "Berlin");
        values.put(COLUMN_ANSWER, "A");
        db.insert(TABLE_QUESTIONS, null, values);

        // Question 2
        values.clear();
        values.put(COLUMN_QUESTION_TEXT, "Which planet is known as the Red Planet?");
        values.put(COLUMN_OPTION_A, "Earth");
        values.put(COLUMN_OPTION_B, "Mars");
        values.put(COLUMN_OPTION_C, "Venus");
        values.put(COLUMN_OPTION_D, "Jupiter");
        values.put(COLUMN_ANSWER, "B");
        db.insert(TABLE_QUESTIONS, null, values);

        // Question 3
        values.clear();
        values.put(COLUMN_QUESTION_TEXT, "What is the largest ocean on Earth?");
        values.put(COLUMN_OPTION_A, "Atlantic Ocean");
        values.put(COLUMN_OPTION_B, "Indian Ocean");
        values.put(COLUMN_OPTION_C, "Pacific Ocean");
        values.put(COLUMN_OPTION_D, "Southern Ocean");
        values.put(COLUMN_ANSWER, "C");
        db.insert(TABLE_QUESTIONS, null, values);

        // Question 4
        values.clear();
        values.put(COLUMN_QUESTION_TEXT, "Who wrote 'Hamlet'?");
        values.put(COLUMN_OPTION_A, "Charles Dickens");
        values.put(COLUMN_OPTION_B, "William Shakespeare");
        values.put(COLUMN_OPTION_C, "Mark Twain");
        values.put(COLUMN_OPTION_D, "Leo Tolstoy");
        values.put(COLUMN_ANSWER, "B");
        db.insert(TABLE_QUESTIONS, null, values);

        // Question 5
        values.clear();
        values.put(COLUMN_QUESTION_TEXT, "What is the chemical symbol for water?");
        values.put(COLUMN_OPTION_A, "H2O");
        values.put(COLUMN_OPTION_B, "CO2");
        values.put(COLUMN_OPTION_C, "O2");
        values.put(COLUMN_OPTION_D, "N2");
        values.put(COLUMN_ANSWER, "A");
        db.insert(TABLE_QUESTIONS, null, values);
    }

    // Method to get all questions from the database
    public Cursor getAllQuestions() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_QUESTIONS, null);
    }

    // Method to save a user's score in the database
    public void saveUserScore(int userId, int score, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_SCORE, score);
        values.put(COLUMN_DATE, date);
        db.insert(TABLE_SCORES, null, values);
    }

    // Additional methods to manage users, retrieve scores, etc., can be added here.
}

