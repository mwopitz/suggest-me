package de.mwopitz.suggestions.data;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
interface SuggestionDao {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insert(Suggestion suggestion);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insertAll(Suggestion... suggestions);

    @Query("DELETE FROM suggestions")
    void deleteAll();

    @Query("SELECT * FROM suggestions WHERE id = :id")
    LiveData<Suggestion> getSuggestion(@NonNull String id);

    @Query("SELECT * FROM suggestions ORDER BY id ASC, difficulty ASC")
    LiveData<List<Suggestion>> getAllSuggestions();

    @Query("SELECT * FROM suggestions WHERE category_id = :categoryId")
    LiveData<List<Suggestion>> getSuggestionsForCategory(@NonNull String categoryId);

    @Query("SELECT * FROM suggestions " +
            "WHERE category_id = :categoryId AND difficulty IN(:difficulties)")
    LiveData<List<Suggestion>> getSuggestionsForUserPrefs(@NonNull String categoryId,
                                                          @NonNull List<Suggestion.Difficulty> difficulties);
}
