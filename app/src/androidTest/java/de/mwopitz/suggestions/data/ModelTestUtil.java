package de.mwopitz.suggestions.data;

/**
 * A helper class for the {@link DatabaseTest}.
 */
final class ModelTestUtil {

    static Category createOneCategory() {
        return new Category("test_category", 0, 0, 0);
    }

    static Category[] createMultipleCategories() {
        return new Category[] {
                new Category("test_category_1", 0, 0, 0),
                new Category("test_category_2", 0, 0, 0),
                new Category("test_category_3", 0, 0, 0)
        };
    }

    static Suggestion createOneSuggestion() {
        return new Suggestion("test_suggestion", "test_category",
                Suggestion.Difficulty.EASY, 0);
    }

    static Suggestion[] createMultipleSuggestions() {
        return new Suggestion[] {
                new Suggestion("test_suggestion_1", "test_category_1",
                        Suggestion.Difficulty.EASY, 0),
                new Suggestion("test_suggestion_2", "test_category_2",
                        Suggestion.Difficulty.MEDIUM, 0),
                new Suggestion("test_suggestion_3", "test_category_3",
                        Suggestion.Difficulty.HARD, 0)
        };
    }
}
