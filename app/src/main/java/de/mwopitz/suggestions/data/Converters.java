package de.mwopitz.suggestions.data;

import androidx.room.TypeConverter;

public class Converters {

    // Not sure if I can use @NonNull anywhere here...
    // Probably not.

    @TypeConverter
    public static Suggestion.Difficulty toDifficulty(Integer code) {
        return code == null ? null : Suggestion.Difficulty.values()[code];
    }

    @TypeConverter
    public static Integer difficultyToCode(Suggestion.Difficulty difficulty) {
        return difficulty == null ? null : difficulty.getCode();
    }
}
