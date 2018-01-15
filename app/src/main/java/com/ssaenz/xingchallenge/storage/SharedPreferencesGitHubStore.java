package com.ssaenz.xingchallenge.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.ssaenz.xingchallenge.domain.GitHubRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 */

public class SharedPreferencesGitHubStore implements GitHubStore {

    public static final String PREFS_NAME = "github_store_prefs";
    public static final String TIMESTAMP_KEY = "timestamp";
    public static final String LAST_PAGE_KEY = "last_page";

    private static SharedPreferences sharedPreferences;

    private static Gson gson = new Gson();

    private static volatile SharedPreferencesGitHubStore store;


    private SharedPreferencesGitHubStore(Context context) {

        if (sharedPreferences != null) {
            throw new RuntimeException("Private constructor. Obtain the instance from getInstance method");
        }
        sharedPreferences = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferencesGitHubStore getInstance (Context context) {

        if (store == null) {
            synchronized (SharedPreferencesGitHubStore.class) {
                if (store == null) {
                    store = new SharedPreferencesGitHubStore(context);
                }
            }
        }
        return store;
    }

    @Override
    public List<GitHubRepository> listRepos(int page, int size) {
        List<GitHubRepository> repos = new ArrayList<>();
        for (int i = 0; i < size; i ++) {
            String jsonRepo = sharedPreferences.getString(repoKey(page, size, i), null);
            if (jsonRepo == null) {
                return null;
            }
            GitHubRepository repo = gson.fromJson(jsonRepo, GitHubRepository.class);
            repos.add(repo);
        }
        return repos;
    }

    @Override
    public void saveReposPage(List<GitHubRepository> repos, int page, int size) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 0; i < size; i ++) {
            String jsonRepo = gson.toJson(repos.get(i));
            editor.putString(repoKey(page, size, i), jsonRepo);
        }
        editor.putLong(TIMESTAMP_KEY, Calendar.getInstance().getTimeInMillis());
        editor.putInt(LAST_PAGE_KEY, page);
        editor.apply();
    }

    @Override
    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

    public String repoKey (int page, int size, int index) {
        return Integer.toString((page -1) * size + index);
    }
}
