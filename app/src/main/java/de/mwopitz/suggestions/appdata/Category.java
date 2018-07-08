package de.mwopitz.suggestions.appdata;

import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category {

    @PrimaryKey
    public final int id;

    @NonNull
    public final String name;

    @DrawableRes
    @ColumnInfo(name = "drawable_res_id")
    public final int drawableResId;

    @StringRes
    @ColumnInfo(name = "title_res_id")
    public final int titleResId;

    @StringRes
    @ColumnInfo(name = "description_res_id")
    public final int descriptionResId;

    Category(int id, @NonNull String name, @DrawableRes int drawableResId,
             @StringRes int titleResId, @StringRes int descriptionResId) {
        this.id = id;
        this.name = name;
        this.drawableResId = drawableResId;
        this.titleResId = titleResId;
        this.descriptionResId = descriptionResId;
    }

    @BindingAdapter("android:src")
    public static void setImageResource(@NonNull ImageView imageView, @DrawableRes int resId) {
        imageView.setImageResource(resId);
    }
}
