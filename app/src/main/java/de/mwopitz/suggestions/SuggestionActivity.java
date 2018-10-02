package de.mwopitz.suggestions;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;
import de.mwopitz.suggestions.data.Suggestion;
import de.mwopitz.suggestions.databinding.SuggestionActivityBinding;

/**
 * The suggestion activity displays a single suggestion, at some point maybe even with a nice
 * description, an image gallery, and maybe some web links. Currently, it's only the suggestion
 * title...
 */
public class SuggestionActivity extends AppCompatActivity {

    private static final String LOG_TAG = "SuggestionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final SuggestionActivityBinding binding =
                DataBindingUtil.setContentView(this, R.layout.suggestion_activity);

        setSupportActionBar(findViewById(R.id.toolbar));
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        final String categoryId = getIntent().getStringExtra(MainActivity.EXTRA_CATEGORY_ID);
        final List<Suggestion.Difficulty> difficulties = getSelectedDifficulties();

        AppDataViewModel model = ViewModelProviders.of(this).get(AppDataViewModel.class);
        binding.setSuggestion(model.getSuggestionPlaceholder()); // TODO: Crashes without placeholder. Investigate.

        model.getSuggestionsForUserPrefs(categoryId, difficulties).observe(this, suggestions -> {
            if (suggestions != null && suggestions.size() > 0) {
                int rand = (new Random()).nextInt(suggestions.size());
                binding.setSuggestion(suggestions.get(rand));
            } else {
                Log.w(LOG_TAG, "No suggestions found for category \"" + categoryId + "\"");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_toolbar, menu);
        return true;
    }

    /**
     * Little helper method to offload some code from {@link SuggestionActivity#onCreate(Bundle)}.
     *
     * @return All difficulties that are selected via checkbox from the settings screen.
     */
    private List<Suggestion.Difficulty> getSelectedDifficulties() {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final ArrayList<Suggestion.Difficulty> selectedDifficulties = new ArrayList<>();

        // There's probably a more elegant way to to this.
        // But maybe not in Java 8...
        if (prefs.getBoolean(SettingsActivity.KEY_PREF_EASY_DIFFICULTY_CHECKED, false)) {
            selectedDifficulties.add(Suggestion.Difficulty.EASY);
        }
        if (prefs.getBoolean(SettingsActivity.KEY_PREF_MEDIUM_DIFFICULTY_CHECKED, false)) {
            selectedDifficulties.add(Suggestion.Difficulty.MEDIUM);
        }
        if (prefs.getBoolean(SettingsActivity.KEY_PREF_HARD_DIFFICULTY_CHECKED, false)) {
            selectedDifficulties.add(Suggestion.Difficulty.HARD);
        }

        return selectedDifficulties;
    }
}
