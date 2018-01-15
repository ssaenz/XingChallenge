package com.ssaenz.xingchallenge;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ssaenz.xingchallenge.domain.GitHubRepository;
import com.ssaenz.xingchallenge.data.storage.SharedPreferencesGitHubStore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.ssaenz.xingchallenge.GitHubReposMother.createMockData;
import static com.ssaenz.xingchallenge.data.storage.SharedPreferencesGitHubStore.LAST_PAGE_KEY;
import static com.ssaenz.xingchallenge.data.storage.SharedPreferencesGitHubStore.PREFS_NAME;
import static com.ssaenz.xingchallenge.data.storage.SharedPreferencesGitHubStore.TIMESTAMP_KEY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 */

@RunWith(AndroidJUnit4.class)
public class SharedPreferencesGitHubStoreTest {

    private static final int REPOS_SIZE = 10;
    private static final int INITAL_PAGE = 1;

    private SharedPreferencesGitHubStore store;
    private SharedPreferences sharedPreferences;
    List<GitHubRepository> mockRepos;

    @Before
    public void setUp () {
        Context appContext = InstrumentationRegistry.getTargetContext();
        store = SharedPreferencesGitHubStore.getInstance(appContext);
        mockRepos = createMockData(REPOS_SIZE);
        store.saveReposPage(mockRepos, INITAL_PAGE, REPOS_SIZE);
        sharedPreferences = appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Test
    public void saveReposTest () {
        assertTrue(sharedPreferences.contains(TIMESTAMP_KEY));
        assertTrue(sharedPreferences.contains(LAST_PAGE_KEY));
        for (int i = 0; i < REPOS_SIZE; i++) {
            assertTrue(sharedPreferences.contains(store.repoKey(INITAL_PAGE, REPOS_SIZE, i)));
        }
    }

    @Test
    public void listReposTest () {
        List<GitHubRepository> repos = store.listRepos(INITAL_PAGE, REPOS_SIZE);
        for (int i = 0; i < repos.size(); i++) {
            assertEquals(mockRepos.get(i).getName(), repos.get(i).getName());
        }
    }

}
