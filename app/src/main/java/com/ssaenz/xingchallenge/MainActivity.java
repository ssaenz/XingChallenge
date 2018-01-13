package com.ssaenz.xingchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private GitHubRepoPresenter gitHubRepoPresenter;
    private GitHubRepoAdapter mGitHubRepoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO instantiate the presenter and the adapter...
        loadLayoutViews();
    }

    private void loadLayoutViews() {
        mRecyclerView = findViewById(R.id.rv_list);
    }
}
