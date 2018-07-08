package de.mwopitz.suggestions.appdata;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Category.class, Suggestion.class}, version = 1)
abstract class SuggestionDatabase extends RoomDatabase {

    private static SuggestionDatabase INSTANCE;

    static SuggestionDatabase getDatabase(@NonNull final Context context) {
        if (INSTANCE == null) {
            synchronized (SuggestionDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SuggestionDatabase.class,
                            "suggestion_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    abstract CategoryDao categoryDao();

    abstract SuggestionDao suggestionDao();
}
