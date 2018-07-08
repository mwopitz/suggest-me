package de.mwopitz.suggestions.appdata;

import android.content.res.Resources;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

class CategoryJsonDeserializer implements JsonDeserializer<Category> {

    private final Resources mResources;

    CategoryJsonDeserializer(@NonNull Resources resources) {
        mResources = resources;
    }

    @Override
    public Category deserialize(JsonElement json, Type typeofT, JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        final int id = jsonObject.get("id").getAsInt();
        final String name = jsonObject.get("name").getAsString();
        @StringRes final int titleResId = mResources.getIdentifier(
                jsonObject.get("title").getAsString(), "string", "de.mwopitz.suggestions");
        @StringRes final int descriptionResId = mResources.getIdentifier(
                jsonObject.get("description").getAsString(), "string", "de.mwopitz.suggestions");
        @DrawableRes final int drawableResId = mResources.getIdentifier(
                jsonObject.get("drawable").getAsString(), "drawable", "de.mwopitz.suggestions");

        if (titleResId == 0) {
            throw new Error("Found category entry " + name + " with title resource id 0x0");
        }
        if (descriptionResId == 0) {
            throw new Error("Found category entry " + name + " with description resource id 0x0");
        }
        if (drawableResId == 0) {
            throw new Error("Found category entry " + name + " with drawable resource id 0x0");
        }

        return new Category(id, name, drawableResId, titleResId, descriptionResId);
    }
}
