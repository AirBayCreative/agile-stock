package hk.reality.stock.service.fetcher;

import hk.reality.stock.model.Index;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import android.util.Log;

public class Money18IndexesFetcherTest extends TestCase {
    public static final String TAG = "Money18IndexesFetcherTest";
    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testFetch() {
        IndexesFetcher fetcher = getFetcher();
        List<Index> indexes = fetcher.fetch();
        Assert.assertNotNull(indexes);
        Assert.assertTrue(indexes.size() > 0);
        Assert.assertNotNull(indexes.get(0));

        Log.d(TAG, "index: " + indexes.get(0));
    }
    
    protected IndexesFetcher getFetcher() {
        return new Money18IndexesFetcher();
    }
    
}
