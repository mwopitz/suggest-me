package de.mwopitz.suggestions.appdata;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

/**
 * See: <a href="https://codelabs.developers.google.com/codelabs/android-room-with-a-view">Google Developers Codelabs: Android Room with a View</a>
 */
public class AppDataRepository {

    private static final String TAG = AppDataRepository.class.getSimpleName();
    private final CategoryDao mCategoryDao;
    private final SuggestionDao mSuggestionDao;

    public AppDataRepository(Application application) {
        final SuggestionDatabase db = SuggestionDatabase.getDatabase(application);
        mCategoryDao = db.categoryDao();
        mSuggestionDao = db.suggestionDao();
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
