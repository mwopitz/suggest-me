package de.mwopitz.suggestions.data;

import java.util.List;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import de.mwopitz.suggestions.util.LiveDataTestUtil;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    private CategoryDao mCategoryDao;
    private SuggestionDao mSuggestionDao;
    private SuggestionDatabase mTestDatabase;

    @Rule
    public final TestRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void createTestDatabase() {
        Context context = InstrumentationRegistry.getTargetContext();
        mTestDatabase = Room.inMemoryDatabaseBuilder(context, SuggestionDatabase.class).build();
        mCategoryDao = mTestDatabase.categoryDao();
        mSuggestionDao = mTestDatabase.suggestionDao();
    }

    @After
    public void closeTestDatabase() {
        mTestDatabase.close();
    }

    @Test
    public void testInsertAndReadOneCategory() throws InterruptedException {
        Category insertedCategory = ModelTestUtil.createOneCategory();
        mCategoryDao.insert(insertedCategory);

        LiveData<Category> liveData = mCategoryDao.getCategory(insertedCategory.id);
        Category retrievedCategory = LiveDataTestUtil.getValue(liveData);

        assertThat(retrievedCategory, equalTo(insertedCategory));
    }

    @Test
    public void testInsertAndReadMultipleCategories() throws InterruptedException {
        Category[] insertedCategories = ModelTestUtil.createMultipleCategories();
        mCategoryDao.insertAll(insertedCategories);

        LiveData<List<Category>> liveData = mCategoryDao.getAllCategories();
        List<Category> retrievedCategoryList = LiveDataTestUtil.getValue(liveData);
        Category[] retrievedCategories = new Category[retrievedCategoryList.size()];
        retrievedCategories = retrievedCategoryList.toArray(retrievedCategories);

        assertThat(retrievedCategories, equalTo(insertedCategories));
    }

    @Test
    public void testInsertAndReadOneSuggestion() throws InterruptedException {
        Category insertedParentCategory = ModelTestUtil.createOneCategory();
        Suggestion insertedSuggestion = ModelTestUtil.createOneSuggestion();

        mCategoryDao.insert(insertedParentCategory);
        mSuggestionDao.insert(insertedSuggestion);
        LiveData<Suggestion> liveData = mSuggestionDao.getSuggestion(insertedSuggestion.id);
        Suggestion retrievedSuggestion = LiveDataTestUtil.getValue(liveData);

        assertThat(retrievedSuggestion, equalTo(insertedSuggestion));
    }

    @Test
    public void testInsertAndReadMultipleSuggestions() throws InterruptedException {
        Category[] insertedParentCategories = ModelTestUtil.createMultipleCategories();
        Suggestion[] insertedSuggestions = ModelTestUtil.createMultipleSuggestions();

        mCategoryDao.insertAll(insertedParentCategories);
        mSuggestionDao.insertAll(insertedSuggestions);
        LiveData<List<Suggestion>> liveData  = mSuggestionDao.getAllSuggestions();
        List<Suggestion> retrievedSuggestionList = LiveDataTestUtil.getValue(liveData);
        Suggestion[] retrievedSuggestions = new Suggestion[retrievedSuggestionList.size()];
        retrievedSuggestions = retrievedSuggestionList.toArray(retrievedSuggestions);

        assertThat(retrievedSuggestions, equalTo(insertedSuggestions));
    }
}
