package de.mwopitz.suggestions.appdata;

import android.app.Application;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.io.InputStreamReader;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import de.mwopitz.suggestions.R;

/**
 * See: <a href="https://codelabs.developers.google.com/codelabs/android-room-with-a-view">Google Developers Codelabs: Android Room with a View</a>
 */
public class AppDataRepository {

    private static final String LOG_TAG = AppDataRepository.class.getSimpleName();
    private final Application mApplication;
    private final CategoryDao mCategoryDao;
    private final SuggestionDao mSuggestionDao;

    public AppDataRepository(Application application) {
        mApplication = application;
        mCategoryDao = SuggestionDatabase.getDatabase(application).categoryDao();
        mSuggestionDao = SuggestionDatabase.getDatabase(application).suggestionDao();
    }

    public void setupDatabase() {
        new DatabaseSetup(mApplication).execute();
    }

    public LiveData<List<Category>> getAllCategories() {
        return mCategoryDao.getAllCategories();
    }

    public LiveData<List<Suggestion>> getAllSuggestions() {
        return mSuggestionDao.getAllSuggestions();
    }

    public LiveData<List<Suggestion>> getSuggestionsForCategory(Category category) {
        return mSuggestionDao.getSuggestionsForCategory(category.id);
    }

    public LiveData<List<Suggestion>> getSuggestionsForCategory(String categoryName) {
        LiveData<Category> categoryLiveData = mCategoryDao.findCategoryWithName(categoryName);
        return Transformations.switchMap(categoryLiveData,
                category -> mSuggestionDao.getSuggestionsForCategory(category.id));
    }

    public void insertAll(Category... categories) {
        new InsertCategories(mCategoryDao).execute(categories);
    }

    public void insertAll(Suggestion... suggestions) {
        new InsertSuggestions(mSuggestionDao).execute(suggestions);
    }

    private static class DatabaseSetup extends AsyncTask<Void, Void, Void> {

        private final Application mApplication;

        DatabaseSetup(Application application) {
            mApplication = application;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            Log.d(LOG_TAG, "Setting up the suggestion database...");

            final Resources resources = mApplication.getResources();
            final InputStreamReader categoriesJsonFile = new InputStreamReader(resources.openRawResource(R.raw.categories));
            final InputStreamReader suggestionsJsonFile = new InputStreamReader(resources.openRawResource(R.raw.suggestions));

            final GsonBuilder gsonBuilder = new GsonBuilder();

            final JsonDeserializer<Category> categoryDeserializer = new CategoryJsonDeserializer(resources);
            final JsonDeserializer<Suggestion> suggestionDeserializer = new SuggestionJsonDeserializer(resources);
            gsonBuilder.registerTypeAdapter(Category.class, categoryDeserializer);
            gsonBuilder.registerTypeAdapter(Suggestion.class, suggestionDeserializer);

            final Gson gson = gsonBuilder.create();

            final Category[] categories = gson.fromJson(categoriesJsonFile, Category[].class);
            final Suggestion[] suggestions = gson.fromJson(suggestionsJsonFile, Suggestion[].class);

            final SuggestionDatabase db = SuggestionDatabase.getDatabase(mApplication);
            final CategoryDao categoryDao = db.categoryDao();
            final SuggestionDao suggestionDao = db.suggestionDao();

            suggestionDao.deleteAll();
            categoryDao.deleteAll();

            categoryDao.insertAll(categories);
            suggestionDao.insertAll(suggestions);

            return null;
        }
    }

    private static class InsertCategories extends AsyncTask<Category, Void, Void> {
        private final CategoryDao mCategoryDao;

        InsertCategories(CategoryDao dao) {
            this.mCategoryDao = dao;
        }

        @Override
        protected Void doInBackground(final Category... params) {
            mCategoryDao.insertAll(params);
            return null;
        }
    }

    private static class InsertSuggestions extends AsyncTask<Suggestion, Void, Void> {
        private final SuggestionDao mSuggestionDao;

        InsertSuggestions(SuggestionDao dao) {
            this.mSuggestionDao = dao;
        }

        @Override
        protected Void doInBackground(final Suggestion... params) {
            mSuggestionDao.insertAll(params);
            return null;
        }
    }
}
