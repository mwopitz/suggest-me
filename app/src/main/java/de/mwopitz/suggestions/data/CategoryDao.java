package de.mwopitz.suggestions.data;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insert(Category category);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insertAll(Category... categories);

    @Query("DELETE FROM categories")
    void deleteAll();

    @Query("SELECT * FROM categories WHERE id LIKE :id")
    LiveData<Category> getCategory(@NonNull String id);

    @Query("SELECT * FROM categories ORDER BY id ASC")
    LiveData<List<Category>> getAllCategories();
}
