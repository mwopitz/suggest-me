package de.mwopitz.suggestions.appdata;

import androidx.annotation.StringRes;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "suggestions",
        foreignKeys = @ForeignKey(entity = Category.class,
                parentColumns = "id",
                childColumns = "category_id"),
        indices = @Index("category_id"))
public class Suggestion {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId = 0;

    @ColumnInfo(name = "category_id")
    private int mCategoryId;

    @ColumnInfo(name = "difficulty")
    private int mDifficulty;

    @StringRes
    @ColumnInfo(name = "title_res_id")
    private int mTitleResId;

    Suggestion(int categoryId, int difficulty, @StringRes int titleResId) {
        this.mCategoryId = categoryId;
        this.mDifficulty = difficulty;
        this.mTitleResId = titleResId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public int getCategoryId() {
        return mCategoryId;
    }

    public int getDifficulty() {
        return mDifficulty;
    }

    @StringRes
    public int getTitleResId() {
        return mTitleResId;
    }
}
