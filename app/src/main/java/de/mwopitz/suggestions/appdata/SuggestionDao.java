package de.mwopitz.suggestions.appdata;

import java.util.List;

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

    @Query("SELECT * FROM suggestions ORDER BY id ASC, difficulty ASC")
    LiveData<List<Suggestion>> getAllSuggestions();

    @Query("SELECT * FROM suggestions WHERE category_id = :categoryId")
    LiveData<List<Suggestion>> getSuggestionsForCategory(int categoryId);
}
