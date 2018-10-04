package de.mwopitz.suggestions.data;

import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The model class for all suggestion categories.
 * <p>
 * This is used for categories like "creativity", "logic and learning", "reading and writing", etc.
 * Each category has an image reference and a title reference. These are used to display category
 * cards on the category selection screen.
 */
@Entity(tableName = "categories")
public class Category {

    /**
     * The primary key.
     * I wanted at least one human-readable string for each database entry. The other fields are
     * just obscure numbers.
     */
    @NonNull
    @PrimaryKey
    public final String id;

    /**
     * The ID for the translatable title string resource.
     */
    @StringRes
    @ColumnInfo(name = "title_res_id")
    public final int titleResId;

    /**
     * The ID for the translatable description string resource.
     */
    @StringRes
    @ColumnInfo(name = "description_res_id")
    public final int descriptionResId;

    /**
     * The ID for the drawable (i.e. image) resource file.
     */
    @DrawableRes
    @ColumnInfo(name = "drawable_res_id")
    public final int drawableResId;

    Category(@NonNull String id, @DrawableRes int drawableResId,
             @StringRes int titleResId, @StringRes int descriptionResId) {
        this.id = id;
        this.drawableResId = drawableResId;
        this.titleResId = titleResId;
        this.descriptionResId = descriptionResId;
    }

    @BindingAdapter("android:src")
    public static void setImageResource(@NonNull ImageView imageView, @DrawableRes int resId) {
        imageView.setImageResource(resId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Category)) return false;

        return id.equals(((Category) obj).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
