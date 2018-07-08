package de.mwopitz.suggestions;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.mwopitz.suggestions.appdata.Category;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY_NAME = "de.mwopitz.suggestions.CATEGORY_NAME";

    private static final String FIRST_TIME_SETUP_KEY = "first-time setup required";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setSupportActionBar(findViewById(R.id.toolbar));

        final RecyclerView categoryList = findViewById(R.id.categories);
        categoryList.setLayoutManager(new GridLayoutManager(this, 2));

        final CategoryListAdapter adapter = new CategoryListAdapter();
        adapter.setOnCategoryClickListener(this::onCategorySelected);
        categoryList.setAdapter(adapter);

        final AppDataViewModel vm = ViewModelProviders.of(this).get(AppDataViewModel.class);
        vm.getAllCategories().observe(this, categories -> {
            if (categories != null) {
                adapter.setCategories(categories);
            } else {
                Log.e(TAG, "Could not find any categories!");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        final boolean setup = getPreferences(MODE_PRIVATE).getBoolean(FIRST_TIME_SETUP_KEY, true);
        if (setup) {
            onFirstTimeSetup();
            getPreferences(MODE_PRIVATE).edit().putBoolean(FIRST_TIME_SETUP_KEY, false).apply();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                Toast.makeText(this, "Filter clicked", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onFirstTimeSetup() {
        Log.i(TAG, "Performing first-time setup");
        ViewModelProviders.of(this).get(AppDataViewModel.class).setup();
    }

    private void onCategorySelected(Category category) {
        Intent intent = new Intent(this, SuggestionActivity.class);
        intent.putExtra(EXTRA_CATEGORY_NAME, category.name);
        startActivity(intent);
    }
}
