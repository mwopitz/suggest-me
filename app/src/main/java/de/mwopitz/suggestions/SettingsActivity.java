package de.mwopitz.suggestions;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    public static final String KEY_PREF_EASY_DIFFICULTY_CHECKED = "easy_difficulty_checked";
    public static final String KEY_PREF_MEDIUM_DIFFICULTY_CHECKED = "medium_difficulty_checked";
    public static final String KEY_PREF_HARD_DIFFICULTY_CHECKED = "hard_difficulty_checked";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
