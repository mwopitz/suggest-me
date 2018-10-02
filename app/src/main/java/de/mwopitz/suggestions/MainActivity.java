package de.mwopitz.suggestions;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.mwopitz.suggestions.data.Category;

/**
 * The entry point for this app.
 * <p>
 * The main activity displays a list of category cards. When one of these cards is touched,
 * this activity starts the secondary {@link SuggestionActivity}, which in turn shows a
 * random suggestion (for the selected category).
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Identifier for passing the selected category ID to the {@link SuggestionActivity}.
     */
    public static final String EXTRA_CATEGORY_ID = "de.mwopitz.suggestions.CATEGORY_ID";

    private static final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setSupportActionBar(findViewById(R.id.toolbar));

        // Saving the default values for the user preferences. This seems to be required, somehow.
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);

        final RecyclerView categoryList = findViewById(R.id.category_list);
        categoryList.setLayoutManager(new GridLayoutManager(this, 2));

        final CategoryListAdapter adapter = new CategoryListAdapter();
        adapter.setOnCategoryClickListener(this::onCategorySelected);
        categoryList.setAdapter(adapter);

        final AppDataViewModel vm = ViewModelProviders.of(this).get(AppDataViewModel.class);
        vm.getAllCategories().observe(this, categories -> {
            if (categories != null) {
                adapter.setCategories(categories);
            } else {
                Log.e(LOG_TAG, "Could not find any categories!");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                onSettingsSelected();
                return true;
            case R.id.action_about:
                Toast.makeText(this, "About clicked", Toast.LENGTH_LONG).show(); // TODO: Maybe implement this sometime...
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onCategorySelected(Category category) {
        Intent intent = new Intent(this, SuggestionActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, category.id);
        startActivity(intent);
    }

    private void onSettingsSelected() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
