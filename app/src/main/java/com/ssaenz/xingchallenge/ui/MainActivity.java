package com.ssaenz.xingchallenge.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ssaenz.xingchallenge.R;
import com.ssaenz.xingchallenge.data.EndpointFactory;
import com.ssaenz.xingchallenge.data.GitHubService;
import com.ssaenz.xingchallenge.ui.adapter.GitHubRepoAdapter;
import com.ssaenz.xingchallenge.ui.presenter.GitHubRepoPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String GITHUB_URL = "https://api.github.com";
    private static final String XING_LOGIN = "xing";
    private static final int FIRST_PAGE = 1;
    private static final int PAGE_SIZE = 10;

    private RecyclerView mRecyclerView;
    private GitHubRepoPresenter mGitHubRepoPresenter = new GitHubRepoPresenter();
    private GitHubRepoAdapter mGitHubRepoAdapter = new GitHubRepoAdapter(mGitHubRepoPresenter);
    private EndpointFactory mEndpointFactory = new EndpointFactory();
    private GitHubService mGitHubService = (GitHubService) mEndpointFactory.createEndpoint(GitHubService.class, GITHUB_URL);
    CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadGitHubRepos();
        loadLayoutViews();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mDisposable!=null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    private void loadGitHubRepos() {
        Disposable subscribe = mGitHubService.listRepos(XING_LOGIN, FIRST_PAGE, PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(repos -> mGitHubRepoAdapter.addRepositories(repos));
        mDisposable.add(subscribe);
    }

    private void loadLayoutViews() {
        mRecyclerView = findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.setAdapter(mGitHubRepoAdapter);
    }

}
