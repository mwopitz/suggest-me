package de.mwopitz.suggestions;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import de.mwopitz.suggestions.appdata.AppDataRepository;
import de.mwopitz.suggestions.appdata.Category;
import de.mwopitz.suggestions.appdata.Suggestion;

public class AppDataViewModel extends AndroidViewModel {

    private AppDataRepository mRepository;

    public AppDataViewModel(Application application) {
        super(application);
        mRepository = new AppDataRepository(application);
    }

    void setup() {
        mRepository.setupDatabase();
    }

    LiveData<List<Category>> getAllCategories() {
        return mRepository.getAllCategories();
    }

    LiveData<List<Suggestion>> getAllSuggestions() {
        return mRepository.getAllSuggestions();
    }

    LiveData<List<Suggestion>> getSuggestionsForCategory(Category category) {
        return mRepository.getSuggestionsForCategory(category);
    }

    LiveData<List<Suggestion>> getSuggestionsForCategory(String categoryName) {
        return mRepository.getSuggestionsForCategory(categoryName);
    }

    void insertAll(Category... categories) {
        mRepository.insertAll(categories);
    }

    void insertAll(Suggestion... suggestions) {
        mRepository.insertAll(suggestions);
    }
}
