package com.ssaenz.xingchallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private GitHubRepoPresenter mGitHubRepoPresenter = new GitHubRepoPresenter();
    private GitHubRepoAdapter mGitHubRepoAdapter = new GitHubRepoAdapter(mGitHubRepoPresenter);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO add data to the adapter.
        loadLayoutViews();
    }


    private void loadLayoutViews() {
        mRecyclerView = findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.setAdapter(mGitHubRepoAdapter);
    }

}
