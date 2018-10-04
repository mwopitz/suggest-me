package de.mwopitz.suggestions.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;

/**
 * Helper class for testing {@link LiveData} objects.
 *
 * The code is adapted from
 * <a href="https://github.com/googlesamples/android-sunflower">Google's Sunflower app</a>.
 */
public class LiveDataTestUtil {

    /**
     * Extracts the data from a LiveData wrapper.
     * Times out if the data doesn't arrive after 2 seconds.
     *
     * @param liveData An arbitrary LiveData instance.
     * @param <T> The type of the object wrapped by the liveData.
     * @return The object wrapped by the liveData.
     * @throws InterruptedException On timeout.
            */
    @SuppressWarnings("unchecked")
    public static <T> T getValue(LiveData<T> liveData) throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(1);
        final Object[] data = new Object[1];

        liveData.observeForever(object -> {
            data[0] = object;
            latch.countDown();
        });

        latch.await(2, TimeUnit.SECONDS);

        return (T) data[0];
    }
}
