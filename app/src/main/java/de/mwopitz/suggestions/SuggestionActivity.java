package de.mwopitz.suggestions;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import java.util.Random;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class SuggestionActivity extends AppCompatActivity {

    private static final String TAG = "SuggestionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.suggestion_activity);
        setSupportActionBar(findViewById(R.id.toolbar));

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        String categoryName = getIntent().getStringExtra(MainActivity.EXTRA_CATEGORY_NAME);

        AppDataViewModel model = ViewModelProviders.of(this).get(AppDataViewModel.class);
        model.getSuggestionsForCategory(categoryName).observe(this, suggestions -> {
            if (suggestions != null && suggestions.size() > 0) {
                int rand = (new Random()).nextInt(suggestions.size());
                int titleResId = suggestions.get(rand).getTitleResId();
                ((TextView) findViewById(R.id.suggestion_title)).setText(getResources().getString(titleResId));
            } else {
                Log.w(TAG, "No suggestions found for category \"" + categoryName + "\"");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_toolbar, menu);
        return true;
    }
}
