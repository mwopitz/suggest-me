package de.mwopitz.suggestions.appdata;

import de.mwopitz.suggestions.R;

final class InitialData {
    private static final Category[] CATEGORIES = {
            new Category(1, "creativity",
                    R.drawable.bm_category_creativity,
                    R.string.category_creativity_title,
                    R.string.category_creativity_description),
            new Category(2, "logic_and_learning",
                    R.drawable.bm_category_logic_and_learning,
                    R.string.category_logic_and_learning_title,
                    R.string.category_logic_and_learning_description),
            new Category(3, "nature_and_outdoor_activities",
                    R.drawable.bm_category_nature_and_outdoor_activities,
                    R.string.category_nature_and_outdoor_activities_title,
                    R.string.category_nature_and_outdoor_activities_description),
            new Category(4, "sport_and_exercise",
                    R.drawable.bm_category_sport_and_exercise,
                    R.string.category_sport_and_exercise_title,
                    R.string.category_sport_and_exercise_description),
            new Category(5, "reading_and_writing",
                    R.drawable.bm_category_reading_and_writing,
                    R.string.category_reading_and_writing_title,
                    R.string.category_reading_and_writing_description)
    };
    private static final Suggestion[] SUGGESTIONS = {
            new Suggestion(1, 0, R.string.suggestion_category_1_easy_1_title),
            new Suggestion(1, 1, R.string.suggestion_category_1_medium_1_title),
            new Suggestion(1, 2, R.string.suggestion_category_1_hard_1_title),
            new Suggestion(2, 0, R.string.suggestion_category_2_easy_1_title),
            new Suggestion(2, 1, R.string.suggestion_category_2_medium_1_title),
            new Suggestion(2, 2, R.string.suggestion_category_2_hard_1_title),
            new Suggestion(3, 0, R.string.suggestion_category_3_easy_1_title),
            new Suggestion(3, 1, R.string.suggestion_category_3_medium_1_title),
            new Suggestion(3, 2, R.string.suggestion_category_3_hard_1_title),
            new Suggestion(4, 0, R.string.suggestion_category_4_easy_1_title),
            new Suggestion(4, 1, R.string.suggestion_category_4_medium_1_title),
            new Suggestion(4, 2, R.string.suggestion_category_4_hard_1_title),
            new Suggestion(5, 0, R.string.suggestion_category_5_easy_1_title),
            new Suggestion(5, 1, R.string.suggestion_category_5_medium_1_title),
            new Suggestion(5, 2, R.string.suggestion_category_5_hard_1_title)
    };

    static Category[] getCategories() {
        return CATEGORIES;
    }

    static Suggestion[] getSuggestions() {
        return SUGGESTIONS;
    }
}
