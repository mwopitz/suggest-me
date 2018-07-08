package de.mwopitz.suggestions.appdata;

import android.content.res.Resources;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

class SuggestionJsonDeserializer implements JsonDeserializer<Suggestion> {

    private final Resources mResources;

    SuggestionJsonDeserializer(@NonNull Resources resources) {
        mResources = resources;
    }

    @Override
    public Suggestion deserialize(JsonElement json, Type typeofT, JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        final int categoryId = jsonObject.get("category").getAsInt();
        final int difficulty = jsonObject.get("difficulty").getAsInt();
        @StringRes final int titleResId = mResources.getIdentifier(
                jsonObject.get("title").getAsString(), "string", "de.mwopitz.suggestions");

        if (titleResId == 0) {
            throw new Error("Found suggestion entry for category " + categoryId + " with title resource id 0x0");
        }

        return new Suggestion(categoryId, difficulty, titleResId);
    }
}
