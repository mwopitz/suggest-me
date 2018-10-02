package de.mwopitz.suggestions.data;

import de.mwopitz.suggestions.R;

/**
 * Yeah, this data is mostly used for testing purposes. I should probably move it
 */
final class InitialData {

    private static final Category[] CATEGORIES = {
            new Category("creativity",
                    R.drawable.bm_category_creativity,
                    R.string.category_creativity_title,
                    R.string.category_creativity_description),
            new Category("logic_and_learning",
                    R.drawable.bm_category_logic_and_learning,
                    R.string.category_logic_and_learning_title,
                    R.string.category_logic_and_learning_description),
            new Category("nature_and_outdoor",
                    R.drawable.bm_category_nature_and_outdoor_activities,
                    R.string.category_nature_and_outdoor_activities_title,
                    R.string.category_nature_and_outdoor_activities_description),
            new Category("sport_and_exercise",
                    R.drawable.bm_category_sport_and_exercise,
                    R.string.category_sport_and_exercise_title,
                    R.string.category_sport_and_exercise_description),
            new Category("reading_and_writing",
                    R.drawable.bm_category_reading_and_writing,
                    R.string.category_reading_and_writing_title,
                    R.string.category_reading_and_writing_description)
    };

    private static final Suggestion[] SUGGESTIONS = {
            new Suggestion("paint_a_stone",
                    "creativity",
                    Suggestion.Difficulty.EASY,
                    R.string.suggestion_category_1_easy_1_title),
            new Suggestion("abstract_self_portrait",
                    "creativity",
                    Suggestion.Difficulty.MEDIUM,
                    R.string.suggestion_category_1_medium_1_title),
            new Suggestion("drinking_straw_sculpture",
                    "creativity",
                    Suggestion.Difficulty.HARD,
                    R.string.suggestion_category_1_hard_1_title),
            new Suggestion("learn_foreign_word",
                    "logic_and_learning",
                    Suggestion.Difficulty.EASY,
                    R.string.suggestion_category_2_easy_1_title),
            new Suggestion("learn_synesthesia",
                    "logic_and_learning",
                    Suggestion.Difficulty.MEDIUM,
                    R.string.suggestion_category_2_medium_1_title),
            new Suggestion("play_chess",
                    "logic_and_learning",
                    Suggestion.Difficulty.HARD,
                    R.string.suggestion_category_2_hard_1_title),
            new Suggestion("hug_a_tree",
                    "nature_and_outdoor",
                    Suggestion.Difficulty.EASY,
                    R.string.suggestion_category_3_easy_1_title),
            new Suggestion("short_walk",
                    "nature_and_outdoor",
                    Suggestion.Difficulty.MEDIUM,
                    R.string.suggestion_category_3_medium_1_title),
            new Suggestion("long_walk",
                    "nature_and_outdoor",
                    Suggestion.Difficulty.HARD,
                    R.string.suggestion_category_3_hard_1_title),
            new Suggestion("yoga_mountain_pose",
                    "sport_and_exercise",
                    Suggestion.Difficulty.EASY,
                    R.string.suggestion_category_4_easy_1_title),
            new Suggestion("five_sit_ups",
                    "sport_and_exercise",
                    Suggestion.Difficulty.MEDIUM,
                    R.string.suggestion_category_4_medium_1_title),
            new Suggestion("jogging",
                    "sport_and_exercise",
                    Suggestion.Difficulty.HARD,
                    R.string.suggestion_category_4_hard_1_title),
            new Suggestion("letter_to_yourself",
                    "reading_and_writing",
                    Suggestion.Difficulty.EASY,
                    R.string.suggestion_category_5_easy_1_title),
            new Suggestion("book_backwards",
                    "reading_and_writing",
                    Suggestion.Difficulty.MEDIUM,
                    R.string.suggestion_category_5_medium_1_title),
            new Suggestion("biography",
                    "reading_and_writing",
                    Suggestion.Difficulty.HARD,
                    R.string.suggestion_category_5_hard_1_title)
    };

    static Category[] getCategories() {
        return CATEGORIES;
    }

    static Suggestion[] getSuggestions() {
        return SUGGESTIONS;
    }
}
