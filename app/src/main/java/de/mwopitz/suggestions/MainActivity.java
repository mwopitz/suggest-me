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

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setSupportActionBar(findViewById(R.id.toolbar));

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
                Log.e(TAG, "Could not find any categories!");
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
                Toast.makeText(this, "About clicked", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onCategorySelected(Category category) {
        Intent intent = new Intent(this, SuggestionActivity.class);
        intent.putExtra(EXTRA_CATEGORY_NAME, category.name);
        startActivity(intent);
    }

    private void onSettingsSelected() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
