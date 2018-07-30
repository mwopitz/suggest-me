package de.mwopitz.suggestions.appdata;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Category.class, Suggestion.class}, version = 1)
abstract class SuggestionDatabase extends RoomDatabase {

    private static SuggestionDatabase sInstance; // Singleton

    private static final RoomDatabase.Callback sPopulateDatabase = new RoomDatabase.Callback() {
        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateAsync(sInstance).execute();
        }
    };

    static SuggestionDatabase getDatabase(@NonNull final Context context) {
        if (sInstance == null) {
            synchronized (SuggestionDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            SuggestionDatabase.class, "suggestion_database")
                            .addCallback(sPopulateDatabase)
                            .build();
                }
            }
        }
        return sInstance;
    }

    private static class PopulateAsync extends AsyncTask<Void, Void, Void> {

        private final CategoryDao mCategoryDao;
        private final SuggestionDao mSuggestionDao;

        PopulateAsync(SuggestionDatabase db) {
            mCategoryDao = db.categoryDao();
            mSuggestionDao = db.suggestionDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mCategoryDao.insertAll(InitialData.getCategories());
            mSuggestionDao.insertAll(InitialData.getSuggestions());

            return null;
        }
    }

    abstract CategoryDao categoryDao();

    abstract SuggestionDao suggestionDao();
}
