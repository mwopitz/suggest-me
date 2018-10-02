package de.mwopitz.suggestions;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import de.mwopitz.suggestions.data.AppDataRepository;
import de.mwopitz.suggestions.data.Category;
import de.mwopitz.suggestions.data.Suggestion;

public class AppDataViewModel extends AndroidViewModel {

    private AppDataRepository mRepository;

    public AppDataViewModel(Application application) {
        super(application);
        mRepository = new AppDataRepository(application);
    }

    Suggestion getSuggestionPlaceholder() {
        return mRepository.getSuggestionPlaceholder();
    }

    LiveData<List<Category>> getAllCategories() {
        return mRepository.getAllCategories();
    }

    LiveData<List<Suggestion>> getAllSuggestions() {
        return mRepository.getAllSuggestions();
    }

    LiveData<List<Suggestion>> getSuggestionsForCategory(@NonNull Category category) {
        return mRepository.getSuggestionsForCategory(category);
    }

    LiveData<List<Suggestion>> getSuggestionsForCategory(@NonNull String categoryId) {
        return mRepository.getSuggestionsForCategory(categoryId);
    }

    LiveData<List<Suggestion>> getSuggestionsForUserPrefs(@NonNull String categoryId,
                                                          @NonNull List<Suggestion.Difficulty> difficulties) {
        return mRepository.getSuggestionsForUserPrefs(categoryId, difficulties);
    }

    void insertAll(@NonNull Category... categories) {
        mRepository.insertAll(categories);
    }

    void insertAll(@NonNull Suggestion... suggestions) {
        mRepository.insertAll(suggestions);
    }
}
