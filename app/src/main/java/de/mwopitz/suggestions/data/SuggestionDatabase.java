package de.mwopitz.suggestions.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * This is the main database interface. The RoomDatabase is almost definitely backed by SQLite.
 */
@Database(entities = {Category.class, Suggestion.class}, version = 1)
@TypeConverters({Converters.class})
abstract class SuggestionDatabase extends RoomDatabase {

    private static SuggestionDatabase sInstance; // Singleton

    private static final RoomDatabase.Callback sPopulateDatabase = new RoomDatabase.Callback() {
        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateAsync(sInstance).execute();
        }
    };

    static SuggestionDatabase getDatabase(@NonNull Context context) {
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
