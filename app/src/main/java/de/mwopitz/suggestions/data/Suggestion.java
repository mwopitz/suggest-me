package de.mwopitz.suggestions.data;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * The suggestion model class. It's a bit bare-bones, currently.
 * <p>
 * TODO: Add these fields: description, gallery image IDs, maybe some free-form text w/ web links
 */
@Entity(tableName = "suggestions",
        foreignKeys = @ForeignKey(entity = Category.class,
                parentColumns = "id",
                childColumns = "category_id"),
        indices = @Index("category_id"))
public class Suggestion {

    /**
     * The primary key.
     * Using strings here seems like a good idea, because it'll make debugging slightly less
     * painful. Also, there's only a limited, predefined set of suggestions.
     */
    @NonNull
    @PrimaryKey
    public final String id;

    /**
     * The ID of the parent category.
     */
    @NonNull
    @ColumnInfo(name = "category_id")
    public final String categoryId;

    /**
     * The difficulty. Seems pretty straightforward
     */
    @ColumnInfo(name = "difficulty")
    public final Difficulty difficulty;

    /**
     * Resource ID of the translatable title string.
     */
    @StringRes
    @ColumnInfo(name = "title_res_id")
    public final int titleResId;

    Suggestion(@NonNull String id, @NonNull String categoryId, @NonNull Difficulty difficulty,
               @StringRes int titleResId) {
        this.id = id;
        this.categoryId = categoryId;
        this.difficulty = difficulty;
        this.titleResId = titleResId;
    }

    public enum Difficulty {
        EASY(0), MEDIUM(1), HARD(2);

        private final int code;

        Difficulty(int code) {
            this.code = code;
        }

        public int getCode() {
            return code; // required for the interaction with @TypeConverter
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Suggestion)) return false;

        return id.equals(((Suggestion) obj).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
